package LogicController;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import Character.Player;
import Item.Item;
import Item.KeyItem;
import Puzzle.Puzzle;
import Room.*;
/**
 * This class is responsible for dealing with all puzzle logic.
 * This checks if the player inside the current room contains a puzzle, if so check the puzzle type and display information of puzzle to player.
 * @author John
 *
 */
public class PuzzleLogic {
	
	private boolean puzzleLoop, riddleLoop, puzzleSolved, puzzleRun;
	private Puzzle puzzle;
	private ArrayList<Item> playerInventory;
	private Scanner input;
	private String userInput;
	
	public PuzzleLogic(){
		input = new Scanner(System.in);
	}
	
	/**
	 * @method This method will check if puzzle exists in the current room.
	 * If empty, return null. If not the method wil prompt the player on console to initiate puzzle and 
	 * depending on the puzzle type it will display different behaviors.
	 * Puzzle key type will check player ArrayList<Item> inventory and puzzle type riddle
	 * will compare and check player's user input and the riddle's answer.
	 * @param currentRoom
	 * @param player
	 * @return true when puzzle solved, else return false
	 * @throws InterruptedException 
	 */
	public boolean initiatePuzzle(Room currentRoom, Player player) {
		
		puzzle = currentRoom.getRoomPuzzle();
		playerInventory = player.getInventory();

		puzzleSolved = false;
		puzzleRun = true;
		
		//check if puzzle is null AND if puzzle is NOT solved
		if(puzzle != null && puzzle.isSolved() == false) {
			
			print("There is a puzzle in this room...\n", 40);
			print("Do you want to initiate puzzle? (Y/N)\n", 40);
			
			do {
				System.out.print(">>");
				userInput = input.nextLine();
				
				//check user's choice (Y/N)
				if(userInput.equalsIgnoreCase("Y")) {
					print("Initiating puzzle", 40);
					print("...\n", 300);
					String puzzleDesc = puzzle.getDescription();
					KeyItem puzzleKeyItem = puzzle.getKeyItem();
					String riddleAnswer = puzzle.getRiddleAnswer();
					
					puzzleLoop = true;
					
					while(puzzleLoop) {
						System.out.println("-------------------------------------------------");
						System.out.println("The puzzle commands are:");
						System.out.println("[VIEW] to view the puzzle description\n[LEAVE] to leave puzzle and return to room");
						System.out.println("-------------------------------------------------");
						System.out.print(">>");
						userInput = input.nextLine();
						
						//user command 1
						if(userInput.equalsIgnoreCase("VIEW")) {
							System.out.println("-------------------------------------------------");
							System.out.println(puzzleDesc);
							System.out.println("-------------------------------------------------");
		
							//check puzzle type PuzzleKey
							if(puzzle.getType() == 1) {
								runKeyPuzzle(puzzleKeyItem);
							}
							
							//check puzzle type PuzzleRiddle
							if(puzzle.getType() == 0) {
								runRiddlePuzzle(userInput, riddleAnswer);
							}
							
						}
							
						//user command 2
						else if(userInput.equalsIgnoreCase("LEAVE")) {
							System.out.println("Left puzzle. Returning to room...\n");
							puzzleLoop = false;
						}
						
						else if(userInput.equalsIgnoreCase("HELP")) {
							System.out.println("-------------------------------------------------");
							System.out.println("Commands are: [VIEW PUZZLE] and [LEAVE].\n");
							System.out.println("-------------------------------------------------");
						}
						
						//user input invalid
						else {
							System.out.println("Invalid command input.\n");
						}
						
					}
				}
				
				//user inputs N, returns to room
				if(userInput.equalsIgnoreCase("N")) {
					System.out.println("You decided not to attempt the puzzle at this time.\n");
					puzzleRun = false;
				}
				
				else {
					System.out.println("Invalid input. Try again.");
				}

			} while(puzzleRun);
		}	
		
		return puzzleSolved;
	}
	
	/**
	 * @method Checks for player's inventory for compatible key item with PuzzleKey key item.
	 * @param puzzleKeyItem
	 */
	private void runKeyPuzzle(KeyItem puzzleKeyItem) {
			
			//if player has puzzle key in inventory, the key puzzle is solved and exits loop
			if(playerInventory.contains(puzzleKeyItem)) {
				puzzle.setSolved(true);
				System.out.println(puzzle.getName() + " has been solved!");
				puzzleRun = false;
				puzzleSolved = true;
				puzzleLoop = false;
			}
			
			//if player does not have puzzle key in inventory, puzzle is not solved and exits loop
			else {
				System.out.println("-------------------------------------------------");
				System.out.println("You do not have the key item: " + puzzleKeyItem.getName() + " to solve this puzzle.");
				System.out.println("You are returning back room...\n");
				System.out.println("-------------------------------------------------");
				puzzleLoop = false;
			}
	}
	
	/**
	 * @method Checks for user's answer input with PuzzleRiddle solution.
	 * @param userInput
	 * @param riddleAnswer
	 */
	private void runRiddlePuzzle(String userInput, String riddleAnswer) {
		riddleLoop = true;
		
		while(riddleLoop) {
			System.out.println("What is your answer? (Type [LEAVE] to leave puzzle)");
			System.out.print(">>");
			userInput = input.nextLine();
			
			//user will exit riddle puzzle and puzzle loops
			if(userInput.equalsIgnoreCase("LEAVE")) {
				System.out.println("Leaving riddle puzzle...\n");
				puzzleRun = false;
				riddleLoop = false;
				puzzleLoop = false;
			}
			
			//if user answers riddle correctly, riddle is solved and exits loop
			else if(riddleAnswer.equalsIgnoreCase(userInput)) {
				puzzle.setSolved(true);
				System.out.println("-------------------------------------------------");
				System.out.println("Your answer [" + userInput.toUpperCase() + "] is correct!");
				System.out.println(puzzle.getName() + " riddle has been solved!");
				System.out.println("-------------------------------------------------");
				puzzleSolved = true;
				riddleLoop = false;
				puzzleLoop = false;
			}
			
			//if riddle answer is incorrect
			else if(!riddleAnswer.equalsIgnoreCase(userInput)) {
				System.out.println("Your answer [" + userInput.toUpperCase() + "] is wrong.");
				System.out.println("Try again? (Y/N)");
				System.out.print(">>");
				userInput = input.nextLine();
				
				if(userInput.equalsIgnoreCase("N")) {
					System.out.println("Leaving puzzle...\n");
					riddleLoop = false;
					puzzleLoop = false;
				}
				else if(userInput.equalsIgnoreCase("Y")) {
					//do nothing
				}
				else{
					System.out.println("Invalid command.\n");
				}
			}
			//invalid command from user
			else {
				System.out.println("Invalid command. Try again.\n");
			}
		}

	}
	
	public void print(String string, long delay) {
		try {
		    for (char ch : string.toCharArray()) {
		        System.out.print(ch);
		        TimeUnit.MILLISECONDS.sleep(delay);
		    }
		}
		catch(InterruptedException e) {
			System.out.println("InterruptedException: print()");
		}

	}

//	private String wrapText(String longDescription){
//		String shortDesc = WordUtils.wrap(longDescription, 50);
//		return shortDesc;
//	}

}
