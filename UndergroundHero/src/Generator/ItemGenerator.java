package Generator;

import java.util.ArrayList;

import Item.Armor;
import Item.ConsumableItem;
import Item.Item;
import Item.KeyItem;
import Item.Weapon;

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
		
		Item item = new Armor		(0, "Starter Clothes", 			"ARMOR", 1, "Default Armor. It's normal and worned.");
		itemList.add(item);
		
		item = new Armor			(1, "Chain Mail",				"ARMOR", 3, "A lightweight piece of armor that is particularly good at stopping stabbing attacks.");
		itemList.add(item);
		
		item = new Weapon			(2, "Starter Weapon",			"WEAPON", 1, "Default Weapon. It's worned out and chipped.");
		itemList.add(item);
		
		item = new Weapon			(3, "Prototype Laser Pistol", 	"WEAPON", 3, "A sleek looking pistol, seemingly made out of titanium judging by the color. It has no visible seams or welds and fires a brilliant blue bolt of light.");
		itemList.add(item);
		
		item = new ConsumableItem	(4, "Adrenaline Shot",			"CONSUMABLE", 0, "Heals the user 10 HP.");
		itemList.add(item);
		
		item = new ConsumableItem	(5, "Steroid",					"CONSUMABLE", 0, "Increases max HP by 5, and heals fully.");
		itemList.add(item);
		
		item = new KeyItem			(6, "Diamond Tipped Cutter",	"KEY", "This item allows the player to cut through ballistic glass.");
		itemList.add(item);
		
		item = new KeyItem			(7, "Grappling Hook",			"KEY", "This item allows the player to go across the chasm.");
		itemList.add(item);
		
		item = new KeyItem			(8, "Speed Boots",				"KEY", "This item allows the player to run through the lasers.");
		itemList.add(item);
		
		item = new KeyItem			(9, "Computer Chip",			"KEY", "Use to access computer terminal in Room 3-3.");
		itemList.add(item);
		
		item = new KeyItem			(10, "Hero's Journal",			"KEY", "The Hero's Journal, nothing special.");
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
