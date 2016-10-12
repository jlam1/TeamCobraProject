import java.util.ArrayList;

public class Inventory {

	private ArrayList bag;

	private Weapon weaponSlot;

	private Armor armorSlot;

	private boolean removed;

	public void useItem() {

	}

	public void viewInventory() {

	}

	public boolean itemRemoved() {
		return false;
	}
	
	public ArrayList<Item> openInventory(){
		return bag;
	}

}
