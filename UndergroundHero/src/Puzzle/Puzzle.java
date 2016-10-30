package Puzzle;

import java.io.Serializable;

import Game.Properties;
import Item.*;

public abstract class Puzzle implements Properties, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1045004670933137084L;
	protected String name, description, riddleAnswer;
	protected int id, type;
	protected boolean solved;
	protected KeyItem keyItem;
	
	public Puzzle(int id, String name, int type, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.type = type;
		this.solved = false;
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

	public String getRiddleAnswer() {
		return riddleAnswer;
	}

	public void setRiddleAnswer(String riddleAnswer) {
		this.riddleAnswer = riddleAnswer;
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

	public KeyItem getKeyItem() {
		return keyItem;
	}

	@Override
	public String toString() {
		return "Name= " + name + "\nDesc= " + description + "\nSolved= " + solved;
	}
	
}
