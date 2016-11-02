package Room;

import Character.Monster;

public class RoomMonster extends Room{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4280534618587637309L;

	public RoomMonster(int id, String name, String description, String exits, boolean locked, Monster roomMonster) {
		super(id, name, description, exits, locked);
		this.roomMonster = roomMonster;
	}
	
}
