public class Monster extends Status {
	
	private double spawnRate;

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
}
