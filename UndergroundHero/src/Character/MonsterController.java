package Character;

import java.util.ArrayList;

public class MonsterController {

	private ArrayList<Monster> monsterList;
	
	public MonsterController(){
		monsterList = new MonsterFactory().getMonsterList();
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
