import java.util.List;

public class Tester1 {

	public static void main(String[] args) {
		
		ResourceManager resource = new ResourceManager();
		
		List<Room> rooms = resource.getRoomList();
		System.out.println("Room name " + rooms.get(4).getName());
		System.out.println("Room exits " + rooms.get(10).getExits());
		
		List<Item> items = resource.getItemList();
		System.out.println("Item id: " + items.get(1).getID());
		
		
		
		
		
	}
	
}
