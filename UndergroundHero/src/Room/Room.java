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
public class Room implements Properties, Comparable<Room>, Serializable {

	private static final long serialVersionUID = -1722849142865991875L;
	protected String name, description, exits;
	protected boolean locked;
	protected int id;
	protected Item roomItem;
	protected Monster roomMonster;
	protected Puzzle roomPuzzle;
	private Map<String, Room> exitMap;

	/**
	 * The following method is the constructor for basic Room objects.
	 * 
	 * @param id
	 * @param name
	 * @param description
	 * @param exits
	 * @param locked
	 */
	public Room(int id, String name, String description, String exits, boolean locked) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.exits = exits;
		this.locked = locked;
		exitMap = new HashMap<String, Room>();
	}

	/**
	 * The following method is the specific constructor for Item type Room
	 * objects.
	 * 
	 * @param id
	 * @param name
	 * @param description
	 * @param exits
	 * @param locked
	 * @param roomItem
	 *            Specific to Item type Room objects.
	 */
	public Room(int id, String name, String description, String exits, boolean locked, Item roomItem) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.exits = exits;
		this.locked = locked;
		this.roomItem = roomItem;
		;
	}

	/**
	 * The following method is the specific constructor for Monster type
	 * Room objects.
	 * 
	 * @param id
	 * @param name
	 * @param description
	 * @param exits
	 * @param locked
	 * @param roomMonster
	 *            Specific to Monster type Room objects.
	 */
	public Room(int id, String name, String description, String exits, boolean locked, Monster roomMonster) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.exits = exits;
		this.locked = locked;
		this.roomMonster = roomMonster;
	}
	
	/**
	 * The following method is the constructor for Puzzle type Room objects.
	 * 
	 * @param id
	 * @param name
	 * @param description
	 * @param exits
	 * @param locked
	 * @param roomPuzzle
	 *            Specific to Puzzle type Room objects.
	 */
	public Room(int id, String name, String description, String exits, boolean locked, Puzzle roomPuzzle) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.exits = exits;
		this.locked = locked;
		this.roomPuzzle = roomPuzzle;
	}

	public String getExits() {
		return "Exits: " + this.exits;
	}

	/**
	 * The following method maps a string for each cardinal direction to every
	 * possible room exit in the exitMap map.
	 * 
	 * @param north
	 * @param east
	 * @param south
	 * @param west
	 */
	public void setExits(Room north, Room east, Room south, Room west) {
		if (north != null)
			exitMap.put("NORTH", north);
		if (east != null)
			exitMap.put("EAST", east);
		if (south != null)
			exitMap.put("SOUTH", south);
		if (west != null)
			exitMap.put("WEST", west);
	}

	/**
	 * The following method gets the room that is in the direction indicated by
	 * parameter.
	 * 
	 * @param direction
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
