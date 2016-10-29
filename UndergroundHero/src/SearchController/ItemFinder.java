package SearchController;
/**
 * This class is responsible for searching Item objects by name
 */
import java.util.ArrayList;

import Generator.ItemGenerator;
import Item.Item;
import Item.KeyItem;

/**
 * This class is responsible for searching item attributes.
 * @author John
 *
 */
public class ItemFinder {
	
	private ArrayList<Item> itemList;
	
	public ItemFinder(){
		itemList = new ItemGenerator().getItemList();
	}

	/**
	 * @method Searches for key item name and returns its object
	 * @param name
	 * @return KeyItem
	 */
	public KeyItem key(String name) {
		Item item;
		KeyItem keyItem = null;
		for(int i = 0; i < itemList.size(); i++){
			
			item = itemList.get(i);
			String itemName = item.getName();
			
			if(itemName.equalsIgnoreCase(name)){
				keyItem = (KeyItem) item;
				break;
			}	
		}
		return keyItem;
	}
	
	/**
	 * @method Searches for misc item name and returns its object
	 * @param name
	 * @return Item
	 */
	public Item item(String name) {
		Item item;
		Item miscItem = null;
		
		for(int i = 0; i < itemList.size(); i++){
			
			item = itemList.get(i);
			String itemName = item.getName();
			
			if(itemName.equalsIgnoreCase(name)){
				miscItem = item;
				break;
			}	
		}
		
		return miscItem;
	}
	
}
