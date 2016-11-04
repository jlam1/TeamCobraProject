package Item;

import java.io.Serializable;

import Character.Properties;

/**
 * This is an abstract super class for Item objects which share common behaviors
 * and attributes.
 * 
 * @author John
 *
 */
public abstract class Item implements Properties, Serializable {
	private static final long serialVersionUID = -3361235989041007891L;
	private int id;
	private String name, type, description;

	/**
	 * The following is the constructor method for Item objects.
	 * 
	 * @param id
	 *            This is the first parameter to to Item constructor.
	 * @param name
	 *            This is the second parameter to to Item constructor.
	 * @param type
	 *            This is the third parameter to to Item constructor.
	 * @param description
	 *            This is the fourth parameter to to Item constructor.
	 */
	public Item(int id, String name, String type, String description) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.description = description;
	}

	public int getId() {
		return id;
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

	public String getType() {
		return type;
	}

	/**
	 * The following method overrides the toString method for Object to create a
	 * custom Item toString().
	 */
	@Override
	public String toString() {
		return "Item [name=" + name + ", description=" + description + ", type=" + type + "]";
	}

}
