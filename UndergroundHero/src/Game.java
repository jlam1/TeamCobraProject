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

	public void saveGame()
	{

	}

	public void help()
	{

	}

	public void loadGame()
	{

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
