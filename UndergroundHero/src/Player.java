public class Player extends Status {

	public Player(int hp, int def, int atk, int spd) {
		this.hp = hp;
		this.def = def;
		this.atk = atk;
		this.spd = spd;
	}

	@Override
	public String toString() {
		return "[HP]: " + hp + "\n[DEF] : " + def + "\n[SPD] : " + spd + "\n[ATK] : " + atk;
	}

}
