package Character;

import java.util.ArrayList;

import Item.ConsumableItem;
import Item.Item;

/**
 * This class is responsible for player attributes and behavior regarding with status and inventory bag (ArrayList<Item>).
 * @author John
 *
 */
public class Player extends Character {
	
	private ArrayList<Item> inventory;

	public Player(int hp, int atk, int spd, int def) {
		super(hp, atk, spd, def);
		inventory = new ArrayList<Item>();
	}
	
	@Override
	public String toString() {
		return "PLAYER\nHealth:\t [" + hp + "/" + hp + "]\nDefense:\t [" + def + "]\nSpeed:\t [" + spd + "]\nAttack:\t [" + atk + "]";
	}

	public void openInventory() {
		int bagID = 0;
		
		System.out.println("[[[ INVENTORY BAG ]]]");
		for(Item i : this.inventory){
			System.out.println(bagID + ". " + i.getName());
			bagID++;
		}
	}

	public void addToInventory(Item item) {
		this.inventory.add(item);
	}
	
	public void useItem(ConsumableItem item) {
		int itemCount = item.getCount();
		
		if(item.getCount() > 0)
			item.setCount(itemCount--);
	}
	
	

}
