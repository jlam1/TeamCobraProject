package Tester;

import java.util.ArrayList;

import Room.Room;
import Room.RoomFactory;

public class Tester5 {

	public static void main(String[] args) {
		
		RoomFactory factory = new RoomFactory();
		
		ArrayList<Room> rooms = new ArrayList<Room>();
		
		rooms = factory.getRoomFactoryList();
		
//		for(Room i : rooms){
//			System.out.println(i.getName());
//			System.out.println(i.getRoomPuzzle());
//			System.out.println();
//		}
		
		System.out.println(rooms.get(8).getRoomPuzzle().getName());
		
	}
	
}
