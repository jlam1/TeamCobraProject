package Item;

import java.io.Serializable;

import Game.Properties;
/**
 * This abstract class is responsible for providing common attributes and behaviors for each item object type.
 * @author John
 *
 */
public abstract class Item implements Properties, Serializable{
	private static final long serialVersionUID = -3361235989041007891L;
	private int id;
	private String name, type, description;
	
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

	@Override
	public String toString() {
		return "Item [name=" + name + ", description=" + description + ", type=" + type + "]";
	}

}
