package LogicController;

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

	public PuzzleLogic(Scanner in) {
		input = in;
		musicLogic = new MusicLogic("src/sound/puzzle.wav");
	}

	public void puzzleMusic() {
		musicLogic.BGMStop();
		musicLogic = new MusicLogic("src/sound/puzzle.wav");
		musicLogic.BGMLoop();
	}

	public void transverseMusic() {
		musicLogic.BGMStop();
		musicLogic = new MusicLogic("src/sound/traverse.wav");
		musicLogic.BGMLoop();
	}

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

			System.out.println("There is a puzzle in this room...");
			System.out.println("Do you want to initiate puzzle? (Y/N)");

			while (puzzleRun) {
				System.out.print(">>");
				userInput = input.nextLine();
				// check puzzle type PuzzleKey
				if (puzzle.getType() == 1) {
					runKeyPuzzle(puzzleKeyItem, player);
					// check if puzzle is solved
					if (puzzleSolved == true) {
						// check if puzzle has reward item
						if (puzzle.getItemReward() != null) {
							System.out.println(
									"Your reward is: [" + puzzle.getItemReward().getName().toUpperCase() + "]");
							player.pickUp(puzzle.getItemReward());
						}
					}
					puzzleRun = false;
				}
					//check puzzle type PuzzleRiddle
					if(puzzle.getType() == 0)
					{
						runRiddlePuzzle(userInput, riddleAnswer);
						viewCommands();
					// check user's choice (Y/N)
					if (userInput.equalsIgnoreCase("Y")) {
						System.out.println("Initiating puzzle");
						puzzleMusic();
						runRiddlePuzzle(userInput, riddleAnswer);
						userInput = input.nextLine();

						// user command 1
						if (userInput.equalsIgnoreCase("VIEW")) {
							System.out.println("-------------------------------------------------");
							System.out.println(puzzleDesc);
							System.out.println("-------------------------------------------------");

							// check if puzzle is solved
							if (puzzleSolved == true) {
								// check if puzzle has reward item
								if (puzzle.getItemReward() != null) {
									System.out.println(
											"Your reward is: [" + puzzle.getItemReward().getName().toUpperCase() + "]");
									player.pickUp(puzzle.getItemReward());
								}
							}
						}

						// user command 2
						if (userInput.equalsIgnoreCase("LEAVE")) {
							System.out.println("Leaving puzzle");
							puzzleLoop = false;
							puzzleRun = false;
							transverseMusic();
						}

						if (userInput.equalsIgnoreCase("HELP")) {
							System.out.println("-------------------------------------------------");
							System.out.println("Commands are: [VIEW PUZZLE] and [LEAVE].\n");
							System.out.println("-------------------------------------------------");
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
			System.out.println(puzzle.getName() + " has been solved!");
			puzzleRun = false;
			puzzleLoop = false;
			puzzleSolved = true;
			transverseMusic();
		}

		// if player does not have puzzle key in inventory, puzzle is not solved
		// and exits loop
		else {
			System.out.println("-------------------------------------------------");
			System.out.println("You do not have the key item: " + puzzleKeyItem.getName() + " to solve this puzzle.");
			System.out.println("You are returning back room");
			System.out.println("-------------------------------------------------");
			puzzleLoop = false;
			puzzleRun = false;
			transverseMusic();
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
				System.out.println("Leaving riddle puzzle...\n");
				puzzleRun = false;
				riddleLoop = false;
				puzzleLoop = false;
				transverseMusic();
			}

			// if user answers riddle correctly, riddle is solved and exits loop
			else if (riddleAnswer.equalsIgnoreCase(userInput)) {
				puzzle.setSolved(true);
				System.out.println("-------------------------------------------------");
				System.out.println("Your answer [" + userInput.toUpperCase() + "] is correct!");
				System.out.println(puzzle.getName() + " riddle has been solved!");
				System.out.println("-------------------------------------------------");
				puzzleSolved = true;
				riddleLoop = false;
				puzzleLoop = false;
				puzzleRun = false;
				transverseMusic();
			}

			// if riddle answer is incorrect
			else if (!riddleAnswer.equalsIgnoreCase(userInput)) {
				System.out.println("Your answer [" + userInput.toUpperCase() + "] is wrong.");
				System.out.println("Try again? (Y/N)");
				System.out.print(">>");
				userInput = input.nextLine();

				if (userInput.equalsIgnoreCase("N")) {
					System.out.println("Leaving puzzle...\n");
					riddleLoop = false;
					puzzleLoop = false;
					puzzleRun = false;
					transverseMusic();
				} else if (userInput.equalsIgnoreCase("Y")) {
					// do nothing
				} else {
					System.out.println("Invalid command.\n");
				}
			}
			// invalid command from user
			else {
				System.out.println("Invalid command. Try again.\n");
			}
		}

	}

	private void viewCommands() {
		System.out.println("-------------------------------------------------");
		System.out.println("The puzzle commands are:");
		System.out.println("[VIEW] to view the puzzle description\n[LEAVE] to leave puzzle and return to room");
		System.out.println("-------------------------------------------------");
		System.out.print(">>");
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
