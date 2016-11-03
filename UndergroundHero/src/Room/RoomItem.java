package Room;

import Item.Item;
/**
 * Class extends room, responsible for create room objects that contains items.
 * @author John
 *
 */
public class RoomItem extends Room{

	private static final long serialVersionUID = -7929862074742949065L;

	public RoomItem(int id, String name, String description, String exits, boolean locked, Item roomItem) {
		super(id, name, description, exits, locked);
		this.roomItem = roomItem;;
	}

}
