package Character;

import Game.Properties;

/**
 * 
 * This is an abstract class for character entities that shares a common behavior and attribute.
 * @author John
 */
public abstract class Character implements Properties {
	
	protected int id, hp, atk, spd, def;
	protected String name, description;

	protected boolean isDead;

	
	public Character(int hp, int atk, int spd, int def) {
		this.hp = hp;
		this.atk = atk;
		this.spd = spd;
		this.def = def;
		this.isDead = false;
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
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public boolean getIsDead() {
		return isDead;
	}
	
	public void setIsDead(boolean isDead) {
		this.isDead = isDead;
	}
	
}
