package Tester;

import java.util.List;
import java.util.Scanner;

import Game.ResourceManager;
import Game.saveLoadData;
import Room.*;

public class SaveLoadTester
{

	static Room currentRoom;
	static Scanner in;
	static List<Room> factoryList;

	public static void main(String[] args)
	{

		factoryList = new RoomFactory().getRoomFactoryList();

		in = new Scanner(System.in);
		String input = in.nextLine();

		
		play(input);

		for (Room i : factoryList)
		{
			System.out.println(i.getId());
			if (i.isLocked() == true)
				System.out.println(i.isLocked());
			if (i.getRoomPuzzle() != null)
				System.out.println(i.getRoomPuzzle().getName());
			if (i.getRoomMonster() != null)
				System.out.println(i.getRoomMonster().getName());
			if (i.getRoomItem() != null)
				System.out.println(i.getRoomItem().getName());
			System.out.println();
		}

	}

	static void play(String input)
	{
		boolean gameRun = true;
		currentRoom = factoryList.get(1);
		System.out.println(currentRoom.getName());
		System.out.println(currentRoom.getDescription());
		System.out.println(currentRoom.getExits());
		while (gameRun)
		{
			System.out.print(">");

			input = in.nextLine();

			if (validDirectionInput(input))
			{
				roomLogic(input);
			}
			else if (input.equalsIgnoreCase("LOOK"))
			{
				System.out.println(currentRoom.getName());
				System.out.println(currentRoom.getDescription());
				System.out.println(currentRoom.getExits());
			}
			else if (input.equalsIgnoreCase("EXITS"))
			{
				System.out.println(currentRoom.getExits());
			}
			else if (input.equalsIgnoreCase("QUIT"))
			{
				gameRun = false;
			}
			//TODO: need to save player stats, items the player is carrying, booleans of monsters and puzzles
			else if (input.equalsIgnoreCase("save"))
			{

				//use the class saveLoadData to save values in to binary file
				saveLoadData data = new saveLoadData();
				data.setRoomArrayNumber(currentRoom.getId());
				System.out.println(currentRoom.getId());
				//data.setRoom(currentRoom);
				//data.setRoomDescription(roomLabel.getText());
				//TODO: need to save the player stats, save already solve puzzle, save items in bag, save room boolean, and save already defeated monsters
				try
				{
					ResourceManager.saveGame(data, "UndergroundHero.dat");

					System.out.println("Save Sucessful");
				}
				catch (Exception e)
				{
					System.out.println("error saving");
					e.printStackTrace();
				}
			}

			else if (input.equalsIgnoreCase("load"))
			{
				//use the class saveLoadData to load values in the binary file
				try
				{
					saveLoadData data = (saveLoadData) ResourceManager.loadGame("UndergroundHero.dat");
					currentRoom = factoryList.get(data.getRoomArrayNumber());
					System.out.println("Load Sucessful");
					System.out.println();
					System.out.println(currentRoom.getName() + "\n" + currentRoom.getDescription());

					//data.setRoom(currentRoom);
					//TODO: need to load the player stats, load already solve puzzle, load items in bag, load room boolean and load already defeated monsters

				}
				catch (Exception e)
				{
					System.out.println("error loading");
					e.printStackTrace();
				}
			}

			else if(input.equalsIgnoreCase("help"))
			{
				System.out.println("NAVIGATION: \nNorth \nSouth \nEast \nWest \n\nROOM: \nLook \n\nCombat: \nAttack \nDefend \nRun [Direction] ");
			}
			else
			{
				System.out.println("Invalid Input");
			}

		}
	}

	static void roomLogic(String direction)
	{
		Room nextRoom = currentRoom.nextRoom(direction);

		if (nextRoom == null)
		{
			System.out.println("Theres no exit that way, try another direction.");
		}
		else if (nextRoom.isLocked() == true)
		{
			System.out.println(currentRoom.getDescription() + "\n\n\nDoor is locked.");
		}
		else
		{

			currentRoom = nextRoom;
			System.out.println("Room number: " + currentRoom.getName());
			System.out.println(currentRoom.getDescription());

		}
	}

	static boolean validDirectionInput(String input)
	{

		if (input.equalsIgnoreCase("EAST") || input.equalsIgnoreCase("WEST") || input.equalsIgnoreCase("NORTH")
				|| input.equalsIgnoreCase("SOUTH"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
