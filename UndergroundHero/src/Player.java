public class Player extends Character {

	public Player(int hp, int def, int atk, int spd) {
		this.hp = hp;
		this.def = def;
		this.atk = atk;
		this.spd = spd;
	}

	@Override
	public String toString() {
		return "Health:\t [" + hp + "/" + hp + "]\nDefense:\t [" + def + "]\nSpeed:\t [" + spd + "]\nAttack:\t [" + atk + "]";
	}

}
