package Character;


public class Skill {
	
	private String name, description;
	private int id, damage;
	private double chance;
	
	public Skill(String name, int id, int damage, double chance, String description) {
		this.name = name;
		this.description = description;
		this.id = id;
		this.damage = damage;
		this.chance = chance;
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

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public double getChance() {
		return chance;
	}

	public void setChance(double chance) {
		this.chance = chance;
	}
	
	
}
