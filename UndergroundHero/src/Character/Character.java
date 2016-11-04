package Character;

import java.io.Serializable;

/**
 * 
 * This is an abstract super class for character entities which share common
 * behaviors and attributes.
 * 
 * @author John, Kyle, Matt
 */
public abstract class Character implements Properties, Serializable {

	private static final long serialVersionUID = -5789756218470902984L;
	protected int id, maxhp, hp, atk, spd, def;
	protected String name;
	protected boolean dead;

	/**
	 * The following is the constructor for Character objects.
	 * 
	 * @param maxHp
	 *            This is the first parameter to to Character constructor.
	 * @param hp
	 *            This is the second parameter to to Character constructor.
	 * @param atk
	 *            This is the third parameter to to Character constructor.
	 * @param spd
	 *            This is the fourth parameter to to Character constructor.
	 * @param def
	 *            This is the fifth parameter to to Character constructor.
	 */
	public Character(int maxhp, int hp, int atk, int spd, int def) {
		this.maxhp = maxhp;
		this.hp = hp;
		this.atk = atk;
		this.spd = spd;
		this.def = def;
		this.dead = false;
	}

	/**
	 * The following method is the main attack logic. This calculates damage
	 * output to character object, and prints a string providing information for
	 * the attack.
	 * 
	 * @param Character
	 *            This is the only parameter for the attack method.
	 * @return Void
	 */
	public void attack(Character character) {
		character.setHp(character.getHp() - this.getAtk());
		System.out.println("[" + this.getName().toUpperCase() + "] strikes [" + character.getName().toUpperCase()
				+ "] for " + this.getAtk() + " damage!");
	}

	public int getMaxhp() {
		return maxhp;
	}

	public void setMaxhp(int maxhp) {
		this.maxhp = maxhp;
	}

	public int getId() {
		return id;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getAtk() {
		return atk;
	}

	public void setAtk(int atk) {
		this.atk = atk;
	}

	public int getSpd() {
		return spd;
	}

	public void setSpd(int spd) {
		this.spd = spd;
	}

	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

}
