package Generator;

import java.util.ArrayList;

import Item.Item;
import Puzzle.*;

/**
 * This class is reponsible for generating Puzzle objects
 * 
 * @author John
 *
 */
public class PuzzleGenerator {

	private ArrayList<Puzzle> puzzleList;
	private ArrayList<Item> itemList = new ItemGenerator().getItemList();

	/**
	 * @method Creates Item objects and adds them into an ArrayList<Puzzle>
	 */
	private void generatePuzzleList() {

		puzzleList = new ArrayList<Puzzle>();

		Puzzle puzzle = new Puzzle(0, "A Blood Type", 0, null, "AB", "What is your blood type?");
		puzzleList.add(puzzle);

		puzzle = new Puzzle(1, "Painting", 0, key("Diamond Tipped Cutter"), "MONA LISA",
				"The player feels like he/she is being watched, this room has many fancy objects all around it, but one stands out the most, a painting, the painting is looking straight at you, giving you a chill down your spine, the painting is very famous. The player thinks to himself what is the name of painting that is watching me?");
		puzzleList.add(puzzle);

		puzzle = new Puzzle(2, "The Animal Statues", 0, key("Speed Boots"), "4213",
				"There are four animal statues here, what is the number ordering of these statues?");
		puzzleList.add(puzzle);

		puzzle = new Puzzle(3, "The Laser Hallway", 1, null, key("Speed Boots"),
				"A laser blocks your path, it seems like I'll need to be really quick on my feet to dodge them.");
		puzzleList.add(puzzle);

		puzzle = new Puzzle(4, "The Colored Buttons", 0, null, "213",
				"In room 2-7 is a monitor with several groups of numbers, each group a different color. The final group of numbers is “2, 1, 3” in black. In room 2-8 are three buttons, red, green, and blue.");
		puzzleList.add(puzzle);

		puzzle = new Puzzle(5, "Jump The Chasm", 1, null, key("Grappling Hook"),
				"There appears to be a strange outcropping of rock in the ceiling. If only you could attach some rope to it you could swing across.");
		puzzleList.add(puzzle);

		puzzle = new Puzzle(6, "Security Bypass", 1, null, key("Computer Chip"),
				"A computer terminal stands in your way, it looks like I need to insert some kind of [COMPUTER CHIP] for it activate.");
		puzzleList.add(puzzle);

		puzzle = new Puzzle(7, "Riddle 1", 0, null, "BRAIN",
				"I have billions of eyes, yet I live in darkness. I have millions of ears, yet only four lobes. I have no muscle, yet I rule two hemispheres. What am I?");
		puzzleList.add(puzzle);

		puzzle = new Puzzle(8, "Riddle 2", 0, null, "TOMMORROW",
				"What is always on its way here, but never arrives?");
		puzzleList.add(puzzle);

	}

	/**
	 * 
	 * @return ArrayList<Puzzle>
	 */
	public ArrayList<Puzzle> getPuzzleList() {
		generatePuzzleList();
		return puzzleList;
	}

	/**
	 * @method Searches for key item name and returns its object
	 * @param name
	 * @return KeyItem
	 */
	public Item key(String name) {
		Item item;
		Item keyItem = null;
		for (int i = 0; i < itemList.size(); i++) {

			item = itemList.get(i);
			String itemName = item.getName();

			if (itemName.equalsIgnoreCase(name)) {
				keyItem = (Item) item;
				break;
			}
		}
		return keyItem;
	}

}
