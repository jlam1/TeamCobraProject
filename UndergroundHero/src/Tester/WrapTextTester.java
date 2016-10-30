package Tester;

import java.util.List;

import Room.Room;
import Room.RoomFactory;

public class WrapTextTester {
	
	public static void main(String[] args) {
		
		List<Room> rooms = new RoomFactory().getRoomFactoryList();
		
		Room currentRoom = rooms.get(1);
		
		String shortDesc = shortenStringFullWords(currentRoom.getDescription(), 30);
		
		System.out.println(shortDesc);
		
	}
	
	static String shortenStringFullWords(String str, int maxLength) {
	    StringBuilder output = new StringBuilder();
	    String[] tokens = str.split(" ");
	    for (String token: tokens) {
	        if (output.length() + token.length() <= maxLength - 3) {
	            output.append(token);
	            output.append(" ");
	        } else {
	            return output.toString().trim() + "...";
	        }
	    }
	    return output.toString().trim();
	}

}
