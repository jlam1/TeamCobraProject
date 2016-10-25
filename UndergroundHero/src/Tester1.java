import java.util.List;
import java.util.Scanner;

public class Tester1 {
	
	static Room currentRoom;
	static List<Room> rooms;

	public static void main(String[] args) {
		
		ResourceManager resource = new ResourceManager();
		rooms = resource.getRoomList();
		boolean gameRun = true;	
		Scanner input = new Scanner(System.in);
		
		connectRooms();
		
		currentRoom = room(1);
		
		while(gameRun){
			System.out.print(">");
			String command = input.nextLine();
			
			if(validCommand(command)){
				String direction = command;
				Room nextRoom = currentRoom.nextRoom(direction);
				
				if(nextRoom == null){
					System.out.println("No door");
				}else{
					currentRoom = nextRoom;
					System.out.println("Room number: " + currentRoom.getName());
				}
				
			}else if(command.equalsIgnoreCase("LOOK")){
				System.out.println(currentRoom.getName());
				System.out.println(currentRoom.getDescription());
				System.out.println(currentRoom.getExits());
			}else if(command.equalsIgnoreCase("QUIT")){
				System.out.println("Quitting Game.");
				gameRun = false;
			}else{
				System.out.println("Not a valid command.");
			}

		}
		
	}
	
	static boolean validCommand(String input){
		if(input.equalsIgnoreCase("EAST") || input.equalsIgnoreCase("WEST") || input.equalsIgnoreCase("NORTH") || input.equalsIgnoreCase("SOUTH")){
			return true;
		}else{
			return false;
		}
	}
	
	static Room room(int index){
		return rooms.get(index);
	}
	
	static void connectRooms(){
		//  NORTH   EAST   SOUTH   WEST
		
		//connect floor 1
		room(0).setExits(null, room(1), null, null);
		room(1).setExits(null, room(2), null, room(0));
		room(2).setExits(null, room(3), null, room(1));
		room(3).setExits(null, room(4), room(6), room(2));
		room(4).setExits(null, null, room(5), room(3));
		room(5).setExits(room(4), room(9), null, room(6));
		room(6).setExits(room(3), room(5), null, room(7));
		room(7).setExits(null, room(6), null, room(8));
		room(8).setExits(null, room(7), null, null);
		room(9).setExits(null, room(10), null, room(5));
		
		//connect floor 2
		
		//connect floor 3
		
		//connect floor 4
		
	}
	
}
