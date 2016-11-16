package Item;

import java.util.ArrayList;
/**
 * This class is responsible for generating Item objects
 * @author John
 *
 */
public class ItemGenerator {
	
	private ArrayList<Item> itemList;
	
	/**
	 * @method Create item object and add them to an ArrayList
	 */
	private void generateItemList(){
		
		itemList = new ArrayList<Item>();
		
		Item item = new Item	(0, "Old Rags", 				"ARMOR", 1, "[+1 DEF] - It's normal and worned.");
		itemList.add(item);
		
		item = new Item			(1, "Chain Mail",				"ARMOR", 3, "[+3 DEF] - A lightweight chain armor.");
		itemList.add(item);
		
		item = new Item			(2, "Broken Stick",				"WEAPON", "[+1 ATK] - It's worned out and chipped.", 1);
		itemList.add(item);
		
		item = new Item			(3, "Prototype Laser Pistol", 	"WEAPON", " [+3 ATK] - A laser pistol.", 3);
		itemList.add(item);
		
		item = new Consumables	(4, "Adrenaline Shot",			"CONSUMABLE", 0, "[+10 HP]");
		itemList.add(item);
		
		item = new Consumables	(5, "Steroid",					"CONSUMABLE", 0, "[+5 MAXHP & FULL HEAL]");
		itemList.add(item);
		
		item = new Item			(6, "Diamond Tipped Cutter",	"KEY", "This item allows the player to cut through ballistic glass.");
		itemList.add(item);
		
		item = new Item			(7, "Grappling Hook",			"KEY", "This item allows the player to go across the chasm.");
		itemList.add(item);
		
		item = new Item			(8, "Speed Boots",				"KEY", "This item allows the player to run through the lasers.");
		itemList.add(item);
		
		item = new Item			(9, "Computer Chip",			"KEY", "This item allows the player to access computer terminal in Room 3-3.");
		itemList.add(item);
		
		item = new Item			(10, "Hero's Journal",			"KEY", "The Hero's Journal, nothing special.");
		itemList.add(item);
		
	}

	/**
	 * 
	 * @return itemList
	 */
	public ArrayList<Item> getItemList() {
		generateItemList();
		return itemList;
	}

}
