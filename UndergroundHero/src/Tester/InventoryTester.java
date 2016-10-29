package Tester;

import java.util.List;

import Character.Player;
import Generator.ItemGenerator;
import Item.Item;

public class InventoryTester {
	
	public static void main(String[] args) {
		
		Player player = new Player(10, 3, 2, 1);
		List<Item> itemList = new ItemGenerator().getItemList();
		
		player.addToInventory(itemList.get(0));
		player.addToInventory(itemList.get(1));
		
		player.openInventory();
		
		
	}

}
