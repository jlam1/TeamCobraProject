package Game;
import java.util.ArrayList;
import java.util.Map;

import Character.Monster;
import Character.Player;
import Item.Item;
import Room.Room;

/**
 * Class name: saveLoadData.java
 *
 * This class is a class that implements Serializable interface.
 * The class stores the objects values in to binary.
 */


public class saveLoadData implements java.io.Serializable
{

	private static final long serialVersionUID = 1L;
	private Player player;
	private Monster monster;
	private ArrayList<Item> item;
	private int roomArrayNumber;
	/**
	 * @return the player
	 */
	public Player getPlayer()
	{
		return player;
	}
	/**
	 * @param player the player to set
	 */
	public void setPlayer(Player player)
	{
		this.player = player;
	}
	/**
	 * @return the monster
	 */
	public Monster getMonster()
	{
		return monster;
	}
	/**
	 * @param monster the monster to set
	 */
	public void setMonster(Monster monster)
	{
		this.monster = monster;
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
	 * @return the roomArrayNumber
	 */
	public int getRoomArrayNumber()
	{
		return roomArrayNumber;
	}
	/**
	 * @param roomArrayNumber the roomArrayNumber to set
	 */
	public void setRoomArrayNumber(int roomArrayNumber)
	{
		this.roomArrayNumber = roomArrayNumber;
	}
	

	//will add more once game comes near completion
	
}
	