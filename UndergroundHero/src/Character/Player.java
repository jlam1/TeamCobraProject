package Character;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Game.Function;
import Item.Item;

/**
 * 
 * This class is responsible for player attributes and behaviors including
 * battle status and inventory usage.
 * 
 * @author John, Matt, King
 *
 */
public class Player extends Character implements Serializable {

	private static final long serialVersionUID = 8154838844933306541L;
	private List<Item> inventory;
	private ArrayList<Item> equipment;
	private Function f = new Function();

	public Player(int maxhp, int hp, int atk, int spd, int def) {
		super(maxhp, hp, atk, spd, def);
		inventory = new ArrayList<Item>();
		equipment = new ArrayList<Item>();
	}

	/**
	 * @method Player checks if keyItem is in inventory
	 * @param keyItem
	 * @return boolean
	 */
	public boolean checkInventoryKeyItem(Item keyItem) {
		boolean itemIsThere = false;
		for(Item i : inventory) {
			if(i.getId() == keyItem.getId()) {
				itemIsThere = true;
				break;
			}
			else {
				itemIsThere = false;
			}
		}
		
		return itemIsThere;
	}

	/**
	 * The following method is a player command which prints out a list of
	 * non-equipment Item objects to console.
	 * 
	 * @return Void
	 */
	public void openInventory() {
		if (!this.inventory.isEmpty()) {
			int bagID = 0;

			f.printBox("# INVENTORY BAG #");

			for (Item i : this.inventory) {
				System.out.println("[" + bagID + "] " + formatPrintInventory(i));
				bagID++;
			}

			System.out.println("\n\n");
		} else {
			System.out.println("Your inventory is empty.");
		}
	}

	/**
	 * The following method is a player command which prints a list of
	 * equipment, <Armor> and <Weapon>, Item objects to console.
	 * 
	 * @return Void
	 */
	public void viewEquipment() {
		if (!this.equipment.isEmpty()) {
			
			f.printBox("# EQUIPMENT #");

			for (Item i : this.equipment) {
				System.out.println(formatPrintInventory(i));
			}

			System.out.println("\n\n");
		} else {
			System.out.println("Your equipment is empty.");
		}
	}

	/**
	 * The following method is a player command which adds items to
	 * ArrayList<Item> inventory.
	 * 
	 * @param item
	 * @return Void
	 */
	public void pickUp(Item item) {
		this.inventory.add(item);
	}

	/**
	 * The following method is a player command which can only initiate when
	 * Item "type" is a Consumable. After initiating, this method will apply the
	 * "used Item"'s abilities and then remove the Item from its respective
	 * list.
	 * 
	 * @exception IndexOutOfBoundsException
	 *                caught whenever Item<ConsumableItem> is called outside of
	 *                List<Item> inventory's size index.
	 * @param index
	 */
	public void useItem(int index) {
		try {
			if (this.inventory.get(index).getType().equalsIgnoreCase("CONSUMABLE")) {
				
				System.out.println("You used [" + this.inventory.get(index).getName() + "]\n");
				
				if (this.inventory.get(index).getId() == 4) {
					int healing = this.getHp() + 10;
					if (healing > this.getMaxhp()) {
						this.setHp(this.getMaxhp());
					} else {
						this.setHp(healing);
					}
					System.out.println("You healed for 10 HP!");
				}
				
				if (this.inventory.get(index).getId() == 5) {
					this.setMaxhp(this.maxhp + 5);
					this.setHp(this.getMaxhp());
					System.out.println("You are fully healed, and your max hp is increased by 5!");
				}

				this.inventory.remove(index);

			} else {
				System.out.println("[" + this.inventory.get(index).getName() + "] is not a CONSUMABLE item.\n");
			}
		} catch (IndexOutOfBoundsException e) {

		}
	}

	/**
	 * The following method is a player command which can only initiate when
	 * item type is either a Weapon or Armor. After initiating, this method will
	 * adjust the player's attack or defense according to Item type, as well as
	 * move the Item from inventory list to equipment list.
	 * 
	 * @exception IndexOutOfBoundsException
	 *                caught whenever Item<Weapon> or Item<Armor> is called
	 *                outside of List<Item> inventory's size index.
	 * @param index
	 */
	public void equip(int index) {
		try {

			if (this.inventory.get(index).getType().equalsIgnoreCase("WEAPON")) {
				if(this.equipment != null) {
					for(Item i : this.equipment) {
						if(i.getType().equalsIgnoreCase("WEAPON")) {
							this.equipment.remove(i);
						}
					}
				}
				Item weapon = (Item) this.inventory.get(index);
				this.inventory.remove(index);
				this.equipment.add(weapon);
				this.setAtk(this.getAtk() + weapon.getWeaponAtk());

				System.out.println("You equipped [" + weapon.getName() + "].");
				System.out.println("Your attack has increased by [" + weapon.getWeaponAtk() + "].\n");
			}

			if (this.inventory.get(index).getType().equalsIgnoreCase("ARMOR")) {
				if(this.equipment != null) {
					for(Item i : this.equipment) {
						if(i.getType().equalsIgnoreCase("ARMOR")) {
							this.equipment.remove(i);
						}
					}
				}
				Item armor = (Item) this.inventory.get(index);
				this.inventory.remove(index);
				this.equipment.add(armor);
//				this.setDef(this.getDef() + armor.getArmorDef());
				this.setDef(armor.getArmorDef());

				System.out.println("You equipped [" + armor.getName() + "].");
				System.out.println("Your defense has increased by [" + armor.getArmorDef() + "].\n");
			}

		} catch (IndexOutOfBoundsException e) {

		}
	}

	/**
	 * When a new game starts the player will start with these equip items
	 * 
	 * @exception IndexOutOfBoundsException
	 *                caught whenever Item<Weapon> or Item<Armor> is called
	 *                outside of List<Item> inventory's size index.
	 * @param index
	 */
	
	public void startingEquip(int index) {
		try {

			if (this.inventory.get(index).getType().equalsIgnoreCase("WEAPON")) {
				if(this.equipment != null) {
					for(Item i : this.equipment) {
						if(i.getType().equalsIgnoreCase("WEAPON")) {
							this.equipment.remove(i);
						}
					}
				}
				Item weapon = (Item) this.inventory.get(index);
				this.inventory.remove(index);
				this.equipment.add(weapon);
				this.setAtk(this.getAtk() + weapon.getWeaponAtk());
			}

			if (this.inventory.get(index).getType().equalsIgnoreCase("ARMOR")) {
				if(this.equipment != null) {
					for(Item i : this.equipment) {
						if(i.getType().equalsIgnoreCase("ARMOR")) {
							this.equipment.remove(i);
						}
					}
				}
				Item armor = (Item) this.inventory.get(index);
				this.inventory.remove(index);
				this.equipment.add(armor);
				this.setDef(this.getDef() + armor.getArmorDef());
			}

		} catch (IndexOutOfBoundsException e) {
			
		}
	}

	public List<Item> getInventory() {
		return inventory;
	}
	
	private String formatPrintInventory(Item item) {
		return String.format("%-30s%-20s", item.getName(), item.getDescription());
	}
	
	@Override
	public String toString() {
		return "PLAYER\nHP:\t [" + this.getHp() + "/" + this.getMaxhp() + "]\nDEF:\t [" + this.def + "]\nSPD:\t ["
				+ this.spd + "]\nATK:\t [" + this.atk + "]\n";
	}

	@Override
	public String getDescription() {
		String heroDescription = "";
		return heroDescription;
	}

}
