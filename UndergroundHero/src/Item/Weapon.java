package Item;

public class Weapon extends Item {

	public Weapon(int id, String name, String type, int weaponAtk, String description) {
		super(id, name, type, description);
		this.weaponAtk = weaponAtk;
	}
	
}
