package Character;

import Item.Item;
/**
 * This class is responsible for monster attributes and behavior.
 * @author John
 */
public class Monster extends Character {
	
	private boolean isBoss;
	private Item loot;
	private String description;

	public Monster(int id, String name, int hp, int atk, int def, int spd, boolean isBoss, Item loot, String description){
		super(hp, atk, spd, def);
		this.id = id;
		this.name = name;
		this.description = description;
		this.isBoss = isBoss;
		this.loot = loot;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Item getLoot() {
		return loot;
	}

	public void setLoot(Item itemLoot) {
		this.loot = itemLoot;
	}

	public boolean isBoss() {
		return isBoss;
	}
	
	public void setIsBoss(boolean isBoss) {
		this.isBoss = isBoss;
	}
	
	@Override
	public String toString() {
		return "MONSTER\nHealth: [" + hp + "]\nAttack: [" + atk + "]\nSpeed: [" + spd + "]\nDefense: ["
				+ def + "]";
	}
	
}
