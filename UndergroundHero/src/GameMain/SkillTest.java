package GameMain;

import java.util.List;

import Character.Monster;
import Character.MonsterGenerator;
import Character.Skill;
import Room.Room;
import Room.RoomFactory;

public class SkillTest {

	public static void main(String[] args) {
		
		List<Room> rooms = new RoomFactory().getRoomFactoryList();
		List<Monster> monsters = new MonsterGenerator().getMonsterList();
		
		List<Skill> skills = rooms.get(24).getRoomMonster().getSkillList();
		
		for(Skill s : skills) {
			System.out.println(s.getName());
			System.out.println(s.getType());
			System.out.println(s.getDmgRatio());
			System.out.println(s.getChance());
			System.out.println(s.getDescription());
			System.out.println(s.getChargeDesc());
			System.out.println();
		}
		
//		@Test
//		public void postiveCase1() {
//			assertEquals('what you want the output to equal to', 'methodName(insert params)');
//		}
		
	}
	
}
