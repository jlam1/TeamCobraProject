package LogicController;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import Character.*;
import Game.Function;
import Item.*;
/**
 * This class is responsible for battle logic between the player and monster.
 * @author John, Kyle, Matt
 *
 */
public class BattleLogic {
	private Scanner input;
	private ArrayList<Item> itemList;
	private MusicLogic musicLogic;
	private Function f;
	private List<Item> playerInventory;
	private boolean battleRun, fled;
	private int whoseDead;
	
	public BattleLogic(Scanner in) {
		input = in;
		itemList = new ItemGenerator().getItemList();
		musicLogic = new MusicLogic("src/sound/battle.wav");
		f = new Function();
	}

	/**
	 * @method Main method for this class. Deals with the battle logic.
	 * @param player
	 * @param monster
	 */
	public void initiateBattle(Player player, Monster monster){
		playerInventory = player.getInventory();
		battleRun = true;
		fled = false;
		
		//set whoseDead to -1; if player is dead set whoseDead to 0 and monster to 1 and return value to Game class.
		whoseDead = -1;
		
//		delay();
		encounterMusic();
		f.printBox("# " + monster.getName().toUpperCase() + " HAS APPEARED! #");
		
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(monster.isBoss()) {
			bossMusic();
		}
		else{
			battleMusic();
		}
		while (battleRun) {
			System.out.println("########################################################\n");
//			f.delay(100);
			System.out.println("+------------------------------------------------------+");
//			f.delay(100);
			
			System.out.println("|                        BATTLE                        |");
			f.printBox("|--- PLAYER: [" + player.getHp() + "/" + player.getMaxhp() + "] ----|"
					+ "---- " + "MONSTER: [" + monster.getHp() + "/" + monster.getMaxhp() + "] ---|");
			
//			f.delay(100);
			System.out.println("");
//			f.delay(100);
			System.out.println("+------------------------------------------------------+");
			System.out.println("|                    BATTLE COMMANDS                   |");
			System.out.println("|       1. [ATTACK]                   3. [DEFEND]      |");
			System.out.println("|       2. [USE ITEM]                 4. [RUN]         |");
			System.out.println("+------------------------------------------------------+");
			System.out.println();
//			f.delay(100);
			System.out.println("########################################################\n");

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
			floor1Music();
		}
			
	}
	
	/**
	 * @method Compares speed between player and monster and then initiates attack. Whoever has more speed, has the highest attack priority.
	 * @param player
	 * @param monster
	 */
	private void battle(Player player, Monster monster) {
		//if player has more speed
		if(player.getSpd() > monster.getSpd()) {
			System.out.println("+------------------------------------------------------+");
			delay();
			System.out.println("[" + player.getName() + "] goes first!");
			delay();
			player.attack(monster);
			delay();
			checkDead(player, monster);
			
			if(!monster.isDead()) {
				monster.attack(player);
				checkDead(player, monster);
			}

			System.out.println("+------------------------------------------------------+");
			delay();
		}
		//if both player and monster have same speed
		else if(player.getSpd() == monster.getSpd()) {
			double chance = (Math.random()*100);
			//chance goes to player first
			if(chance > 50) {
				System.out.println("+------------------------------------------------------+");
//				delay();
				System.out.println("[" + player.getName() + "] goes first!");
//				delay();
				player.attack(monster);
//				delay();
				checkDead(player, monster);
				
				if(!monster.isDead()) {
					monster.attack(player);
					checkDead(player, monster);
				}
				System.out.println("+------------------------------------------------------+");
//				delay();
			}
			//chance goes to monster first
			else {
				System.out.println("+------------------------------------------------------+");
//				delay();
				System.out.println("[" + monster.getName().toUpperCase() + "] goes first!");
//				delay();
				monster.attack(player);
//				delay();
				checkDead(player, monster);
				
				if(!monster.isDead()) {
					player.attack(monster);
					checkDead(player, monster);
				}
				System.out.println("+------------------------------------------------------+");
//				delay();
			}
		}
		//if monster has more speed
		else{
			System.out.println("+------------------------------------------------------+");
//			delay();
			System.out.println("[" + monster.getName().toUpperCase() + "] goes first!");
//			delay();
			monster.attack(player);
//			delay();
			checkDead(player, monster);
			
			if(!monster.isDead()) {
				player.attack(monster);
				checkDead(player, monster);
			}
			System.out.println("+------------------------------------------------------+");
//			delay();
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
				e.printStackTrace();
			}
			player.setDead(true);
			battleRun = false;
		}
		if(monster.getHp() <= 0) {
			monster.setDead(true);
			battleRun = false;
			System.out.println("+------------------------------------------------------+");
			System.out.println(" # " + monster.getName().toUpperCase() + " was defeated! #");
			victoryMusic();
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			dropLoot(player, monster);
		}
	}
	
	private void defend(Player player, Monster monster) {
		System.out.println("+------------------------------------------------------+");
//		delay();
		System.out.println("[" + player.getName().toUpperCase() + "] defends!");
		int damageDealt = Math.abs(player.getDef() - monster.getAtk());
		player.setHp(player.getHp() - damageDealt);
//		delay();
		System.out.println("[" + monster.getName().toUpperCase() + "] strikes [" + player.getName().toUpperCase() + "] for " + damageDealt + " damage!");
		System.out.println("+------------------------------------------------------+");
		checkDead(player, monster);
//		delay();
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
//				delay();
				int bagIndex;
				player.openInventory();
				System.out.println("Which healing item do you want to use?");
				System.out.print(">>");
				bagIndex = input.nextInt();
				player.useItem(bagIndex);
				input.nextLine();
			} 
			catch(InputMismatchException e) {
				System.out.println("Invalid input.");
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
//			delay();
			System.out.println("You escaped from battle!");
			battleRun = false;
			fled = true;
			floor1Music();
		}
		
		else if(player.getSpd() == monster.getSpd()) {
			double fleeChance = (Math.random()*100);
			if(fleeChance >= 50.0) {
//				delay();
				System.out.println("You successfully fled barely!");
				battleRun = false;
				fled = true;
				floor1Music();
			}
			else {
				fled = false;
//				delay();
				System.out.println("You couldn't run away!");
//				delay();
				monster.attack(player);
//				delay();
			}
		}
		else {
			fled = false;
//			delay();
			System.out.println("You couldn't run away!");
//			delay();
			monster.attack(player);
//			delay();
		}
	}
	

	
	public int getWhoseDead() {
		return this.whoseDead;
	}
	
	public boolean playerFled() {
		return fled;
	}
	
	private void battleMusic()
	{
		musicLogic.BGMStop();
		musicLogic = new MusicLogic("src/sound/battle.wav");
		musicLogic.BGMLoop();
	}
	
	private void bossMusic()
	{
		musicLogic.BGMStop();
		musicLogic = new MusicLogic("src/sound/boss.wav");
		musicLogic.BGMLoop();
	}
	
	private void floor1Music() 
	{
		musicLogic.BGMStop();
		musicLogic = new MusicLogic("src/sound/floor1.wav");
		musicLogic.BGMLoop();
	}
	
	private void victoryMusic()
	{
		musicLogic.BGMStop();
		musicLogic = new MusicLogic("src/sound/victorytune.wav");
		musicLogic.BGMPlay();
	}
	
	private void deathMusic()
	{
		musicLogic.BGMStop();
		musicLogic = new MusicLogic("src/sound/death.wav");
		musicLogic.BGMPlay();
	}
	
	private void encounterMusic()
	{
		musicLogic.BGMStop();
		musicLogic = new MusicLogic("src/sound/encounter.wav");
		musicLogic.BGMPlay();
	}
	
	private void delay() {
		try {
			TimeUnit.MILLISECONDS.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
