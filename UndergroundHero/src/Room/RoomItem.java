package Room;

import Item.Item;

public class RoomItem extends Room{

	public RoomItem(int id, String name, String description, String exits, boolean locked, Item roomItem) {
		super(id, name, description, exits, locked);
		this.roomItem = roomItem;;
	}

}
