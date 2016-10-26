public class Player extends Character {

	public Player(int hp, int atk, int def, int spd) {
		this.hp = hp;
		this.def = def;
		this.atk = atk;
		this.spd = spd;
	}

	@Override
	public String toString() {
		return "PLAYER\nHealth:\t [" + hp + "/" + hp + "]\nDefense:\t [" + def + "]\nSpeed:\t [" + spd + "]\nAttack:\t [" + atk + "]";
	}

}
