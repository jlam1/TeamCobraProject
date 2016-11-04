package Puzzle;

import Item.*;

/**
 * Class is responsible for puzzle type PuzzleRiddle objects.
 * @author John
 *
 */
public class PuzzleRiddle extends Puzzle{

	private static final long serialVersionUID = -8063374268538882868L;

	public PuzzleRiddle(int id, String name, int type, Item itemReward, String riddleAnswer, String description) {
		super(id, name, type, itemReward, description);
		this.riddleAnswer = riddleAnswer;
	}
	
}
