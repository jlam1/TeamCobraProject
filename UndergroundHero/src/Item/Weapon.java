package Item;

/**
 * This class is responsible for creating a Weapon type Item object.
 * 
 * @author John
 *
 */
public class Weapon extends Item {

	private int weaponAtk;

	/**
	 * The following method is the constructor for Weapon type Item objects.
	 * 
	 * @param id
	 *            From parent.
	 * @param name
	 *            From parent.
	 * @param type
	 *            From parent.
	 * @param description
	 *            From parent.
	 * @param weaponAtk
	 *            Specific to Weapon.
	 */
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
