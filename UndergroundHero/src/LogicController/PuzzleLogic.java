package LogicController;

import java.util.ArrayList;
import java.util.Scanner;

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
	
	/**
	 * @method This method will check if puzzle exists in the current room.
	 * If empty, return null.
	 * If not the method wil prompt the player on console to initiate puzzle and 
	 * depending on the puzzle type it will display different behaviors.
	 * Puzzle key type will check player ArrayList<Item> inventory and puzzle type riddle
	 * will compare and check player's user input and the riddle's answer.
	 * @param currentRoom
	 * @param player
	 * @return true when puzzle solved, else return false
	 */
	public boolean initiatePuzzle(Room currentRoom, Player player, Scanner input) {
		
		boolean puzzleLoop, riddleLoop, puzzleSolved;
		Puzzle puzzle = currentRoom.getRoomPuzzle();	
		ArrayList<Item> playerInventory = player.getInventory();

//		Scanner input = new Scanner(System.in);
		String userInput;
		
		puzzleSolved = false;
		
		//check if puzzle is null AND if puzzle is NOT solved
		if(puzzle != null && puzzle.isSolved() == false) {
			System.out.println("-------------------------------------------------");
			System.out.println("There is a puzzle in this room...");
			System.out.println("Do you want to initiate puzzle? (Y/N)");
			System.out.println("-------------------------------------------------");
			System.out.print(">>");
			userInput = input.nextLine();
		
			//check user's choice (Y/N)
			if(userInput.equalsIgnoreCase("Y")) {
				System.out.println("Initiating puzzle...\n");
				String puzzleDesc = puzzle.getDescription();
				KeyItem puzzleKeyItem = puzzle.getKeyItem();
				String riddleAnswer = puzzle.getRiddleAnswer();
				
				puzzleLoop = true;
				
				while(puzzleLoop) {
					System.out.println("Commands are: [VIEW PUZZLE] and [LEAVE]");
					System.out.print(">>");
					userInput = input.nextLine();
					
					//user command 1
					if(userInput.equalsIgnoreCase("VIEW PUZZLE")) {
						System.out.println("-------------------------------------------------");
						System.out.println(puzzleDesc);
						System.out.println("-------------------------------------------------");
	
						//check puzzle type PuzzleKey
						if(puzzle.getType() == 1) {
							
							//if player has puzzle key in inventory, the key puzzle is solved and exits loop
							if(playerInventory.contains(puzzleKeyItem)) {
								puzzle.setSolved(true);
								System.out.println(puzzle.getName() + " has been solved!");
								puzzleSolved = true;
								puzzleLoop = false;
							}
							
							//if player does not have puzzle key in inventory, puzzle is not solved and exits loop
							else {
								System.out.println("You do not have the key item: " + puzzleKeyItem.getName() + " to solve this puzzle.");
								System.out.println("You are returning back room...\n");
								puzzleLoop = false;
							}
						}
						
						//check puzzle type PuzzleRiddle and prompts user for riddle answer input
						if(currentRoom.getRoomPuzzle().getType() == 0) {
							riddleLoop = true;
							
							while(riddleLoop) {
								System.out.println("What is your answer? (Type EXIT to exit puzzle)");
								System.out.print(">>");
								userInput = input.nextLine();
								
								//user will exit riddle puzzle and puzzle loops
								if(userInput.equalsIgnoreCase("EXIT")) {
									System.out.println("Leaving riddle puzzle...\n");
									riddleLoop = false;
									puzzleLoop = false;
								}
								
								//if user answers riddle correctly, riddle is solved and exits loop
								else if(riddleAnswer.equalsIgnoreCase(userInput)) {
									puzzle.setSolved(true);
									System.out.println(puzzle.getName() + " has been solved!\n");
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
									System.out.println("Invalid command for riddle puzzle. Try again.\n");
								}
							}
						}
						
					}
						
					//user command 2
					else if(userInput.equalsIgnoreCase("LEAVE")) {
						System.out.println("Leaving puzzle...\n");
						puzzleLoop = false;
					}
					
					else if(userInput.equalsIgnoreCase("HELP")) {
						System.out.println("Commands are: [VIEW PUZZLE] and [LEAVE].\n");
					}
					
					//user input invalid
					else {
						System.out.println("Invalid command for puzzle.\n");
					}
					
				}
			}
			
			//user inputs N, returns to room
			else if(userInput.equalsIgnoreCase("N")) {
				System.out.println("You decided not to attempt the puzzle at this time.\n");
			}
			
			else {
				System.out.println("Invalid command, returning to room...\n");
			}
		}	
		
		return puzzleSolved;
	}

//	private String wrapText(String longDescription){
//		String shortDesc = WordUtils.wrap(longDescription, 50);
//		return shortDesc;
//	}

}
