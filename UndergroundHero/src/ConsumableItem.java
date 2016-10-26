
/**
 * 
 * This class contains method and attributes of items that can be consumed
 * 
 * Purpose: The purpose is to increase and decrease the amount consumable items.
 */
public class ConsumableItem extends Item
{

	static int count = 0;
	
	/**
	 * 
	 * @param name
	 * @param description
	 * @param type
	 * @param count
	 */
	public ConsumableItem(int id, String name, String description, String type, int count)
	{
		super(id, name, description, type);
		this.count = count;
	}
	
	/**
	 * @return the count
	 */
	static int getCount()
	{
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(int count)
	{
		this.count = count;
	}
	
	public void countItem()
	{
		count++;
	}
	public static void useItem()
	{
		count--;
	}
	
	@Override
	public String toString() 
	{
		return super.toString()+ "quanity" + count;
	}
}
