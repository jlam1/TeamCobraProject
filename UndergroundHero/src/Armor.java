public class Armor extends Item {

	private int defense;
	
	/**
	 * 
	 * @param name
	 * @param description
	 * @param type
	 */
	public Armor(String name, String description, String type){
		super(name, description, type);
	}
	
	/**
	 * 
	 * @return defense
	 */
	public int getDefense(){
		return this.defense;
	}
}
