package Tester;

import java.util.List;
import java.util.Scanner;

import Character.Player;
import Generator.ItemGenerator;
import Item.Item;
import LogicController.PuzzleLogic;
import Room.Room;
import Room.RoomFactory;

public class test {
	
	public static void main(String[] args) {
		
		List<Room> rooms = new RoomFactory().getRoomFactoryList();
		
		Room room = rooms.get(11);
		
		System.out.println(room.getName());
		
		List<Item> items = new ItemGenerator().getItemList();
		
		Player player = new Player(10, 10, 1, 1, 1);
		
		List<Item> inventory = player.getInventory();
		
		Item item = items.get(7);
		
		player.pickUp(items.get(8));
		player.pickUp(item);
		
		player.openInventory();
		
		System.out.println("I have this item: " + player.checkInventoryKeyItem(room.getRoomPuzzle().getKeyItem()));
		
		Scanner in = new Scanner(System.in);
		
		PuzzleLogic logic = new PuzzleLogic(in);
		
		logic.initiatePuzzle(room, player);
		
	}

}
