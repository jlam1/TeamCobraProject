package Character;

import java.io.Serializable;

/**
 * Class is responsible for skill objects that both monster and player will use.
 * @author John
 *
 */
public class Skill implements Serializable{

	private static final long serialVersionUID = -5814177093313468577L;
	private String name, description, chargeDesc, type;
	private int id;
	private double dmgRatio, chance;
	private boolean isCharging;
	
	public Skill(int id, String name, String type, double dmgRatio, double chance, String description) {
		this.name = name;
		this.type = type;
		this.description = description;
		this.id = id;
		this.dmgRatio = dmgRatio;
		this.chance = chance;
	}
	
	public Skill(int id, String name, String type, double dmgRatio, double chance, boolean isCharging, String description, String chargeDesc) {
		this.name = name;
		this.type = type;
		this.description = description;
		this.id = id;
		this.dmgRatio = dmgRatio;
		this.chance = chance;
		this.isCharging = isCharging;
		this.chargeDesc = chargeDesc;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isCharging() {
		return isCharging;
	}

	public void setCharging(boolean isCharging) {
		this.isCharging = isCharging;
	}

	public String getChargeDesc() {
		return chargeDesc;
	}

	public void setChargeDesc(String chargeDesc) {
		this.chargeDesc = chargeDesc;
	}

	public void setDmgRatio(double dmgRatio) {
		this.dmgRatio = dmgRatio;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getDmgRatio() {
		return dmgRatio;
	}

	public void setDamage(double dmgRatio) {
		this.dmgRatio = dmgRatio;
	}

	public double getChance() {
		return chance;
	}

	public void setChance(double chance) {
		this.chance = chance;
	}
	
}
