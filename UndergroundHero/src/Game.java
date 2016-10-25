import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;

public class Game
{
	private static Game game;
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
	 * Method: exitGame();
	 * 
	 * Exits the game
	 */
	public void exitGame()
	{
		System.exit(0);
	}

	/**
	 * Method: getItem()
	 * 
	 * @return the items in the game
	 */
	public List<Item> getItem()
	{
		return game.item;
	}
	
	/**
	 * Method: getRoom()
	 * 
	 * @return the first room in the game
	 */
	public List<Room> getRooms()
	{

		return game.startingRoom;

	}

	/**
	 * Method: setGameRun()
	 * 
	 * @return a boolean to set the game.
	 */
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

	/**
	 * Method: runs GUI class
	 * @param args
	 */
	public void play(String[] args){
		Application.launch(GUI.class, args);
	}
}
