package Character;

import java.io.Serializable;
import java.util.ArrayList;

import Item.Armor;
import Item.Item;
import Item.Weapon;

/**
 * This class is responsible for player attributes and behavior regarding with status and inventory bag (ArrayList<Item>).
 * @author John, Matt
 *
 */
public class Player extends Character implements Serializable{
	
	private static final long serialVersionUID = 8154838844933306541L;
	private ArrayList<Item> inventory;
	private ArrayList<Item> equipment;

	public Player(int maxhp, int hp, int atk, int spd, int def) {
		super(maxhp, hp, atk, spd, def);
		inventory = new ArrayList<Item>();
		equipment = new ArrayList<Item>();
	}
	
	public ArrayList<Item> getInventory() {
		return inventory;
	}
	
	/**
	 * @method Player command that prints out a list of Item objects in console.
	 */
	public void openInventory() {
		if(!this.inventory.isEmpty()){
			int bagID = 0;
			
			System.out.println("----------------------------------");
			System.out.println("[[[ INVENTORY BAG ]]]");
			
			for(Item i : this.inventory){
				System.out.println(bagID + ". [" + i.getName().toUpperCase() + "]");
				bagID++;
			}
			
			System.out.println("----------------------------------");
			System.out.println("\n");
		}
		else{
			System.out.println("Your inventory is empty.");
		}
	}
	
	/**
	 * @method Player command that prints out a list of Item<Armor> and Item<Weapon> objects in console.
	 */
	public void viewEquipment(){
		try{
			int bagID = 0;
			
			System.out.println("----------------------------------");
			System.out.println("[[[ EQUIPMENT ]]]");
			
			for(Item i : this.equipment){
				System.out.println(bagID + ". " + i.getName());
				bagID++;
			}
			
			System.out.println("----------------------------------");
			System.out.println("\n");

		}
		catch(NullPointerException e){
			System.out.println("You are not equipped.");
		}
	}

	public void startingItem(Item item)
	{
		this.inventory.add(item);
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
				if(this.inventory.get(index).getId() == 4) {
					int healing = this.getHp() + 10;
					if(healing > this.getMaxhp()) {
						this.setHp(this.getMaxhp());
					}
					else {
						this.setHp(this.getHp() + 10);
					}
					System.out.println("You healed for 10 HP!");
				}
				if(this.inventory.get(index).getId() == 5) {
					this.setMaxhp(this.maxhp + 5);
					this.setHp(this.getMaxhp());
					System.out.println("Your max hp is increased by 5!");
					System.out.println("You healed to max!");
				}
				this.inventory.remove(index);
				System.out.println("You used [" + this.inventory.get(index).getName() + "]\n");
			}
			else{
				System.out.println("[" + this.inventory.get(index).getName() + "] is not a CONSUMABLE item.\n");
			}
		}
		catch(IndexOutOfBoundsException e){
			
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
				this.equipment.add(weapon);
				this.setAtk(this.getAtk() + weapon.getWeaponAtk());
				
				System.out.println("You equipped [" + weapon.getName() + "].");
				System.out.println("Your attack has increased by [" + weapon.getWeaponAtk() + "].\n");
			}
			
			if(this.inventory.get(index).getType().equalsIgnoreCase("ARMOR")){
				Armor armor = (Armor) this.inventory.get(index);
				this.inventory.remove(index);
				this.equipment.add(armor);
				this.setDef(this.getDef() + armor.getArmorDef());
				
				System.out.println("You equipped [" + armor.getName() + "].");
				System.out.println("Your defense has increased by [" + armor.getArmorDef() + "].\n");
			}
			
			else{
				System.out.println("[" + this.inventory.get(index).getName() + "] is not an EQUIPPABLE item.\n");
				System.out.println("Try again.");
			}
			
		}
		catch(IndexOutOfBoundsException e){
			System.out.println("Equipment does not exist in inventory, try again.\n");
		}
	}
	
	/**
	 * @method Calculates damage output after defending.
	 * @param monster
	 */
	public void defend(Monster monster) {
		System.out.println("[" + this.getName().toUpperCase() + "] defends!");
		int damageDealt = Math.abs(this.def - monster.getAtk());
		this.setHp(this.getHp() - damageDealt);
		System.out.println("[" + monster.getName().toUpperCase() + "] strikes [" + this.getName().toUpperCase() + "] for " + damageDealt + " damage!");
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
