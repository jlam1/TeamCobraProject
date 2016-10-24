public class KeyItem extends Item {
	
	private boolean compatible;

	/**
	 * 
	 * @param name
	 * @param description
	 * @param type
	 * @param compatible 
	 */
	public KeyItem(String name, String description, String type, boolean compatible) {
		super(name, description, type);
		this.compatible = compatible;
	}

	public boolean isCompatible() {
		return compatible;
	}

	public void setCompatible(boolean compatible) {
		this.compatible = compatible;
	}
	
	
}
