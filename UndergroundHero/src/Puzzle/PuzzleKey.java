package Puzzle;

import Item.*;

public class PuzzleKey extends Puzzle{

	/**
	 * 
	 */
	private static final long serialVersionUID = -35284228181929451L;

	public PuzzleKey(int id, String name, int type, KeyItem keyItem, String description) {
		super(id, name, type, description);
		this.keyItem = keyItem;
	}
	
}
