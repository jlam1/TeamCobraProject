import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadFiles {

	public static void main(String[] args){
		
		Scanner in = null;
		List<Monster> monsterList = new ArrayList<Monster>();
	
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
		} catch (IOException e) {
			System.out.println("Unable to read file."); 
		}
		

		//print test
		for(Monster i : monsterList){
			System.out.println("NAME: " + i.getName());
			System.out.println("DESC: " + i.getDescription());
			System.out.println("HP: " + i.getHp());
			System.out.println("ATK: " + i.getAtk());
			System.out.println("DEF: " + i.getDef());
			System.out.println("SPD: " + i.getSpd());
		}

	}

}
