import java.util.List;

public class Item implements Properties{

	private String name, description, type;
	private int id;

	public Item(){
		
	}
	
	/**
	 * 
	 * @param name
	 * @param description
	 * @param type
	 */
	public Item(String name, String description, String type) {
		this.name = name;
		this.description = description;
		this.type = type;
	}

	@Override
	public int getID() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	@Override
	public String toString() {
		return "Item [name=" + name + ", description=" + description + ", type=" + type + "]";
	}

	/**
	 * 
	 * @param list
	 * Method will display a list of items using toString
	 */
	public void openInventory(List<Item> list){
		for(Item i : list){
			System.out.println(i.toString());
		}
	}

}
