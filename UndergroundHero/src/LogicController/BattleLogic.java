package LogicController;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
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
	private List<Item> playerInventory;
	private boolean battleRun;
	private int whoseDead;
	private MusicLogic musicLogic;
	
	public BattleLogic(Scanner in) {
		input = in;
		itemList = new ItemGenerator().getItemList();
		musicLogic = new MusicLogic("src/sound/battle.wav");

	}

	/**
	 * @method Main method for this class. Deals with the battle logic.
	 * @param player
	 * @param monster
	 */
	
	public void battleMusic()
	{
		musicLogic.BGMStop();
		musicLogic = new MusicLogic("src/sound/battle.wav");
		musicLogic.BGMLoop();
	}
	public void bossMusic()
	{
		musicLogic.BGMStop();
		musicLogic = new MusicLogic("src/sound/boss.wav");
		musicLogic.BGMLoop();
	}
	public void transverseMusic() 
	{
		musicLogic.BGMStop();
		musicLogic = new MusicLogic("src/sound/traverse.wav");
		musicLogic.BGMLoop();
	}
	public void victoryMusic()
	{
		musicLogic.BGMStop();
		musicLogic = new MusicLogic("src/sound/victorytune.wav");
		musicLogic.BGMPlay();
	}
	public void deathMusic()
	{
		musicLogic.BGMStop();
		musicLogic = new MusicLogic("src/sound/death.wav");
		musicLogic.BGMPlay();
	}
	public void encounterMusic()
	{
		musicLogic.BGMStop();
		musicLogic = new MusicLogic("src/sound/encounter.wav");
		musicLogic.BGMPlay();
	}
	public void initiateBattle(Player player, Monster monster){
		playerInventory = player.getInventory();
		battleRun = true;
		
		//set whoseDead to -1; if player is dead set whoseDead to 0 and monster to 1 and return value to Game class.
		whoseDead = -1;
		
		System.out.println("---------------------------------------------\n");
		System.out.println("\t# " + monster.getName().toUpperCase() + " has appeared! #\n");
		System.out.println(monster.getDescription());
		System.out.println("---------------------------------------------");
		encounterMusic();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(monster.isBoss()) {
			bossMusic();
		}
		else{
			battleMusic();
		}
		while (battleRun) {
			System.out.println("\tYour HP: [" + player.getHp() + "/" + player.getMaxhp() + "]");
			System.out.println("\t" + monster.getName() + "'s HP [" + monster.getHp() + "/" + monster.getMaxhp() + "]");
			System.out.println("\n\tWhat would you like to do next?");
			System.out.println("\t1. Attack");
			System.out.println("\t2. Use Item");
			System.out.println("\t3. Defend");
			System.out.println("\t4. Run");
			System.out.println("\t5. View Inventory");

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
				
			}
			
			catch(InputMismatchException e) {
				System.out.println("Invalid Command");
			}
			
		}
		
		if(player.isDead()) {
			System.out.println("Returning to menu screen...");
			whoseDead = 0;
			musicLogic.BGMStop();
		}
		if(monster.isDead()) {
			whoseDead = 1;
			transverseMusic();
		}
			
	}
	
	public int getWhoseDead() {
		return this.whoseDead;
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
			deathMusic();
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			player.setDead(true);
			battleRun = false;
		}
		
		if(monster.getHp() <= 0) {
			monster.setDead(true);
			battleRun = false;
			System.out.println("---------------------------------------------");
			System.out.println(" # " + monster.getName().toUpperCase() + " was defeated! #");
			victoryMusic();
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
			transverseMusic();
		}
		
		else if(player.getSpd() == monster.getSpd()) {
			double fleeChance = (Math.random()*100);
			if(fleeChance >= 50.0) {
				System.out.println("You successfully fled barely!");
				battleRun = false;
				transverseMusic();
			}
			else {
				System.out.println("You couldn't run away!");
			}
		}
		else {
			System.out.println("You couldn't run away!");
		}
	}
}
