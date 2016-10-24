public class Weapon extends Item {

	private int weaponAttack;
	
	public Weapon(String name, String description, String type, int weaponAttack) {
		super(name, description, type);
		this.weaponAttack = weaponAttack;
	}
	
	/*
	 * Returns weapon attack
	 */
	public int getWeaponAttack(){
		return this.weaponAttack;
	}
}
