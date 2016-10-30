package Tester;

import java.io.InvalidClassException;
import java.nio.file.NoSuchFileException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Character.Player;
import Game.Game;
import Game.ResourceManager;
import Game.saveLoadData;
import Generator.ItemGenerator;
import Item.Item;
import Room.*;

public class SaveLoadTester {
	
	static Room currentRoom;
	static Item inventory;
	static Item equipment;
	static List<Room> factoryList;
	static List<Item> itemList;
	static List<Item> inventoryList;
	static List<Item> equipmentList;
	static Player player;
	static Scanner input;
	static int bagIndex;

	public static void main(String[] args) {
		
		player = new Player(10, 3, 2, 3);
		
		factoryList = new RoomFactory().getRoomFactoryList();
		itemList = new ItemGenerator().getItemList();
		
		//add default items to player
		player.pickUp(itemList.get(10));
		player.pickUp(itemList.get(0));
		player.pickUp(itemList.get(2));
		
//		Game game = new Game(new RoomFactory().getRoomFactoryList(), new Player(10, 1, 2, 1));
		play();
		
	}
	
	static void play(){
		input = new Scanner(System.in);
		String command;
		boolean gameRun = true;
		currentRoom = factoryList.get(1);

		System.out.println("Welcome to Underground Hero");
		
		while(gameRun){
			System.out.print(">>");
			command = input.nextLine();
			
			if(validCommandInput(command)){
				roomLogic(command);
			}
			
			else if(command.equalsIgnoreCase("LOOK")){
				System.out.println(currentRoom.getName());
				System.out.println(currentRoom.getDescription());
				System.out.println(currentRoom.getExits());
			}
			
			else if(command.equalsIgnoreCase("EXITS")){
				System.out.println(currentRoom.getExits());
			}
			
			else if(command.equalsIgnoreCase("BAG")) {
				player.openInventory();
			}
			
			else if(command.equalsIgnoreCase("EQUIP")) {
				equip();
			}
			
			else if(command.equalsIgnoreCase("VIEW")) {
				player.viewEquipment();
			}
			
			else if(command.equalsIgnoreCase("USE")) {
				useItem();
			}
			
			else if(command.equalsIgnoreCase("INFO")) {
				System.out.println(player.toString());
			}
			
			else if (command.equalsIgnoreCase("SAVE")){
				save();
			}
			
			else if (command.equalsIgnoreCase("LOAD")) {
				try
				{
					load();
				}
				catch (NoSuchFileException | InvalidClassException e)
				{
					System.out.println("There is currently no save file.");
					e.printStackTrace();
				}
			}
			
			else if(command.equalsIgnoreCase("QUIT")){
				gameRun = false;
			}
			
			else if(command.equalsIgnoreCase("HELP")) {
				System.out.println("NAVIGATION: \nNorth \nSouth \nEast \nWest \n\nROOM: \nLook \n\nCombat: \nAttack \nDefend \nRun [Direction] ");
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
	
	static void useItem(){
		try {
			player.openInventory();
			System.out.println("Which item do you want to use? (Choose a number)");
			System.out.print(">>");
			bagIndex = input.nextInt();
			player.useItem(bagIndex);
			input.nextLine();
		}
		catch(InputMismatchException e){
			System.out.println("Wrong use item command.\n");
		}
	}
	
	static void equip(){
		try {
			player.openInventory();
			System.out.println("What item do you want to equip? (Choose a number)");
			System.out.print(">>");
			bagIndex = input.nextInt();
			player.equip(bagIndex);
			//equipment = itemList.get(bagIndex);
			
			/*try{
			equipmentList.add(equipment);
			System.out.println(equipmentList);
			}
			catch(NullPointerException npe)
			{
				System.out.println("Error equiping");
			}*/
			input.nextLine();
		}
		catch(InputMismatchException e){
			System.out.println("Wrong equip command.\n");
		}
	}
	
	static void save(){
		//use the class saveLoadData to save values in to binary file
		saveLoadData data = new saveLoadData();
		data.setRoomArrayNumber(currentRoom.getId());
		System.out.println(currentRoom.getId());
		data.setPlayer(player);
		/*data.setAtk(player.getAtk());
		data.setDef(player.getDef());
		data.setHp(player.getHp());
		data.setSpeed(player.getSpd());*/
		
		/*for(Item e : equipmentList)
		{
			equipmentList.add(e);
		}*/
		//data.setEquipment(equipmentList);
		//System.out.println(equipmentList);
		
		/*for(Item i : itemList)
		{
			itemList.add(i);
		}
			data.setItem(itemList);*/
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
	
	static void load() throws NoSuchFileException, InvalidClassException{
		//use the class saveLoadData to load values in the binary file
		try
		{
			saveLoadData data = (saveLoadData) ResourceManager.loadGame("UndergroundHero.dat");
			currentRoom = factoryList.get(data.getRoomArrayNumber());
			System.out.println("Load Sucessful");
			System.out.println();
			System.out.println(currentRoom.getName() + "\n" + currentRoom.getDescription());
			data.getPlayer();
			/*data.getAtk();
			data.getDef();
			data.getHp();
			data.getSpeed();
			data.getItemArray();
			data.getEquipmentArray();*/
			
			//TODO: need to load the player stats, load already solve puzzle, load items in bag, load room boolean and load already defeated monsters

		}
		catch (Exception e)
		{
			System.out.println("error loading");
			e.printStackTrace();
		}
	}
	
	static boolean validCommandInput(String input){
		
		if(input.equalsIgnoreCase("EAST") || input.equalsIgnoreCase("WEST") || input.equalsIgnoreCase("NORTH") || input.equalsIgnoreCase("SOUTH")){
			return true;
		}else{
			return false;
		}
	}
	
	
	
//	for(Room i : factoryList){
//	System.out.println(i.getId());
//	if(i.isLocked() == true)
//		System.out.println(i.isLocked());
//	if(i.getRoomPuzzle() != null)
//		System.out.println(i.getRoomPuzzle().getName());
//	if(i.getRoomMonster() != null)
//		System.out.println(i.getRoomMonster().getName());
//	if(i.getRoomItem() != null)
//		System.out.println(i.getRoomItem().getName());
//	System.out.println();
//}
	
}