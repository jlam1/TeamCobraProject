package LogicController;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import Character.*;
import Game.Function;
import Game.Game;
import Item.*;
import Room.Room;
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
	private boolean battleRun;
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
		
		//set whoseDead to -1; if player is dead set whoseDead to 0 and monster to 1 and return value to Game class.
		whoseDead = -1;
		
//		f.delay(300);
		encounterMusic();
		f.printBox("######### " + monster.getName().toUpperCase() + " HAS APPEARED! #########");
		f.print(monster.getDescription() + "\n\n", 10);
		
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
			System.out.println("########################################################");
//			f.delay(100);
			System.out.println("+------------------------------------------------------+");
//			f.delay(100);
			
			f.printBox("                     |- BATTLE -|                   ");
			f.printBox("|--- PLAYER: [" + player.getHp() + "/" + player.getMaxhp() + "] ----|"
					+ "---- " + "MONSTER: [" + monster.getHp() + "/" + monster.getMaxhp() + "] ---|");
			
//			f.delay(100);
			System.out.println("");
//			f.delay(100);
			System.out.println("+------------------------------------------------------+");
			System.out.println("|                     |- COMMANDS -|                   |");
			System.out.println("|       1. [ATTACK]                   3. [DEFEND]      |");
			System.out.println("|       2. [USE ITEM]                 4. [RUN]         |");
			System.out.println("+------------------------------------------------------+");
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
					monster.attack(player);
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
	
	private void startBattle(Player player, Monster monster) {
		if(battlePriority(player, monster) == 0) {
			System.out.println(player.getName() + " goes first!");
			playerTurn(player);
			checkDead(player, monster);
			
			if(!monster.isDead()) {
				monsterTurn(monster);
				checkDead(player, monster);
			}
		}
		
		if(battlePriority(player, monster) == 1) {
			System.out.println(monster.getName() + " goes first!");
			monsterTurn(monster);
			checkDead(player, monster);
			
			if(!player.isDead()) {
				playerTurn(player);
				checkDead(player, monster);
			}
		}
		
		else {
			System.out.println("ERROR: checkSpeed() -> returned -1");
		}
		
	}

	private void playerTurn(Player player) {
		
	}
	
	
	private void monsterTurn(Monster monster) {
		Skill skill = getRandomSkill(monster);
		monsterUseSkill(skill);
	}

	private Skill getRandomSkill(Monster monster) {
		List<Skill> skillList = monster.getSkillList();
		Skill monsterSkill = null;
		double chance = (Math.random()*100);
		for(Skill s : skillList) {
			if(s.getChance() >= chance) {
				monsterSkill = s;
			}
		}
		
		return monsterSkill;
	}
	
	private void monsterUseSkill(Skill skill) {
		if(skill.getType().equalsIgnoreCase("NORMAL"))
			useNormalSkill(skill);
		if(skill.getType().equalsIgnoreCase("CHARGE"))
			useChargeSkill(skill);
	}
	
	private void useChargeSkill(Skill skill) {
		
	}

	private void useNormalSkill(Skill skill) {
		
	}
	
	/**
	 * @method Checks both speed of player and monster and returns priority of battle initiative
	 * 			Method should never return a negative 1.
	 * @param player
	 * @param monster
	 * @return int
	 */
	private int battlePriority(Player player, Monster monster) {
		int checkSpeed = -1;
		if(player.getSpd() > monster.getSpd())
			checkSpeed = 0;
		if(player.getSpd() < monster.getSpd())
			checkSpeed = 1;
		if(player.getSpd() == monster.getSpd()) {
			double chance = (Math.random()*100);
			if(chance < 50.0)
				checkSpeed = 0;
			checkSpeed = 1;
		}
		return checkSpeed;
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
			f.delay(300);
			System.out.println("[" + player.getName() + "] goes first!");
			f.delay(300);
			player.attack(monster);
			f.delay(300);
			checkDead(player, monster);
			
			if(!monster.isDead()) {
				monster.attack(player);
				checkDead(player, monster);
			}

			System.out.println("+------------------------------------------------------+");
			f.delay(300);
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
				
				if(!player.isDead()) {
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
			
			if(!player.isDead()) {
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
			player.setDead(true);
			battleRun = false;
			System.out.println("You died, Game Over!");
			deathMusic();
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		if(monster.getHp() <= 0) {
			monster.setDead(true);
			battleRun = false;
			System.out.println("+------------------------------------------------------+");
			f.printBox(" # " + monster.getName().toUpperCase() + " was defeated! #");
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
			floor1Music();
		}
		
		else if(player.getSpd() == monster.getSpd()) {
			double fleeChance = (Math.random()*100);
			if(fleeChance >= 50.0) {
//				delay();
				System.out.println("You escape from battle, barely!");
				battleRun = false;
				floor1Music();
			}
			else {
//				delay();
				System.out.println("You couldn't run away!");
//				delay();
				monster.attack(player);
//				delay();
			}
		}
		else {
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
	
	public void checkFloorMusic(Room currentRoom){
		if (currentRoom.getId() == 1 && currentRoom.getId() == 9){
			floor1Music();
		}
		else if (currentRoom.getId() >= 10 && currentRoom.getId() <= 18){
			floor2Music();
		}
		else if (currentRoom.getId() >= 19 && currentRoom.getId() <= 28){
			floor3Music();
		}
		else if (currentRoom.getId() >= 29 && currentRoom.getId() <= 41){
			floor4Music();
		}
	}
	private void floor1Music() 
	{
		musicLogic.BGMStop();
		musicLogic = new MusicLogic("src/sound/floor1.wav");
		musicLogic.BGMLoop();
	}
	
	private void floor2Music() {
		musicLogic.BGMStop();
		musicLogic = new MusicLogic("src/sound/floor2.wav");
		musicLogic.BGMLoop();
	}
	
	private void floor3Music() {
		musicLogic.BGMStop();
		musicLogic = new MusicLogic("src/sound/floor3.wav");
		musicLogic.BGMLoop();
	}
	
	private void floor4Music() {
		musicLogic.BGMStop();
		musicLogic = new MusicLogic("src/sound/floor4.wav");
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
	
}
