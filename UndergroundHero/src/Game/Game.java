package Game;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
//import org.apache.commons.lang3.text.WordUtils;
import java.util.concurrent.TimeUnit;

import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;
import Character.Monster;
import Character.Player;
import Generator.ItemGenerator;
import Item.Item;
import LogicController.BattleLogic;
import LogicController.PuzzleLogic;
import LogicController.MusicLogic;
import Puzzle.Puzzle;
import Room.Room;
import Room.RoomFactory;

/**
 * This class is responsible for parsing user's inputs and creating new game.
 *
 */
public class Game {
	// AudioStream roomMusic;
	// AudioPlayer musicPlayer = AudioPlayer.player;

	private List<Room> factoryList;
	private List<Item> itemList;
	private Room nextRoom, currentRoom, lockedRoom;
	private Player player;
	private PuzzleLogic puzzleLogic;
	private BattleLogic battleLogic;
	private MusicLogic musicLogic;
	private Scanner input;
	private int bagIndex;
	private boolean gameRun;

	/**
	 * @return
	 * @method Load all assets to game object
	 */

	public void roomMusic() {
		musicLogic.BGMStop();
		musicLogic = new MusicLogic("src/sound/traverse.wav");
		musicLogic.BGMLoop();
	}

	public void menuMusic() {
		musicLogic.BGMStop();
		musicLogic = new MusicLogic("src/sound/menu.wav");
		musicLogic.BGMLoop();
	}

	public Game() {
		input = new Scanner(System.in);
		factoryList = new RoomFactory().getRoomFactoryList();
		itemList = new ItemGenerator().getItemList();
		puzzleLogic = new PuzzleLogic(input);
		battleLogic = new BattleLogic(input);
		musicLogic = new MusicLogic("src/sound/traverse.wav");
	}

	/**
	 * @method Loads default room and player for new game state.
	 */

	private void createNewGame() {
		gameRun = true;
		player = new Player(100, 100, 1, 3, 2);
		player.setName("HERO");
		player.startingItem(itemList.get(0));
		player.startingItem(itemList.get(2));
		player.startingItem(itemList.get(10));
		player.startingItem(itemList.get(4));
		currentRoom = factoryList.get(1);

		System.out.println("-------------------------------------------------------");
		System.out.println("[" + currentRoom.getName() + "]");
		System.out.println(currentRoom.getDescription());
		// System.out.println("[" + currentRoom.getExits() + "]");
		System.out.println("-------------------------------------------------------");
	}

	public void menuScreen() {
		displayIntro();
		viewCommands();
		viewHelp();
		boolean start = true;
		while (start) {
			menuMusic();
			System.out.println("");
			System.out.println("------------------------# MENU #-----------------------");
			System.out.println("                   1. Start New Game                   ");
			System.out.println("                   2. Load Saved Game                  ");
			System.out.println("                   3. Exit Game                        ");
			System.out.println("-------------------------------------------------------");
			System.out.println("");
			System.out.print(">>");
			String userInput = input.nextLine();

			try {
				if (userInput.equals("1")) {
					System.out.println("Starting a new game...");
					start = false;
					roomMusic();
					createNewGame();
					play();
				}
				if (userInput.equals("2")) {
					System.out.println("Loading a saved state...");
					gameRun = true;
					start = false;
					load();
					roomMusic();
					play();
				}
				if (userInput.equals("3")) {
					System.out.println("Exiting game...");
					start = false;
					musicLogic.BGMStop();
					System.exit(0);
				} else {
					System.out.println("Invalid Input.");
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid Input, please try again.");
			}
		}
	}

	/**
	 * @method Main method for initiating game
	 */
	private void play() {
		String userInput;

		boolean menuScreen = true;

		do {

			while (gameRun) {
				System.out.print(">>");
				userInput = input.nextLine();
				parseCommand(userInput);
			}

			menuScreen();

		} while (menuScreen);

	}

	/**
	 * @method Responsible for checking valid exits, monsters, and puzzles in
	 *         the current room.
	 * @param direction
	 */
	private void roomLogic(String direction) {
		nextRoom = currentRoom.getNextRoom(direction);
		lockedRoom = nextRoom;

		if (nextRoom == null) {
			System.out.println("Theres no exit that way, try another direction.");
		}

		else if (factoryList.get(nextRoom.getId()).isLocked()) {
			if (factoryList.get(currentRoom.getId()).getId() == 19) {
				System.out.println("Theres no exit that way, try another direction.");
			}
			// Initiate puzzle when approaching a lock room
			else if (lockedRoom.getRoomPuzzle() != null) {
				puzzleLogic.initiatePuzzle(lockedRoom, player);
				if (puzzleLogic.getPuzzleSolved()) {
					checkRoomPuzzleLocks(nextRoom.getRoomPuzzle());
					System.out.println("-------------------------------------------------------");
					System.out.println("[" + nextRoom.getName() + "]");
					System.out.println(nextRoom.getDescription());
					System.out.println("-------------------------------------------------------");
					
				}
				if (puzzleLogic.getPuzzleSolved()) {
					factoryList.get(nextRoom.getId()).getRoomPuzzle().setSolved(true);
					currentRoom = lockedRoom;
					iniMonster();
					checkFled();

				}
			} else {
				System.out.println("[" + nextRoom.getName() + "] door is locked.");
				System.out.println("Try a different route.");
			}
		}

		else {
			currentRoom = nextRoom;
			System.out.println("-------------------------------------------------------");
			System.out.println("[" + currentRoom.getName() + "]");
			System.out.println(currentRoom.getDescription());
			System.out.println("-------------------------------------------------------");
			iniMonster();
			checkFled();
			iniPuzzle();
		}
	}

	public void iniMonster() {
		Monster monster = nextRoom.getRoomMonster();
		// check if roomMonster exists and if not dead
		if (monster != null && !factoryList.get(nextRoom.getId()).getRoomMonster().isDead()) {

			// if boss, spawnrate is 100%
			if (monster.isBoss()) {
				battleLogic.initiateBattle(player, monster);
			}

			// check player dead
			if (battleLogic.getWhoseDead() == 0) {
				gameRun = false;
			}

			// check monster dead and set monster dead if boss
			if (battleLogic.getWhoseDead() == 1) {
				if (monster.isBoss())
					factoryList.get(nextRoom.getId()).getRoomMonster().setDead(true);

				if (monster.getId() == 5) {
					displayEnding();
				}
			}

			checkRoomMonsterLocks(monster);

		}

		if (monster != null) {
			//
			monster.setHp(monster.getMaxhp());

			// if common monster, set spawn rate
			if (!monster.isBoss()) {
				double chance = (Math.random() * 100);
				final double SPAWN_RATE = 25.0;

				if (chance < SPAWN_RATE) {
					battleLogic.initiateBattle(player, monster);
				}
			}

			// check player dead
			if (battleLogic.getWhoseDead() == 0) {
				gameRun = false;
			}
		}
		
	}

	public void iniPuzzle() {
		Puzzle puzzle = nextRoom.getRoomPuzzle();
		if (puzzle != null && !factoryList.get(nextRoom.getId()).getRoomPuzzle().isSolved()) {
			puzzleLogic.initiatePuzzle(currentRoom, player);

			if (puzzleLogic.getPuzzleSolved()) {
				checkRoomPuzzleLocks(nextRoom.getRoomPuzzle());
			}

			if (puzzleLogic.getPuzzleSolved()) {
				factoryList.get(nextRoom.getId()).getRoomPuzzle().setSolved(true);
			}
		}
	}
	
	private void checkFled() {
		if(battleLogic.playerFled()) {
			
//			roomLogic("EAST");
//			
//			if(currentRoom.getNextRoom("NORTH") != null) {
//				roomLogic("NORTH");
//			}
//			else if(currentRoom.getNextRoom("EAST") != null) {
//				roomLogic("EAST");
//			}
//			else if(currentRoom.getNextRoom("SOUTH") != null) {
//				roomLogic("SOUTH");
//			}
//			else if(currentRoom.getNextRoom("WEST") != null) {
//				roomLogic("WEST");
//			}
//			else {
//				//do nothing
//			}
		}
	}

	/**
	 * @method Parses user's input's
	 * @param input
	 */
	private void parseCommand(String command) {

		if (validCommandInput(command))
			roomLogic(command);

		switch (command.toUpperCase()) {

		case "LOOK":
			look();
			break;

		case "BAG":
			player.openInventory();
			break;

		case "VIEW":
			player.viewEquipment();
			break;

		case "EQUIP":
			equip();
			break;

		case "INFO":
			System.out.println(player.toString());
			break;

		case "USE":
			useItem();
			break;

		case "PICK":
			pick();
			break;

		case "HELP":
			viewHelp();
			break;

		case "VIEW COMMANDS":
			viewCommands();
			break;

		case "QUIT":
			gameRun = false;
			break;

		case "SAVE":
			save();

			break;
		case "LOAD":
			load();
			break;

		default:
			break;

		}
	}

	/**
	 * @method Prompts user to use item and removes item from inventory.
	 */
	private void useItem() {
		try {
			player.openInventory();
			System.out.println("Which item do you want to use? (Choose a number)");
			System.out.print(">>");
			bagIndex = input.nextInt();
			player.useItem(bagIndex);
			input.nextLine();
		} catch (InputMismatchException e) {

		}
	}

	/**
	 * @method Displays current room's name, description, and exits.
	 */
	private void look() {
		System.out.println("-------------------------------------------------------");
		System.out.println("[" + currentRoom.getName() + "]");
		System.out.println(currentRoom.getDescription());
		System.out.println("[" + currentRoom.getExits() + "]");
		if (factoryList.get(currentRoom.getId()).getRoomItem() != null) {
			System.out.println("You spotted " + currentRoom.getRoomItem().getName() + " on the ground.");

		}
		System.out.println("-------------------------------------------------------");
	}

	private void pick() {
		if (factoryList.get(currentRoom.getId()).getRoomItem() != null) {
			System.out.println("You have picked " + currentRoom.getRoomItem().getName() + ".");
			player.pickUp(itemList.get(currentRoom.getRoomItem().getId()));
			factoryList.get(currentRoom.getId()).setRoomItem(null);
		}
	}

	/**
	 * @method Prompts user to equip item. If so, remove equipment from
	 *         inventory to player and increase/decrease stats.
	 */
	private void equip() {
		try {
			player.openInventory();
			System.out.println("What item do you want to equip? (Choose a number)");
			System.out.print(">>");
			bagIndex = input.nextInt();
			player.equip(bagIndex);
			input.nextLine();
		} catch (InputMismatchException e) {

		}
	}

	private void save() {
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

	private void load() {
		// use the class saveLoadData to load values in the binary file
		try {
			saveLoadData data = (saveLoadData) ResourceManager.loadGame("UndergroundHero.dat");
			currentRoom = factoryList.get(data.getRoomArrayNumber());
			player = data.getPlayer();
			factoryList = data.getFactoryList();
			System.out.print("LOADING ");
			print(".....\n", 300);
			System.out.println("Loading successful.");
			System.out.println();
			look();

		} catch (Exception e) {
			System.out.println("Error loading");
			e.printStackTrace();
		}
	}

	/**
	 * @method checks for valid navigation command
	 * @param input
	 * @return boolean
	 */
	private boolean validCommandInput(String input) {
		if (input.equalsIgnoreCase("EAST") || input.equalsIgnoreCase("WEST") || input.equalsIgnoreCase("NORTH")
				|| input.equalsIgnoreCase("SOUTH"))
			return true;
		return false;
	}

	/**
	 * @method Opens room lock depending on monster's death.
	 * @param monster
	 */
	private void checkRoomMonsterLocks(Monster monster) {

		if (monster.isDead() == true) {

			switch (monster.getId()) {

			case 0:
				factoryList.get(10).setLocked(false);
				System.out.println("Room: [" + factoryList.get(10).getName() + "] is now unlocked!");
				break;
			case 1:
				factoryList.get(19).setLocked(false);
				System.out.println("Room: [" + factoryList.get(19).getName() + "] is now unlocked!");
				break;
			case 4:
				factoryList.get(41).setLocked(false);
				System.out.println("Room: [" + factoryList.get(41).getName() + "] is now unlocked!");
				break;
			default:
				break;

			}
		}
	}

	/**
	 * @method Opens room lock depending on puzzle solved.
	 * @param puzzle
	 */
	private void checkRoomPuzzleLocks(Puzzle puzzle) {
		
		if(puzzle.isSolved() == true) {
			
			switch(puzzle.getId()) {
				case 3: factoryList.get(14).setLocked(false);
						System.out.println("Room: [" + factoryList.get(14).getName() + "] is now unlocked!");
					break;
				case 4: factoryList.get(18).setLocked(false); 
						System.out.println("Room: [" + factoryList.get(18).getName() + "] is now unlocked!");
					break;
				case 5: factoryList.get(28).setLocked(false); 
						System.out.println("Room: [" + factoryList.get(28).getName() + "] is now unlocked!");
					break;
				case 6: factoryList.get(29).setLocked(false); 
						System.out.println("Room: [" + factoryList.get(29).getName() + "] is now unlocked!");
					break;
				case 7: factoryList.get(34).setLocked(false); 
						System.out.println("Room: [" + factoryList.get(34).getName() + "] is now unlocked!");
					break;
				case 8: factoryList.get(41).setLocked(false); 
						System.out.println("Room: [" + factoryList.get(41).getName() + "] is now unlocked!");
					break;
				default:
					break;
			}
		}
	}

	/**
	 * @method Displays further information about what the user will be
	 *         interacting in the game.
	 */
	private void viewHelp() {
		System.out.println("-------------------------------------------------------\n");
		System.out.println("******************[NAGIVATION]******************");
		System.out.println(
				"To navigate, the inputs allowed are north, east, west, and south. Not all rooms will have exits.");
		System.out.println("The player is allowed to revisit past rooms.\n");
		System.out.println("******************[ROOM]******************");
		System.out.println(
				"While inside a room the player is allow to view the room number, descriptions, and existing exits by the look command.");
		System.out.println("Rooms can contain a either a puzzle or monster will prompt the player of its existence.\n");
		System.out.println("******************[PUZZLE]******************");
		System.out.println(
				"While in a puzzle, the player can view the puzzle or leave. Once viewed the puzzle will display the description and prompt the user to solve it.\n");
		System.out.println("******************[INVENTORY]******************");
		System.out.println(
				"The player, while not in battle or a puzzle, is allowed to view their status, equipments, inventory, and able to use items.\n");
		System.out.println("******************[BATTLE]******************");
		System.out.println(
				"During a battle, the player can attack, defend, use item, and run with the following commands [1], [2], [3], [4].\n");
		System.out.println("To view the following commands, type [VIEW COMMANDS]\n");
		System.out.println("-------------------------------------------------------");
	}

	/**
	 * @method Displays game commands
	 */
	private void viewCommands() {
		System.out.println("-------------------------------------------------------");
		System.out.println("The following commands are not case sensitive.\n");
		System.out.println("NAVIGATION:	[NORTH], [EAST], [SOUTH], [WEST]");
		System.out.println("ROOM:		[LOOK]");
		System.out.println("INVENTORY:	[BAG], [EQUIP], [INFO], [USE]");
		System.out.println("PUZZLE:		[VIEW], [LEAVE]");
		System.out.println("BATTLE:		[1. ATTACK], [2. DEFEND], [3. FLEE], [4. USE ITEM]");
		System.out.println("SAVE/LOAD:	[SAVE], [LOAD]\n");
		System.out.println("To exit the game, type [QUIT]");
		System.out.println("Type [HELP] for more information.");
		System.out.println("-------------------------------------------------------");
	}

	/**
	 * @method Displays game intro
	 */
	private void displayIntro() {
		System.out.println("-------------------------------------------------------");
		System.out.println("Welcome to Underground Hero");
		System.out.println(
				"The game is about a person without any superpowers but is a clever and good fighter. He has infiltrated a super villain lair to stop an apocalypse from happening. There is a total of 4 floors and 42 rooms, traverse through all floors and beat the final boss to win the game!");
		System.out.println(
				"Notice: This game has sound! Please unmute your speakers/headphones for the best experience!");
	}

	private void displayEnding() {
		System.out.println("-------------------------------------------------------");
		System.out.println("You have beaten the game!");
		System.out.println("-------------------------------------------------------");
		gameRun = false;
	}

	/**
	 * @method Prints a delayed string
	 * @param string
	 * @param delay
	 */

	public void print(String string, long delay) {
		try {
			for (char ch : string.toCharArray()) {
				System.out.print(ch);
				TimeUnit.MILLISECONDS.sleep(delay);
			}
		} catch (InterruptedException e) {
			System.out.println("InterruptedException: print()");
		}

	}

	// private String wrapText(String longDescription){
	// String shortDesc = WordUtils.wrap(longDescription, 50);
	// return shortDesc;
	// }

}
