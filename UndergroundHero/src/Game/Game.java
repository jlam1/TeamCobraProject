package Game;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
//import org.apache.commons.lang3.text.WordUtils;
import java.util.concurrent.TimeUnit;

import Character.Monster;
import Character.Player;
import Generator.ItemGenerator;
import Item.Item;
import LogicController.BattleLogic;
import LogicController.MusicLogic;
import LogicController.PuzzleLogic;
import Puzzle.Puzzle;
import Room.Room;
import Room.RoomFactory;

/**
 * This class is responsible for parsing user's inputs and creating new game.
 *
 */
public class Game {
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

	public void endingMusic() {
		musicLogic.BGMStop();
		musicLogic = new MusicLogic("src/sound/ending.wav");
		musicLogic.BGMPlay();
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
		player.startingItem(itemList.get(4));
		player.startingEquip(0);
		player.startingEquip(0);
		currentRoom = factoryList.get(1);

		System.out.println("-------------------------------------------------------");
		System.out.println("[" + currentRoom.getName() + "]");
		System.out.println(currentRoom.getDescription());
		System.out.println("-------------------------------------------------------");
	}

	public void menuScreen() {
		displayIntro();
		// viewCommands();
		// viewHelp();
		boolean start = true;
		while (start) {
			menuMusic();
			System.out.println("");
			System.out.println("------------------------# MENU #-----------------------");
			System.out.println("                   1. Start New Game                   ");
			System.out.println("                   2. Load Saved Game                  ");
			System.out.println("                   3. Gameplay Guide                   ");
			System.out.println("                   4. Exit Game                        ");
			System.out.println("-------------------------------------------------------");
			System.out.println("");
			System.out.print(">>");
			String userInput = input.nextLine();

			try {
				if (userInput.equals("1")) {
					System.out.println("Starting a new game...");
					viewCommands();
					start = false;
					roomMusic();
					createNewGame();
					play();
				}
				else if (userInput.equals("2")) {
					System.out.println("Loading a saved state...");
					gameRun = true;
					start = false;
					load();
					roomMusic();
					play();
				}
				else if (userInput.equalsIgnoreCase("3")) {
					viewHelp();
					viewCommands();
				}
				else if (userInput.equals("4")) {
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

				}
			} else {
				System.out.println("\"The door is locked.\"");
				System.out.println("\"I need to find a way to unlock the door.\"");
			}
		}

		else {
			currentRoom = nextRoom;
			System.out.println("-------------------------------------------------------");
			System.out.println("[" + currentRoom.getName() + "]");
			System.out.println(currentRoom.getDescription());
			System.out.println("-------------------------------------------------------");
			iniMonster();
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

	/**
	 * @method Parses user's input's
	 * @param input
	 */
	private void parseCommand(String command) {

		if (validCommandInput(command)) {
			String navInput = command.toUpperCase();
			roomLogic(navInput);
		}

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
			viewCommands();
			break;

		case "VIEW COMMANDS":
			viewCommands();
			break;
			
		case "DESC":
			commandDescription();
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
		} catch (InputMismatchException e) 
		{
			System.out.println("You have put the bag away.");
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
		if (factoryList.get(currentRoom.getId()).getRoomItem() != null && factoryList.get(currentRoom.getId()).getId() != 21) {
			System.out.println("You spotted " + currentRoom.getRoomItem().getName() + " on the ground.");
		}
		if (factoryList.get(currentRoom.getId()).getRoomItem() != null && factoryList.get(currentRoom.getId()).getId() == 21) {
			System.out.println("You spotted " + currentRoom.getRoomItem().getName() + " inside of the ballistic glass.");
		}
		System.out.println("-------------------------------------------------------");
	}

	private void pick() {
		if (factoryList.get(currentRoom.getId()).getRoomItem() != null) 
		{
			System.out.println("You have picked " + currentRoom.getRoomItem().getName() + ".");
			player.pickUp(itemList.get(currentRoom.getRoomItem().getId()));
			factoryList.get(currentRoom.getId()).setRoomItem(null);
		}
		else {
			if(player.checkInventoryKeyItem(itemList.get(6))) {
				System.out.println("You open the case with the ballistic diamond cutter.");
				System.out.println("You have picked " + itemList.get(3) + ".");
				player.pickUp(itemList.get(3));
			}
			else {
				System.out.println("You do not have ballistic diamond cutter to cut this case.");
			}
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
		} catch (InputMismatchException e) 
		{
			System.out.println("You have exited the equip menu.");
		}
	}

	private void save() {
		// use the class saveLoadData to save values in to binary file
		ResourceData data = new ResourceData();
		data.setRoomArrayNumber(currentRoom.getId());
		System.out.println(currentRoom.getId());
		data.setPlayer(player);
		data.setFactoryList(factoryList);
		try {
			ResourceData.saveGame(data, "UndergroundHero.dat");
			System.out.println("Save Sucessful");
		} catch (Exception e) {
			System.out.println("error saving");
			e.printStackTrace();
		}

	}

	private void load() {
		// use the class saveLoadData to load values in the binary file
		try {
			ResourceData data = (ResourceData) ResourceData.loadGame("UndergroundHero.dat");
			currentRoom = factoryList.get(data.getRoomArrayNumber());
			player = data.getPlayer();
			factoryList = data.getFactoryList();
			System.out.print("LOADING ");
			print(".....\n", 300);
			System.out.println("Loading successful.");
			System.out.println();
			viewCommands();
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
				System.out.println("As the Pogo falls, the door to the east became visible.");
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
						System.out.println("You have avoided all the bullets and lasers and reached at the end of the room.");
						System.out.println("You spotted a red button and pushed.");
						try {
							System.out.println("");
							TimeUnit.SECONDS.sleep(1);
							System.out.println("COUNT DOWN COMMENCING!");
							TimeUnit.MILLISECONDS.sleep(500);
							System.out.println("10");
							TimeUnit.MILLISECONDS.sleep(500);
							System.out.println("9");
							TimeUnit.MILLISECONDS.sleep(500);
							System.out.println("8");
							TimeUnit.MILLISECONDS.sleep(500);
							System.out.println("7");
							TimeUnit.MILLISECONDS.sleep(500);
							System.out.println("6");
							TimeUnit.MILLISECONDS.sleep(500);
							System.out.println("5");
							TimeUnit.MILLISECONDS.sleep(500);
							System.out.println("4");
							TimeUnit.MILLISECONDS.sleep(500);
							System.out.println("3");
							TimeUnit.MILLISECONDS.sleep(500);
							System.out.println("2");
							TimeUnit.MILLISECONDS.sleep(500);
							System.out.println("1");
							TimeUnit.MILLISECONDS.sleep(500);
							System.out.println("0");
							TimeUnit.SECONDS.sleep(1);
							System.out.println("DEFENCE SYSTEM SHUTTING DOWN!");
							TimeUnit.SECONDS.sleep(1);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					break;
				case 4: factoryList.get(18).setLocked(false); 
						System.out.println("You hear the sound of a door unlocking.");
					break;
				case 5: factoryList.get(28).setLocked(false); 
						System.out.println("You sucessfully swinged across the room.");
				try {
					TimeUnit.SECONDS.sleep(1);
					System.out.println("As you landed, you encounter a giant robot");
				} catch (InterruptedException e) 
				{
				}
					break;
				case 6: factoryList.get(29).setLocked(false); 
						System.out.println("You went to the computer and input the chip");
						System.out.println("You hear the sound of a door unlocking.");
					break;
				case 7: factoryList.get(34).setLocked(false); 
						System.out.println("You hear the sound of a door unlocking.");
					break;
				case 8: factoryList.get(41).setLocked(false); 
						System.out.println("You hear the sound of a door unlocking.");
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
				"The inputs allowed for navigation are North, South, East, and West. Not all rooms will have four exits.");
		System.out.println("The player is allowed to revisit past rooms.\n");
		System.out.println("******************[ROOM]******************");
		System.out.println(
				"While inside a room the player can view the room number, room description, and existing exits by using the \"look\" command.");
		System.out.println("Rooms can contain either a puzzle or monster, and the game will prompt the player of their existence.\n");
		System.out.println("******************[PUZZLE]******************");
		System.out.println(
				"While in a puzzle, the player can view the puzzle or leave. Once viewed, the puzzle will display the description and prompt the user to solve it.\n");
		System.out.println("******************[INVENTORY]******************");
		System.out.println(
				"The player, while not in battle or a puzzle, is allowed to view their status, equipments, inventory, and are able to use items.\n");
		System.out.println("******************[BATTLE]******************");
		System.out.println(
				"During a battle, the player can \"attack\", \"use item\", \"defend\", \"run\", and \"view inventory\" using the following commands [1], [2], [3], [4], & [5] respectively.\n");
		System.out.println("\n\nTo view commands In-Game, type [VIEW COMMANDS]\n");
		System.out.println("-------------------------------------------------------");
	}

	/**
	 * @method Displays game commands
	 */
	private void viewCommands() {
		System.out.println("-------------------------------------------------------");
		System.out.println("The following commands are not case sensitive.\n");
		System.out.println("NAVIGATION:	[NORTH], [EAST], [SOUTH], [WEST]");
		System.out.println("ROOM:		[LOOK], [PICK]");
		System.out.println("INVENTORY:	[BAG], [EQUIP], [INFO], [USE], [VIEW]");
		System.out.println("BATTLE:		[1. ATTACK], [2. USE ITEM], [3. DEFEND], [4. FLEE], [5. VIEW INVENTORY]");
		System.out.println("SAVE/LOAD:	[SAVE], [LOAD]\n");
		System.out.println("To get a description of the commands type [DESC]");
		System.out.println("To exit the game, type [QUIT]");
		System.out.println("For \"In-Game\" help only, type [HELP]");
		System.out.println("-------------------------------------------------------");
	}

	private void commandDescription()
	{
		System.out.println("------------------[NAVIGATION]-------------------------");
		System.out.println("These commands can be use only during navigation phase.");
		System.out.println("-------------------------------------------------------");
		System.out.println("[NORTH] move to the room to the north.");
		System.out.println("[EAST]  move to the room to the east.");
		System.out.println("[SOUTH] move to the room to the south.");
		System.out.println("[WEST]  move to the room to the west.");
		System.out.println("---------------------------------------");
		System.out.println("[LOOK]  display the room description, exits, "
				         + "and item existing in the room but it will not "
				         + "show any hidden exits.");
		System.out.println("[PICK]  pick up the item in the room");
		System.out.println("-------------------------------------------------");
		System.out.println("[BAG]    display the items in the inventory");
		System.out.println("[EQUIP]  equip any equipable the items in the inventory");
		System.out.println("[INFO]   display the player current status");
		System.out.println("[USE]    use any consumeable items in the inventory");
		System.out.println("[VIEW]   look at the player current equipment");
		System.out.println("---------------------------------------------------");
		System.out.println("[SAVE]   save the current game.");
		System.out.println("[LOAD]   load the latest save file game.");
		System.out.println("---------------------------------------------------");
		System.out.println();
		System.out.println("----------------------[BATTLE]--------------------------");
		System.out.println("These commands can only be used during the battle phase.");
		System.out.println("--------------------------------------------------------");
		System.out.println("[1. ATTACK]           attack the present enemy");
		System.out.println("[2. USE ITEM]         use any consumeable items");
		System.out.println("[3. DEFEND]           defend from enemy attacks");
		System.out.println("[4. FLEE]             run from battle");
		System.out.println("[5. VIEW INVENTORY]   look at the player current items");
		System.out.println("--------------------------------------------------------");
		System.out.println();
	}
	/**
	 * @method Displays game intro
	 */
	private void displayIntro() {
		System.out.println("-------------------------------------------------------");
		System.out.println(
						  "O       o             o                                                      o       o      O                     \n"
						+ "o       O            O                                                      O        O      o                     \n"
						+ "O       o            o                                                      o        o      O                     \n"
						+ "o       o            o                                                      o        OoOooOOo                     \n"
						+ "o       O 'OoOo. .oOoO  .oOo. `OoOo. .oOoO `OoOo. .oOo.  O   o  'OoOo.  .oOoO        o      O  .oOo. `OoOo. .oOo. \n"
						+ "O       O  o   O o   O  OooO'  o     o   O  o     O   o  o   O   o   O  o   O        O      o  OooO'  o     O   o \n"
						+ "`o     O`  O   o O   o  O      O     O   o  O     o   O  O   o   O   o  O   o        o      o  O      O     o   O \n"
						+ " `OoooO`   o   O `OoO`  `OoO'  o     `OoOo  o     `OoO'  `OoO'o  o   O  `OoO'o       o      O  `OoO'  o     `OoO' \n"
						+ "                                         O                                                                        \n"
						+ "                                      OoO'                                                                        \n");
		System.out.println("You have infiltrated the lair of the infamous super villain, \"Joe-Ker\"."
				+ "\n\nYour only powers are your exceeding wit and skill in combat. "
				+ "\n\nThere are a total of 4 floors and 42 rooms filled with monsters and puzzles blocking your way. "
				+ "\n\nTraverse through all floors and beat \"Joe-Ker\" to win the game and SAVE THE WORLD!! "
				+ "\n\n\n\nNotice: This game has sound! Please unmute your speakers/headphones for the best experience!"
				+ "\nWe recommend resizing your command line window size to 128x64."
				+ "\n - For Windows users: Right-click the title bar and click 'Properties'"
				+ "\n - Click the 'Layout' tab and adjust 'Window Size'"
				+ "\n - You'll have to restart the game for the changes to take place");

	}

	private void displayEnding() {
		endingMusic();
		System.out.println("-------------------------------------------------------");
		System.out.println("You have beaten the game!");
		System.out.println("-------------------------------------------------------");
		try {
			System.out.println("");
			TimeUnit.SECONDS.sleep(1);
			System.out.println("==================== CREDITS ====================");
			TimeUnit.SECONDS.sleep(1);
			System.out.println("|                                               |");
			TimeUnit.SECONDS.sleep(1);
			System.out.println("|                                               |");
			TimeUnit.SECONDS.sleep(1);
			System.out.println("|------------- TEAM COBRA PROJECT --------------|");
			TimeUnit.SECONDS.sleep(1);
			System.out.println("|                                               |");
			TimeUnit.SECONDS.sleep(1);
			System.out.println("|                                               |");
			TimeUnit.SECONDS.sleep(1);
			System.out.println("|.................. John  Lam ..................|");
			TimeUnit.SECONDS.sleep(1);
			System.out.println("|                                               |");
			TimeUnit.SECONDS.sleep(1);
			System.out.println("|................... King Lo ...................|");
			TimeUnit.SECONDS.sleep(1);
			System.out.println("|                                               |");
			TimeUnit.SECONDS.sleep(1);
			System.out.println("|................ Kyle  Cousins ................|");
			TimeUnit.SECONDS.sleep(1);
			System.out.println("|                                               |");
			TimeUnit.SECONDS.sleep(1);
			System.out.println("|............ William 'Matt' Smith .............|");
			TimeUnit.SECONDS.sleep(1);
			System.out.println("|                                               |");
			TimeUnit.SECONDS.sleep(1);
			System.out.println("|                                               |");
			TimeUnit.SECONDS.sleep(1);
			System.out.println("============ THANK YOU FOR PLAYING! =============");
			TimeUnit.SECONDS.sleep(1);
			System.out.println("|                                               |");
			TimeUnit.SECONDS.sleep(1);
			System.out.println("==================== (C)2016 ====================");
			TimeUnit.SECONDS.sleep(21);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gameRun = false;
	}

	/**
	 * @method Prints a delayed string
	 * @param string
	 * @param delay
	 */
	private void print(String string, long delay) {
		try {
			for (char ch : string.toCharArray()) {
				System.out.print(ch);
				TimeUnit.MILLISECONDS.sleep(delay);
			}
		} catch (InterruptedException e) {
			System.out.println("InterruptedException: print()");
		}

	}
	/*private void checkSequence()
	{
		System.out.print("CHECKING INPUT");
		for(int i = 0; i < 3; i++)
		print("...", 300);
		System.out.println();
		System.out.println("INPUT IS CORRECT");
	}*/
	
	// private String wrapText(String longDescription){
	// String shortDesc = WordUtils.wrap(longDescription, 50);
	// return shortDesc;
	// }

}
