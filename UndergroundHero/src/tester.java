
//CLASS IS JUST FOR TESTING, WILL NOT INCLUDE IN FINAL PRODUCT

public class tester {
	
	public static void main(String[] args) {
		
		ResourceManager rm = new ResourceManager();
		
		rm.writeToMonsterList();
		rm.writeToRoomList();
		
		for(Monster i : rm.getMonsterList()){
//			System.out.println(i.getName());
//			System.out.println(i.getHp());
//			System.out.println(i.getDescription());
//			System.out.println("Skill: " + i.getAction().getActionName1());
			System.out.println(i.toString());
		}
		
		
		
//		System.out.println("\n\n");
//		
//		for(Room i : rm.getRoomList()){
//			System.out.println(i.getRoomNumber());
//			System.out.println(i.getRoomDescription());
//			System.out.println(i.getExits());
//		}
		
		
		
	}

}
