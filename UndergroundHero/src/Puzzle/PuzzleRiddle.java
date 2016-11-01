package Puzzle;

public class PuzzleRiddle extends Puzzle{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8063374268538882868L;

	public PuzzleRiddle(int id, String name, int type, String riddleAnswer, String description) {
		super(id, name, type, description);
		this.riddleAnswer = riddleAnswer;
	}
	
}
