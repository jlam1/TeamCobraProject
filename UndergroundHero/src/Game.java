import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Game
{
	private Game game;
	private Player player;
	private List<Room> startingRoom;
	private ArrayList<Item> item;
	
	//this is a test constructor
	public Game(){
		
	}

	public Game(List<Room> rooms, Player player)
	{
		this.startingRoom = rooms;
		this.player = player;
	}

	/**
	 * Method: newGame()
	 * 
	 * When starting a new game, this method will initialize the player status, staring point, and items that the player have.
	 * 
	 */
	public void newGame()
	{
		
		player = new Player(10, 1, 1, 2);	//default attributes 
		startingRoom = (List<Room>) this.getRooms().get(0);
		item.add(item.get(0));
		item.add(item.get(1));
		setGameRun();
	}
	
	/**
	 * Method: saveGame()
	 * 
	 * This method will save the game. It will throw an exception when overwriting a existing save 
	 * file and create a new file if the file does not exist.
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveGame() throws Exception
	{
		if(game != null)
			throw new Exception();
		ObjectOutputStream output;
		try
		{
			output = new ObjectOutputStream(new FileOutputStream("UndergroundHero.dat"));
			while(true)
			{
				System.out.println("A save exist. Save data will over write the old save data");
				output.writeObject(game);
			}
		}
		catch(FileNotFoundException e)
		{
			System.out.println("There is currently no save data. A new save data will be created.");
			return false;
		}
		catch(IOException e)
		{
			
		}
		return true;
	}
	
	/**
	 * Method: help()
	 * 
	 * This method return a String to display help commands
	 * 
	 * @return
	 */

	public String help()
	{
		return "NAVIGATION: \nNorth \nSouth \nEast \nWest \n\nROOM: \nLook \n\nCombat: \nAttack \nDefend \nRun [Direction] ";
	}

	/**
	 * Method: loadGame()
	 * 
	 * This method will load the file. It will throw an exception when the file does 
	 * not exist and creates a new game. it will throw an exception when the file cannot be loaded.
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean loadGame() throws Exception
	{
		if (game != null)
			throw new Exception();
		ObjectInputStream input;
		try
		{
			input = new ObjectInputStream(new FileInputStream("UndergroundHero.dat"));

			while (true)
			{
				game = (Game) input.readObject();
			}
		}
		catch (FileNotFoundException e)
		{
			System.out.println("There is currently no save data. A new game will be created.");
			return false;
		}
		catch (IOException e)
		{

		}
		catch (ClassNotFoundException e)
		{

			System.out.println("Your game data is corrupted. A new game will be created.");
			return false;
		}
		return true;

	}

	/**
	 * Method: exitGame();
	 * 
	 * Exits the game
	 */
	public void exitGame()
	{
		System.exit(0);
	}

	public List<Item> getItem()
	{
		return game.item;
	}
	
	public List<Room> getRooms()
	{

		return game.startingRoom;

	}

	public boolean setGameRun()
	{
		if (game == null)
		{
			game = new Game(startingRoom, player);
			return true;
		}
		else
			return false;
	}

}
