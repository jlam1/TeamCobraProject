
public class ConsumableItem extends Item
{

	private int count = 0;
	
	
	public ConsumableItem( String name, String description, String type, int count)
	{
		super();
		this.count = count;
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
	@Override
	public String toString() 
	{
		return super.toString()+ "quanity" + count;
	}
}
