import java.util.List;
import java.util.Random;

public class BattleTester extends Character{
	static Player player;
	// TODO Create Speed comparison
	// TODO Create Attack calculation
	// TODO Create Defend calculation
	// TODO Create Escape method
	
	public static boolean AtkCmd = false;
	public static boolean DefCmd = false;
	public static boolean HealCmd = false;
	public static boolean PlayerTurn = true;
	static Random Generator = new Random(); 
	static int RandMon = Generator.nextInt(7)+ 1; //Randomly selects a monster from Monster_Data


	
	public static void main(String[] args) {
		player = new Player(10,1,3,2);
		ResourceManager resourceManager = new ResourceManager();
		resourceManager.loadAssetToList();		
		List<Monster> monsterList = resourceManager.getMonsterList();
		String monsterName = monsterList.get(RandMon).getName();
		String monsterDesc = monsterList.get(RandMon).getDescription();
		int monsterHP = monsterList.get(RandMon).getHp();
		int monsterAtk = monsterList.get(RandMon).getAtk();
		int monsterDef = monsterList.get(RandMon).getDef();
		int monsterSpd = monsterList.get(RandMon).getSpd();
		
 
		//Heal Command - Unfinished
		if (HealCmd == true && ConsumableItem.getCount() > 0){
			ConsumableItem.useItem();
			// Print feedback that item has been used.
			HealCmd = false;
		}
		else if (HealCmd == true && ConsumableItem.count <= 0){
			// Print feedback that there is no item to use
			HealCmd = false;
		}
		
		//Attack Command - Unfinished
		if (AtkCmd == true && player.spd >= monsterSpd){ //When attacking and player is faster than monster
			System.out.println("Player will attack first");
			monsterHP = monsterHP - (player.atk - monsterDef);
			AtkCmd = false;
			if (monsterHP > 0){
				//Monster attack
			}
		}
		else if (AtkCmd == true && player.spd < monsterSpd){ //When attacking and player is slower than monster
			System.out.println("Monster will attack first");
			//Monster attack
			if (player.hp > 0){
				monsterHP = monsterHP - (player.atk - monsterDef);
				AtkCmd = false;
			}
		}
		
		//Defend Command - Unfinished
		if (DefCmd == true){
			player.def = player.def * 2;
			//Monster attack
			player.def = player.def / 2;
			DefCmd = false;
		}
		
				
		System.out.println(monsterName+": "+monsterDesc+" HP: "+monsterHP+" Atk: "+monsterAtk+" Def: "+monsterDef+
				" Spd: "+monsterSpd);
		System.out.println(player.hp+" "+player.atk+" "+player.def+" "+player.spd);
		System.out.println(RandMon);
		
		
		
	}
}
