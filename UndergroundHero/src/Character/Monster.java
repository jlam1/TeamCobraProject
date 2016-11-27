package Character;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Item.Item;

/**
 * 
 * This class is responsible for monster attributes and behaviors.
 * 
 * @author John, Matt
 */
public class Monster extends Character implements Serializable {

	private static final long serialVersionUID = -8132473599872303086L;
	private boolean isBoss;
	private Item loot;
	private String description;
	private List<Skill> skillList;

	public Monster(int id, String name, int maxhp, int hp, int atk, int def, int spd, boolean isBoss, Item loot,
			String description) {
		super(maxhp, hp, atk, spd, def);
		this.id = id;
		this.name = name;
		this.description = description;
		this.isBoss = isBoss;
		this.loot = loot;
		skillList = new ArrayList<Skill>();
	}
	
	public void addSkill(Skill skill) {
		this.skillList.add(skill);
	}
	
	public List<Skill> getSkillList() {
		return skillList;
	}
	
	public void setSkillList(List<Skill> skillList) {
		this.skillList = skillList;
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
		return "MONSTER"
				+ "\nHealth: [" + hp + "]"
				+ "\nAttack: [" + atk + "]"
				+ "\nDefense: [" + def + "]"
				+ "\nSpeed: [" + spd + "]";
	}

}
