package LogicController;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.applet.*; // For audio

import sun.audio.*;

import java.io.*;

import javax.sound.sampled.*;
import javax.swing.*;

import Character.*;
import Item.*;
import Generator.ItemGenerator;
/**
 * This class is responsible for battle logic between the player and monster.
 * @author John, Kyle, Matt
 *
 */
public class BattleLogic {
	private Scanner input;
	private ArrayList<Item> itemList;
	private ArrayList<Item> playerInventory;
	private boolean battleRun;
	
	public BattleLogic() {
		input = new Scanner(System.in);
		itemList = new ItemGenerator().getItemList();
	}

	/**
	 * @method Main method for this class. Deals with the battle logic.
	 * @param player
	 * @param monster
	 */
	
	public void music(){
		AudioStream backgroundMusic;
		AudioData musicData;
		AudioPlayer musicPlayer = AudioPlayer.player;
		ContinuousAudioDataStream loop = null;
		try{
			backgroundMusic = new AudioStream(new FileInputStream("src/sound/battleMOCK.wav"));
			musicData = backgroundMusic.getData();loop = new ContinuousAudioDataStream(musicData);
			musicPlayer.start(loop);
		} 
		catch(IOException error){ 
			System.out.println(error);
		}
	}
	
	public void initiateBattle(Player player, Monster monster){
		music();
		playerInventory = player.getInventory();
		battleRun = true;
		
		System.out.println("---------------------------------------------\n");
		System.out.println("\t# " + monster.getName().toUpperCase() + " has appeared! #\n");
		System.out.println("---------------------------------------------");
		
		while (battleRun) {
			System.out.println("\tYour HP: [" + player.getHp() + "/" + player.getMaxhp() + "]");
			System.out.println("\t" + monster.getName() + "'s HP [" + monster.getHp() + "/" + monster.getMaxhp() + "]");
			System.out.println("\n\tWhat would you like to do next?");
			System.out.println("\t1. Attack");
			System.out.println("\t2. Use Item");
			System.out.println("\t3. Defend");
			System.out.println("\t4. Run");
			System.out.println("\t5. View Inventory");
			System.out.println("\t6. Type [KILL] to defeat monster");

			
			System.out.print(">>");
			String userInput = input.nextLine();
			
			try {
				if (userInput.equals("1")) {	//attack
					battle(player, monster);
					System.out.println();
				}
				
				if (userInput.equals("2")) {	//use item
					useHealingItem(player);
					System.out.println();
				} 
				
				if (userInput.equals("3")) {	//defend
					defend(player, monster);
					System.out.println();
				} 
				
				if(userInput.equals("4")) {		//flee
					tryToFlee(player, monster);
					System.out.println();
				}
				
				if(userInput.equals("5")) {		//view inventory
					player.openInventory();
				}
				
				if(userInput.equalsIgnoreCase("KILL")) {		//ADMIN 
					monster.setDead(true);
					battleRun = false;
				}
				
			}
			
			catch(InputMismatchException e) {
				System.out.println("Invalid Command");
			}
			
		}
		
		if(player.isDead())
			System.out.println("Returning to menu screen...");
		
	}
	
	/**
	 * @method Compares speed between player and monster and then initiates attack. Whoever has more speed, has the highest attack priority.
	 * @param player
	 * @param monster
	 */
	private void battle(Player player, Monster monster) {
		//if player has more speed
		if(player.getSpd() > monster.getSpd()) {
			System.out.println("---------------------------------------------");
			System.out.println("[" + player.getName() + "] goes first!");
			player.attack(monster);
			checkDead(player, monster);
			
			if(monster.isDead() == false) {
				monster.attack(player);
				checkDead(player, monster);
			}
			System.out.println("---------------------------------------------");
		}
		//if both player and monster have same speed
		else if(player.getSpd() == monster.getSpd()) {
			double chance = (Math.random()*100);
			//chance goes to player first
			if(chance > 50) {
				System.out.println("---------------------------------------------");
				System.out.println("[" + player.getName() + "] goes first!");
				player.attack(monster);
				checkDead(player, monster);
				
				if(monster.isDead() == false) {
					monster.attack(player);
					checkDead(player, monster);
				}
				System.out.println("---------------------------------------------");
			}
			//chance goes to monster first
			else {
				System.out.println("---------------------------------------------");
				System.out.println("[" + monster.getName() + "] goes first!");
				monster.attack(player);
				checkDead(player, monster);
				
				if(monster.isDead() == false) {
					player.attack(monster);
					checkDead(player, monster);
				}
				System.out.println("---------------------------------------------");
			}
		}
		//if monster has more speed
		else{
			System.out.println("---------------------------------------------");
			System.out.println("[" + monster.getName() + "] goes first!");
			monster.attack(player);
			checkDead(player, monster);
			
			if(monster.isDead() == false) {
				player.attack(monster);
				checkDead(player, monster);
			}
			System.out.println("---------------------------------------------");
		}
	}
	
	/**
	 * @method Checks if player or monster hp is below zero.
	 * @param player
	 * @param monster
	 */
	private void checkDead(Player player, Monster monster) {
		if(player.getHp() <= 0) {
			System.out.println("You died, Game Over!");
			player.setDead(true);
			battleRun = false;
		}
		
		if(monster.getHp() <= 0) {
			monster.setDead(true);
			battleRun = false;
			System.out.println("---------------------------------------------");
			System.out.println(" # " + monster.getName().toUpperCase() + " was defeated! #");
			dropLoot(player, monster);
		}
	}
	
	private void defend(Player player, Monster monster) {
		System.out.println("[" + player.getName().toUpperCase() + "] defends!");
		int damageDealt = Math.abs(player.getDef() - monster.getAtk());
		player.setHp(player.getHp() - damageDealt);
		System.out.println("[" + monster.getName().toUpperCase() + "] strikes [" + player.getName().toUpperCase() + "] for " + damageDealt + " damage!");
		
		checkDead(player, monster);
	}
	
	/**
	 * @method Checks if monster is boss and drops loot(s) from boss monster or common monster
	 * @param player
	 * @param monster
	 */
	private void dropLoot(Player player, Monster monster) {
		if(monster.isBoss() && monster.getLoot() != null) {
			System.out.println("[" + monster.getName() + "] dropped [" + monster.getLoot().getName().toUpperCase() + "].");
			player.pickUp(monster.getLoot());
			player.pickUp(itemList.get(5));
		}
		
		if(monster.isBoss()) {
			System.out.println("[" + monster.getName().toUpperCase() + "] dropped [" + itemList.get(5).getName().toUpperCase() + "].");
			player.pickUp(itemList.get(5));
		}
		
		double lootChance = (Math.random()*100);	
		if (lootChance > 50.0) {
			System.out.println("[" + monster.getName().toUpperCase() + "] dropped an [" + itemList.get(4).getName().toUpperCase() + "]");
			player.pickUp(itemList.get(4));
		}
	}
	
	/**
	 * @method Checks if inventory is not empty, if not empty -> player uses healing item
	 * @param player
	 */
	private void useHealingItem(Player player) {
		if(!playerInventory.isEmpty()) {
			try {
				int bagIndex;
				player.openInventory();
				System.out.println("Which healing item do you want to use?");
				System.out.print(">>");
				bagIndex = input.nextInt();
				player.useItem(bagIndex);
				input.nextLine();
			} 
			catch(InputMismatchException e) {
				//do nothing
			}
		} 
		else {
			System.out.println("Inventory is empty...");
		}
	}
	
	/**
	 * @method Compares player's and monster's speed and calculates chance of escaping from battle.
	 * @param player
	 * @param monster
	 */
	private void tryToFlee(Player player, Monster monster) {
		if(player.getSpd() > monster.getSpd()) {
			System.out.println("You escaped from battle!");
			battleRun = false;
		}
		
		else if(player.getSpd() == monster.getSpd()) {
			double fleeChance = (Math.random()*100);
			if(fleeChance >= 50.0) {
				System.out.println("You successfully fled barely!");
			}
			else {
				System.out.println("You couldn run away!");
			}
		}
		else {
			System.out.println("You couldn't run away!");
		}
	}
}
