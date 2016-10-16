public class KeyItem extends Item {

	public KeyItem(String name, String description, String type) {
		super(name, description, type);
	}

	private boolean compatible;

	public boolean isCompatible() {
		return compatible;
	}

	public void setCompatible(boolean compatible) {
		this.compatible = compatible;
	}

	
	
}
