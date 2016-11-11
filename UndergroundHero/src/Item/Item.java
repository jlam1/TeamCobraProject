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
public class Item implements Properties, Serializable {
	private static final long serialVersionUID = -3361235989041007891L;
	private int id;
	private String name, type, description;
	private int armorDef;
	private int weaponAtk;

	/**
	 * The following is the constructor method for basic Item objects.
	 * 
	 * @param id
	 * @param name
	 * @param type
	 * @param description
	 */
	public Item(int id, String name, String type, String description) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.description = description;
	}

	/**
	 * The following method is the constructor for Armor type Item objects.
	 * 
	 * @param id
	 * @param name
	 * @param type
	 * @param armorDef
	 *            Specific to Armor type Item objects.
	 * @param description
	 * 
	 */
	public Item(int id, String name, String type, int armorDef, String description) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.description = description;
		this.armorDef = armorDef;
	}

	/**
	 * The following method is the constructor for Weapon type Item objects.
	 * 
	 * @param id
	 * @param name
	 * @param type
	 * @param description
	 * @param weaponAtk
	 *            Specific to Weapon type Item objects.
	 */
	public Item(int id, String name, String type, String description, int weaponAtk) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.description = description;
		this.weaponAtk = weaponAtk;
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

	public int getArmorDef() {
		return armorDef;
	}

	public void setArmorDef(int armorDef) {
		this.armorDef = armorDef;
	}

	public int getWeaponAtk() {
		return weaponAtk;
	}

	public void setWeaponAtk(int weaponAtk) {
		this.weaponAtk = weaponAtk;
	}

}
