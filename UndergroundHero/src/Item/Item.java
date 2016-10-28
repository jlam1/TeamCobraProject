package Item;
import java.util.List;

import Game.Properties;

public abstract class Item implements Properties{

	private int id;
	private String name, type, description;
	protected int weaponAtk, armorDef, count;
	
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

	public int getWeaponAtk() {
		return weaponAtk;
	}

	public void setWeaponAtk(int weaponAtk) {
		this.weaponAtk = weaponAtk;
	}

	public int getArmorDef() {
		return armorDef;
	}

	public void setArmorDef(int armorDef) {
		this.armorDef = armorDef;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	public void countItem() {
		this.count++;
	}
	
	public void useItem() {
		this.count--;
	}

	@Override
	public String toString() {
		return "Item [name=" + name + ", description=" + description + ", type=" + type + "]";
	}

	/**
	 * 
	 * @param list
	 * Method will display a list of items using toString
	 */
	public void openInventory(List<Item> list){
		for(Item i : list){
			System.out.println(i.toString());
		}
	}
	
	

}
