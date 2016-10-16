import java.util.List;

public class Item {

	private String name, description, type;

	public Item(){
		
	}
	
	public Item(String name, String description, String type) {
		this.name = name;
		this.description = description;
		this.type = type;
	}

	public String getName() {
		return name;
	}

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

	public void openInventory(List<Item> list){
		for(Item i : list){
			System.out.println(i.toString());
		}
	}
	
	public void useItem(){
		//TODO: will remove item when on use with the exception for equips and key items
	}

}
