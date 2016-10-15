public class Room {

	private int roomNumber;
	private String roomDescription;
	private String exits;
	private Monster roomMonster;
	private Puzzle roomPuzzle;
	
	/*
	 * Room willl be in integer number instead of strings.
	 * ArrayList: 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 |
	 *            ---------------------------------------
	 *   Floor 1: 11  12  13  14  15  16  17  18  19 
	 * 
	 * ArrayList: 9 | 10 | 11 | 12 | 13 | 14 | 15 | 16 | 17 |
	 *            ---------------------------------------
	 *   Floor 2: 21  22   23   24   25   26   27   28   29 
	 *   
	 * ArrayList: 18 | 19 | 20 | 21 | 22 | 23 | 24 | 25 | 26 | 27 |
	 *            ---------------------------------------
	 *   Floor 3: 31   32   33   34   35   36   37   38   39  310
	 * 
	 * ArrayList: 28 | 29 | 30 | 31 | 32 | 33 | 34 | 35 | 36 | 37 | 38  |  39  | 40  |  41
	 *            ---------------------------------------  	  
	 *   Floor 4: 41   42   43   44   45   46   47   48   49  410   411   412    413    414
	 */       
	
	Room(int roomNumber, String roomDescription, String exits, Monster roomMonster, Puzzle roomPuzzle){
		this.roomNumber = roomNumber;
		this.roomDescription = roomDescription;
		this.exits = exits;
		this.roomMonster = roomMonster;
		this.roomPuzzle = roomPuzzle;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
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
