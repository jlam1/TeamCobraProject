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
	
<<<<<<< HEAD
	public void removeItem()
	{
		
=======
	public ArrayList<Item> openInventory(){
		return bag;
>>>>>>> 744ab196361ecda6e59d2894fa58243a41056e1a
	}

}
