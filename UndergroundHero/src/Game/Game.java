package Game;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
//import org.apache.commons.lang3.text.WordUtils;
import java.util.concurrent.TimeUnit;

import Character.Monster;
import Character.Player;
import Item.Item;
import Item.ItemGenerator;
import LogicController.BattleLogic;
import LogicController.MusicLogic;
import LogicController.PuzzleLogic;
import Puzzle.Puzzle;
import Room.Room;
import Room.RoomFactory;

/**
 * This class is responsible for parsing user's inputs and creating new game.
 * 
 * @author John, King, Kyle, Matt
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
	private Function f;
	private int bagIndex;
	private boolean gameRun;

	public Game() {
		input = new Scanner(System.in);
		resetGame();
		f = new Function();
		puzzleLogic = new PuzzleLogic(input);
		battleLogic = new BattleLogic(input);
		musicLogic = new MusicLogic("src/sound/menu.wav");
	}

	private void resetGame() {
		factoryList = new RoomFactory().getRoomFactoryList();
		itemList = new ItemGenerator().getItemList();
	}

	/**
	 * @method Loads default room and player for new game state.
	 */

	private void createNewGame() {
		gameRun = true;
		resetGame();
		player = new Player(15, 15, 3, 2, 2);
		player.setName("HERO");
		player.pickUp(itemList.get(0));
		player.pickUp(itemList.get(2));
		player.pickUp(itemList.get(4));

		player.startingEquip(0);
		player.startingEquip(0);
		currentRoom = factoryList.get(1);
		floorMusicChecker(currentRoom);

		f.printBox("######################### ROOM " + currentRoom.getName() + " #############################");
		System.out.println(currentRoom.getDescription() + "\n");
	}

	public void menuScreen() {
		displayIntro();

		boolean start = true;
		while (start) {
			menuMusic();
			System.out.println("");
			f.printBox("######################## MENU #########################");
			System.out.println("|                 [1] New Game                            |");
			System.out.println("|                 [2] Load Game                           |");
			System.out.println("|                 [3] Gameplay Guide                      |");
			System.out.println("|                 [4] Exit Game                           |");
			f.printBox("#######################################################");
			System.out.println("");
			System.out.print(">> ");
			String userInput = input.nextLine();

			try {
				if (userInput.equals("1")) {
					System.out.print("<< CREATING NEW GAME ");
					f.print("....", 300);
					System.out.println("\n\n");
					f.printBox("############## Type 'HELP' for COMMANDS ##############");
					System.out.println("\n");
					start = false;
					createNewGame();
				//	floor1Music();
					play();
				} else if (userInput.equals("2")) {
					gameRun = true;
					start = false;
					load();
				//	floor1Music();
					play();
				} else if (userInput.equalsIgnoreCase("3")) {
					f.delay(500);
					commandDescription();
				} else if (userInput.equals("4")) {
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
				System.out.println();
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
			f.delay(500);
			// look();
		} else if (nextRoom.getId() == 0) {
			currentRoom = nextRoom;
			floorMusicChecker(currentRoom);
			f.printBox("######################### ROOM " + currentRoom.getName() + " #############################");
			System.out.println(currentRoom.getDescription() + "\n");
			try {
				TimeUnit.SECONDS.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			gameRun = false;

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
				}
				if (puzzleLogic.getPuzzleSolved()) {
					factoryList.get(lockedRoom.getId()).getRoomPuzzle().setSolved(true);
					currentRoom = nextRoom;
					f.printBox("#################### ROOM " + currentRoom.getName() + " ########################");
					System.out.println(currentRoom.getDescription() + "\n");
					iniMonster();
				}
			} else {
				System.out.println("\"The door is locked.\"");
				System.out.println("\"I need to find a way to unlock the door.\"");
				f.delay(500);
				// look();
			}
		}

		else {
			currentRoom = nextRoom;
			floorMusicChecker(currentRoom);
			f.printBox("######################### ROOM " + currentRoom.getName() + " #############################");
			System.out.println(currentRoom.getDescription() + "\n");
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
				battleLogic.checkFloorMusic(currentRoom);
			}

			// check player dead
			if (battleLogic.getWhoseDead() == 0) {
				gameRun = false;
			}

			// check monster dead and set monster dead if boss
			if (battleLogic.getWhoseDead() == 1) {
				if (monster.isBoss())
					factoryList.get(nextRoom.getId()).getRoomMonster().setDead(true);

				if (monster.getId() == 2)// giant bull shark
				{
					System.out.println("The shark swam very quickly bashing itself on to the walls."
							+ "\nIt swan deep into the lake. As you waited, the lifeless body"
							+ "\nsurfaced and you pulled the shark out of the water.");
				}
				if (monster.getId() == 3) // robo boss
				{
					try {
						f.print("E...ER...ERR....OR....", 200);
						f.print("E...ER...ERR....OR", 200);
						System.out.println();
						f.print("S...SYS..T...EM...MAL....F..U...NC..TION....ING!", 200);
						System.out.println();
						f.print("R...RE...STA.....R....TING.....SE....QU....QUEN...CE....IN...INIT..I..IATED", 200);
						System.out.println();
						TimeUnit.MILLISECONDS.sleep(250);
						System.out.println("10");
						TimeUnit.MILLISECONDS.sleep(250);
						System.out.println("9");
						TimeUnit.MILLISECONDS.sleep(250);
						System.out.println("8");
						f.print("R....RES...TA..R....T...F..FA....AIL...", 200);
						System.out.println();
						f.print("S...SE..ELF...FDEST...TRU.....UCT...I..NI...TI.A....TE...D", 200);
						f.print("E.ER..ERR...OR....", 200);
						f.print("E..ER...ERR..OR", 200);
						System.out.println();
						f.print("S...SYS..T...EM...SH...UU..T....IN...NG..... DOOWWWNNNN!", 100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (monster.getId() == 5) // Joe Ker
				{
					try {
						System.out.println("You sent him flying off of the stage as he pummeled ");
						TimeUnit.SECONDS.sleep(3);
						System.out.println("onto the ground. He lied there laughing,");
						TimeUnit.SECONDS.sleep(3);
						System.out.println("\"HAHAHAHAAHAA! DID YOU THINK IT WOULD BE THAT EASY TO STOP ME?\"" );
						TimeUnit.SECONDS.sleep(3);
						System.out.println("He pulled out a device,\"With a push of a button ");
						TimeUnit.SECONDS.sleep(3);
						System.out.println("the world will burn.\" You quickly grabbed the Grappling hook");
						TimeUnit.SECONDS.sleep(3);
						System.out.println("and shot towards his hand and pulled the device into you position,");
						TimeUnit.SECONDS.sleep(3);
						System.out.println("\"Not on my watch, you won't destroy anything after I'm done with you.\" ");
						TimeUnit.SECONDS.sleep(3);
						System.out.println("Joe laughed, \"C'mon, give it back to me and let's see the world ");
						TimeUnit.SECONDS.sleep(3);
						System.out.println("burn together.\" \"No thank you.\" as you slowly walked ");
						TimeUnit.SECONDS.sleep(3);
						System.out.println("toward him, but the door opened behind and Quinn appeared with");
						TimeUnit.SECONDS.sleep(3);
						System.out.println("a machine gun and blasted towards you. You ran for cover but ");
						TimeUnit.SECONDS.sleep(3);
						System.out.println("as the shooting stops, Joe said,\"Until the next time we meet,");
						TimeUnit.SECONDS.sleep(3);
						System.out.println("I will drown you with madness.\" As you jumped out of hiding,");
						TimeUnit.SECONDS.sleep(3);
						System.out.println("they are nowhere to be seen.");
						TimeUnit.SECONDS.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}									
					displayEnding();
				} 
			}

			checkRoomMonsterLocks(monster);
			// look();
		}

		if (monster != null) {
			monster.setHp(monster.getMaxhp());

			// if common monster, set spawn rate
			if (!monster.isBoss()) {
				monster.setDead(false);
				double chance = (Math.random() * 100);
				final double SPAWN_RATE = 25.0;

				if (chance < SPAWN_RATE) {
					battleLogic.initiateBattle(player, monster);
					//battleLogic.checkFloorMusic(currentRoom);
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
			puzzleLogic.checkFloorMusic(currentRoom);

			if (puzzleLogic.getPuzzleSolved()) {
				checkRoomPuzzleLocks(nextRoom.getRoomPuzzle());
				// look();
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
			f.delay(300);
			player.openInventory();
			break;

		case "VIEW":
			f.delay(300);
			player.viewEquipment();
			break;

		case "EQUIP":
			f.delay(300);
			equip();
			break;

		case "INFO":
			f.delay(300);
			System.out.println(player.toString());
			break;

		case "USE":
			f.delay(300);
			useItem();
			break;

		case "PICK":
			pick();
			break;

		case "HELP":
			f.delay(300);
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
			System.out.print(">> ");
			bagIndex = input.nextInt();
			player.useItem(bagIndex);
			input.nextLine();
		} catch (InputMismatchException e) {
			System.out.println("You have put the bag away.");
		}
	}

	/**
	 * @method Displays current room's name, description, and exits.
	 */
	private void look() {
		System.out.println();
		f.printBox("######################### ROOM " + currentRoom.getName() + " ############################");
		System.out.println(currentRoom.getDescription());
		System.out.println("[" + currentRoom.getExits() + "]");
		if (factoryList.get(currentRoom.getId()).getRoomItem() != null
				&& factoryList.get(currentRoom.getId()).getId() != 21) {
			System.out.println("You spotted " + currentRoom.getRoomItem().getName() + " on the ground.");
		}
		if (factoryList.get(currentRoom.getId()).getRoomItem() != null
				&& factoryList.get(currentRoom.getId()).getId() == 21) {
			System.out.println("You spotted " + currentRoom.getRoomItem().getName() + " inside of the ballistic glass.");
		}
	}

	private void pick() {
		if (factoryList.get(currentRoom.getId()).getRoomItem() != null 
				&& factoryList.get(currentRoom.getId()).getId() != 21) {
			System.out.println("You have picked " + currentRoom.getRoomItem().getName() + ".");
			player.pickUp(itemList.get(currentRoom.getRoomItem().getId()));
			factoryList.get(currentRoom.getId()).setRoomItem(null);
		} else {
			if (player.checkInventoryKeyItem(itemList.get(6))
					&& factoryList.get(currentRoom.getId()).getRoomItem() != null) {
				System.out.println("You open the case with the ballistic diamond cutter.");
				System.out.println("You have picked " + itemList.get(3) + ".");
				player.pickUp(itemList.get(3));
			} else {
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
		} catch (InputMismatchException e) {
			System.out.println("You have exited the equip menu.");
		}
	}

	private void save() {
		// use the class saveLoadData to save values in to binary file
		System.out.println("Which slot would you like to save?");
		System.out.println("Slot 1");
		System.out.println("Slot 2");
		System.out.println("Slot 3");
		String save = input.nextLine();

		if (save.equalsIgnoreCase("Slot 1") || save.equalsIgnoreCase("1") || save.equalsIgnoreCase("Slot1")) {
			saveFile1();
		}
		else if (save.equalsIgnoreCase("Slot 2") || save.equalsIgnoreCase("2") || save.equalsIgnoreCase("Slot2"))
		{
			saveFile2();
		}
		else if (save.equalsIgnoreCase("Slot 3") || save.equalsIgnoreCase("3") || save.equalsIgnoreCase("Slot3"))
		{
			saveFile3();
		}
		else
		{
			System.out.println("Invalid Input. Exiting Save Menu.");
		}

	}
	private void saveFile1()
	{
		ResourceData data = new ResourceData();
		data.setRoomArrayNumber(currentRoom.getId());
		System.out.println(currentRoom.getId());
		data.setPlayer(player);
		data.setFactoryList(factoryList);
		try {
			ResourceData.saveGame(data, "UndergroundHero1.dat");
			System.out.println("<< SAVE SUCCESSFUL");
		} catch (Exception e) {
			System.out.println("<< ERROR SAVING");
			e.printStackTrace();
		}
	}
	private void saveFile2()
	{
		ResourceData data = new ResourceData();
		data.setRoomArrayNumber(currentRoom.getId());
		System.out.println(currentRoom.getId());
		data.setPlayer(player);
		data.setFactoryList(factoryList);
		try {
			ResourceData.saveGame(data, "UndergroundHero2.dat");
			System.out.println("<< SAVE SUCCESSFUL");
		} catch (Exception e) {
			System.out.println("<< ERROR SAVING");
			e.printStackTrace();
		}
	}	
	private void saveFile3()
	{
		ResourceData data = new ResourceData();
		data.setRoomArrayNumber(currentRoom.getId());
		System.out.println(currentRoom.getId());
		data.setPlayer(player);
		data.setFactoryList(factoryList);
		try {
			ResourceData.saveGame(data, "UndergroundHero3.dat");
			System.out.println("<< SAVE SUCCESSFUL");
		} catch (Exception e) {
			System.out.println("<< ERROR SAVING");
			e.printStackTrace();
		}
	}
	
	private void load() {
		System.out.println("Which slot would you like to load?");
		System.out.println("Slot 1");
		System.out.println("Slot 2");
		System.out.println("Slot 3");
		String load = input.nextLine();

		if (load.equalsIgnoreCase("Slot 1") || load.equalsIgnoreCase("1") || load.equalsIgnoreCase("Slot1")) {
			loadFile1();
		}
		else if (load.equalsIgnoreCase("Slot 2") || load.equalsIgnoreCase("2") || load.equalsIgnoreCase("Slot2"))
		{
			loadFile2();
		}
		else if (load.equalsIgnoreCase("Slot 3") || load.equalsIgnoreCase("3") || load.equalsIgnoreCase("Slot3"))
		{
			loadFile3();
		}
		else
		{
			System.out.println("Invalid Input. Exiting Load Menu.");
		}
	}
	private void loadFile1() throws ClassCastException, NullPointerException {
		// use the class saveLoadData to load values in the binary file
		
		try {

			ResourceData data = (ResourceData) ResourceData.loadGame("UndergroundHero1.dat");
			if (data != null) {
				currentRoom = factoryList.get(data.getRoomArrayNumber());
				floorMusicChecker(currentRoom);
				player = data.getPlayer();
				factoryList = data.getFactoryList();
				System.out.print("<< LOADING ");
				f.print("....\n", 300);
				System.out.println("Loading successful");
				System.out.println();
				look();
				System.out.println();
			} else {
				System.out.println("There is currently no file to load. \nA new game will be created.");
				f.print("       ", 500);
				System.out.println();
				System.out.print("Please Wait");
				f.print("       ", 500);
				System.out.println();
				System.out.println("New game created.");
				f.print("       ", 500);
				System.out.println();

				createNewGame();
			}

		} catch (Exception e) {
			System.out.println("Error loading, A new game will be created.");
			createNewGame();
			e.printStackTrace();
		}
	}
	private void loadFile2() throws ClassCastException, NullPointerException {
		// use the class saveLoadData to load values in the binary file
		
		try {

			ResourceData data = (ResourceData) ResourceData.loadGame("UndergroundHero2.dat");
			if (data != null) {
				currentRoom = factoryList.get(data.getRoomArrayNumber());
				floorMusicChecker(currentRoom);
				player = data.getPlayer();
				factoryList = data.getFactoryList();
				System.out.print("<< LOADING ");
				f.print("....\n", 300);
				System.out.println("Loading successful");
				System.out.println();
				look();
				System.out.println();
			} else {
				System.out.println("There is currently no file to load. \nA new game will be created.");
				f.print("       ", 500);
				System.out.println();
				System.out.print("Please Wait");
				f.print("       ", 500);
				System.out.println();
				System.out.println("New game created.");
				f.print("       ", 500);
				System.out.println();

				createNewGame();
			}

		} catch (Exception e) {
			System.out.println("Error loading, A new game will be created.");
			createNewGame();
			e.printStackTrace();
		}
	}
	private void loadFile3() throws ClassCastException, NullPointerException {
		// use the class saveLoadData to load values in the binary file
		
		try {

			ResourceData data = (ResourceData) ResourceData.loadGame("UndergroundHero3.dat");
			if (data != null) {
				currentRoom = factoryList.get(data.getRoomArrayNumber());
				floorMusicChecker(currentRoom);
				player = data.getPlayer();
				factoryList = data.getFactoryList();
				System.out.print("<< LOADING ");
				f.print("....\n", 300);
				System.out.println("Loading successful");
				System.out.println();
				look();
				System.out.println();
			} else {
				System.out.println("There is currently no file to load. \nA new game will be created.");
				f.print("       ", 300);
				System.out.println();
				System.out.print("Please Wait");
				f.print("       ", 300);
				System.out.println();
				System.out.println("New game created.");
				f.print("       ", 300);
				System.out.println();

				createNewGame();
			}

		} catch (Exception e) {
			System.out.println("Error loading, A new game will be created.");
			createNewGame();
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
				System.out.println("The Puzzler vanished as he falls on to the ground exposing the door to the west.");
				break;
			case 1:
				factoryList.get(19).setLocked(false);
				System.out.println("You slammed the Pogo on the table and caused a shockwave making the hula hoops "
								+ "\ntumbled on to the Pogo. The Pogo crawled out from the pile of hula hoops and "
								+ "\nscreamed. As the Pogo is picking up his stick, you charged at the Pogo and "
								+ "\nknock him flying to the east breaking the door.");
				break;
			case 4:
				factoryList.get(41).setLocked(false); // Quinn Har
				System.out.println("She jumped into the fire as You watched her laughing evilly, "
						     + "\n\"You may have beaten me but you will never win against him."
						       + "\nBURN! BURN! AND DESTROY THE WORLD MY LOVE...!\""
						       + "\nThe fire fiercely blazed her as she vanished into the fire.");
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

		if (puzzle.isSolved() == true) {

			switch (puzzle.getId()) {
			case 3:
				factoryList.get(14).setLocked(false);
				System.out.println("You have avoided all the bullets and lasers and reached at the end of the room.");
				System.out.println("You spotted a red button and pushed.");
				try {
					System.out.println("");
					TimeUnit.SECONDS.sleep(1);
					System.out.println("COUNT DOWN COMMENCING!");
					for (int i = 5; i > 0; i--) {
						TimeUnit.MILLISECONDS.sleep(500);
						System.out.println(i);
					}
					TimeUnit.SECONDS.sleep(1);
					System.out.println("DEFENCE SYSTEM SHUTTING DOWN!");
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;
			case 4:
				factoryList.get(18).setLocked(false);
				System.out.println("You hear the sound of a door unlocking.");
				break;
			case 5:
				factoryList.get(28).setLocked(false);
				System.out.println("You sucessfully swinged across the room.");
				try {
					TimeUnit.SECONDS.sleep(1);
					System.out.println("As you landed, you encounter a giant robot");
				} catch (InterruptedException e) {
				}
				break;
			case 6:
				factoryList.get(29).setLocked(false);
				System.out.println("You went to the computer and insert the chip");
				System.out.print("PLEASE WAIT!");
				for (int i = 0; i < 3; i++) {
					f.print("VALIDATING....", 250);
					System.out.println();
				}
				System.out.println("ACCESS GRANTED!");
				break;
			case 7:
				factoryList.get(34).setLocked(false);
				System.out.println("You hear the sound of a door unlocking.");
				break;
			case 8:
				factoryList.get(41).setLocked(false);
				System.out.println("You hear the sound of a door unlocking.");
				break;
			default:
				break;
			}
		}
	}

	/**
	 * @method displays list of commands and behavior description
	 */
	private void commandDescription() {
		System.out.println("\n");
		f.printBox("################### [NAVIGATION] ###################");
		System.out.println("[NORTH]\tMove North.");
		System.out.println("[EAST]\tMove East.");
		System.out.println("[SOUTH]\tMove South.");
		System.out.println("[WEST]\tMove West.");
		System.out.println("--------------------------------------------------------");
		System.out.println("[LOOK]\tDisplay room description, exits, "
				+ "\n\tand item existing in the room but it will not " + "\n\tshow any hidden exits.");
		System.out.println("[PICK]\tPick up the item in the room");
		System.out.println("--------------------------------------------------------");
		System.out.println("[BAG]\tDisplay items in inventory");
		System.out.println("[EQUIP]\tEquip a weapon or armor");
		System.out.println("[INFO]\tDisplay the player current status");
		System.out.println("[USE]\tUse an item");
		System.out.println("[VIEW]\tView current equipment");
		System.out.println("--------------------------------------------------------");
		System.out.println("[SAVE]\tSave game.");
		System.out.println("[LOAD]\tLoad the latest save file.");
		System.out.println("[HELP]\tView commands");
		System.out.println();
		f.printBox("##################### [BATTLE] #####################");
		System.out.println("[1. ATTACK]           Attack enemy");
		System.out.println("[2. USE ITEM]         Use item");
		System.out.println("[3. DEFEND]           Defend from enemy attack");
		System.out.println("[4. FLEE]             Run from battle");
		System.out.println("--------------------------------------------------------");
		System.out.println("\n\n");
	}

	/**
	 * @method Displays game intro
	 */
	private void displayIntro() {
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
		System.out.println("");
		f.printBox("######################## INTRO ########################");
		System.out.println("You have infiltrated the lair of the infamous "
				+ "\nsuper villain, \"Joe-Ker\". Your only powers are your exceeding wit and skill in combat. "
				+ "\nThere are a total of 4 floors and 42 rooms filled with monsters and puzzles blocking your way. "
				+ "\nTraverse through all floors and beat \"Joe-Ker\" to win the game and SAVE THE WORLD!! "
				+ "\n\nNotice: This game has sound! Please unmute your speakers/headphones for the best experience!"
				+ "\nWe recommend resizing your command line window size to 128x64."
				+ "\n - For Windows users: Right-click the title bar and click 'Properties'"
				+ "\n - Click the 'Layout' tab and adjust 'Window Size'"
				+ "\n - You'll have to restart the game for the changes to take place");

	}

	private void displayEnding() {
		endingMusic();
		f.printBox("CONGRATUATION HERO! \nYou have saved the world from being destroyed!");
		try {
			System.out.println("");
			TimeUnit.SECONDS.sleep(1);
			f.printBox("================== CREDITS ==================");
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
			System.out.println("|.................. John Lam ...................|");
			TimeUnit.SECONDS.sleep(1);
			System.out.println("|                                               |");
			TimeUnit.SECONDS.sleep(1);
			System.out.println("|................... King Lo ...................|");
			TimeUnit.SECONDS.sleep(1);
			System.out.println("|                                               |");
			TimeUnit.SECONDS.sleep(1);
			System.out.println("|................ Kyle Cousins .................|");
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
			f.printBox("================== (C)2016 ==================");
			TimeUnit.SECONDS.sleep(21);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("Would you like to play again?");
		System.out.println("          [Yes/No/Main Menu]           ");

		String userInput = input.nextLine();
		if (userInput.equalsIgnoreCase("y") || userInput.equalsIgnoreCase("yes")) {
			createNewGame();
		} else if (userInput.equalsIgnoreCase("n") || userInput.equalsIgnoreCase("no")) {
			System.out.println("Game Shutting down");
			try {
				System.out.println("5");
				TimeUnit.SECONDS.sleep(1);
				System.out.println("4");
				TimeUnit.SECONDS.sleep(1);
				System.out.println("3");
				TimeUnit.SECONDS.sleep(1);
				System.out.println("2");
				TimeUnit.SECONDS.sleep(1);
				System.out.println("1");
				TimeUnit.SECONDS.sleep(1);
				System.out.println("0");

			} catch (InterruptedException e) {
				gameRun = false;
				e.printStackTrace();
			}
			System.exit(0); // exits application

		} else {
			gameRun = false;
		}
	}

	private void floor1Music() {
		musicLogic.BGMStop();
		musicLogic = new MusicLogic("src/sound/floor1.wav");
		musicLogic.BGMLoop();
	}

	private void floor2Music() {
		musicLogic.BGMStop();
		musicLogic = new MusicLogic("src/sound/floor2.wav");
		musicLogic.BGMLoop();
	}

	private void floor3Music() {
		musicLogic.BGMStop();
		musicLogic = new MusicLogic("src/sound/floor3.wav");
		musicLogic.BGMLoop();
	}

	private void floor4Music() {
		musicLogic.BGMStop();
		musicLogic = new MusicLogic("src/sound/floor4.wav");
		musicLogic.BGMLoop();
	}

	private void menuMusic() {
		musicLogic.BGMStop();
		musicLogic = new MusicLogic("src/sound/menu.wav");
		musicLogic.BGMLoop();
	}

	private void endingMusic() {
		musicLogic.BGMStop();
		musicLogic = new MusicLogic("src/sound/ending.wav");
		musicLogic.BGMPlay();
	}

	public void floorMusicChecker(Room currentRoom) {
		if (currentRoom.getId() == 1 || currentRoom.getId() == 8) {
			floor1Music();
		} else if (currentRoom.getId() == 10 || currentRoom.getId() == 18) {
			floor2Music();
		} else if (currentRoom.getId() == 19 || currentRoom.getId() == 28) {
			floor3Music();
		} else if (currentRoom.getId() == 29 || currentRoom.getId() == 41) {
			floor4Music();
		}
	}

	// private String wrapText(String longDescription){
	// String shortDesc = WordUtils.wrap(longDescription, 50);
	// return shortDesc;
	// }

}
