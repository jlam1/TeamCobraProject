import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * This class will read text files and load them into a list. Call list by using getter method.
 */
public class ResourceManager {
	
	private List<Monster> monsterList;
	private List<Room> roomList;
	private List<Item> itemList;
	private List<Puzzle> puzzleList;
	
	/*
	 * When constructor is called, load all assets
	 */
	public ResourceManager(){
		loadAssetToList();
	}
	
	/*
	 * Reads file and adds to attributes to a List call monsterList
	 */
	private void writeToMonsterList(){
		Scanner in = null;
		monsterList = new ArrayList<Monster>();

		try{
			
			in = new Scanner(new BufferedReader(new FileReader("res/Monster_Data.txt")));

			while (in.hasNext() && in != null) {
				String name = in.nextLine();
				String description = in.nextLine();
				String health = in.nextLine();
				String damage = in.nextLine();
				String defense = in.nextLine();
				String speed = in.nextLine();
				String dead = in.nextLine();
				String action1 = in.nextLine();
				String action2 = in.nextLine();
				String action3 = in.nextLine();
				String action4 = in.nextLine();
				
				int hp = Integer.parseInt(health);
				int atk = Integer.parseInt(damage);
				int def = Integer.parseInt(defense);
				int spd = Integer.parseInt(speed);
				boolean isDead;
				if(dead.equalsIgnoreCase("false")){
					isDead = false;
				}else{
					isDead = true;
				}
				
				
				Monster.Action skills = new Monster.Action(action1, action2, action3, action4);
				
				Monster newMonster = new Monster(name, description, hp, atk, def, spd, isDead, skills);
				monsterList.add(newMonster);
				
			}
		}catch (IOException e){
			System.out.println("Unable to read file."); 
		}finally{
			in.close();
		}
	}
	
	/*
	 * Returns monsterList
	 */
	public List<Monster> getMonsterList() {
		return monsterList;
	}
	
	/*
	 * Reads file and adds to attributes to a List call roomList
	 */
	private void writeToRoomList(){
		Scanner in = null;
		roomList = new ArrayList<Room>();

		try{
			
			in = new Scanner(new BufferedReader(new FileReader("res/Room_Data.txt")));
			
			while (in.hasNext() && in != null) {
				String roomNumber = in.nextLine();
				String roomDescription = in.nextLine();
				String roomExits = in.nextLine();
				String locked = in.nextLine();
				
				boolean roomLocked;
				if(locked.equalsIgnoreCase("true")){
					roomLocked = true;
				}else{
					roomLocked = false;
				}
				
				Room newRoom = new Room(roomNumber, roomDescription, roomExits, roomLocked);
				roomList.add(newRoom);
				
			}
		}catch (IOException e){
			System.out.println("Unable to read file."); 
		}finally{
			in.close();
		}
	}
	
	/*
	 * Returns roomList
	 */
	public List<Room> getRoomList(){
		return roomList;
	}
	
	/*
	 * Reads file and adds to attributes to a List call puzzleList
	 */
	private void writeToPuzzleList(){
		Scanner in = null;
		puzzleList = new ArrayList<Puzzle>();

		try{
			
			in = new Scanner(new BufferedReader(new FileReader("res/Puzzle_Data.txt")));
			
			while (in.hasNext() && in != null) {
				String puzzleName = in.nextLine();
				String puzzleDescription = in.nextLine();
				String puzzleAnswerDescription = in.nextLine();
				String puzzleReward = in.nextLine();
				
				Puzzle newPuzzle = new Puzzle(puzzleName, puzzleDescription, puzzleAnswerDescription, puzzleReward);
				puzzleList.add(newPuzzle);
				
			}
		}catch (IOException e){
			System.out.println("Unable to read file."); 
		}finally{
			in.close();
		}
	}
	
	/*
	 * Returns puzzleList
	 */
	public List<Puzzle> getPuzzleList(){
		return puzzleList;
	}
	
	/*
	 * Reads file and adds to attributes to a List call itemList
	 */
	private void writeToItemList(){
		Scanner in = null;
		itemList = new ArrayList<Item>();

		try{
			
			in = new Scanner(new BufferedReader(new FileReader("res/Item_Data.txt")));
			
			while (in.hasNext() && in != null) {
				int itemID = in.nextInt();
				in.nextLine();
				String itemName = in.nextLine();
				String itemDescription = in.nextLine();
				String itemType = in.nextLine();
				
				Item newItem = new Item(itemID, itemName, itemDescription, itemType);
				itemList.add(newItem);
			}
		}catch (IOException e){
			System.out.println("Unable to read file."); 
		}finally{
			in.close();
		}
	}
	
	/*
	 * Returns itemList
	 */
	public List<Item> getItemList(){
		return itemList;
	}
	
	/*
	 * A method that calls all writeToList methods
	 */
	public void loadAssetToList(){
		writeToMonsterList();
		writeToRoomList();
		writeToPuzzleList();
		writeToItemList();
	}
	
	/**
	 * Method: saveGame()
	 * 
	 * This method will save the game. It will throw an exception when overwriting a existing save 
	 * file and create a new file if the file does not exist.
	 * 
	 * @throws Exception
	 */
	public static void saveGame(Serializable data, String fileName) throws Exception
	{
		try(ObjectOutputStream output = new ObjectOutputStream(Files.newOutputStream(Paths.get(fileName))))
		{
			output.writeObject(data);
		}
		
	}
	
	/**
	 * Method: loadGame()
	 * 
	 * This method will load the file. It will throw an exception when the file does 
	 * not exist and creates a new game. it will throw an exception when the file cannot be loaded.
	 * 
	 * @return game
	 * @throws Exception
	 */
	public static Object loadGame(String fileName) throws Exception
	{
		try(ObjectInputStream input = new ObjectInputStream(Files.newInputStream(Paths.get(fileName))))
		{
			return input.readObject();
		}
	}
}
