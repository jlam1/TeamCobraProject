package LogicController;

import java.util.ArrayList;
import java.util.Scanner;

import Character.Player;
import Item.Item;
import Puzzle.Puzzle;
import Room.Room;

public class PuzzleSystem {
	
	private boolean puzzleLoop, riddleLoop, puzzleSolved, puzzleRun;
	private Puzzle puzzle;
	private ArrayList<Item> playerInventory;
	private Scanner input;
	private String userInput;
	
	public PuzzleSystem(Scanner in) {
		input = in;
	}
	
	public boolean initiatePuzzleSystem(Room currentRoom, Player player) {
		
		puzzle = currentRoom.getRoomPuzzle();
		playerInventory = player.getInventory();
		
		
		
	}
	
	private void runRiddlePuzzle() {
		
	}
	
	private void runKeyPuzzle() {
		
	}

}
