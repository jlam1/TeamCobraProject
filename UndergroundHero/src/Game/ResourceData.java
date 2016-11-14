package Game;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import Character.Player;
import LogicController.PuzzleLogic;
import Room.Room;

/**
 * Class name: saveLoadData.java
 *
 * This class is a class that implements Serializable interface.
 * The class stores the objects values in to binary.
 * @author King
 */


public class ResourceData implements Serializable
{

	private static final long serialVersionUID = 1L;
	private Player player;
	private PuzzleLogic puzzle;
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

	/**
	 * Method: saveGame()
	 * 
	 * This method will save the game. It will throw an exception when overwriting a existing save 
	 * file and create a new file if the file does not exist.
	 * 
	 * @throws Exception
	 */
	public static void saveGame(Serializable data, String fileName) throws Exception
	{
		try(ObjectOutputStream output = new ObjectOutputStream(Files.newOutputStream(Paths.get(fileName))))
		{
			output.writeObject(data);
		}
		
	}
	
	/**
	 * Method: loadGame()
	 * 
	 * This method will load the file. It will throw an exception when the file does 
	 * not exist and creates a new game. it will throw an exception when the file cannot be loaded.
	 * 
	 * @return game
	 * @throws Exception
	 */
	public static Object loadGame(String fileName) throws Exception
	{
		try(ObjectInputStream input = new ObjectInputStream(Files.newInputStream(Paths.get(fileName))))
		{
			return input.readObject();
		}
	}
	
}
	