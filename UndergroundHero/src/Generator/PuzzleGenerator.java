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
		
		Puzzle puzzle = new PuzzleRiddle(0, "A Blood Type", 0, "ANSWER_PLACEHOLDER", "Blood sucker is a sucker for math and science, for whatever reason he has used his immortality to study and understand the world better. The Puzzler is a smart guy that likes to play with people’s minds and talk in funny ways, but don’t disregard him as he can very well attack using his cane.");
		puzzleList.add(puzzle);
		
		puzzle = new PuzzleRiddle		(1, "Painting", 0, "MONA LISA", "The player walks into room 1-9, the player feels like he/she is being watched, this room has many fancy objects all around it, but one stands out the most, a painting, the painting is looking straight at you, giving you a chill down your spine, the painting is very famous. The player thinks to himself what is the name of painting that is watching me?");
		puzzleList.add(puzzle);

		puzzle = new PuzzleRiddle		(2, "The Animal Statues", 0, "1234", "There are four animal statues in rooms 2-3 and 2-4. In 2-4, they are on the South wall and cannot be moved and are, in order from East to West (which will appear to be left to right to the player): Duck, Giraffe, Elephant, Cow. In room 2-3, they are on the North wall, the order is randomized (but cannot be randomized to the puzzle’s solution), and can be moved.");
		puzzleList.add(puzzle);
		
		puzzle = new PuzzleKey			(3, "The Laser Hallway", 1, ic.key("Speed Boots"), "There is a hallway that shoots lasers at anyone who tries to pass in room 2-5. If the player tries to pass without the Speed Boots, then they are unable to and take 1 HP of damage.");
		puzzleList.add(puzzle);
		
		puzzle = new PuzzleRiddle		(4, "The Colored Buttons", 0, "213", "In room 2-7 is a monitor with several groups of numbers, each group a different color. The final group of numbers is “2, 1, 3” in black. In room 2-8 are three buttons, red, green, and blue.");
		puzzleList.add(puzzle);
		
		puzzle = new PuzzleKey			(5, "Jump The Chasm", 1, ic.key("Grappling Hook"), "You need to clear a chasm (in room 3-9) to progress further into the villain’s lair. There appears to be a strange outcropping of rock in the ceiling. If only you could attach some rope to it you could swing across, but be careful, it’s a long way down; and you think you see some spikes... The player may attempt to jump the chasm, but doing so will result in their death. The player must input the command to jump three times before they actually jump. The first two times the game will output text along the lines of the player second guessing themself/losing their nerve at the last second.");
		puzzleList.add(puzzle);
		
		puzzle = new PuzzleKey			(6, "Security Bypass", 1, ic.key("Computer Chip"), "You have just defeated the level boss, the giant robotic sentry. You are winded. You gather yourself to push on, but as you come to the door you notice that it is locked with no visible way to open it, and it appears far too thick to break through with anything less than a comically large amount of explosives, or a giant robot ? ironically. Defeated, you angrily hit the door with your fist. You turn to take out your anger on your defeated foe when you notice an odd looking computer chip protruding from the robot’s broken head. “Security Bypass” is printed on the chip. Hoping against hope you press your head against the door and hear the hum of a strong electromagnet. You take the chip and begin your search for a suitable computer terminal.");
		puzzleList.add(puzzle);
		
		puzzle = new PuzzleRiddle		(7, "Riddle 1", 0, "BRAIN", "You walk into a room  and on the wall towards the exit is a riddle to which the user must enter the answer.");
		puzzleList.add(puzzle);
		
		puzzle = new PuzzleRiddle		(8, "Riddle 2", 0, "NAME",  "Another door blocks your way and it needs the correct answer for you to pass through.");
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
