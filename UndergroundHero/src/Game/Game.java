package Game;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
//import org.apache.commons.lang3.text.WordUtils;

import Character.Monster;
import Character.Player;
import Generator.ItemGenerator;
import Item.Item;
import LogicController.BattleLogic;
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
	private Room currentRoom, lockedRoom;
	private Player player;
	private PuzzleLogic puzzleLogic;
	private BattleLogic battleLogic;
	private Scanner input;
	private int bagIndex;
	private boolean gameRun;
	
	/**
	 * @method Load all assets to game object
	 */
	public Game() {
		factoryList = new RoomFactory().getRoomFactoryList();
		itemList = new ItemGenerator().getItemList();
		puzzleLogic = new PuzzleLogic();
		battleLogic = new BattleLogic();
	}
	
	/**
	 * @method Loads default room and player for new game state.
	 */
	public void createNewGame() {
		gameRun = true;
		player = new Player(10, 10, 1, 3, 2);
		player.setName("HERO");
		player.startingItem(itemList.get(0));
		player.startingItem(itemList.get(2));
		player.startingItem(itemList.get(10));
		player.startingItem(itemList.get(4));
		currentRoom = factoryList.get(1);
	}
	
	/**
	 * @method Main method for initiating game
	 */
	public void play() {
		String userInput;
		input = new Scanner(System.in);
		
		displayIntro();
		
		while(gameRun) {
			System.out.print(">>");
			userInput = input.nextLine();
			parseCommand(userInput);
		}
	}
	
	/**
	 * @method Responsible for checking valid exits, monsters, and puzzles in the current room.
	 * @param direction
	 */
	private void roomLogic(String direction) {
		Room nextRoom = currentRoom.nextRoom(direction);
		
		if(nextRoom == null){
			System.out.println("Theres no exit that way, try another direction.");
		}
		
		else if(nextRoom.isLocked() == true){
			lockedRoom = nextRoom;
			System.out.println("[" + nextRoom.getName() + "] door is locked.");
			System.out.println("Try a different route.");
		}
		
		else{
			currentRoom = nextRoom;
			System.out.println("-------------------------------------------------------");
			System.out.println("[" + currentRoom.getName() + "]");
			System.out.println(currentRoom.getDescription());
			System.out.println("[" + currentRoom.getExits() + "]");
			System.out.println("-------------------------------------------------------");
			
			//check if roomMonster exists and if not dead
			if(nextRoom.getRoomMonster() != null && nextRoom.getRoomMonster().isDead() == false) {
				
				//if boss, spawnrate is 100%
				if(nextRoom.getRoomMonster().isBoss()) {
					battleLogic.initiateBattle(player, nextRoom.getRoomMonster());
				}
				
				//if common monster, spawn rate is 30%
				else {
					double chance = (Math.random()*100);
					final double SPAWN_RATE = 30.0;
					
					if(chance < SPAWN_RATE) {
						battleLogic.initiateBattle(player, nextRoom.getRoomMonster());
					}
				}
				
				
				if(player.getHp() <= 0) {
					gameRun = false;
				}
				
				checkRoomMonsterLocks(nextRoom.getRoomMonster());
				
				System.out.println("Returning to room...");
				System.out.println("-------------------------------------------------------");
				System.out.println("[" + currentRoom.getName() + "]");
				System.out.println("[" + currentRoom.getExits() + "]");
				System.out.println("-------------------------------------------------------");
				
			}
			
			if(nextRoom.getRoomPuzzle() != null) {
				puzzleLogic.initiatePuzzle(currentRoom, player);
				checkRoomPuzzleLocks(nextRoom.getRoomPuzzle());
			}
			
		}
	}
	
	/**
	 * @method Parses user's input's
	 * @param input
	 */
	private void parseCommand(String command) {
		
		if(validCommandInput(command))
			roomLogic(command);
		
		switch(command.toUpperCase()) {
		
			case "LOOK": look();
				break;
				
			case "BAG": player.openInventory();
				break;
				
			case "VIEW": player.viewEquipment();
				break;
				
			case "EQUIP": equip();
				break;
				
			case "INFO": System.out.println(player.toString());
				break;
				
			case "USE": useItem();
				break;
				
			case "HELP": viewHelp();
				break;
				
			case "VIEW COMMANDS": viewCommands();
				break;
				
			case "QUIT": gameRun = false;
				break;
				
			case "SAVE": save();
				
				break;
			case "LOAD": load();
				break;
				
			case "UNLOCK":
					System.out.println("Locked: " + lockedRoom.isLocked());
					lockedRoom.setLocked(false);
					System.out.println("Locked: " + lockedRoom.isLocked());
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
		}
		catch(InputMismatchException e){
			
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
		System.out.println("-------------------------------------------------------");
	}
	
	/**
	 * @method Prompts user to equip item. If so, remove equipment from inventory to player and increase/decrease stats.
	 */
	private void equip(){
		try {
			player.openInventory();
			System.out.println("What item do you want to equip? (Choose a number)");
			System.out.print(">>");
			bagIndex = input.nextInt();
			player.equip(bagIndex);
			input.nextLine();
		}
		catch(InputMismatchException e){
			
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
			System.out.println("Load Sucessful");
			System.out.println();
			System.out.println(currentRoom.getName() + "\n" + currentRoom.getDescription());

		} catch (Exception e) {
			System.out.println("error loading");
			e.printStackTrace();
		}
	}
	
	/**
	 * @method checks for valid navigation command
	 * @param input
	 * @return boolean
	 */
	private boolean validCommandInput(String input) {
		if(input.equalsIgnoreCase("EAST") || input.equalsIgnoreCase("WEST") || input.equalsIgnoreCase("NORTH") || input.equalsIgnoreCase("SOUTH"))
			return true;
			return false;
	}
	
	/**
	 * @method Opens room lock depending on monster's death.
	 * @param monster
	 */
	private void checkRoomMonsterLocks(Monster monster) {
		
		if(monster.isDead() == true) {
			
			switch(monster.getId()) {
			
				case 0: factoryList.get(10).setLocked(false);
						System.out.println("Room: [" + factoryList.get(10).getName() + "] is now unlocked!");
					break;
				case 1: factoryList.get(19).setLocked(false);
						System.out.println("Room: [" + factoryList.get(19).getName() + "] is now unlocked!");
					break;
				case 4: factoryList.get(41).setLocked(false);
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
				case 5: factoryList.get(28).setLocked(false);
						System.out.println("Room: [" + factoryList.get(28).getName() + "] is now unlocked!");
					break;
				case 6: factoryList.get(29).setLocked(false);
						System.out.println("Room: [" + factoryList.get(29).getName() + " is now unlocked!");
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
	 * @method Displays further information about what the user will be interacting in the game.
	 */
	private void viewHelp() {
		System.out.println("-------------------------------------------------------\n");
		System.out.println("******************[NAGIVATION]******************");
		System.out.println("To navigate, the inputs allowed are north, east, west, and south. Not all rooms will have exits.");
		System.out.println("The player is allowed to revisit past rooms.\n");
		System.out.println("******************[ROOM]******************");
		System.out.println("While inside a room the player is allow to view the room number, descriptions, and existing exits by the look command.");
		System.out.println("Rooms can contain a either a puzzle or monster will prompt the player of its existence.\n");
		System.out.println("******************[PUZZLE]******************");
		System.out.println("While in a puzzle, the player can view the puzzle or leave. Once viewed the puzzle will display the description and prompt the user to solve it.\n");
		System.out.println("******************[INVENTORY]******************");
		System.out.println("The player, while not in battle or a puzzle, is allowed to view their status, equipments, inventory, and able to use items.\n");
		System.out.println("******************[BATTLE]******************");
		System.out.println("During a battle, the player can attack, defend, use item, and run with the following commands [1], [2], [3], [4].\n");
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
		System.out.println("BATTLE:		[1. ATTACK], [2. DEFEND], [3. FLEE], [4. USE ITEM]\n");
		System.out.println("Type [HELP] for more information.");
		System.out.println("-------------------------------------------------------");
	}
	
	/**
	 * @method Displays game intro
	 */
	private void displayIntro() {
		System.out.println("-------------------------------------------------------");
		System.out.println("Welcome to Underground Hero");
		System.out.println("The game is about a person without any superpowers but is a clever and good fighter. He has infiltrated a super villain lair to stop an apocalypse from happening. There is a total of 4 floors and 42 rooms, traverse through all floors and beat the final boss to win the game!");
		viewCommands();
	}
	
//	private String wrapText(String longDescription){
//		String shortDesc = WordUtils.wrap(longDescription, 50);
//		return shortDesc;
//	}
	
}
