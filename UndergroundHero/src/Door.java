public class Door {

	private int doorID;

	private boolean lockedDoor;

	public Door(int doorID, boolean lockedDoor)
	{
		this.doorID = doorID;
		this.lockedDoor = lockedDoor;
	}

	/**
	 * @return the doorID
	 */
	public int getDoorID()
	{
		return doorID;
	}

	/**
	 * @param doorID the doorID to set
	 */
	public void setDoorID(int doorID)
	{
		this.doorID = doorID;
	}

	/**
	 * @return the lockedDoor
	 */
	public boolean isLockedDoor()
	{
		return lockedDoor;
	}

	/**
	 * @param lockedDoor the lockedDoor to set
	 */
	public void setLockedDoor(boolean lockedDoor)
	{
		this.lockedDoor = lockedDoor;
	}
	
	
	
}
