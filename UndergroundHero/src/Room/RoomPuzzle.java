package Room;

import Puzzle.*;

public class RoomPuzzle extends Room{

	public RoomPuzzle(String name, String description, String exits, boolean locked, Puzzle roomPuzzle) {
		super(name, description, exits, locked);
		this.roomPuzzle = roomPuzzle;
	}

}
