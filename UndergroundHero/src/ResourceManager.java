import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * This class is going to read all text files and put them into an ArrayList.
 */

//TODO PUZZLE


//TODO ITEMS


//TODO ROOMS
//CONTRUCTOR => Room(String roomNumber, String roomDescription, String exits, Monster roomMonster, Puzzle roomPuzzle)

public class ResourceManager {
	
	private List<Monster> monsterList;
	private List<Room> roomList;
	private List<Item> itemList;
	private List<Puzzle> puzzleList;
	
	public void writeToMonsterList(){
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
				
				int hp = Integer.parseInt(health);
				int atk = Integer.parseInt(damage);
				int def = Integer.parseInt(defense);
				int spd = Integer.parseInt(speed);
				
				Monster newMonster = new Monster(name, description, hp, atk, def, spd);
				monsterList.add(newMonster);
				
			}
		}catch (IOException e){
			System.out.println("Unable to read file."); 
		}finally{
			in.close();
		}
	}
	
	public List<Monster> getMonsterList() {
		return monsterList;
	}
	
	public void writeToRoomList(){
		Scanner in = null;
		roomList = new ArrayList<Room>();

		try{
			
			in = new Scanner(new BufferedReader(new FileReader("res/Room_Data.txt")));
			
			while (in.hasNext() && in != null) {
				String roomNumber = in.nextLine();
				String roomDescription = in.nextLine();
				String roomExits = in.nextLine();
				
				Room newRoom = new Room(roomNumber, roomDescription, roomExits);
				roomList.add(newRoom);
				
			}
		}catch (IOException e){
			System.out.println("Unable to read file."); 
		}finally{
			in.close();
		}
	}
	
	public List<Room> getRoomList(){
		return roomList;
	}


}
