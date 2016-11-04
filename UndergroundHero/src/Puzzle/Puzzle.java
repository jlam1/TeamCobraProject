package Puzzle;

import java.io.Serializable;

import Character.Properties;
import Item.*;
/**
 * This class is responsible for puzzle attributes and behaviors.
 * @author John
 *
 */
public abstract class Puzzle implements Properties, Serializable{

	private static final long serialVersionUID = 1045004670933137084L;
	protected String name, description;
	protected int id, type;
	protected boolean solved;
	protected Item itemReward;
	protected String riddleAnswer;
	protected KeyItem keyItem;
	
	public Puzzle(int id, String name, int type, Item itemReward, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.type = type;
		this.itemReward = itemReward;
		this.solved = false;
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

	@Override
	public String toString() {
		return "Name= " + name + "\nDesc= " + description + "\nSolved= " + solved;
	}
	
}
