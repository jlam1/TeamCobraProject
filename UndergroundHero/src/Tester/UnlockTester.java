package Tester;

import java.util.List;

import Room.*;

public class UnlockTester {
	
	public static void main(String[] args) {
		
		List<Room> rooms = new RoomFactory().getRoomFactoryList();
		
		Room room = rooms.get(10);
		
		System.out.println(room.getName());
		System.out.println(room.isLocked());
		
		
	}

}
