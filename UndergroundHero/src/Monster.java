
public class Monster extends Character {

	private Action action;
	
	/**
	 * 
	 * @param name
	 * @param description
	 * @param hp
	 * @param atk
	 * @param def
	 * @param spd
	 * @param action
	 */
	Monster(String name, String description, int hp, int atk, int def, int spd, boolean dead, Action action){
		this.name = name;
		this.description = description;
		this.hp = hp;
		this.atk = atk;
		this.def = def;
		this.spd = spd;
		this.dead = dead;
		this.action = action;
	}
	
	public Action getAction(){
		return action;
	}

	@Override
	public String toString() {
		return "Monster [name=" + name + ", hp=" + hp + ", atk=" + atk + ", spd=" + spd
				+ ", def=" + def + ", id=" + id + ", description="
				+ description + "]";
	}

	static class Action{
		
		private String actionName1;
		private String actionName2;
		private String actionName3;
		private String actionName4;
		
		/**
		 * 
		 * @param actionName1
		 * @param actionName2
		 * @param actionName3
		 * @param actionName4
		 */
		Action(String actionName1, String actionName2, String actionName3, String actionName4){
			this.actionName1 = actionName1;
			this.actionName2 = actionName2;
			this.actionName3 = actionName3;
			this.actionName4 = actionName4;
		}

		public String getActionName1() {
			return actionName1;
		}

		public void setActionName1(String actionName1) {
			this.actionName1 = actionName1;
		}

		public String getActionName2() {
			return actionName2;
		}

		public void setActionName2(String actionName2) {
			this.actionName2 = actionName2;
		}

		public String getActionName3() {
			return actionName3;
		}

		public void setActionName3(String actionName3) {
			this.actionName3 = actionName3;
		}

		public String getActionName4() {
			return actionName4;
		}

		public void setActionName4(String actionName4) {
			this.actionName4 = actionName4;
		}
		
	}
}
