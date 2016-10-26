import java.util.HashMap;
import java.util.Map;

public class Room implements Properties{

	private String name, description, exits;
	private boolean locked;
	private Map<String, Room> exitMap;
	private Monster roomMonster;
	private Puzzle roomPuzzle;
	
	Room(String name, String description, String exits, boolean locked){
		this.name = name;
		this.description = description;
		this.exits = exits;
		this.locked = locked;
		exitMap = new HashMap<String, Room>();
		roomMonster = new Monster();
		roomPuzzle = new Puzzle();
	}
	
	/**
	 * 
	 * @return Room's exits
	 */
	public String getExits(){
		return "Exits: " + this.exits;
	}
	
	/**
	 * Puts lists of rooms in a map of <String,Room>
	 * @param north
	 * @param east
	 * @param south
	 * @param west
	 */
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
    
    /**
     * @method Access Map to get value of key direction
     * @param direction
     * @return Map's value
     */
    public Room nextRoom(String direction){
        return (Room)exitMap.get(direction);
    }  

	/**
	 * @method adds a monster object to room
	 * @param monster
	 */
	public void setRoomMonster(Monster roomMonster) {
		this.roomMonster = roomMonster;
	}
	
	/**
	 * @method adds a puzzle object to room
	 * @param puzzle
	 */
	public void setRoomPuzzle(Puzzle roomPuzzle) {
		this.roomPuzzle = roomPuzzle;
	}
	
	public Monster getRoomMonster() {
		return roomMonster;
	}

	public Puzzle getRoomPuzzle() {
		return roomPuzzle;
	}

	public String getName() {
		return this.name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public boolean getLocked(){
		return this.locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
