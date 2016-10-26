import java.util.List;

public class Tester1 {

	public static void main(String[] args) {
		
		List<Monster> monsterList = new ResourceManager().getMonsterList();
		List<Room> roomList = new ResourceManager().getRoomList();
		List<Puzzle> puzzleList = new ResourceManager().getPuzzleList();
		
//		double spawnChance;
//		for(int i = 0; i < 20; i++){
//			spawnChance = (Math.random()*100);
//			System.out.printf("Chance: %.2f\n", spawnChance);
//		}
//		
//		System.out.println("\n");
//		
//		for(Monster i : monsterList){
//			System.out.println(i.toString() + "\n");
//		}
//		
//		System.out.println("\n");
//		
//		for(Puzzle i : puzzleList){
//			System.out.println(i.toString());
//		}
		
//		for(Room i : roomList){
//			System.out.println(i.getName());
//			System.out.println(i.getLocked());
//		}
		
		puzzleList.get(0).setSolved(false);
		System.out.println(puzzleList.get(0).isSolved());
		
		roomList.get(2).setRoomPuzzle(puzzleList.get(0));
		roomList.get(2).setRoomMonster(monsterList.get(0));
		
		System.out.println(roomList.get(2).getRoomPuzzle().getName());
		System.out.println(roomList.get(2).getRoomMonster());
		
		
	}
}
