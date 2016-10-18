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

public class Game
{
	private Game game;
	private Player player;
	private List<Room> rooms;

	private Game(List<Room> rooms, Player player)
	{
		this.rooms = rooms;
		this.player = player;
	}

	public Game() {
		
	}

	public void newGame(ResourceManager resource)
	{
		resource.loadAssetToList();
		List<Room> roomList = resource.getRoomList();
		List<Monster> monsterList = resource.getMonsterList();
		List<Puzzle> puzzleList = resource.getPuzzleList();
		List<Item> itemList = resource.getItemList();
		Player player = new Player(50, 1, 1, 2);	//default attributes 
		Room room = new Room();
		Monster monster = new Monster();
		Puzzle puzzle = new Puzzle();
		Item item = new Item();
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

	public void help()
	{

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

//	public ArrayList<Room> getRooms()
//	{
//
//		return game.rooms;
//
//	}

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
