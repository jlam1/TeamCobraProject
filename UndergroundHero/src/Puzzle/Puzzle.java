package Puzzle;

import java.io.Serializable;

import Character.Properties;
import Item.*;

/**
 * 
 * This class is responsible for all puzzle attributes and behaviors.
 * 
 * @author John
 *
 */
public class Puzzle implements Properties, Serializable {

	private static final long serialVersionUID = 1045004670933137084L;
	protected String name, description;
	protected int id, type;
	protected boolean solved;
	protected Item itemReward;
	protected String riddleAnswer;
	protected KeyItem keyItem;

	/**
	 * The following is the constructor method for regular Puzzle objects.
	 * 
	 * @param id
	 * @param name
	 * @param type
	 * @param itemReward
	 * @param description
	 */
	public Puzzle(int id, String name, int type, Item itemReward, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.type = type;
		this.itemReward = itemReward;
		this.solved = false;
	}

	/**
	 * The following is the unique constructor method for PuzzleRiddle Puzzle
	 * objects.
	 * 
	 * @param id
	 * @param name
	 * @param type
	 * @param itemReward
	 * @param riddleAnswer
	 *            Specific to PuzzleRiddle type Puzzle objects.
	 * @param description
	 */
	public Puzzle(int id, String name, int type, Item itemReward, String riddleAnswer, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.type = type;
		this.itemReward = itemReward;
		this.solved = false;
		this.riddleAnswer = riddleAnswer;
	}

	/**
	 * The following is the unique constructor method for PuzzleKey Puzzle
	 * objects.
	 * 
	 * @param id
	 * @param name
	 * @param type
	 * @param itemReward
	 * @param keyItem
	 *            Specific to PuzzleKey type Puzzle objects.
	 * @param description
	 */
	public Puzzle(int id, String name, int type, Item itemReward, KeyItem keyItem, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.type = type;
		this.itemReward = itemReward;
		this.solved = false;
		this.keyItem = keyItem;
	}

	public Item getItemReward() {
		return itemReward;
	}

	public void setItemReward(Item itemReward) {
		this.itemReward = itemReward;
	}

	public KeyItem getKeyItem() {
		return keyItem;
	}

	public void setKeyItem(KeyItem keyItem) {
		this.keyItem = keyItem;
	}

	public String getRiddleAnswer() {
		return riddleAnswer;
	}

	public void setRiddleAnswer(String riddleAnswer) {
		this.riddleAnswer = riddleAnswer;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public int getType() {
		return type;
	}

	public boolean isSolved() {
		return solved;
	}

	public void setSolved(boolean solved) {
		this.solved = solved;
	}

	/**
	 * The following method overrides the toString method for Object to create a
	 * custom Puzzle toString().
	 */
	@Override
	public String toString() {
		return "Name= " + name + "\nDesc= " + description + "\nSolved= " + solved;
	}

}
