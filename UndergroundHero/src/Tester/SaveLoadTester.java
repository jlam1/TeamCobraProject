package Tester;

import java.util.ArrayList;
import java.util.Scanner;

import org.omg.CORBA.Current;

import Character.Player;
import Game.Game;
import Game.ResourceManager;
import Game.saveLoadData;
import Room.Room;
import Room.RoomFactory;

public class SaveLoadTester
{
	private static int roomID;

	public static void main(String[] args)
	{

		RoomFactory factory = new RoomFactory();
		ArrayList<Room> rooms = new ArrayList<Room>();
		rooms = factory.getRoomFactoryList();
		//Player player;
		//Room room;
		//Game game;
		Room currentRoom;
		Room startingRoom;

		/*for(Room i : rooms)
		{
			System.out.println(i.getName());
			System.out.println(i.getDescription());
			System.out.println(i.getRoomPuzzle());
			System.out.println();
		}*/
		startingRoom = rooms.get(1);
		currentRoom = startingRoom;
		System.out.println(startingRoom.getName() + "\n" + startingRoom.getDescription());

		while (true)
		{
			Scanner scanner = new Scanner(System.in);
			String text = scanner.nextLine();
			System.out.println(scanner);

				if (currentRoom == rooms.get(1) && text.equalsIgnoreCase("east"))
				{
					currentRoom = rooms.get(2);
					System.out.println(currentRoom.getName() + "\n" + currentRoom.getDescription());
					roomID = currentRoom.getId();
				}

				else if (currentRoom == rooms.get(1) && text.equalsIgnoreCase("west"))
				{
					currentRoom = rooms.get(0);
					System.out.println(currentRoom.getName() + "\n" + currentRoom.getDescription());
					roomID = currentRoom.getId();
				}
				
				else if (currentRoom == rooms.get(0) && text.equalsIgnoreCase("east"))
				{
					currentRoom = rooms.get(1);
					System.out.println(currentRoom.getName() + "\n" + currentRoom.getDescription());
					roomID = currentRoom.getId();
				}
				
				else if (currentRoom == rooms.get(1) && text.equalsIgnoreCase("east"))
				{
					currentRoom = rooms.get(2);
					System.out.println(currentRoom.getName() + "\n" + currentRoom.getDescription());
					roomID = currentRoom.getId();
				}
				else if (currentRoom == rooms.get(2) && text.equalsIgnoreCase("west"))
				{
					currentRoom = rooms.get(1);
					System.out.println(currentRoom.getName() + "\n" + currentRoom.getDescription());
					roomID = currentRoom.getId();
				}
				else
					System.out.println("You bumped into the wall.You lost 1 HP");
			

			//System.out.println(rooms.get(8).getDescription());

				
			if(text.equalsIgnoreCase("look"))
			{
				//views currentroom description
				System.out.println("Room: [" + currentRoom.getName() + "]");
				System.out.println(currentRoom.getDescription());
			}
				
			if(text.equalsIgnoreCase("exits"))
			{
				//views currentroom exits
				System.out.println("Room: [" + currentRoom.getName() + "]");
				System.out.println(currentRoom.getExits());
			}
			
			if (text.equalsIgnoreCase("save"))
			{

				//use the class saveLoadData to save values in to binary file
				saveLoadData data = new saveLoadData();
				data.setRoomArrayNumber(roomID);
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

			if (text.equalsIgnoreCase("load"))
			{
				//use the class saveLoadData to load values in the binary file

				try
				{
					saveLoadData data = (saveLoadData) ResourceManager.loadGame("UndergroundHero.dat");
					currentRoom = rooms.get(data.getRoomArrayNumber());
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

		}
	}
}
