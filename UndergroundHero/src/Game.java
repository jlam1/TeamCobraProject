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
