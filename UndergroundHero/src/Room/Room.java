package Room;
import java.util.HashMap;
import java.util.Map;

import Character.Monster;
import Game.Properties;
import Item.Item;
import Puzzle.Puzzle;

public abstract class Room implements Properties{

	protected String name, description, exits;
	protected boolean locked;
	protected int id;
	protected Item roomItem;
	protected Monster roomMonster;
	protected Puzzle roomPuzzle;
	private Map<String, Room> exitMap;
	
	public Room(int id, String name, String description, String exits, boolean locked){
		this.id = id;
		this.name = name;
		this.description = description;
		this.exits = exits;
		this.locked = locked;
		exitMap = new HashMap<String, Room>();
	}

	public String getExits(){
		return "Exits: " + this.exits;
	}
	
    public void setExits(Room north, Room east, Room south, Room west){
        if(north != null)
        	exitMap.put("north", north);
        if(east != null)
        	exitMap.put("east", east);
        if(south != null)
        	exitMap.put("south", south);
        if(west != null)
        	exitMap.put("west", west);
    }
    
    public Room nextRoom(String direction){
        return (Room)exitMap.get(direction);
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
