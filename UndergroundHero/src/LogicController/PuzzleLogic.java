package LogicController;
/**
 * This class is reponsible for dealing with player's behaviors pertaining to puzzles.
 * @author John, King, Kyle
 */
import java.io.Serializable;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import Character.Player;
import Item.Item;
import Puzzle.Puzzle;
import Room.*;
import LogicController.MusicLogic;

/**
 * This class is responsible for dealing with all puzzle logic. This checks if
 * the player inside the current room contains a puzzle, if so check the puzzle
 * type and display information of puzzle to player.
 * 
 * @author John, King
 *
 */
public class PuzzleLogic implements Serializable {

	private static final long serialVersionUID = -3089189101952541374L;
	private boolean puzzleLoop, riddleLoop, puzzleSolved, puzzleRun;
	private Puzzle puzzle;
	private Scanner input;
	private String userInput;
	private MusicLogic musicLogic;
	private Room currentRoom;

	public PuzzleLogic(Scanner in) {
		input = in;
		musicLogic = new MusicLogic("src/sound/puzzle.wav");
	}

	private void puzzleMusic() {
		musicLogic.BGMStop();
		musicLogic = new MusicLogic("src/sound/puzzle.wav");
		musicLogic.BGMLoop();
	}
	private void solveMusic()
	{
		musicLogic.BGMStop();
		musicLogic = new MusicLogic("src/sound/solvetune.wav");
		musicLogic.BGMPlay();
	}
	
	public void floor1Music() {
		musicLogic.BGMStop();
		musicLogic = new MusicLogic("src/sound/floor1.wav");
		musicLogic.BGMLoop();
	}
	
	/*public void floor2Music() {
		musicLogic.BGMStop();
		musicLogic = new MusicLogic("src/sound/floor2.wav");
		musicLogic.BGMLoop();
	}
	
	public void floor3Music() {
		musicLogic.BGMStop();
		musicLogic = new MusicLogic("src/sound/floor3.wav");
		musicLogic.BGMLoop();
	}
	
	public void floor4Music() {
		musicLogic.BGMStop();
		musicLogic = new MusicLogic("src/sound/floor4.wav");
		musicLogic.BGMLoop();
	}

	public void floorMusicChecker(){
		if (currentRoom.getId() >= 1 && currentRoom.getId() <= 9){
			floor1Music();
		}
		else if (currentRoom.getId() >= 10 && currentRoom.getId() <= 18){
			floor2Music();
		}
		else if (currentRoom.getId() >= 19 && currentRoom.getId() <= 28){
			floor3Music();
		}
		else if (currentRoom.getId() >= 29 && currentRoom.getId() <= 41){
			floor4Music();
		}
	}*/

	/**
	 * @method This method will check if puzzle exists in the current room. If
	 *         empty, return null. If not the method wil prompt the player on
	 *         console to initiate puzzle and depending on the puzzle type it
	 *         will display different behaviors. Puzzle key type will check
	 *         player ArrayList<Item> inventory and puzzle type riddle will
	 *         compare and check player's user input and the riddle's answer.
	 * @param currentRoom
	 * @param player
	 * @return true when puzzle solved, else return false
	 * @throws InterruptedException
	 */
	public void initiatePuzzle(Room currentRoom, Player player) {

		puzzle = currentRoom.getRoomPuzzle();

		String puzzleDesc = puzzle.getDescription();
		Item puzzleKeyItem = puzzle.getKeyItem();
		String riddleAnswer = puzzle.getRiddleAnswer();

		puzzleSolved = false;
		puzzleRun = true;
		puzzleLoop = true;

		// check if puzzle is null AND if puzzle is NOT solved
		if (puzzle != null && puzzle.isSolved() == false) {
			
			if (puzzle.getType() == 1) {
				runKeyPuzzle(puzzleKeyItem, player);
				}
			else
			{
				System.out.println("There is a puzzle in this room...");
				System.out.println("Do you want to initiate puzzle? (Y/N)");

				while (puzzleRun) {
					System.out.print(">>");
					userInput = input.nextLine();

					// check user's choice (Y/N)
					if (userInput.equalsIgnoreCase("Y")) {
						System.out.println("Initiating puzzle...");
						delay();
						puzzleMusic();
						
						while (puzzleLoop) {
							System.out.println("-------------------------------------------------");
							System.out.println(puzzleDesc);
							System.out.println("-------------------------------------------------");

							// check puzzle type PuzzleRiddle
							if (puzzle.getType() == 0)
								runRiddlePuzzle(userInput, riddleAnswer);
							// check puzzle type PuzzleKey
							
							// check if puzzle is solved
							if (puzzleSolved == true) {
								
								// check if puzzle has reward item
								if (puzzle.getItemReward() != null) {
									System.out.println("You gained the item ["
											+ puzzle.getItemReward().getName().toUpperCase() + "]");
									player.pickUp(puzzle.getItemReward());
								}
							}
						}
					}

					// user inputs N, returns to room
					if (userInput.equalsIgnoreCase("N")) {
						System.out.println("Leaving puzzle");
						puzzleRun = false;

					}

				}
			}
			}

		}
 
	public boolean getPuzzleSolved() {
		return puzzleSolved;
	}

	/**
	 * @method Checks for player's inventory for compatible key item with
	 *         PuzzleKey key item.
	 * @param puzzleKeyItem
	 */
	private void runKeyPuzzle(Item puzzleKeyItem, Player player) {

		// if player has puzzle key in inventory, the key puzzle is solved and
		// exits loop
		if (player.checkInventoryKeyItem(puzzleKeyItem)) {
			puzzle.setSolved(true);
			puzzleSolved = true;
			floor1Music();
		}
 
		// if player does not have puzzle key in inventory, puzzle is not solved
		// and exits loop
		else {
			if(puzzle.getId() == 3)
			{
				System.out.println("As the door open, you notice the hallway is too long. \nYou gently ripped a piece of you clothing and toss it into the room. \n Guns and laser came out of the wall and shot continueously until the cloth is no more visible.");
				System.out.println("You took a step back and thought \"I need to look for another way. Unless, if I can move fast enough I can probably avoid guns and lasers.\" ");
			}
			else if(puzzle.getId() == 6)
			{
				System.out.println("You spotted a computer termial by the southern wall.");
				System.out.println("You played with the computer and it prompt to input an access chip.");
				System.out.println("But appearently you dont have the access chip and left the computer alone");
			}
			else if(puzzle.getId() == 5)
			{
				System.out.println("You approached the door but stopped before the chasm.");
				System.out.println("You looked down and see nothing but the abyss calling out to you");
				System.out.println("You gauged the jumping distance and prepared to jump.");
				System.out.println("You ran but right before reaching the edge, you stopped.");
				System.out.println("You said, \"Nope, I won't make it.\"");
				System.out.println("You looked around the area and found a stronger outcropping of rock above the chasm with steel pikes sticking out.");
				System.out.println("You looked at the steel pikes, \"I might need something at will allow me to swing to the other side.\"");
			}
			else
			{
			System.out.println("-------------------------------------------------");
			System.out.println("Unable to traverse that room.");
			System.out.println("I will need [" + puzzleKeyItem.getName().toUpperCase() + "] if I want to go any further.");
			System.out.println("-------------------------------------------------");
			puzzleLoop = false;
			puzzleRun = false;
			floor1Music();
			}
		}
	}

	/**
	 * @method Checks for user's answer input with PuzzleRiddle solution.
	 * @param userInput
	 * @param riddleAnswer
	 */
	private void runRiddlePuzzle(String userInput, String riddleAnswer) {
		riddleLoop = true;

		while (riddleLoop) {
			System.out.println("What is your answer? (Type [LEAVE] to leave puzzle)");
			System.out.print(">>");
			userInput = input.nextLine();

			// user will exit riddle puzzle and puzzle loops
			if (userInput.equalsIgnoreCase("LEAVE")) {
				System.out.println("Leaving puzzle...\n");
				floor1Music();
				puzzleRun = false;
				riddleLoop = false;
				puzzleLoop = false;
			}

			// if user answers riddle correctly, riddle is solved and exits loop
			else if (riddleAnswer.equalsIgnoreCase(userInput)) {
				puzzle.setSolved(true);
				checkSequenceCorrect();
				solveMusic();
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				puzzleSolved = true;
				riddleLoop = false;
				puzzleLoop = false;
				puzzleRun = false;
				floor1Music();
			}

			// if riddle answer is incorrect
			else if (!riddleAnswer.equalsIgnoreCase(userInput)) {
				System.out.println("Your answer [" + userInput.toUpperCase() + "] is wrong.");
				boolean promptAgain = true;
				while(promptAgain) {
					checkSequenceIncorrect();
					System.out.println("Try again? (Y/N)");
					System.out.print(">>");
					userInput = input.nextLine();
	
					if (userInput.equalsIgnoreCase("N")) {
						System.out.println("Leaving puzzle...\n");
						promptAgain = false;
						riddleLoop = false;
						puzzleLoop = false;
						puzzleRun = false;
						floor1Music();
						break;
					}
					if(userInput.equalsIgnoreCase("Y")) {
						promptAgain = false;
					}
					else {
						System.out.println("Wrong input command.");
					}
				}
				
			}
			
			else {
				System.out.println("Invalid command. Try again.\n");
			}
		}

	}

	private void print(String string, long delay) {
		try {
			for (char ch : string.toCharArray()) {
				System.out.print(ch);
				TimeUnit.MILLISECONDS.sleep(delay);
			}
		} catch (InterruptedException e) {
			System.out.println("InterruptedException: print()");
		}

	}
	
	private void checkSequenceCorrect()
	{
		System.out.print("CHECKING INPUT");
		for(int i = 0; i < 3; i++)
		print("...", 300);
		System.out.println();
		System.out.println("INPUT IS CORRECT");
	}
	private void checkSequenceIncorrect()
	{
		System.out.print("CHECKING INPUT");
		for(int i = 0; i < 3; i++)
		print("...", 300);
		System.out.println();
		System.out.println("INPUT IS INCORRECT");
	}
	
	private void delay() {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	
	// public void print(String string, long delay) {
	// try {
	// for (char ch : string.toCharArray()) {
	// System.out.print(ch);
	// TimeUnit.MILLISECONDS.sleep(delay);
	// }
	// }
	// catch(InterruptedException e) {
	// System.out.println("InterruptedException: print()");
	// }
	//
	// }

	// private String wrapText(String longDescription){
	// String shortDesc = WordUtils.wrap(longDescription, 50);
	// return shortDesc;
	// }

}
