package Room;

import java.util.ArrayList;

/**
 * This class is responsible for generating Room objects
 * @author King
 *
 */

public class RoomGenerator {
	
	private ArrayList<Room> roomList;
	
	/**
	 * 
	 * @return ArrayList<Room>
	 */
	public ArrayList<Room> getRoomList() {
		generateRoom();
		return roomList;
	}
	
	/**
	 * @method Reads in a text file located in res folder and creates Room object and adds them to an ArrayList
	 */
	private void generateRoom(){
		roomList = new ArrayList<Room>();
		
		Room room = new Room(0, "1-0", "You decided to leave the underground lair, which ultimately leads to the destruction of all"
				+ "\nhumanity!"
				+ "\n(Secret Bad Ending)", "EAST", false);
		roomList.add(room);
		
		room = new Room(1, "1-1", "You find yourself in an office, the light is dimmed but you are still able to see. "
				+ "\nThere is a desk to the left with a tall artificial plant and a brown leather couch to the right."
				+ "\nThere is no trace of people in the office."
				+ "\nYou see a painting of a red boat on the sea hanging above the couch, and a poster next to the desk"
				+ "\nwhich reads \"Blood type AB\".", "EAST", false);
		roomList.add(room);
		
		room = new Room(2, "1-2", "You're in an office supply storage room with two big printers to the left upon"
				+ "\nentering the room."
				+ "\nAlong the north wall, there are some shelves with papers, locked cabinets,"
				+ "\nand computer equipment, as well as some important reminders"
				+ "\nand a contact list.", "WEST, EAST", false);
		roomList.add(room);
		 
		room = new Room(3, "1-3", "This is a meeting room which has a white board in the corner, several chairs and two"
				+ "\ncouches."
				+ "\nThere is a window on the north wall with the blinds down."
				+ "\nThe white board has some unfinished equations written on it.", "WEST, EAST", false);
		roomList.add(room);
		
		room = new Room(4, "1-4", "You come to a room with several shelves with neatly stacked clothes on them, along the"
				+ "\n shelves, there is a counter with a sink and a coffee maker."
				+ "\nA cabinet is above the coffee maker.", "SOUTH, WEST", false);
		roomList.add(room);
		
		room = new Room(5, "1-5", "This is a room containing a desk which looks fancy that is located in the south east"
				+ "\ncorner of the room."
				+ "\nIt is made from dark brown wood and has a long bookshelf next to it which extends to and"
				+ "\ncovers the entire north wall."
				+ "\nThe decorated shelves go from the ceiling to the ground and are filled with books of all kinds."
				+ "\nThere is a portrait of [MONA LISA] on the east wall.", "WEST, NORTH", false);
		roomList.add(room);
		
		room = new Room(6, "1-6", "You are in a room with a closet and a clean shower room, the floor is white, the light is"
				+ "\nwhite, everything is white, except for the bloody hand prints everywhere. In the northwestern"
				+ "\ncorner of the room, there's a thick trail of blood going into the room to the west."
				+ "\nThe doors are thick and not much sound gets through to the other side.", "WEST, EAST", false);
		roomList.add(room);
		
		room = new Room(7, "1-7", "Here is a room consisting of a steel bed with a body on top of it inside a body bag."
				+ "\nit's very cold in this room and doesn't smell like anything."
				+ "\nThe body could just be a dummy."
				+ "\nSame as the previous to this one, everything is white, the lights are dimmed, and are somewhat flickery."
				+ "\nThere are some sharp tools on a tray.", "WEST, EAST", false);
		roomList.add(room);
		
		room = new Room(8, "1-8", "The air in this room is filled with smoke and a large bed resides in the center."
				+ "\nThe room feels ominous and there is a strong odor.", "WEST, EAST", false);
		roomList.add(room);
		
		room = new Room(9, "1-9", "There is a lock to the door that can be locked once inside it, "
				+ "\nand it works as a safe place in case it is needed. "
				+ "\nThe room has a purple light bulb which makes the room appear very purple.", "WEST", false);
		roomList.add(room);
		
		room = new Room(10, "2-1", "You are in a small and narrow corridor that links the two exits together.", "WEST, EAST", true);
		roomList.add(room);
		
		room = new Room(11, "2-2", "This is a room with pictures of fine art that contains four exits.", "NORTH, SOUTH, WEST, EAST", false);
		roomList.add(room);
		
		room = new Room(12, "2-3", "There are four statues ordered on the north wall which look like they can be moved."
				+ "\nThey are ordered, from left to right: Duck, Giraffe, Elephant, and Cow. "
				+ "\nThere is a pedestal in the center of the room holding a pair of [SPEED BOOTS] that is protected by an"
				+ "\nenergy force field.", "SOUTH",false);
		roomList.add(room);
		
		room = new Room(13, "2-4", "There are four statues on the South wall which cannot be moved."
				+ "\nThey are ordered, from left to right: Cow, Giraffe, Duck, and Elephant. "
				+ "\nBehind each statue is a number:"
				+ "\nDuck has 1, Giraffe has 2, Elephant has 3, and Cow has 4. "
				+ "\nThe ordering of each statue seems important.", "NORTH", false);
		roomList.add(room);
		
		room = new Room(14, "2-5", "This is a long hallway with lasers that shoot at anyone who tries to pass."
				, "WEST, EAST", true);
		roomList.add(room);
		
		room = new Room(15, "2-6", "You are in a room containing a variety of furnitures and four exits.", "NORTH, SOUTH, WEST ,EAST", false);
		roomList.add(room);
		
		room = new Room(16, "2-7", "This is a room with a single old computer terminal on a desk."
				+ "\nIt seems to be working still.", "SOUTH", false);
		roomList.add(room);
		
		room = new Room(17, "2-8", "This is a room containing three walls with different colors."
				+ "\nLooking clockwise, the colors of the walls are:"
				+ "\n[BLUE], [GREEN], [RED].", "NORTH", false);
		roomList.add(room);
		
		room = new Room(18, "2-9", "Here is a room containing alot of hula hoops and a table with dice.", "WEST, EAST", true);
		roomList.add(room);
		
		room = new Room(19, "3-1", "You are in a square room with steel plating covering all surfaces."
				+ "\nThe only objects here are the doors leading to the two exits.", "WEST, EAST", true);
		roomList.add(room);
		
		room = new Room(20, "3-2", "This is another room covered in steel plating."
				+ "\nOne of the overhead lights flicker at a frequency that doesn't actually diminish the amount"
				+ "\nof light in the room, but causes an annoyance.", "SOUTH, WEST, EAST", false);
		roomList.add(room);
		
		room = new Room(21, "3-3", "You are now in a rectangular room also lined with steel plating."
				+ "\nIt is filled with computer monitors and cabling... a rack of computers sits at the end. "
				+ "\nThe lighting is dimmer than normal and has a slight blueish hue. "
				+ "\nThe electronics put out a lot of heat."
				+ "\nThere is a case made of ballistic glass on the far wall.", "NORTH", false);
		roomList.add(room);
		
		room = new Room(22, "3-4", "This is a slightly rectangular room with a fork at the eastern end. "
				+ "\nThe room's ceiling grows higher and wider as the room approaches the fork, and the room's"
				+ "\nconstruction transitions from steel plating to exposed cave wall, reinforced in places by"
				+ "\nrebar and I-beams. "
				+ "\nDuring this transition the lighting changes from overhead to standing work lights.", "NORTH, SOUTH, WEST", false);
		roomList.add(room);
		
		room = new Room(23, "3-5", "There is a moderately wide, but short, ledge that is semicircular in shape surrounding an"
				+ "\nunderground lake's northern edge. "
				+ "\nThe room has high ceilings that curve upwards toward the center of the lake. "
				+ "\nThe ceiling is covered by medium-large stalactites. "
				+ "\nThe room has exposed cave walls reinforced in places by rebar and I-beams. "
				+ "\nThe room is lit from the ground by upward facing lights that are angled toward the wall.", "SOUTH, WEST, EAST", false);
		roomList.add(room);
		
		room = new Room(24, "3-6", "There is a moderately wide, but short, ledge that is semicircular in shape surrounding an"
				+ "\nunderground lake's southern edge. "
				+ "\nThe room has high ceilings that curve upwards toward the center of the lake. "
				+ "\nThe ceiling is covered by medium-large stalactites. "
				+ "\nThe room has exposed cave walls reinforced in places by rebar and I-beams. "
				+ "\nThe room is lit from the ground by upward facing lights angled toward the wall.", "NORTH, WEST, EAST", false);
		roomList.add(room);
		
		room = new Room(25, "3-7", "You are at a lake which is both deep underground and is deep itself. "
				+ "\nThe water is incredibly clear and clean, and yet very deep in color as well. "
				+ "\nIn what is more or less the center of the lake is an underwater plateau containing live"
				+ "\npiranhas.", "NORTH, SOUTH", false);
		roomList.add(room);
		
		room = new Room(26, "3-8", "Here is a rectangular room with a fork at the western end. "
				+ "\nThe room's ceiling lowers as the room moves away from the fork. "
				+ "\nThe room has exposed cave walls reinforced in places by rebar and I-beams. "
				+ "\nThe room is lit from the ground by upward facing lights angled toward the wall. "
				+ "\nThere is a ventilation duct in the lower part of the ceiling partially obscured by"
				+ "\nmoderate sized stalactites.", "NORTH, SOUTH, EAST", false);
		roomList.add(room);
		
		room = new Room(27, "3-9", "This is a massive rectangular room. "
				+ "\nDue to the room's sheer size, the lighting isn't quite adequate and so the room is a good bit"
				+ "\ndarker than the other rooms on this floor.", "WEST, EAST", false);
		roomList.add(room);
		
		room = new Room(28, "3-10", "This is a small square room with high ceilings and a large steel blast door, which is locked,"
				+ "\n on the eastern wall of the room. "
				+ "\nThis room is well lit with both the floor lighting, as described in rooms previous, and"
				+ "\noverhead lighting.", "WEST, EAST, SOUTH", true);
		roomList.add(room);
		
		room = new Room(29, "4-1", "This room is colored in a dark blue light with statues of lions at"
				+ "\n each of the eight corners of the room. "
				+ "\nThe floor is a slick black concrete and has a sloppily laid trail of bloody green"
				+ "\narrows leading to the door.", "WEST, EAST", true);
		roomList.add(room);
		
		room = new Room(30, "4-2", "This room is a dimmed, straight hallway, with one door on the east side.", "WEST, EAST", false);
		roomList.add(room);
		
		room = new Room(31, "4-3", "You are in a dark room with eyes drawn all over the walls, and in the center of the room,"
				+ "\nleading towards the exit, is a picture of a brain. "
				+ "\nA bright green light shines directly into the room onto the only door. "
				+ "\nAround it is a very sinister 'HaHA' scrawled over the pictures on the wall.", "WEST, EAST", false);
		roomList.add(room);
		
		room = new Room(32, "4-4", "This room is lit in a bright green light with a single door to the east. "
				+ "\nThe recurring pattern of a brain is still scrawled along the wall."
				+ "\nThe door is old and very decrepit looking.", "WEST, EAST", false);
		roomList.add(room);
		
		room = new Room(33, "4-5", "This room is a large box with a majestic door to the south. "
				+ "\nThe room is a majestic as the door with the sides being decorated "
				+ "\nwith intricately designed patterns.", "WEST, SOUTH", false);
		roomList.add(room);
		
		room = new Room(34, "4-6", "This room continues to follow its majestic pattern, but it begins to"
				+ "\n look more sadistic. "
				+ "\nThe paintings on the walls, despite their ornate frames, are pictures of clowns who are either"
				+ "\nscreaming, crying, or looking sinister.", "NORTH, SOUTH, WEST", true);
		roomList.add(room);
		
		room = new Room(35, "4-7", "Here is a room containing all the costumes generally worn during a circus performance. "
				+ "\nThere is a newspaper on the desk. "
				+ "\nThe paper titled The 'Clown Times' contains a headline regarding the rising price of clown college.", "WEST, EAST", false);
		roomList.add(room);
		
		room = new Room(36, "4-8", "This room seems to contain the animals of the circus, however it does not seem"
				+ "\nthat anyone has attempted to cage them. "
				+ "\nLions, elephants, monkeys, and more roam the open room. "
				+ "\nThe lions have made their stake in the corner of the room and have amassed a large body count"
				+ "\nthat they are feeding on.", "EAST", false);
		roomList.add(room);
		
		room = new Room(37, "4-9", "This room is boldly titled, \"The Hall of Mirrors\" at the very top, and much as the"
				+ "\nname suggest, the room is filled with mirrors. "
				+ "\nMirrors that make you long, mirrors that make you short, there is just about every type of"
				+ "\nmirror in this room.", "NORTH, WEST", false);
		roomList.add(room);

		room = new Room(38, "4-10", "This room contains a wide variety of small cars. "
				+ "\nDespite being clown cars many of these cars are very expensive looking. "
				+ "\nA wide variety of clown muscle cars fills the room.", "WEST, EAST", false);
		roomList.add(room);
		
		room = new Room(39, "4-11", "This room is dark and narrow, leading to the only door on the western section"
				+ "\nof the room.", "WEST, EAST", false);
		roomList.add(room);
		
		room = new Room(40, "4-12", "This room has blazing ring of fire in the center. "
				+ "\nThere is one exit on the westward wall, and by it, a golden statue of a tiger. "
				+ "\nThe wall has been turned into a mural of people jumping through flaming objects.", "WEST, EAST", false);
		roomList.add(room);
		
		room = new Room(41, "4-13", "You are in a room which looks like a decrepit movie theater. "
				+ "\nThere are no seats and the floor boards are all but destroyed. "
				+ "\nIn the center of the the room is a curtain lavishly colored in the orange and red colors"
				+ "\noften seen at carnivals.", "WEST, EAST", true);
		roomList.add(room);

	}
	
}
