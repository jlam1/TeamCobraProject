public class Room implements Properties{

	private String name, description, exits;
	private int id;
	private boolean locked;
	private Monster roomMonster;
	private Puzzle roomPuzzle;
	
	Room(String name, String description, String exits){
		this.name = name;
		this.description = description;
		this.exits = exits;
	}
	
	Room(int id, String name, String description, String exits, Monster roomMonster, Puzzle roomPuzzle){
		this.id = id;
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

	public void setExits(String exits) {
		this.exits = exits;
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
