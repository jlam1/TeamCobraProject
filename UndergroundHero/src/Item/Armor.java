package Item;
public class Armor extends Item {

	public Armor(int id, String name, String type, int armorDef, String description) {
		super(id, name, type, description);
		this.armorDef = armorDef;
	}
}
