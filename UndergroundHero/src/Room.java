import java.util.LinkedList;
import java.util.List;

public class Room {

	private String roomNumber;
	private String roomDescription;
	private String exits;
	private Monster roomMonster;
	private Puzzle roomPuzzle;
	private LinkedList<Room> roomLinkedList;
	
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
	
	public void connectRooms(List<Room> roomList){
		
	}
	
//	public Room[][] roomArray(List<Room> roomList){
//		Room room[][] = new Room[4][14];
//		int roomIndex = 0;
//		
//		for(int i = 0; i < roomList.size(); i++){
//			if(i < 9){
//				room[0][roomIndex] = roomList.get(i);
//				roomIndex++;
//			}else if(i >= 9 && i < 18){
//				roomIndex = 0;
//				room[1][roomIndex] = roomList.get(i);
//				roomIndex++;
//			}else if(i >= 18 && i < 28){
//				roomIndex = 0;
//				room[2][roomIndex] = roomList.get(i);
//				roomIndex++;
//			}else if(i >= 28){
//				roomIndex = 0;
//				room[3][roomIndex] = roomList.get(i);
//				roomIndex++;
//			}else{
//				
//			}
//		}	
//		return room;
//	}
	
	public String viewRoom(Room currentRoom){
		return currentRoom.getRoomDescription();
	}
	
	public String viewExits(Room currentRoom){
		return "Exits: " + currentRoom.getExits();
	}
	
	public String viewRoomNumber(Room currentRoom){
		return "Room: " + currentRoom.getRoomNumber();
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
