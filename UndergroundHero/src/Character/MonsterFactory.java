package Character;

import java.util.ArrayList;

import Generator.ItemGenerator;
import Generator.MonsterGenerator;
import Item.*;

/**
 * This class is responsible for setting Item to Monster and add to an ArrayList<Monster>
 * @author John
 *
 */
public class MonsterFactory {
	
	ArrayList<Monster> monsterFactoryList;
	
	ArrayList<Item> itemList;
	ArrayList<Monster> monsterList;
	private ItemController ic;

	public MonsterFactory(){
		itemList = new ItemGenerator().getItemList();
		monsterList = new MonsterGenerator().getMonsterList();
	}
	
	/**
	 * @return ArrayList<Monster>
	 */
	public ArrayList<Monster> getMonsterList() {
		createMonsterFactory();
		return monsterFactoryList;
	}
	
	/**
	 * @method sets Item object to a Monster object
	 */
	private void createMonsterFactory() {
		
		monsterFactoryList = new ArrayList<Monster>();
		ic = new ItemController();
		
		for(int i = 0; i < monsterList.size(); i++){
			
			Monster monster = monsterList.get(i);
			String monsterName = monster.getName();
			
			if(monsterName.equalsIgnoreCase("Giant Robotic Sentry")) 
			{
				monster.setLoot(ic.keyItem("Computer Chip"));
				monsterFactoryList.add(monster);
			}
			else if(monsterName.equalsIgnoreCase("Giant Bull Shark")) 
			{
				monster.setLoot(ic.keyItem("Grappling Hook"));
				monsterFactoryList.add(monster);
			}
			else
			{
				monster.setLoot(ic.miscItem("Adrenaline Shot"));		//at the moment, all monsters by default will contain this item
				monsterFactoryList.add(monster);
			}
			
		}
		
	}
}
