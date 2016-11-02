package Character;

import Game.Properties;

public abstract class Character implements Properties {
	
	protected int id;
	public int hp;
	public int atk;
	protected int spd;
	public int def;
	protected String name, description;
//<<<<<<< HEAD
	protected boolean isDead;
//=======
	protected boolean dead;
//<<<<<<< HEAD
//<<<<<<< HEAD
//=======
//=======
//<<<<<<< HEAD
//=======
//>>>>>>> 9eae5d48af3d56d29a6beee13229a526215d04fe
//>>>>>>> branch 'master' of https://github.com/jlam1/TeamCobraProject.git
	
	
//>>>>>>> 7b579141f7e7bb636c3c0eb633b99929fa72242b
	
	public Character(int hp, int atk, int spd, int def) {
		this.hp = hp;
		this.atk = atk;
		this.spd = spd;
		this.def = def;
		this.isDead = false;
	}
//<<<<<<< HEAD
//=======
	
//>>>>>>> 7b579141f7e7bb636c3c0eb633b99929fa72242b

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
