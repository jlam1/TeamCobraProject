package Tester;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Character.Monster;
import Character.MonsterController;
import Character.Player;
import Generator.*;
import Item.*;
import Puzzle.*;
import Room.*;

public class Tester2 {
	
	static Room currentRoom;
	static Scanner in;

	public static void main(String[] args) {
		
//		RoomController rc = new RoomController();
//		PuzzleController pc = new PuzzleController();
//		RoomFactory rf = new RoomFactory();
//		ItemController ic = new ItemController();
//		MonsterController mc = new MonsterController();
		
		List<Room> rf = new RoomFactory().getRoomFactoryList();
		List<Room> r = new RoomGenerator().getRoomList();
		
		in = new Scanner(System.in);
		
		currentRoom = rf.get(41);
		
		System.out.println(currentRoom.getId());
		
		
	}
	
	static void roomLoop(){
		boolean gameRun = true;
		String input;
		
		while(gameRun){
			System.out.print(">");
			
			input = in.nextLine();
			
			if(validDirectionInput(input)){
				roomLogic(input);
			}
			else if(input.equalsIgnoreCase("LOOK")){
				System.out.println(currentRoom.getName());
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
