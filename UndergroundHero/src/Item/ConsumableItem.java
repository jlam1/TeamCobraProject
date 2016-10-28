package Item;

/**
 * 
 * This class contains method and attributes of items that can be consumed
 * 
 * Purpose: The purpose is to increase and decrease the amount consumable items.
 */
public class ConsumableItem extends Item
{

	private int count;
	
	public ConsumableItem(int id, String name, String type, int count, String description) {
		super(id, name, type, description);
		this.count = count;
	}
	
	@Override
	public String toString() 
	{
		return super.toString()+ "quanity" + count;
	}
}