package Main;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import Character.Monster;
import Character.MonsterGenerator;
import Character.Player;
import Game.Function;
import Item.Item;
import Item.ItemGenerator;
import LogicController.BattleLogic;
import Room.Room;
import Room.RoomFactory;
/**
 * This class will test only the battle functionality.
 * The purpose is to test damage calculation, monster skills, and players interactions.
 *
 */
public class BattleTest {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
//		List<Room> rooms = new RoomFactory().getRoomFactoryList();
		List<Item> itemList = new ItemGenerator().getItemList();
		List<Monster> monsters = new MonsterGenerator().getMonsterList();
		
		Monster monster = monsters.get(7);
		
		Player player = new Player(20, 20, 2, 3, 2);
		player.setName("HERO");
		player.pickUp(itemList.get(0));
		player.pickUp(itemList.get(2));
		player.pickUp(itemList.get(4));
		player.pickUp(itemList.get(7));
		player.pickUp(itemList.get(8));
		player.pickUp(itemList.get(10));
		player.startingEquip(0);
		player.startingEquip(0);
		
		BattleLogic logic = new BattleLogic(in);
		logic.initiateBattle(player, monster);
		
	}
	
}
