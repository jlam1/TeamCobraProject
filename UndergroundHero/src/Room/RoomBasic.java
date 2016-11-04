package Room;

/**
 * This class is responsible for creating Basic room objects with no monsters or
 * puzzles.
 * 
 * @author John
 *
 */
public class RoomBasic extends Room {

	private static final long serialVersionUID = 8165444880281583428L;

	/**
	 * The following method is the constructor for Basic type Room objects.
	 * 
	 * @param id
	 *            From parent.
	 * @param name
	 *            From parent.
	 * @param description
	 *            From parent.
	 * @param exits
	 *            From parent.
	 * @param locked
	 *            From parent.
	 */
	public RoomBasic(int id, String name, String description, String exits, boolean locked) {
		super(id, name, description, exits, locked);
	}

}
