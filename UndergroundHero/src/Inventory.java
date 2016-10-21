
public class Inventory
{

	private Item item;
	private int count = 0;
	
	public Inventory(Item item, int count)
	{
		this.item = item;
		this.count = count;
	}

	/**
	 * @return the item
	 */
	public Item getItem()
	{
		return item;
	}

	/**
	 * @return the count
	 */
	public int getCount()
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
	public void useItem()
	{
		count--;
	}
	
	
}
