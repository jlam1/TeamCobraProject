package Tester;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import Character.Monster;
import Generator.MonsterGenerator;
import Character.Player;
import Generator.MonsterGenerator;
import Item.Consumables;

public class BattleTester {
	static Player player;

	public static boolean AtkCmd = false;
	public static boolean DefCmd = false;
	public static boolean HealCmd = false;
	public static boolean EscCmd = false;
	public static boolean PlayerTurn = true;
	static Random Generator = new Random();
	static int RandMon = Generator.nextInt(7) + 1; // Randomly selects a monster
													// from Monster_Data
	static Random Generator2 = new Random();
	static int RandAttack = Generator2.nextInt(3) + 1; // Randomly selects the
														// monster's attack

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Random rand = new Random();
		player = new Player(10, 10, 1, 3, 2); // HP, ATK, DEF, SPD
		int potionHeal = 30;
		int potionDropRate = 50; // Percent
		player = new Player(10, 10, 1, 3, 5);
		List<Monster> monsterList = new MonsterGenerator().getMonsterList();
		String monsterName = monsterList.get(RandMon).getName();
		String monsterDesc = monsterList.get(RandMon).getDescription();
		int monsterHP = monsterList.get(RandMon).getHp();
		int monsterAtk = monsterList.get(RandMon).getAtk();
		int monsterDef = monsterList.get(RandMon).getDef();
		int monsterSpd = monsterList.get(RandMon).getSpd();

		boolean running = true;
		GAME: while (running) {
			System.out.println("-----------------------------------");

			// # Skeleton has Appeared #
			System.out.println("\t# " + monsterName + " has appeared! #\n");

			while (monsterHP > 0) {
				System.out.println("\tYour HP: " + player.getHp());
				System.out.println("\t" + monsterName + "'s HP " + monsterHP);
				System.out.println("\n\tWhat would you like to do next?");
				System.out.println("\t1. Attack");
				System.out.println("\t2. Drink Potion");
				System.out.println("\t3. Run");
				int dmgDealth;
				if (player.getAtk() < monsterDef) {
					dmgDealth = 0;
				} else {
					dmgDealth = player.getAtk() - monsterDef;
				}
				System.out.println("\nplayer atk = " + player.getAtk());
				System.out.println("monster def = " + monsterDef);
				System.out.println("damage dealt = " + dmgDealth);
				int dmgTaken;
				if (monsterAtk < player.getDef()) {
					dmgTaken = 0;
				} else {
					dmgTaken = monsterAtk - player.getDef();
				}
				System.out.println("\nmonster atk = " + monsterAtk);
				System.out.println("player def = " + player.getDef());
				System.out.println("damage taken = " + dmgTaken);
				String input = in.nextLine();
				if (input.equals("1")) {

					monsterHP -= dmgDealth;
					player.setHp(player.getHp() - dmgTaken);

					System.out.println("\t> You strike the " + monsterName + " for " + dmgDealth + " damage");
					System.out.println("\t> You recived " + dmgTaken + " damage(s) from " + monsterName + "!");

					if (player.getHp() < 1) {
						System.out.println("\t> Your HP have reached 0.");
						break;
					}
				} else if (input.equals("2")) {
					if (Consumables.count > 0) {
						if (player.getHp() >= 100) {
							player.hp = 100;
							System.out.println("Unable to use potion. Your HP is full.");
						} else {
							player.hp += potionHeal;
							Consumables.count--;

							System.out.println("\t> You drank a potion, healing " + potionHeal + " HP."
									+ "\n\t> You now have " + player.hp + "HP." + "\n\t> You have "
									+ Consumables.count + " health potion(s) left.\n");
							System.out.println("\t> You recived " + dmgTaken + " damage(s) from " + monsterName + "!");
						}

					} else {
						System.out.println("You have no more health potion");
					}
				} else if (input.equals("3")) {
					System.out.println("\t> You have ran from " + monsterName + "!");
					continue GAME;
				} else {
					System.out.println("\tInvalid Command");
				}
			}

			if (player.hp < 1) {
				System.out.println("You died!");
				break;
			}
			System.out.println("-----------------------------------");
			System.out.println(" # " + monsterName + " was defeated! #");
			System.out.println(" # You have " + player.hp + "HP left. #");
			if (rand.nextInt(100) < potionDropRate) {
				Consumables.count++;
				System.out.println(" # The " + monsterName + " dropped a health potion. #");
				System.out.println(" # You currently have " + Consumables.count++ + " potion(s). #");
			}
			System.out.println("-----------------------------------");
			System.out.println("What would you like to do next?");
			System.out.println("  1. Continue.");
			System.out.println("  2. Retreat.");

			String input = in.nextLine();

			while (!input.equals("1") && !input.equals("2")) {
				System.out.println("Invalid Command");
				input = in.nextLine();
			}

			if (input.equals("1")) {
				System.out.println("You continue on your adventure!");
			} else if (input.equals("2")) {
				System.out.println("You have exited the dungeon, sucessfully from you adventures.");
				break;
			}

			// //Heal Command - Unfinished
			// if (HealCmd == true && ConsumableItem.getCount() > 0){
			// ConsumableItem.useItem();
			// // Print feedback that item has been used.
			// HealCmd = false;
			// }
			// else if (HealCmd == true && ConsumableItem.count <= 0){
			// // Print feedback that there is no item to use
			// HealCmd = false;
			// }
			//
			// //Attack Command - Unfinished
			// if (AtkCmd == true && player.spd >= monsterSpd){ //When attacking
			// and player is faster than monster
			// System.out.println("Player will attack first");
			// monsterHP = monsterHP - (player.atk - monsterDef);
			// AtkCmd = false;
			// if (monsterHP > 0){
			// //Monster attack
			// }
			// }
			// else if (AtkCmd == true && player.spd < monsterSpd){ //When
			// attacking and player is slower than monster
			// System.out.println("Monster will attack first");
			// //Monster attack
			// if (player.hp > 0){
			// monsterHP = monsterHP - (player.atk - monsterDef);
			// AtkCmd = false;
			// }
			// }
			//
			// //Defend Command - UNFINISHED
			// if (DefCmd == true){
			// player.def = player.def * 2;
			// //Monster attack
			// player.def = player.def / 2;
			// DefCmd = false;
			// }

			// Escape Command - UNFINISHED
			if (EscCmd == true) {
				// Text will say "Which direction?"
				// if (Player runs in direction with no exit){
				// "Can't run in that direction!"; Given another chance to act;
				// EscCmd = false;}
				// else if (Player runs in direction with exit and isn't being
				// blocked && player.spd >= monsterSpd){
				// Escape successfully into room; EscCmd = false;}
				// else if (Player runs in direction with exit and isn't being
				// blocked && player.spd < monsterSpd){
				// 50% chance of escape
				// if (Successful) {
				// Escape successfully into room; EscCmd = false;}
				// if (Failure) {
				// EscCmd = false; Enemy attack;}
				// else if (Player runs in direction with exit but is blocked{
				// "The enemy blocks your escape!"; Given another chance to act;
				// EscCmd = false;}
			}

			System.out.println(monsterName + ": " + monsterDesc + " HP: " + monsterHP + " Atk: " + monsterAtk + " Def: "
					+ monsterDef + " Spd: " + monsterSpd);
			System.out.println(player.getHp() + " " + player.getAtk() + " " + player.getDef() + " " + player.getSpd());
			System.out.println(RandMon);

		}
	}
}
