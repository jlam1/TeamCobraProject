import java.util.ArrayList;

/**
 * Class name: saveLoadData.java
 *
 * This class is a class that implements Serializable interface.
 * The class stores the objects values in to binary.
 */


public class saveLoadData implements java.io.Serializable
{

	private static final long serialVersionUID = 1L;
	
	//will add more once game comes near completion
	private int hp;
	private int attack;
	private int speed;
	private int def;
	private String roomNumber;
	private String roomDescription;
	private ArrayList<Item> item;
	/**
	 * @return the hp
	 */
	public int getHp()
	{
		return hp;
	}
	/**
	 * @param hp the hp to set
	 */
	public void setHp(int hp)
	{
		this.hp = hp;
	}
	/**
	 * @return the attack
	 */
	public int getAttack()
	{
		return attack;
	}
	/**
	 * @param attack the attack to set
	 */
	public void setAttack(int attack)
	{
		this.attack = attack;
	}
	/**
	 * @return the speed
	 */
	public int getSpeed()
	{
		return speed;
	}
	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(int speed)
	{
		this.speed = speed;
	}
	/**
	 * @return the def
	 */
	public int getDef()
	{
		return def;
	}
	/**
	 * @param def the def to set
	 */
	public void setDef(int def)
	{
		this.def = def;
	}
	/**
	 * @return the item
	 */
	public ArrayList<Item> getItem()
	{
		return item;
	}
	/**
	 * @param item the item to set
	 */
	public void setItem(ArrayList<Item> item)
	{
		this.item = item;
	}
	/**
	 * @return the roomNumber
	 */
	public String getRoomNumber()
	{
		return roomNumber;
	}
	/**
	 * @param roomNumber the roomNumber to set
	 */
	public void setRoomNumber(String roomNumber)
	{
		this.roomNumber = roomNumber;
	}
	/**
	 * @return the roomDescription
	 */
	public String getRoomDescription()
	{
		return roomDescription;
	}
	/**
	 * @param roomDescription the roomDescription to set
	 */
	public void setRoomDescription(String roomDescription)
	{
		this.roomDescription = roomDescription;
	}
}
