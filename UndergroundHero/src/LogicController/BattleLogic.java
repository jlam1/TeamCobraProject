package LogicController;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import Character.*;
import Game.Function;
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
		
		f.delay(300);
		encounterMusic();
		f.printBox("######### " + monster.getName() + " HAS APPEARED! #########");
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
			f.delay(100);

			
			f.printBox("                    |- BATTLE -|                    ");
			f.delay(100);
			f.printBox("     PLAYER: [" + player.getHp() + "/" + player.getMaxhp() + "]     |"
					+ "     " + "MONSTER: [" + monster.getHp() + "/" + monster.getMaxhp() + "]     ");
			f.delay(100);
			System.out.println("");
			System.out.println("+------------------------------------------------------+");
			System.out.println("|                    |- COMMANDS -|                    |");
			System.out.println("|       1. [ATTACK]                   3. [DEFEND]      |");
			System.out.println("|       2. [USE ITEM]                 4. [RUN]         |");
			System.out.println("+------------------------------------------------------+");
			
			f.delay(100);
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
				
//				//testing battle with skills
//				if(userInput.equals("5")) {
//					startBattle(player, monster);
//				}
				
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
	
//	private void startBattle(Player player, Monster monster) { 
//		System.out.println("player spd: " + player.getSpd());
//		System.out.println("monster spd: " + monster.getSpd());
//		
//		if(battlePriority(player, monster) == 0) {
//			System.out.println(player.getName() + " goes first!");
//			playerTurn(player, monster);
//			checkDead(player, monster);
//			
//			if(!monster.isDead()) {
//				if(isCharging(skill)) {
////					useChargeSkill(monster, skill);
//				}
//				if(!isCharging(skill)) {
////					monsterTurn(player, monster);
//				}
//				checkDead(player, monster);
//			}
//		}
//		
//		else if(battlePriority(player, monster) == 1) {
//			System.out.println(monster.getName() + " goes first!");
//			Skill skill = getRandomSkill(monster);
//			monsterUseSkill(player, monster, skill);
//			
//			checkDead(player, monster);
//			
//			if(!player.isDead()) {
//				playerTurn(player, monster);
//				checkDead(player, monster);
//			}
//		}
//		
//		else {
//			System.out.println("battlePriority(): " + battlePriority(player, monster));
//		}
//		
//	}
//
//	private boolean isCharging(Skill skill) {
//		return skill.isCharging();
//	}
//
//	private void playerTurn(Player player, Monster monster) {
//		player.attack(monster);
//	}
//	
//	
//	private void monsterTurn(Player player, Monster monster) {
//		Skill skill = getRandomSkill(monster);
//		monsterUseSkill(player, monster, skill);
//	}
//
//	private Skill getRandomSkill(Monster monster){
//		List<Skill> skillList = monster.getSkillList();
//		Skill monsterSkill = null;
//		
//		while(monsterSkill == null) {
//			double chance = (Math.random()*100);
////			System.out.println("randomSkill chance: " + chance);
//			for(Skill s : skillList) {
//				if(s.getChance() >= chance) {
//					monsterSkill = s;
//					break;
//				}
//			}
//		}
//		
//		return monsterSkill;
//	}
//	
//	private void monsterUseSkill(Player player, Monster monster, Skill skill) {
//		if(skill.getType().equalsIgnoreCase("NORMAL"))
//			useNormalSkill(player, monster, skill);
//		if(skill.getType().equalsIgnoreCase("CHARGE"))
//			useChargeSkill(monster, skill);
//	}
//	
//	private void useChargeSkill(Monster monster, Skill skill) {
//		System.out.println("CHARGE SKILL TEST");
//		//if already charged then skip
//		//else if not charged, set isCharged to true
//		//use skill then set charge back to false
//	}
//
//	private void useNormalSkill(Player player, Monster monster, Skill skill) {
//		System.out.print("[" + monster.getName() + "] uses [" + skill.getName() + "]");
//		f.print("...\n", 500);
//		System.out.println("and " + skill.getDescription());
//		double skillDmg = skill.getDmgRatio() * monster.getAtk();
//		int dmgDealt = (int)Math.abs(skillDmg - player.getDef());
//		System.out.println("[" + skill.getName() + "] deals for " + dmgDealt + " damage!");
//		player.setHp(player.getHp() - dmgDealt);
//	}
//	
//	/**
//	 * @method Checks both speed of player and monster and returns priority of battle initiative
//	 * 			Method should never return a negative 1.
//	 * @param player
//	 * @param monster
//	 * @return int
//	 */
//	private int battlePriority(Player player, Monster monster) {
//		int checkSpeed = -1;
//		if(player.getSpd() > monster.getSpd()) {
//			checkSpeed = 0;
//		}
//		if(player.getSpd() < monster.getSpd()) {
//			checkSpeed = 1;
//		}
//		if(player.getSpd() == monster.getSpd()) {
//			double chance = (Math.random()*100);
//			if(chance < 50.0) {
//				checkSpeed = 0;
//			}
//			if(chance > 50.0) {
//				checkSpeed = 1;
//			}
//		}
//		return checkSpeed;
//	}

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
			//chance goes to monster first
			else {
				System.out.println("+------------------------------------------------------+");
				f.delay(300);
				System.out.println("[" + monster.getName() + "] goes first!");
				f.delay(300);
				monster.attack(player);
				f.delay(300);
				checkDead(player, monster);
				
				if(!player.isDead()) {
					player.attack(monster);
					checkDead(player, monster);
				}
				System.out.println("+------------------------------------------------------+");
				f.delay(300);
			}
		}
		//if monster has more speed
		else{
			System.out.println("+------------------------------------------------------+");
			f.delay(300);
			System.out.println("[" + monster.getName() + "] goes first!");
			f.delay(300);
			monster.attack(player);
			f.delay(300);
			checkDead(player, monster);
			 
			if(!player.isDead()) {
				player.attack(monster);
				checkDead(player, monster);
			}
			System.out.println("+------------------------------------------------------+");
			f.delay(300);
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
			f.printBox(" # " + monster.getName() + " was defeated! #");
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
		f.delay(300);
		System.out.println("[" + player.getName() + "] defends!");
		int damageDealt = Math.abs(player.getDef() - monster.getAtk());
		player.setHp(player.getHp() - damageDealt);
		f.delay(300);
		System.out.println("[" + monster.getName() + "] strikes [" + player.getName() + "] for " + damageDealt + " damage!");
		System.out.println("+------------------------------------------------------+");
		checkDead(player, monster);
		f.delay(300);
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
			System.out.println("[" + monster.getName() + "] dropped [" + itemList.get(5).getName().toUpperCase() + "].");
			player.pickUp(itemList.get(5));
		}
		
		double lootChance = (Math.random()*100);	
		if (lootChance > 50.0) {
			System.out.println("[" + monster.getName() + "] dropped an [" + itemList.get(4).getName().toUpperCase() + "]");
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
				f.delay(300);
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
			f.delay(300);
			System.out.println("You escaped from battle!");
			battleRun = false;
			floor1Music();
		}
		
		else if(player.getSpd() == monster.getSpd()) {
			double fleeChance = (Math.random()*100);
			if(fleeChance >= 50.0) {
				f.delay(300);
				System.out.println("You escape from battle, barely!");
				battleRun = false;
				floor1Music();
			}
			else {
				f.delay(300);
				System.out.println("You couldn't run away!");
				f.delay(300);
				monster.attack(player);
				checkDead(player, monster);
				f.delay(300);
			}
		}
		else {
			f.delay(300);
			System.out.println("You couldn't run away!");
			f.delay(300);
			monster.attack(player);
			checkDead(player, monster);
			f.delay(300);
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
