
public class Monster extends Status {

	Monster(){
		
	}
	
	Monster(String name, String description, int hp, int atk, int def, int spd){
		this.name = name;
		this.description = description;
		this.hp = hp;
		this.atk = atk;
		this.def = def;
		this.spd = spd;
	}

	
//	public class Action{
//		
//		private String actionName1;
//		private String actionName2;
//		private String actionName3;
//		private String actionName4;
//		private double actionChance;
//		
//		Action(String actionName1, String actionName2, String actionName3, String actionName4, double actionChance){
//			this.actionName1 = actionName1;
//			this.actionName2 = actionName2;
//			this.actionName3 = actionName3;
//			this.actionName4 = actionName4;
//			this.actionChance = actionChance;
//		}
//	}
}
