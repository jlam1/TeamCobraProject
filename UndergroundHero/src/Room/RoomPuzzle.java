package Room;

import Puzzle.*;

public class RoomPuzzle extends Room{

	public RoomPuzzle(int id, String name, String description, String exits, boolean locked, Puzzle roomPuzzle) {
		super(id, name, description, exits, locked);
		this.roomPuzzle = roomPuzzle;
	}

}
