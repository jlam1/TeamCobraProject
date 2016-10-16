public class Weapon extends Item {

	public Weapon(String name, String description, String type) {
		super(name, description, type);
	}

	private int damage;
	
	public int getDamage(){
		return this.damage;
	}

	public void equip() {

	}

}
