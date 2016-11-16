package Character;

import java.util.ArrayList;

import Item.Item;
import Item.ItemGenerator;

/**
 * This class is responsible for generating Monster objects
 * @author John, Matt
 *
 */
public class MonsterGenerator {
	
	private ArrayList<Monster> monsterList;
	
	/**
	 * @method Create all monster objects and put into a List<Monster>
	 */
	private void generateMonsterList(){
		
		//int hp, int atk, int def, int spd
		
		ArrayList<Item> itemList = new ItemGenerator().getItemList();
		monsterList = new ArrayList<Monster>();
		
		Monster mon00 = new Monster(0, "Puzzler", 				4, 4, 4, 4, 1, true, null,						"A man with a purple suit and question marks all over this suit, a hat and cane.");
		monsterList.add(mon00);
		
		Monster mon01 = new Monster(1, "Pogo",					12, 12, 6, 1, 3, true, null,					"A man on a pogo stick. "
				+ "\nThe pogo stick has machine guns attached to it.");
		monsterList.add(mon01);
		
		Monster mon02 = new Monster(2, "Giant Bull Shark",		12, 12, 7, 0, 3, true, itemList.get(7),			"A giant bull shark that lives in the lake in the underground lair. "
				+ "\nHis name is Snappy and he wields a [GRAPPLING HOOK].");
		monsterList.add(mon02);
		
		Monster mon03 = new Monster(3, "Giant Robotic Sentry",	15, 15, 4, 5, 1, true, itemList.get(9),			"A giant robotic sentry guarding the entrance to the final floor. "
				+ "\nThe sentry is a slow moving bipedal robot with lots of armor plating. "
				+ "\nThe sentry is armed with a gatling laser, sonic emitters, and a blade that is stored in his"
				+ "\n forearm when not in use. "
				+ "\nThere seems to be a [COMPUTER CHIP] on his forehead");
		monsterList.add(mon03);
		
		Monster mon04 = new Monster(4, "Quinn Har",				30, 30, 9, 4, 2, true, null,					"A female clown with a red and black color scheme. "
				+ "\nShe uses a large mallet.");
		monsterList.add(mon04);
		
		Monster mon05 = new Monster(5, "Joe Ker",				30, 30, 7, 8, 3, true, null,					"A Deranged clown with a thirst for destruction and chaos. "
				+ "\nWhile he is not strong, he is quick and smart.");
		monsterList.add(mon05);
		
		Monster mon06 = new Monster(6, "Hula Hooper",			6, 6, 3, 0, 1, false, null,						"A man using a hula hoop. "
				+ "\nThere are knives strapped to the hula hoop to make it deadly.");
		monsterList.add(mon06);
		
		Monster mon07 = new Monster(7, "Wild Hyena",			12, 12, 7, 0, 3, false, null,   				"A starving wild hyena that lives in the dark corners of that underground lair."
				+ "It will eat anything, including you.");
		monsterList.add(mon07);
		
	}

	/**
	 * 
	 * @return List<Monster> monsterList
	 */
	public ArrayList<Monster> getMonsterList() {
		generateMonsterList();
		return monsterList;
	}
	
}
