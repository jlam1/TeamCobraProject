package Puzzle;

import Item.*;
/**
 * Class is responisble for puzzle type PuzzleKey objects.
 * @author John
 *
 */
public class PuzzleKey extends Puzzle{

	private static final long serialVersionUID = -35284228181929451L;

	public PuzzleKey(int id, String name, int type, Item itemReward, KeyItem keyItem, String description) {
		super(id, name, type, itemReward, description);
		this.keyItem = keyItem;
	}
	
}
