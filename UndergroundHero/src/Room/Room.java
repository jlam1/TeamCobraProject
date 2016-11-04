package Room;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import Character.Monster;
import Character.Properties;
import Item.Item;
import Puzzle.Puzzle;

/**
 * This class is responsible for instantiating room objects. Contains attributes
 * and behaviors for Room objects.
 * 
 * @author John
 *
 */
public abstract class Room implements Properties, Comparable<Room>, Serializable {

	private static final long serialVersionUID = -1722849142865991875L;
	protected String name, description, exits;
	protected boolean locked;
	protected int id;
	protected Item roomItem;
	protected Monster roomMonster;
	protected Puzzle roomPuzzle;
	private Map<String, Room> exitMap;

	/**
	 * The following method is the constructor for Room objects.
	 * 
	 * @param id
	 *            This is the first parameter to to Room constructor.
	 * @param name
	 *            This is the second parameter to to Room constructor.
	 * @param description
	 *            This is the third parameter to to Room constructor.
	 * @param exits
	 *            This is the fourth parameter to to Room constructor.
	 * @param locked
	 *            This is the fifth parameter to to Room constructor.
	 */
	public Room(int id, String name, String description, String exits, boolean locked) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.exits = exits;
		this.locked = locked;
		exitMap = new HashMap<String, Room>();
	}

	public String getExits() {
		return "Exits: " + this.exits;
	}

	/**
	 * The following method maps a string for each cardinal direction to every
	 * possible room exit in the exitMap map.
	 * 
	 * @param north
	 *            This is the first parameter to setExits method.
	 * @param east
	 *            This is the second parameter to setExits method.
	 * @param south
	 *            This is the third parameter to setExits method.
	 * @param west
	 *            This is the fourth parameter to setExits method.
	 */
	public void setExits(Room north, Room east, Room south, Room west) {
		if (north != null)
			exitMap.put("north", north);
		if (east != null)
			exitMap.put("east", east);
		if (south != null)
			exitMap.put("south", south);
		if (west != null)
			exitMap.put("west", west);
	}

	/**
	 * The following method gets the room that is in the direction indicated by
	 * parameter.
	 * 
	 * @param direction
	 *            The only parameter for getNextRoom() method.
	 * @return Room: The room that is set to the direction indicated by
	 *         parameter.
	 */
	public Room getNextRoom(String direction) {
		return (Room) exitMap.get(direction);
	}

	/**
	 * @method Sorting arrays by ascending order of room IDs
	 */
	@Override
	public int compareTo(Room room) {
		int roomID = room.getId();
		return this.id - roomID;
	}

	public void setExitMap(Map<String, Room> exitMap) {
		this.exitMap = exitMap;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public Item getRoomItem() {
		return roomItem;
	}

	public void setRoomItem(Item roomItem) {
		this.roomItem = roomItem;
	}

	public Monster getRoomMonster() {
		return roomMonster;
	}

	public void setRoomMonster(Monster roomMonster) {
		this.roomMonster = roomMonster;
	}

	public Puzzle getRoomPuzzle() {
		return roomPuzzle;
	}

	public void setRoomPuzzle(Puzzle roomPuzzle) {
		this.roomPuzzle = roomPuzzle;
	}

	public String getName() {
		return name;
	}

}
