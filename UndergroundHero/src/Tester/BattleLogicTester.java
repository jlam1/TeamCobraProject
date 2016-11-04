package Tester;

import java.util.ArrayList;

import Character.Monster;
import Character.Player;
import Generator.ItemGenerator;
import Generator.MonsterGenerator;
import Item.Item;
import LogicController.BattleLogic;

public class BattleLogicTester {
	
	public static void main(String[] args) {
		
		ArrayList<Monster> monsters = new MonsterGenerator().getMonsterList();
		Player player = new Player(10, 10, 3, 3, 2);	//hp atk spd def
		ArrayList<Item> items = new ItemGenerator().getItemList();
		ArrayList<Item> bag = player.getInventory();
		
		player.pickUp(items.get(4));
		
		player.setName("HERO");
		
		BattleLogic bl = new BattleLogic();
		
		bl.initiateBattle(player, monsters.get(6));
		
//		Monster monster = monsters.get(1);
//		player.attack(monster);
//		System.out.println();
//		monster.attack(player);
//		
//		player.useItem(0);
//		
//		player.defend(monster);
		
	}

}
