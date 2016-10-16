public class Puzzle {

	private String description;

	private int puzzleID;

	private KeyItem keyAnswer;

	private boolean solved;

	private String logicAnswer;


	public Puzzle(String description, int puzzleID, KeyItem keyAnswer, boolean solved, String logicAnswer)
	{
		this.description = description;
		this.puzzleID = puzzleID;
		this.keyAnswer = keyAnswer;
		this.solved = solved;
		this.logicAnswer = logicAnswer;
	}


	/**
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}


	/**
	 * @param description the description to set
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}


	/**
	 * @return the puzzleID
	 */
	public int getPuzzleID()
	{
		return puzzleID;
	}


	/**
	 * @param puzzleID the puzzleID to set
	 */
	public void setPuzzleID(int puzzleID)
	{
		this.puzzleID = puzzleID;
	}


	/**
	 * @return the keyAnswer
	 */
	public KeyItem getKeyAnswer()
	{
		return keyAnswer;
	}


	/**
	 * @param keyAnswer the keyAnswer to set
	 */
	public void setKeyAnswer(KeyItem keyAnswer)
	{
		this.keyAnswer = keyAnswer;
	}


	/**
	 * @return the solved
	 */
	public boolean isSolved()
	{
		return solved;
	}


	/**
	 * @param solved the solved to set
	 */
	public void setSolved(boolean solved)
	{
		this.solved = solved;
	}


	/**
	 * @return the logicAnswer
	 */
	public String getLogicAnswer()
	{
		return logicAnswer;
	}


	/**
	 * @param logicAnswer the logicAnswer to set
	 */
	public void setLogicAnswer(String logicAnswer)
	{
		this.logicAnswer = logicAnswer;
	}
	
}
