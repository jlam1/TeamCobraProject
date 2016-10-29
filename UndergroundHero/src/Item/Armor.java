package Item;
/**
 * This class is responsible for creating an item type object Armor
 * @author John
 *
 */
public class Armor extends Item {

	public Armor(int id, String name, String type, int armorDef, String description) {
		super(id, name, type, description);
		this.armorDef = armorDef;
	}
	
	public int getArmorDef() {
		return armorDef;
	}

	public void setArmorDef(int armorDef) {
		this.armorDef = armorDef;
	}
}
