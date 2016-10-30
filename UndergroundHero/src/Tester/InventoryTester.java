package Tester;

import java.util.List;

import Character.Player;
import Generator.ItemGenerator;
import Item.*;

public class InventoryTester {
	
	public static void main(String[] args) throws IndexOutOfBoundsException{
		
		Player player = new Player(10, 3, 2, 3);
		List<Item> itemList = new ItemGenerator().getItemList();
		
		ConsumableItem healingItem = (ConsumableItem) itemList.get(4);
		
		System.out.println("Stats before equip.");
		System.out.println(player.toString());
		
		//add random items to inventory
		player.pickUp(itemList.get(0));
		player.pickUp(itemList.get(1));
		player.pickUp(itemList.get(9));
		player.pickUp(healingItem);
		player.pickUp(healingItem);
		
		System.out.println("Inventory before item use.");
		player.openInventory();
		
		player.useItem(3);
		System.out.println("Inventory after item removed.");
		player.openInventory();
		
		player.equip(0);
		player.equip(1);
		player.equip(0);
		System.out.println(player.toString());
		
		player.pickUp(itemList.get(3));
		player.openInventory();
		
		player.equip(2);
		System.out.println(player.toString());
		
		player.openInventory();
		
		
	}

}
