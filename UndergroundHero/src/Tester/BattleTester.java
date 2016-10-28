package Tester;

import java.util.List;
import java.util.Random;

import Character.Monster;
import Character.MonsterFactory;
import Character.Player;
import Item.ConsumableItem;

public class BattleTester{
	static Player player;
	
	public static boolean AtkCmd = false;
	public static boolean DefCmd = false;
	public static boolean HealCmd = false;
	public static boolean EscCmd = false;
	public static boolean PlayerTurn = true;
	static Random Generator = new Random(); 
	static int RandMon = Generator.nextInt(7)+ 1; //Randomly selects a monster from Monster_Data
	static Random Generator2 = new Random();
	static int RandAttack = Generator2.nextInt(3) + 1; //Randomly selects the monster's attack

	public static void main(String[] args) {
		player = new Player(10,1,3,2);	
		List<Monster> monsterList = new MonsterFactory().getMonsterList();
		String monsterName = monsterList.get(RandMon).getName();
		String monsterDesc = monsterList.get(RandMon).getDescription();
		int monsterHP = monsterList.get(RandMon).getHp();
		int monsterAtk = monsterList.get(RandMon).getAtk();
		int monsterDef = monsterList.get(RandMon).getDef();
		int monsterSpd = monsterList.get(RandMon).getSpd();		
 
//		//Heal Command - Unfinished
//		if (HealCmd == true && ConsumableItem.getCount() > 0){
//			ConsumableItem.useItem();
//			// Print feedback that item has been used.
//			HealCmd = false;
//		}
//		else if (HealCmd == true && ConsumableItem.count <= 0){
//			// Print feedback that there is no item to use
//			HealCmd = false;
//		}
//		
//		//Attack Command - Unfinished
//		if (AtkCmd == true && player.spd >= monsterSpd){ //When attacking and player is faster than monster
//			System.out.println("Player will attack first");
//			monsterHP = monsterHP - (player.atk - monsterDef);
//			AtkCmd = false;
//			if (monsterHP > 0){
//				//Monster attack
//			}
//		}
//		else if (AtkCmd == true && player.spd < monsterSpd){ //When attacking and player is slower than monster
//			System.out.println("Monster will attack first");
//			//Monster attack
//			if (player.hp > 0){
//				monsterHP = monsterHP - (player.atk - monsterDef);
//				AtkCmd = false;
//			}
//		}
//		
//		//Defend Command - UNFINISHED
//		if (DefCmd == true){
//			player.def = player.def * 2;
//			//Monster attack
//			player.def = player.def / 2;
//			DefCmd = false;
//		}
		
		//Escape Command - UNFINISHED
		if (EscCmd == true){
			// Text will say "Which direction?"
				// if (Player runs in direction with no exit){
					// "Can't run in that direction!"; Given another chance to act; EscCmd = false;}
				// else if (Player runs in direction with exit and isn't being blocked && player.spd >= monsterSpd){
					// Escape successfully into room; EscCmd = false;}
				// else if (Player runs in direction with exit and isn't being blocked && player.spd < monsterSpd){
					// 50% chance of escape
						// if (Successful) {
							// Escape successfully into room; EscCmd = false;}
						// if (Failure) {
							// EscCmd = false; Enemy attack;}
				// else if (Player runs in direction with exit but is blocked{
					// "The enemy blocks your escape!"; Given another chance to act; EscCmd = false;}
		}
		
				
		System.out.println(monsterName+": "+monsterDesc+" HP: "+monsterHP+" Atk: "+monsterAtk+" Def: "+monsterDef+
				" Spd: "+monsterSpd);
		System.out.println(player.getHp()+" "+player.getAtk()+" "+player.getDef()+" "+player.getSpd());
		System.out.println(RandMon);
		
		
		
	}
}
