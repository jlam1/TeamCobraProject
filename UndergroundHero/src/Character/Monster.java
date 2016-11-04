package Character;

import java.io.Serializable;

import Item.Item;

/**
 * This class is responsible for monster attributes and behaviors.
 * 
 * @author John
 */
public class Monster extends Character implements Serializable {

	private static final long serialVersionUID = -8132473599872303086L;
	private boolean isBoss;
	private Item loot;
	private String description;

	/**
	 * @param id
	 *            Specific to Monster.
	 * @param name
	 *            Specific to Monster.
	 * @param isBoss
	 *            Specific to Monster.
	 * @param loot
	 *            Specific to Monster.
	 * @param description
	 *            Specific to Monster.
	 * @param hp
	 *            From parent.
	 * @param maxHP
	 *            From parent.
	 * @param atk
	 *            From parent.
	 * @param def
	 *            From parent.
	 * @param spd
	 *            From parent.
	 */
	public Monster(int id, String name, int maxhp, int hp, int atk, int def, int spd, boolean isBoss, Item loot,
			String description) {
		super(maxhp, hp, atk, spd, def);
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
		return "MONSTER\nHealth: [" + hp + "]\nAttack: [" + atk + "]\nSpeed: [" + spd + "]\nDefense: [" + def + "]";
	}

}
