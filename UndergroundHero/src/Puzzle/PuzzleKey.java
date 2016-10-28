package Puzzle;

import Item.*;

public class PuzzleKey extends Puzzle{

	public PuzzleKey(int id, String name, int type, KeyItem keyItem, String description) {
		super(id, name, type, description);
		this.keyItem = keyItem;
	}
	
}
