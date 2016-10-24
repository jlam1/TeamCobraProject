import java.util.List;

public class Tester1 {

	public static void main(String[] args) {
		
		ResourceManager resource = new ResourceManager();
		
		List<Room> rooms = resource.getRoomList();
		
		System.out.println(rooms.get(4).getRoomNumber());
		
		
		
	}
	
}
