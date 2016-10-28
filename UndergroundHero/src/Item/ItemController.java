package Item;

import java.util.ArrayList;

import Generator.ItemGenerator;

/**
 * This class is responsible for searching item attributes.
 * @author John
 *
 */
public class ItemController {
	
	private ArrayList<Item> itemList;
	
	public ItemController(){
		itemList = new ItemGenerator().getItemList();
	}

	/**
	 * @method Searches for key item name and returns its object
	 * @param name
	 * @return KeyItem
	 */
	public KeyItem keyItem(String name) {
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
	public Item miscItem(String name) {
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
