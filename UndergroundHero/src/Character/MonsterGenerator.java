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
		
		Monster mon00 = new Monster(0, "PUZZLER", 				5, 5, 4, 2, 1, true, null,						"A man with a purple suit and question marks "
				+ "\nall over this suit, a hat and cane.");
			mon00.addSkill(new Skill(0, "CANE", "NORMAL", 1, 40.0, "Hits you with cane."));
			mon00.addSkill(new Skill(1, "THROW RIDDLE", "NORMAL", 0, 60.0, "Laughs spontaneously, it's not very effective..."));
			monsterList.add(mon00);
		
		Monster mon01 = new Monster(1, "POGO",					8, 8, 7, 2, 3, true, null,					"A man on a pogo stick. The pogo stick "
				+ "\nhas machine guns attached to it.");
			mon01.addSkill(new Skill(0, "POGO JUMP", "NORMAL", 1, 50.0, "Jumps in with a pogo stick and lands on your foot."));
			mon01.addSkill(new Skill(1, "BOUNCING AROUND", "NORMAL", 0, 20.0, "Bounces around with his pogo stick, not very effective."));
			mon01.addSkill(new Skill(2, "FIRES WIDLY", "CHARGE", 1.5, 10.0, false, "Takes out his minigun and reloads, brace yourself!", "Fires his gun randomly around the room."));
			monsterList.add(mon01);
		
		Monster mon02 = new Monster(2, "GIANT BULL SHARK",		10, 10, 7, 2, 3, true, itemList.get(7),			"A giant bull shark that lives in the lake in the "
				+ "\nunderground lair. His name is Snappy "
				+ "\nand he wields a [GRAPPLING HOOK].");
			mon02.addSkill(new Skill(0, "GRAPPLE HOOK", "NORMAL", 1, 80.0, "Pulls you in and tail swipe you away."));
			mon02.addSkill(new Skill(1, "CHARGE ATTACK", "CHARGE", 1.5, 20.0, false, "Runs away for a turn, charging his attack. Unable to attack him this turn, brace yourself!", "Rushed out of the water and charges at you."));
			monsterList.add(mon02);
		
		Monster mon03 = new Monster(3, "GIANT ROBOT SENTRY",	12, 12, 8, 2, 1, true, itemList.get(9),			"A giant robotic sentry guarding an entrance."
				+ "\nThe armored plating sentry is a slow moving robot. "
				+ "\nThe sentry is armed with a laser pistol and a blade."
				+ "\nThere seems to be a [COMPUTER CHIP] on his forehead.");
			mon03.addSkill(new Skill(0, "IMPALE", "NORMAL", 2.0, 20.0, "Impales with blades in his hand. It's super effective."));
			mon03.addSkill(new Skill(1, "LASER SHOT", "NORMAL", 1.5, 80.0, "Fires a laser shot from his chest."));
			monsterList.add(mon03);
		
		Monster mon04 = new Monster(4, "QUINN HAR",				13, 13, 9, 2, 2, true, null,					"A female clown with a red and black color scheme. "
				+ "\nShe uses a large mallet.");
			mon04.addSkill(new Skill(0, "HAMMER SMASH", "NORMAL", 1.5, 20.0, "Smacks with a mallet. It's quite effective."));
			mon04.addSkill(new Skill(1, "ROUND HOUSE KICK", "NORMAL", 1.0, 50.0, "Delivers a round house kick with her heeled boots."));
			mon04.addSkill(new Skill(2, "TACKLE", "NORMAL", 1.0, 40.0, "Rushes forward and tackles."));
			monsterList.add(mon04);
		
		Monster mon05 = new Monster(5, "JOE KER",				15, 15, 9, 2, 2, true, null,					"A Deranged clown with a thirst for "
				+ "\ndestruction and chaos. While he is "
				+ "\nnot strong, he is quick and smart.");
			mon05.addSkill(new Skill(0, "CUT", "NORMAL", 1.0, 40.0, "Slices with a sharp knife. He then licks his knife afterwards."));
			mon05.addSkill(new Skill(1, "CROWBAR", "NORMAL", 1.0, 40.0, "Takes a crowbar and hits. He then laughs furiously."));
			mon05.addSkill(new Skill(2, "SECRET WEAPON", "CHARGE", 0, 20.0, false, "Laughs to himself, what is he up to?", "He takes a magnum and shots. It's super effective!"));
			monsterList.add(mon05);
		
		Monster mon06 = new Monster(6, "HULA HOOPER",			5, 5, 2, 0, 1, false, null,						"A man using a hula hoop. There are knives "
				+ "\nstrapped to the hula hoop to make it deadly.");
			mon06.addSkill(new Skill(0, "HOOP TOSS", "NORMAL", 1.0, 40.0, "Boomerang toss the hoop and returns back."));
			mon06.addSkill(new Skill(1, "HULA HOOPING", "NORMAL", 0, 60.0, "Stands in one spot, hula hooping..."));
			monsterList.add(mon06);
		
		Monster mon07 = new Monster(7, "WILD HYENA",			6, 6, 2, 0, 3, false, null,   				"A starving wild hyena that lives in the dark "
				+ "\ncorners of that underground lair. It will "
				+ "\neat anything, including you.");
			mon07.addSkill(new Skill(0, "BITE", "NORMAL", 1.0, 80.0, "Howls and then bites you."));
			mon07.addSkill(new Skill(1, "CHARGE RUSH", "CHARGE", 1.5, 20.0, false, "Runs away readying a charge attack, brace yourself!", "Furiously charges in and knocks you back."));
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
