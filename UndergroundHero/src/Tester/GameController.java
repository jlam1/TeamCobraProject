package Tester;

import java.util.List;
import java.util.Scanner;

import Character.Player;
import Room.*;

public class GameController {
	
	static Room currentRoom;
	static List<Room> factoryList;
	static Player player;
	static Scanner input;

	public static void main(String[] args) {
		
		factoryList = new RoomFactory().getRoomFactoryList();
		play();
		
	}
	
	static void play(){
		input = new Scanner(System.in);
		String command;
		boolean gameRun = true;
		int bagIndex;
		currentRoom = factoryList.get(1);
		
		System.out.println("Welcome to Underground Hero");
		
		while(gameRun){
			System.out.print(">>");
			command = input.nextLine();
			
			if(validCommandInput(command)){
				roomLogic(command);
			}
			else if(command.equalsIgnoreCase("LOOK")){
				System.out.println(currentRoom.getName());
				System.out.println(currentRoom.getDescription());
				System.out.println(currentRoom.getExits());
			}
			else if(command.equalsIgnoreCase("EXITS")){
				System.out.println(currentRoom.getExits());
			}
			else if(command.equalsIgnoreCase("BAG")) {
				player.openInventory();
			}
			else if(command.equalsIgnoreCase("EQUIP")) {
				System.out.println("What item do you want to equip? (Choose a number)");
				player.openInventory();
				System.out.print(">>");
				bagIndex = input.nextInt();
				player.equip(bagIndex);
				input.nextLine();
			}
			else if(command.equalsIgnoreCase("USE")) {
				System.out.println("Which item do you want to use? (Choose a number)");
				player.openInventory();
				System.out.print(">>");
				bagIndex = input.nextInt();
				player.useItem(bagIndex);
				input.nextLine();
			}
			else if(command.equalsIgnoreCase("INFO")) {
				System.out.println(player.toString());
			}
			else if(command.equalsIgnoreCase("QUIT")){
				gameRun = false;
			}
			else{
				System.out.println("Invalid Input");
			}
			
		}
	}
	
	static void roomLogic(String direction){
		Room nextRoom = currentRoom.nextRoom(direction);
		
		if(nextRoom == null){
			System.out.println("Theres no exit that way, try another direction.");
		}
		else if(nextRoom.isLocked() == true){
			System.out.println(currentRoom.getDescription() + "\n\n\nDoor is locked.");
		}
		else{
			
			currentRoom = nextRoom;
			System.out.println("Room number: " + currentRoom.getName());
			System.out.println(currentRoom.getDescription());
			
		}
	}
	
	static boolean validCommandInput(String input){
		
		if(input.equalsIgnoreCase("EAST") || input.equalsIgnoreCase("WEST") || input.equalsIgnoreCase("NORTH") || input.equalsIgnoreCase("SOUTH")){
			return true;
		}else{
			return false;
		}
	}
	
	
	
//	for(Room i : factoryList){
//	System.out.println(i.getId());
//	if(i.isLocked() == true)
//		System.out.println(i.isLocked());
//	if(i.getRoomPuzzle() != null)
//		System.out.println(i.getRoomPuzzle().getName());
//	if(i.getRoomMonster() != null)
//		System.out.println(i.getRoomMonster().getName());
//	if(i.getRoomItem() != null)
//		System.out.println(i.getRoomItem().getName());
//	System.out.println();
//}
	
}
