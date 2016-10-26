import java.util.List;

public class Tester1 {
	
	static int monsterHP, playerHP, monsterAtk, playerAtk;
	static boolean monsterDead, playerDead;

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
		
		System.out.println();
		
		roomList.get(2).setRoomPuzzle(puzzleList.get(8));
		roomList.get(2).setRoomMonster(monsterList.get(1));
		
		System.out.println("Puzzle name: " + roomList.get(2).getRoomPuzzle().getName());
		System.out.println();
		
		System.out.println(roomList.get(2).getRoomMonster());
		
		System.out.println();
		System.out.println(roomList.get(2).getRoomMonster().getName());
		System.out.println(roomList.get(2).getRoomMonster().getDescription());
		System.out.println(roomList.get(2).getRoomMonster().getHp());
		boolean isDead = roomList.get(2).getRoomMonster().setDead(false);
		System.out.println("dead: " + isDead);
		boolean isNotDead = roomList.get(2).getRoomMonster().setDead(true);
		System.out.println("dead: " + isNotDead);
		
		System.out.println();
		
		Player player = new Player(10, 1, 3, 2);
		
		Monster monster = roomList.get(2).getRoomMonster();
		String monsterName = monster.getName();
		String monsterDescription = monster.getDescription();
		monsterHP = monster.getHp();
		monsterAtk = monster.getAtk();
		playerHP = player.getHp();
		playerAtk = player.getAtk();
		monsterDead = monster.setDead(false);
		playerDead = player.getDead();
		boolean flee = false;
		
		System.out.println(monsterName);
		battle(player, monster);
		
		
		
		
		
		
	}
	
	static void battle(Player player, Monster monster){
		monsterHP -= playerAtk;
		playerHP -= monsterAtk;
		
		System.out.println(monsterHP);
		System.out.println(playerHP);
		
		if(monsterHP <= 0){
			monsterDead = true;
			System.out.println("MONSTER DEAD");
		}
		if(playerHP <= 0){
			playerDead = true;
			System.out.println("PLAYER DEAD");
		}
	}
}
