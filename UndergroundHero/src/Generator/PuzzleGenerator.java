package Generator;

import java.util.ArrayList;

import Puzzle.*;
import SearchController.ItemFinder;

/**
 * This class is reponsible for generating Puzzle objects
 * @author John
 *
 */
public class PuzzleGenerator {
	
	private ArrayList<Puzzle> puzzleList;
	
	/**
	 * @method Creates Item objects and adds them into an ArrayList<Puzzle>
	 */
	private void generatePuzzleList() {
		
		ItemFinder ic = new ItemFinder();
		puzzleList = new ArrayList<Puzzle>();
		
		Puzzle puzzle = new PuzzleRiddle(0, "A Blood Type", 0, "AB", "What is your blood type?");
		puzzleList.add(puzzle);
		
		puzzle = new PuzzleRiddle		(1, "Painting", 0, "MONA LISA", "The player feels like he/she is being watched, this room has many fancy objects all around it, but one stands out the most, a painting, the painting is looking straight at you, giving you a chill down your spine, the painting is very famous. The player thinks to himself what is the name of painting that is watching me?");
		puzzleList.add(puzzle);

		puzzle = new PuzzleRiddle		(2, "The Animal Statues", 0, "4213", "There are four animal statues here, what is the number ordering of these statues?");
		puzzleList.add(puzzle);
		
		puzzle = new PuzzleKey			(3, "The Laser Hallway", 1, ic.key("Speed Boots"), "A laser blocks your path, it seems like I'll need to be really quick on my feet to dodge them.");
		puzzleList.add(puzzle);
		
		puzzle = new PuzzleRiddle		(4, "The Colored Buttons", 0, "213", "In room 2-7 is a monitor with several groups of numbers, each group a different color. The final group of numbers is “2, 1, 3” in black. In room 2-8 are three buttons, red, green, and blue.");
		puzzleList.add(puzzle);
		
		puzzle = new PuzzleKey			(5, "Jump The Chasm", 1, ic.key("Grappling Hook"), "There appears to be a strange outcropping of rock in the ceiling. If only you could attach some rope to it you could swing across.");
		puzzleList.add(puzzle);
		
		puzzle = new PuzzleKey			(6, "Security Bypass", 1, ic.key("Computer Chip"), "A computer terminal stands in your way, it looks like I need to insert some kind of [COMPUTER CHIP] for it activate.");
		puzzleList.add(puzzle);
		
		puzzle = new PuzzleRiddle		(7, "Riddle 1", 0, "BRAIN", "I have billions of eyes, yet I live in darkness. I have millions of ears, yet only four lobes. I have no muscle, yet I rule two hemispheres. What am I?");
		puzzleList.add(puzzle);
		
		puzzle = new PuzzleRiddle		(8, "Riddle 2", 0, "TOMMORROW",  "What is always on its way here, but never arrives?");
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
	
}
