package Item;

/**
 * 
 * This class contains the methods and attributes for items that can be
 * consumed, and is responsible for creating Consumable type Item objects.
 * Purpose: The purpose is to increase and decrease the amount of consumable
 * items.
 * 
 * @author King, John
 */
public class Consumables extends Item {

	public static int count;

	/**
	 * The following method is the constructor for ConsumableItem type Item
	 * objects.
	 * 
	 * @param id
	 *            From parent.
	 * @param name
	 *            From parent.
	 * @param type
	 *            From parent.
	 * @param description
	 *            From parent.
	 * @param count
	 *            Specific to ConsumableItem.
	 */
	public Consumables(int id, String name, String type, int count, String description) {
		super(id, name, type, description);
		this.count = count;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * The following method overrides the toString method for Object to create a
	 * custom Consumables toString().
	 */
	@Override
	public String toString() {
		return super.toString() + "quanity" + count;
	}
}
