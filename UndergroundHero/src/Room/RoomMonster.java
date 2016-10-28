package Room;

import Character.Monster;

public class RoomMonster extends Room{

	public RoomMonster(String name, String description, String exits, boolean locked, Monster roomMonster) {
		super(name, description, exits, locked);
		this.roomMonster = roomMonster;
	}
	
}
