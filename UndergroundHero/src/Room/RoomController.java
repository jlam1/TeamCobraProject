package Room;

import java.util.ArrayList;

public class RoomController {
	
	private ArrayList<Room> roomFactoryList;
	
	public RoomController(){
		roomFactoryList = new RoomFactory().getRoomFactoryList();
	}
	
	public Room room(String name) {
		Room rom;
		Room room = null;
		
		for(int i = 0; i < roomFactoryList.size(); i++){
			
			rom = roomFactoryList.get(i);
			String roomName = rom.getName();
			
			if(roomName.equalsIgnoreCase(name)){
				room = rom;
				break;
			}	
		}
		
		return room;
	}	
	
	private Room room(int index){
		return roomFactoryList.get(index);
	}

}
