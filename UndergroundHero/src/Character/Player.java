package Character;

import java.util.ArrayList;

import Item.Armor;
import Item.Item;
import Item.Weapon;

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

	/**
	 * @method Player command that prints out a list of Item objects in console.
	 */
	public void openInventory() {
		int bagID = 0;
		
		System.out.println("----------------------------------");
		System.out.println("[[[ INVENTORY BAG ]]]");
		
		for(Item i : this.inventory){
			System.out.println(bagID + ". " + i.getName());
			bagID++;
		}
		
		System.out.println("----------------------------------");
		System.out.println("\n");
	}

	/**
	 * @method Player command that adds item to ArrayList<Item> inventory
	 * @param item
	 */
	public void pickUp(Item item) {
		System.out.println("[" + item.getName() + "]" + " has been added to your [INVENTORY].\n");
		this.inventory.add(item);
	}
	
	/**
	 * @method Player command that can only initiate when item type is a Consumable.
	 * @throws IndexOutOfBoundsException whenever Item<ConsumableItem> is called outside of List<Item> inventory's size index.
	 * @param index
	 */
	public void useItem(int index) {
		try{
			if(this.inventory.get(index).getType().equalsIgnoreCase("CONSUMABLE")){
				this.inventory.remove(index);
				System.out.println("You used [" + this.inventory.get(index).getName() + "]\n");
			}
			else{
				System.out.println("[" + this.inventory.get(index).getName() + "] is not a CONSUMABLE item.\n");
			}
		}
		catch(IndexOutOfBoundsException e){
			System.out.println("Item does not exist in inventory, try again.\n");
		}
	}
	
	/**
	 * @method Player command that can only initiate when item type is either a Weapon or Armor. After initiating, set and increase/decrease player's attack or defense.
	 * @throws IndexOutOfBoundsException whenever Item<Weapon> or Item<Armor> is called outside of List<Item> inventory's size index.
	 * @param index
	 */
	public void equip(int index){
		try{
			if(this.inventory.get(index).getType().equalsIgnoreCase("WEAPON")){
				Weapon weapon = (Weapon) this.inventory.get(index);
				this.inventory.remove(index);
				this.setAtk(this.atk + weapon.getWeaponAtk());
				
				System.out.println("You equipped [" + weapon.getName() + "].");
				System.out.println("Your attack has increased by [" + weapon.getWeaponAtk() + "].\n");
			}
			else if(this.inventory.get(index).getType().equalsIgnoreCase("ARMOR")){
				Armor armor = (Armor) this.inventory.get(index);
				this.inventory.remove(index);
				this.setDef(this.atk + armor.getArmorDef());
				
				System.out.println("You equipped [" + armor.getName() + "].");
				System.out.println("Your defense has increased by [" + armor.getArmorDef() + "].\n");
			}
			else{
				System.out.println("[" + this.inventory.get(index).getName() + "] is not an EQUIPPABLE item.\n");
			}
		}
		catch(IndexOutOfBoundsException e){
			System.out.println("Equipment does not exist in inventory, try again.\n");
		}
	}
	
	@Override
	public String toString() {
		return "PLAYER\nHP:\t [" + this.hp + "]\nDEF:\t [" + this.def + "]\nSPD:\t [" + this.spd + "]\nATK:\t [" + this.atk + "]\n";
	}

	@Override
	public String getDescription() {
		String heroDescription = "Some random description about the Hero of the game.";
		return heroDescription;
	}

}
