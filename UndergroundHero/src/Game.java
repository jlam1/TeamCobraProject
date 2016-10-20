import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

public class Game
{
	private Game game;
	private Player player;
	private List<Room> rooms;
	private List<Item> item;

	private Game(List<Room> rooms, Player player)
	{
		this.rooms = rooms;
		this.player = player;
	}

	public void newGame()
	{
		
		Player player = new Player(10, 1, 1, 2);	//default attributes 
		Room room = new Room();
		Inventory starterClothes = new Inventory(item.get(0), 1);
		Inventory starterWeapon = new Inventory(item.get(1), 1);
		setGameRun();
	}

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

	public String help()
	{
		
		return "Directions: \n North \n South \n East \n West \n Up \n\n ViewRoom: \n Look \n\n Combat: \n Attack \n Defend \n Run [Direction] ";

	}

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

	public List<Item> getItem()
	{
		return game.item;
	}
	
	public List<Room> getRooms()
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
