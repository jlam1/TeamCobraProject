package Character;

import java.io.Serializable;
import java.util.ArrayList;

import Item.Armor;
import Item.Item;
import Item.Weapon;

/**
 * This class is responsible for player attributes and behaviors including
 * battle status and inventory usage.
 * 
 * @author John, Matt
 *
 */
public class Player extends Character implements Serializable {

	private static final long serialVersionUID = 8154838844933306541L;
	private ArrayList<Item> inventory;
	private ArrayList<Item> equipment;

	/**
	 * The following is the constructor method for Player objects.
	 * 
	 * @param maxhp
	 *            From parent.
	 * @param hp
	 *            From parent.
	 * @param atk
	 *            From parent.
	 * @param spd
	 *            From parent.
	 * @param def
	 *            From parent.
	 */
	public Player(int maxhp, int hp, int atk, int spd, int def) {
		super(maxhp, hp, atk, spd, def);
		inventory = new ArrayList<Item>();
		equipment = new ArrayList<Item>();
	}

	public ArrayList<Item> getInventory() {
		return inventory;
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

			System.out.println("----------------------------------");
			System.out.println("[[[ INVENTORY BAG ]]]");

			for (Item i : this.inventory) {
				System.out.println(bagID + ". [" + i.getName().toUpperCase() + "]");
				bagID++;
			}

			System.out.println("----------------------------------");
			System.out.println("\n");
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
			int bagID = 0;

			System.out.println("----------------------------------");
			System.out.println("[[[ EQUIPMENT ]]]");

			for (Item i : this.equipment) {
				System.out.println(bagID + ". " + i.getName());
				bagID++;
			}

			System.out.println("----------------------------------");
			System.out.println("\n");
		} else {
			System.out.println("Your equipment is empty.");
		}
	}

	public void startingItem(Item item) {
		this.inventory.add(item);
	}

	/**
	 * The following method is a player command which adds items to
	 * ArrayList<Item> inventory.
	 * 
	 * @param item
	 *            The only parameter for the pickUp() method.
	 * @return Void
	 */

	public void pickUp(Item item) {
		System.out.println("[" + item.getName().toUpperCase() + "]" + " has been added to your [INVENTORY].\n");
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
	 *            - The only parameter for useItem() method.
	 */

	public void useItem(int index) {
		try {
			if (this.inventory.get(index).getType().equalsIgnoreCase("CONSUMABLE")) {
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
				System.out.println("You used [" + this.inventory.get(index).getName() + "]\n");
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
	 *            The only parameter for equip() method.
	 */

	public void equip(int index) {
		try {

			if (this.inventory.get(index).getType().equalsIgnoreCase("WEAPON")) {
				Weapon weapon = (Weapon) this.inventory.get(index);
				this.inventory.remove(index);
				this.equipment.add(weapon);
				this.setAtk(this.getAtk() + weapon.getWeaponAtk());

				System.out.println("You equipped [" + weapon.getName() + "].");
				System.out.println("Your attack has increased by [" + weapon.getWeaponAtk() + "].\n");
			}

			if (this.inventory.get(index).getType().equalsIgnoreCase("ARMOR")) {
				Armor armor = (Armor) this.inventory.get(index);
				this.inventory.remove(index);
				this.equipment.add(armor);
				this.setDef(this.getDef() + armor.getArmorDef());

				System.out.println("You equipped [" + armor.getName() + "].");
				System.out.println("Your defense has increased by [" + armor.getArmorDef() + "].\n");
			}

			else {
				System.out.println("[" + this.inventory.get(index).getName() + "] is not an EQUIPPABLE item.\n");
				System.out.println("Try again.");
			}

		} catch (IndexOutOfBoundsException e) {
			System.out.println("Equipment does not exist in inventory, try again.\n");
		}
	}

	/**
	 * The following method calculates the damage taken from a monster attack.
	 * 
	 * @param monster
	 *            The only parameter for defend() method.
	 */

	public void defend(Monster monster) {
		System.out.println("[" + this.getName().toUpperCase() + "] defends!");
		int damageDealt = Math.abs(this.def - monster.getAtk());
		this.setHp(this.getHp() - damageDealt);
		System.out.println("[" + monster.getName().toUpperCase() + "] strikes [" + this.getName().toUpperCase()
				+ "] for " + damageDealt + " damage!");
	}

	/**
	 * The following method overrides the toString method for Object to create a
	 * custom Player toString.
	 */

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
