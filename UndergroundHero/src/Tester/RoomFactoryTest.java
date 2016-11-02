package Tester;

import java.util.ArrayList;

import Room.Room;
import Room.RoomFactory;

public class RoomFactoryTest {
	
	public static void main(String[] args) {
		
		RoomFactory factory = new RoomFactory();
		ArrayList<Room> rooms = factory.getRoomFactoryList();
		
		System.out.println(rooms.size());
		
		for(Room i : rooms) {
			System.out.println(i.getName());
			
			if(i.getRoomPuzzle() != null)
				System.out.println(i.getRoomPuzzle().getName());
			if(i.getRoomMonster() != null)
				System.out.println(i.getRoomMonster().getName());
			if(i.getRoomItem() != null)
				System.out.println(i.getRoomItem().getName());
		}
		
	}

}
