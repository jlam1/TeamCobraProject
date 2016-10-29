package Tester;

import java.util.List;
import java.util.Scanner;
import Room.*;

public class Tester2 {
	
	static Room currentRoom;
	static Scanner in;
	static List<Room> rf;

	public static void main(String[] args) {
		
		rf = new RoomFactory().getRoomFactoryList();

		in = new Scanner(System.in);
		
		String input = in.nextLine();
		
		play(input);
		
		for(Room i : rf){
			System.out.println(i.getId());
			if(i.isLocked() == true)
				System.out.println(i.isLocked());
			if(i.getRoomPuzzle() != null)
				System.out.println(i.getRoomPuzzle().getName());
			if(i.getRoomMonster() != null)
				System.out.println(i.getRoomMonster().getName());
			if(i.getRoomItem() != null)
				System.out.println(i.getRoomItem().getName());
			System.out.println();
		}
		
	}
	
	static void play(String input){
		boolean gameRun = true;
		
		currentRoom = rf.get(1);
		
		while(gameRun){
			System.out.print(">");
			
			input = in.nextLine();
			
			if(validDirectionInput(input)){
				roomLogic(input);
			}
			else if(input.equalsIgnoreCase("LOOK")){
				System.out.println(currentRoom.getName());
				System.out.println(currentRoom.getDescription());
				System.out.println(currentRoom.getExits());
			}
			else if(input.equalsIgnoreCase("EXITS")){
				System.out.println(currentRoom.getExits());
			}
			else if(input.equalsIgnoreCase("QUIT")){
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
	
	static boolean validDirectionInput(String input){
		
		if(input.equalsIgnoreCase("EAST") || input.equalsIgnoreCase("WEST") || input.equalsIgnoreCase("NORTH") || input.equalsIgnoreCase("SOUTH")){
			return true;
		}else{
			return false;
		}
	}
	
}
