

/**Class name: Inventory.java
 * @author King Lo
 * @version 1.0
 * Course: ITEC 3150 Fall 2016
 * Written: Oct 19, 2016
 *
 * 
 * This class –now describe what the class does
 * 
 * Purpose: –Describe the purpose of this class 
 */
public class Inventory
{

	private Item item;
	private int count = 1;
	
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
	
	public void count()
	{
		count++;
	}
	
	
	
}
