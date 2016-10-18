import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Game
{
	private Game game;
	private ArrayList<Room> rooms;
	private Player player;

	private Game(ArrayList<Room> rooms, Player player)
	{
		this.rooms = rooms;
		this.player = player;
	}

	public void newGame()
	{
		ArrayList<Room> rooms = new ArrayList<Room>();
		player = new Player();
		setGameRun();

	}

	public void saveGame() throws Exception
	{
		if(game != null)
			throw new Exception();
		ObjectOutputStream output;
	    
	    	output = new ObjectOutputStream(new FileOutputStream("UndergroundHero.dat"));
	    
	   
	
	
	
	}
		
			
		
	

	public void help()
	{

	}

	@SuppressWarnings("resource")
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

	public void exitGame()
	{
		System.exit(0);
	}

	public void checkSaveState()
	{

	}

	public ArrayList<Room> getRooms()
	{

		return game.rooms;

	}

	public boolean setGameRun()
	{
		if (game == null)
		{
			game = new Game(rooms, player);
			return true;
		}
		else
			return false;
	}

}
