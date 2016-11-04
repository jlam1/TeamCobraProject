package Character;

import java.io.Serializable;

import Game.Properties;

/**
 * 
 * This is an abstract class for character entities that shares a common behavior and attribute.
 * @author John, Kyle, Matt
 */
public abstract class Character implements Properties, Serializable {
	
	protected int id, maxhp, hp, atk, spd, def;
	protected String name, description;
	protected boolean dead;
	
	public Character(int maxhp, int hp, int atk, int spd, int def) {
		this.maxhp = maxhp;
		this.hp = hp;
		this.atk = atk;
		this.spd = spd;
		this.def = def;
		this.dead = false;
	}
	
	/**
	 * @method Main attack logic, calculates damage output to character object.
	 * @param Character
	 */
	public void attack(Character character){
		character.setHp(character.getHp() - this.getAtk());
		System.out.println("[" + this.getName().toUpperCase() + "] strikes [" + character.getName().toUpperCase() + "] for " + this.getAtk() + " damage!");
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
