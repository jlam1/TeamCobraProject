public class Puzzle {

	private String puzzleName;
	
	private String puzzleID;

	private String description;

	private KeyItem keyAnswer;

	private boolean solved;

	private String logicAnswer;
	
	private String puzzleAnswer;
	
	private String puzzleReward;

	public Puzzle(){
		
	}

	public Puzzle(String puzzleName, String description, String puzzleAnswerDescription, String puzzleReward){
		this.puzzleName = puzzleName;
		this.description = description;
		this.puzzleAnswer = puzzleAnswerDescription;
		this.puzzleReward = puzzleReward;
	}
	
	public Puzzle(String puzzleName, String description)
	{
		this.puzzleName = puzzleName;
		this.description = description;
		
	}
	
	public Puzzle(String puzzleName, String description, KeyItem keyAnswer)
	{
		this.puzzleName = puzzleName;
		this.description = description;
		this.keyAnswer = keyAnswer;
		
	}
	
	public Puzzle(String puzzleName, String description, String logicAnswer)
	{
		this.puzzleName = puzzleName;
		this.description = description;
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
	 * @return the puzzle Name
	 */
	public String getPuzzleName()
	{
		return puzzleName;
	}


	/**
	 * @param puzzleName set the Puzzle Name
	 */
	public void setPuzzleName(String puzzleName)
	{
		this.puzzleName = puzzleName;
	}


	/**
	 * @return the keyAnswer
	 */
	public KeyItem getKeyAnswer()
	{
		return keyAnswer;
	}


	/**
	 * @param keyAnswer set the key answer
	 */
	public void setKeyAnswer(KeyItem keyAnswer)
	{
		this.keyAnswer = keyAnswer;
	}


	/**
	 * @return solved
	 */
	public boolean isSolved()
	{
		return solved;
	}


	/**
	 * @param solved set the solved variable
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
	 * @param logicAnswer set the logic answer
	 */
	public void setLogicAnswer(String logicAnswer)
	{
		this.logicAnswer = logicAnswer;
	}
	
	public String getPuzzleAnswerDescription(){
		return puzzleAnswer;
	}
	
	public String getPuzzleReward(){
		return puzzleReward;
	}

	/**
	 * @return the puzzleID
	 */
	public String getPuzzleID() {
		return puzzleID;
	}

	/**
	 * @param puzzleID set the puzzle ID
	 */
	public void setPuzzleID(String puzzleID) {
		this.puzzleID = puzzleID;
	}
	
}
