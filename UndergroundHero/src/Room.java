public class Room {

	private String roomNumber;
	private String roomDescription;
	private String exits;
	private Monster roomMonster;
	private Puzzle roomPuzzle;
	   
	
	Room(){
		
	}
	
	Room(String roomNumber, String roomDescription, String exits){
		this.roomNumber = roomNumber;
		this.roomDescription = roomDescription;
		this.exits = exits;
	}
	
	Room(String roomNumber, String roomDescription, String exits, Monster roomMonster, Puzzle roomPuzzle){
		this.roomNumber = roomNumber;
		this.roomDescription = roomDescription;
		this.exits = exits;
		this.roomMonster = roomMonster;
		this.roomPuzzle = roomPuzzle;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getRoomDescription() {
		return roomDescription;
	}

	public void setRoomDescription(String roomDescription) {
		this.roomDescription = roomDescription;
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
