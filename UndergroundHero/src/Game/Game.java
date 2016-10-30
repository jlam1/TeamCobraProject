package Game;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import Character.Player;
import Generator.ItemGenerator;
import Item.Item;
import Room.Room;
import Room.RoomFactory;
import javafx.application.Application;

public class Game
{
	private Game game;
	private Player player;
	private ArrayList<Room> factoryList;
	private ArrayList<Item> item;
	private Room room;

	public Game(ArrayList<Room> rooms, Player player)
	{
		factoryList = new RoomFactory().getRoomFactoryList();
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
		ArrayList<Item> itemList = new ItemGenerator().getItemList();
		player = new Player(10, 1, 2, 1);
		player.pickUp(itemList.get(0));
		player.pickUp(itemList.get(2));
		room = factoryList.get(1);		
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

}
