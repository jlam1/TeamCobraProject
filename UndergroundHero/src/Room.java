import java.util.HashMap;
import java.util.Map;

public class Room implements Properties{

	private String name, description, exits;
	private int id;
	private boolean locked;
	private Monster roomMonster;
	private Puzzle roomPuzzle;
	private Map<String, Room> exitMap;
	
	Room(){
		
	}
	
	Room(String name, String description, String exits){
		this.name = name;
		this.description = description;
		this.exits = exits;
		exitMap = new HashMap<String, Room>();
	}
	
	Room(String name, String description, String exits, Monster roomMonster, Puzzle roomPuzzle){
		this.name = name;
		this.description = description;
		this.exits = exits;
		this.roomMonster = roomMonster;
		this.roomPuzzle = roomPuzzle;
	}
	
	public String viewRoom(Room currentRoom){
		return currentRoom.getDescription();
	}
	
	public String viewExits(Room currentRoom){
		return "Exits: " + currentRoom.getExits();
	}
	
	public String viewRoomNumber(Room currentRoom){
		return "Room: " + currentRoom.getName();
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
    
    public Room nextRoom(String direction){
        return (Room)exitMap.get(direction);
    }
	
	@Override
	public int getID() {
		return this.id;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getDescription() {
		return this.description;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getExits() {
		return exits;
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
}
