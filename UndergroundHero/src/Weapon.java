public class Weapon extends Item {

	private int weaponAttack;
	
	public Weapon(int id, String name, String description, String type, int weaponAttack) {
		super(id, name, description, type);
		this.weaponAttack = weaponAttack;
	}
	
	/*
	 * Returns weapon attack
	 */
	public int getWeaponAttack(){
		return this.weaponAttack;
	}
}
