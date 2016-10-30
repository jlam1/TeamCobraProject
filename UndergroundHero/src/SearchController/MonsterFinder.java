package SearchController;

import java.util.ArrayList;

import Character.Monster;
import Generator.MonsterGenerator;

/**
 * This class will be responsible searching monster objects by name and returns monster objects.
 * @author John
 *
 */
public class MonsterFinder {

	private ArrayList<Monster> monsterList;
	
	public MonsterFinder(){
		monsterList = new MonsterGenerator().getMonsterList();
	}
	
	/**
	 * @method Searches for monster by name
	 * @param name
	 * @return Monster
	 */	
	public Monster monster(String name) {
		Monster mon;
		Monster monster = null;
		
		for(int i = 0; i < monsterList.size(); i++){
			
			mon = monsterList.get(i);
			String puzzleName = mon.getName();
			
			if(puzzleName.equalsIgnoreCase(name)){
				monster = mon;
				break;
			}	
		}
		
		return monster;
	}
	
}
