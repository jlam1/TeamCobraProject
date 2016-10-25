public class Puzzle implements Properties{

	private String name, description, logicAnswer, puzzleAnswer, puzzleReward;
	private int id;
	private KeyItem keyAnswer;
	private boolean solved;


	public Puzzle(){
		
	}

	public Puzzle(String name, String description, String puzzleAnswerDescription, String puzzleReward){
		this.name = name;
		this.description = description;
		this.puzzleAnswer = puzzleAnswerDescription;
		this.puzzleReward = puzzleReward;
	}
	
	public Puzzle(int id, String name, String description)
	{
		this.id = id;
		this.name = name;
		this.description = description;
		
	}
	
	public Puzzle(String name, String description, KeyItem keyAnswer)
	{
		this.name = name;
		this.description = description;
		this.keyAnswer = keyAnswer;
		
	}
	
	public Puzzle(String name, String description, String logicAnswer)
	{
		this.name = name;
		this.description = description;
		this.logicAnswer = logicAnswer;
		
	}

	@Override
	public String toString() {
		return "Puzzle [name=" + name + ", description=" + description + ", logicAnswer=" + logicAnswer
				+ ", puzzleAnswer=" + puzzleAnswer + ", puzzleReward=" + puzzleReward + ", id=" + id + ", keyAnswer="
				+ keyAnswer + ", solved=" + solved + "]";
	}

	public int getID() {
		return this.id;
	}

	public String getName() {
		return this.name;
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
	 * @param puzzleName set the Puzzle Name
	 */
	public void setName(String name)
	{
		this.name = name;
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
	
}
