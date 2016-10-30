package Tester;

import java.util.List;
import java.util.Scanner;

import Character.Player;
import LogicController.PuzzleLogic;
import Room.*;

public class PuzzleTester {
	
	public static void main(String[] args) {
		
		List<Room> rooms = new RoomFactory().getRoomFactoryList();
		Player player = new Player(10, 1, 2, 2);
		PuzzleLogic p = new PuzzleLogic();
		boolean gameRun = true;
		
		Room currentRoom = rooms.get(12);
		
		Scanner input = new Scanner(System.in);
		String command;
		
		System.out.println("Start puzzle tester? (Y/N)");
		System.out.print(">>");
		command = input.nextLine();

		if(command.equalsIgnoreCase("Y")) {
			p.initiatePuzzle(currentRoom, player);
		}
		else if(command.equalsIgnoreCase("N")) {
			System.out.println("No");
		}
		else{
			System.out.println("Wrong command input.");
		}
		
	}

}
