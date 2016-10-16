public class KeyItem extends Item {
	
	private boolean compatible;

	public KeyItem(String name, String description, String type) {
		super(name, description, type);
	}

	public boolean isCompatible() {
		return compatible;
	}

	public void setCompatible(boolean compatible) {
		this.compatible = compatible;
	}
	
	
}
