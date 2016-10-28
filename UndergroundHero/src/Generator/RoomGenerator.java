package Generator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Room.Room;
import Room.RoomBasic;

/**
 * This class is responsible for generating Room objects
 * @author John
 *
 */
public class RoomGenerator {
	
	private ArrayList<Room> roomList;
	
	/**
	 * 
	 * @return ArrayList<Room>
	 */
	public ArrayList<Room> getRoomList() {
		generateRoom();
		return roomList;
	}
	
	/**
	 * @method Reads in a text file, creates Room object and adds them to an ArrayList
	 */
	private void generateRoom(){
		Scanner in = null;
		roomList = new ArrayList<Room>();

		try{
			
			in = new Scanner(new BufferedReader(new FileReader("res/Room_Data.txt")));
			
			while (in.hasNext() && in != null) {
				String roomNumber = in.nextLine();
				String roomDescription = in.nextLine();
				String roomExits = in.nextLine();
				String locked = in.nextLine();
				
				boolean roomLocked;
				if(locked.equalsIgnoreCase("true")){
					roomLocked = true;
				}else{
					roomLocked = false;
				}
				
				Room newRoom = new RoomBasic(roomNumber, roomDescription, roomExits, roomLocked);
				roomList.add(newRoom);
				
			}
		}catch (IOException e){
			System.out.println("Unable to read file."); 
		}finally{
			in.close();
		}
	}
	
}
