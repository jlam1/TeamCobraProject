package Item;
/**
 * This class is responsible for creating an item type object Weapon.
 * @author John
 *
 */
public class Weapon extends Item {
	
	private int weaponAtk;

	public Weapon(int id, String name, String type, int weaponAtk, String description) {
		super(id, name, type, description);
		this.weaponAtk = weaponAtk;
	}
	
	public int getWeaponAtk() {
		return weaponAtk;
	}

	public void setWeaponAtk(int weaponAtk) {
		this.weaponAtk = weaponAtk;
	}
	
}
