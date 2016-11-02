package Character;

import java.io.Serializable;

import Game.Properties;

/**
 * 
 * This is an abstract class for character entities that shares a common behavior and attribute.
 * @author John, Kyle
 */
public abstract class Character implements Properties, Serializable {
	
	protected int id;
	public int hp;
	public int atk;
	protected int spd;
	public int def;
	protected String name, description;
	protected boolean isDead;
	protected int maxHP;
	protected boolean dead;
	
	public Character(int maxHP, int atk, int spd, int def) {
		this.hp = maxHP;
		this.maxHP = maxHP;
		this.atk = atk;
		this.spd = spd;
		this.def = def;
		this.isDead = false;
	}
	/**
	 * @method Main attack logic, calculates damage output to character object.
	 * @param character
	 */
	public void attack(Character character){
		System.out.println(character.getName() + " HP: [" + character.getHp() + "]" + " (Before Attacked)");
		character.setHp(character.getHp() - this.atk);
		System.out.println(character.getName() + " has been hit for [" + this.atk + "] damage!");
		System.out.println(character.getName() + " HP: [" + character.getHp() + "]" + " (After Attacked)\n");
	}
	
	/**
	 * @method Main defend logic, calculates damage taken after defense calculation.
	 * @param character
	 */
	public void defend(Character character){
		
	}
	
	public int getMaxHP() {
		int maxHP = this.maxHP;
		return maxHP;
	}
	
	/**
	 * @method Compare speed and set true if higher.
	 * @param character
	 * @return boolean
	 */
	public boolean flee(Character character){
		if(this.spd > character.getSpd()) {
			return true;
		}
		else {
			return false;
		}
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
	
	public boolean getIsDead() {
		return isDead;
	}
	
	public void setIsDead(boolean isDead) {
		this.isDead = isDead;
	}
	
}
