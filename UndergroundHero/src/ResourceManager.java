import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * This class is going to read all text files and put them into an ArrayList.
 */
public class ResourceManager {
	
	private List<Monster> monsterList;
	
	public void writeToMonsterArrayList(){
		Scanner in = null;
		monsterList = new ArrayList<Monster>();
	
		//open file Monster.txt
		try{
			
			in = new Scanner(new BufferedReader(new FileReader("res/Monster_Data.txt")));
			
			//read file and add to an ArrayList<Monster>
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
		
		//TODO PUZZLE
		
		
		//TODO ITEMS
		
		
		//TODO ROOMS
		//CONTRUCTOR => Room(String roomNumber, String roomDescription, String exits, Monster roomMonster, Puzzle roomPuzzle)

		


}
