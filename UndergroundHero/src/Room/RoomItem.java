package Room;

import Item.Item;

public class RoomItem extends Room{

	public RoomItem(String name, String description, String exits, boolean locked, Item roomItem) {
		super(name, description, exits, locked);
		this.roomItem = roomItem;;
	}

}
