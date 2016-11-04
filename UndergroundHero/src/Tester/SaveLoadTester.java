package Tester;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
//import org.apache.commons.lang3.text.WordUtils;

import Character.Player;
import Game.ResourceManager;
import Game.saveLoadData;
import Generator.ItemGenerator;
import Item.Item;
import LogicController.PuzzleLogic;
import Room.Room;
import Room.RoomFactory;

public class SaveLoadTester {

	static Room currentRoom, lockedRoom;
	static List<Room> factoryList;
	static List<Item> itemList;
	static Player player;
	static Scanner input;
	static int bagIndex;
	static String roomDescription;
	static List<Room> lockedRoomList;
	static PuzzleLogic puzzleLogic;

	static void save() {
		// use the class saveLoadData to save values in to binary file
		saveLoadData data = new saveLoadData();
		data.setRoomArrayNumber(currentRoom.getId());
		System.out.println(currentRoom.getId());
		data.setPlayer(player);
		data.setFactoryList(factoryList);
		
		try {
			ResourceManager.saveGame(data, "UndergroundHero.dat");

			System.out.println("Save Sucessful");
		} catch (Exception e) {
			System.out.println("error saving");
			e.printStackTrace();
		}

	}

	static void load() {
		// use the class saveLoadData to load values in the binary file
		try {
			saveLoadData data = (saveLoadData) ResourceManager.loadGame("UndergroundHero.dat");
			currentRoom = factoryList.get(data.getRoomArrayNumber());
			player = data.getPlayer();
			factoryList = data.getFactoryList();
			System.out.println("Load Sucessful");
			System.out.println();
			System.out.println(currentRoom.getName() + "\n" + currentRoom.getDescription());

		} catch (Exception e) {
			System.out.println("error loading");
			e.printStackTrace();
		}
	}

	static void roomLogic(String direction) {
		Room nextRoom = currentRoom.nextRoom(direction);

		if (nextRoom == null) {
			System.out.println("Theres no exit that way, try another direction.");
		}

		else if (factoryList.get(nextRoom.getId()).isLocked() == true) {
			lockedRoom = nextRoom;
			if(nextRoom.getRoomPuzzle() != null && factoryList.get(nextRoom.getId()).getRoomPuzzle().isSolved() == false)
			{
			//	if()
				puzzleLogic.initiatePuzzle(lockedRoom, player);
			}
			else
			{
			System.out.println("[" + nextRoom.getName() + "] door is locked.");
			System.out.println("Try a different route.");
			}
		}

		else {
			currentRoom = nextRoom;
			roomDescription = currentRoom.getDescription();
			System.out.println("-------------------------------------------------------");
			System.out.println("[" + currentRoom.getName() + "]");
			System.out.println(roomDescription);
			System.out.println("[" + currentRoom.getExits() + "]");
			System.out.println("-------------------------------------------------------");

			// if(nextRoom.getRoomMonster() != null) {
			//
			// //TODO: Fight monster
			//
			// if(nextRoom.getRoomPuzzle() != null) {
			// puzzleLogic.initiatePuzzle(nextRoom, player, input);
			// }
			// }
			if (nextRoom.getRoomPuzzle() != null) {
				//
				if (factoryList.get(currentRoom.getId()).getRoomPuzzle().isSolved() == false) {
					puzzleLogic.initiatePuzzle(currentRoom, player);
				}
				// factoryList.get(currentRoom.getId()).getRoomPuzzle().setSolved(true);

				// System.out.println( "Testing -----------"+
				// factoryList.get(currentRoom.getId()).getRoomPuzzle());
			}

			else {
				// do nothing
			}

		}
	}

	static void useItem() {
		try {
			player.openInventory();
			System.out.println("Which item do you want to use? (Choose a number)");
			System.out.print(">>");
			bagIndex = input.nextInt();
			player.useItem(bagIndex);
			input.nextLine();
		} catch (InputMismatchException e) {
			System.out.println("Wrong use item command.\n");
		}
	}

	static void equip() {
		try {
			player.openInventory();
			System.out.println("What item do you want to equip? (Choose a number)");
			System.out.print(">>");
			bagIndex = input.nextInt();
			player.equip(bagIndex);
			input.nextLine();
		} catch (InputMismatchException e) {
			System.out.println("Wrong equip command.\n");
		}
	}

	static boolean validCommandInput(String input) {

		if (input.equalsIgnoreCase("EAST") || input.equalsIgnoreCase("WEST") || input.equalsIgnoreCase("NORTH")
				|| input.equalsIgnoreCase("SOUTH")) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) {

		player = new Player(10, 10, 3, 2, 3);

		factoryList = new RoomFactory().getRoomFactoryList();
		itemList = new ItemGenerator().getItemList();
		puzzleLogic = new PuzzleLogic();

		// add default items to player
		player.startingItem(itemList.get(10));
		player.startingItem(itemList.get(0));
		player.startingItem(itemList.get(2));

		// Game game = new Game(new RoomFactory().getRoomFactoryList(), new
		// Player(10, 1, 2, 1));
		play();

	}

	static void play() {
		input = new Scanner(System.in);
		String command;
		boolean gameRun = true;
		currentRoom = factoryList.get(1);
		roomDescription = currentRoom.getDescription();

		System.out.println("Welcome to Underground Hero");

		while (gameRun) {
			System.out.print(">>");
			command = input.nextLine();
			System.out.println("--");

			if (validCommandInput(command)) {
				roomLogic(command);
			}

			else if (command.equalsIgnoreCase("LOOK")) {
				System.out.println("-------------------------------------------------------");
				System.out.println("[" + currentRoom.getName() + "]");
				System.out.println(roomDescription);
				System.out.println("[" + currentRoom.getExits() + "]");
				System.out.println("-------------------------------------------------------");
			}

			else if (command.equalsIgnoreCase("EXITS")) {
				System.out.println(currentRoom.getExits());
			}

			else if (command.equalsIgnoreCase("BAG")) {
				player.openInventory();
			}

			else if (command.equalsIgnoreCase("EQUIP")) {
				equip();
			}

			else if (command.equalsIgnoreCase("VIEW")) {
				player.viewEquipment();
			}

			else if (command.equalsIgnoreCase("USE")) {
				useItem();
			}

			else if (command.equalsIgnoreCase("INFO")) {
				System.out.println(player.toString());
			}

			else if (command.equalsIgnoreCase("SAVE")) {
				save();
			}

			else if (command.equalsIgnoreCase("LOAD")) {
				load();
			}

			else if (command.equalsIgnoreCase("QUIT")) {
				gameRun = false;
			}

			else if (command.equalsIgnoreCase("HELP")) {
				System.out.println(
						"NAVIGATION: \nNorth \nSouth \nEast \nWest \n\nROOM: \nLook \n\nCombat: \nAttack \nDefend \nRun [Direction] ");
			}

			// testing lock logic
			else if (command.equalsIgnoreCase("UNLOCK")) {

				factoryList.get(lockedRoom.getId()).setLocked(false);

				System.out.println("Locked: " + lockedRoom.isLocked());

				System.out.print(factoryList.get(lockedRoom.getId()).getId() + " "
						+ factoryList.get(lockedRoom.getId()).isLocked());

				if (factoryList.get(lockedRoom.getId()).isLocked() == false) {
					currentRoom = lockedRoom;
					// factoryList.get(currentRoom.getId()).setLocked(false);
					System.out.println();
					System.out.println("[" + currentRoom.getName() + "]");
					System.out.println(roomDescription);
					System.out.println("[" + currentRoom.getExits() + "]");
				} else {
					System.out.println("[" + lockedRoom.getName() + "] door is locked.");
					System.out.println("Try a different route.");
				}
			}

			else {
				System.out.println("Invalid Command");
			}

		}
	}

	// static String wrapText(String longDescription){
	// String shortDesc = WordUtils.wrap(longDescription, 50);
	// return shortDesc;
	// }

}
