package Character;

import Item.Item;

public class Monster extends Character {

	/**
	 * 
	 * @param name
	 * @param hp
	 * @param atk
	 * @param def
	 * @param spd
	 * @param dead
	 * @param isBoss
	 * @param description
	 */
	public Monster(int id, String name, int hp, int atk, int def, int spd, boolean boss, String description){
		super(hp, atk, spd, def);
		this.id = id;
		this.name = name;
		this.description = description;
		this.boss = boss;
	}

	@Override
	public String toString() {
		return "MONSTER\nHealth: [" + hp + "]\nAttack: [" + atk + "]\nSpeed: [" + spd + "]\nDefense: ["
				+ def + "]";
	}
	
}
