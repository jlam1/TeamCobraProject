package Character;
public class Player extends Character {

	public Player(int hp, int atk, int spd, int def) {
		super(hp, atk, spd, def);
	}

	@Override
	public String toString() {
		return "PLAYER\nHealth:\t [" + hp + "/" + hp + "]\nDefense:\t [" + def + "]\nSpeed:\t [" + spd + "]\nAttack:\t [" + atk + "]";
	}

}
