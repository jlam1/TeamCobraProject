package Game;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Character.Monster;
import Character.Player;
import Item.Item;
import LogicController.PuzzleLogic;
import Puzzle.Puzzle;
import Room.Room;

/**
 * Class name: saveLoadData.java
 *
 * This class is a class that implements Serializable interface.
 * The class stores the objects values in to binary.
 * @author King
 */


public class saveLoadData implements Serializable
{

	private static final long serialVersionUID = 1L;
	private Player player;
	private PuzzleLogic puzzle;
	private Monster monster;
	private int roomArrayNumber;
	private List<Room> factoryList;
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
	 * @return the puzzle
	 */
	public PuzzleLogic getPuzzle()
	{
		return puzzle;
	}
	/**
	 * @param puzzle 
	 */
	public void setPuzzle(PuzzleLogic puzzle)
	{
		this.puzzle = puzzle;
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
	/**
	 * @return the factoryList
	 */
	public List<Room> getFactoryList()
	{
		return factoryList;
	}
	/**
	 * @param factoryList the factoryList to set
	 */
	public void setFactoryList(List<Room> factoryList)
	{
		this.factoryList = factoryList;
	}

	//will add more once game comes near completion
	
}
	