package Tester;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Character.Player;
import Generator.ItemGenerator;
import Item.*;

public class InventoryTester {
	
	public static void main(String[] args){
		
		Player player = new Player(10, 3, 2, 3);
		List<Item> itemList = new ItemGenerator().getItemList();
		
		ConsumableItem healingItem = (ConsumableItem) itemList.get(4);
		
		System.out.println("Stats before equip.");
		System.out.println(player.toString());
		
		//add random items to inventory
		player.pickUp(itemList.get(0));
		player.pickUp(itemList.get(1));
		player.pickUp(itemList.get(9));
		player.pickUp(itemList.get(10));
		player.pickUp(healingItem);
		player.pickUp(healingItem);
		
		System.out.println("Inventory before item use.");
		player.openInventory();
		
		Scanner in = new Scanner(System.in);
		String command;
		int bagIndex;
		boolean gameRun = true;
		
		while(gameRun){
			System.out.print(">>");
			command = in.nextLine();
			
			if(command.equalsIgnoreCase("bag")) {
				player.openInventory();
			}
			else if(command.equalsIgnoreCase("quit")) {
				gameRun = false;
			}
			else if(command.equalsIgnoreCase("equip")) {
				System.out.println("What item do you want to equip? (Choose a number)");
				player.openInventory();
				System.out.print(">>");
				bagIndex = in.nextInt();
				player.equip(bagIndex);
				in.nextLine();
			}
			else if(command.equalsIgnoreCase("use")) {
				try{
					System.out.println("Which item do you want to use? (Choose a number)");
					player.openInventory();
					System.out.print(">>");
					bagIndex = in.nextInt();
					player.useItem(bagIndex);
					in.nextLine();
				}
				catch(InputMismatchException e) {
					
				}
			}
			else if(command.equalsIgnoreCase("info")) {
				System.out.println(player.toString());
			}
			else{
				System.out.println("Invalid Command.");
			}
			
		}
		
		
	}

}
