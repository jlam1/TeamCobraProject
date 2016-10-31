package Tester;

import java.util.List;

import Character.Monster;
import Character.Player;
import Generator.MonsterGenerator;

public class PlayerAttackTest {
	
	public static void main(String[] args) {
		
		Player player = new Player(10, 3, 3, 2);
		player.setName("You");
		List<Monster> monsterList = new MonsterGenerator().getMonsterList();
		
		Monster monster = monsterList.get(0);
		
		player.attack(monster);
		
		monster.attack(player);
		
		
	}

}
