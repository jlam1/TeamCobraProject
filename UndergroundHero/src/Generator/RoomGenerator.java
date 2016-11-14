package Generator;

//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
import java.util.ArrayList;
//import java.util.Scanner;

import Room.Room;
//import Room.RoomBasic;

/**
 * This class is responsible for generating Room objects
 * @author John
 *
 */
public class RoomGenerator {
	
	private ArrayList<Room> roomList;
	//private Scanner in;
	
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
		
		Room room = new Room(0, "1-0", "You left the underground lair and ultimately lead to the destruction of all humanity! (Secret Bad Ending)", "EAST", false);
		roomList.add(room);
		
		room = new Room(1, "1-1", "An office room, the light is dimmed to a level that it is still easy to see. There is a front desk to the left with a tall artificial plan and a brown leather couch across to the right. There is no trace of people in the office space. A painting of a red boat on the sea hangs above the couch, and a poster next to the front desk which says blood type AB.", "EAST", false);
		roomList.add(room);
		
		room = new Room(2, "1-2", "An office supply storage room with two big printers to the left as soon as one enters the room. Along the north wall, there are some shelves with paper, and some cabinets which are locked. Some computer equipment is there like routers and modems along with some important reminders and a contact list.", "WEST, EAST", false);
		roomList.add(room);
		 
		room = new Room(3, "1-3", "A meeting room which has a white board on the corner and several chairs and two couches. There is a window on the north wall which has the blinds down. The board has some target numbers written on it.", "WEST, EAST", false);
		roomList.add(room);
		
		room = new Room(4, "1-4", "A room with several shelves with neatly stacked clothes on it, alongside the shelves, there is a counter with a sink and a coffee maker. A cabinet above the coffee maker.", "SOUTH, WEST", false);
		roomList.add(room);
		
		room = new Room(5, "1-5", "A room containing a desk which looks fancy and it is located on the south east corner of the room. It is made from dark brown wood and it has a library next to it extending all the way to the north wall and covering the entire north wall. The shelves go from the ceiling to the ground and are filled with books of all kinds, also the shelves have decorations on them. There is a portrait of [MONA LISA] on the east wall.", "WEST, NORTH", false);
		roomList.add(room);
		
		room = new Room(6, "1-6", "A room with a closet and a clean shower room, the floor is white, the light is white, everything is white, except there is bloody hand prints everywhere on the northwestern corner of the room, there's a thick trail of blood going into the room westward. Keep in mind the doors are thick and not much sound gets through to the other side.", "WEST, EAST", false);
		roomList.add(room);
		
		room = new Room(7, "1-7", "A room consisting of a steel bed, it has a body on top of it. It is inside a body bag, it's very cold in this room, doesn't smell like anything. The body could be just a dummy. Same as the previous to this one, everything is white, the lights are dimmed, and somewhat flickery. There are some sharp tools on a tray.", "WEST, EAST", false);
		roomList.add(room);
		
		room = new Room(8, "1-8", "The air filled the room with smoke and a large bed resides in the center of the room. The room feels ominous and there is a strong odor in the room.", "WEST, EAST", false);
		roomList.add(room);
		
		room = new Room(9, "1-9", "There is a lock to the door once inside it and it works as a safe place in case it is needed. The room has a purple light bulb which makes the room appear very purple.", "WEST", false);
		roomList.add(room);
		
		room = new Room(10, "2-1", "A small and narrow corridor that links the two exits together.", "WEST, EAST", true);
		roomList.add(room);
		
		room = new Room(11, "2-2", "A room with pictures of fine art and contains four exits.", "NORTH, SOUTH, WEST, EAST", false);
		roomList.add(room);
		
		room = new Room(12, "2-3", "Four statues in order on the north wall from left to right: Duck, Giraffe, Elephant, and Cow. A pedestal in the center of the room holding a pair of [SPEED BOOTS] that is protected by an energy force field.", "SOUTH",false);
		roomList.add(room);
		
		room = new Room(13, "2-4", "Four statues on the South wall from left to right: Cow, Giraffe, Duck, and Elephant. Behind each statue is a number behind each: Duck has 1, Giraffe has 2, Elephant has 3, and Cow has 4. The ordering of each statue seems important.", "NORTH", false);
		roomList.add(room);
		
		room = new Room(14, "2-5", "A long hallway that shoots lasers at anyone who tries to pass it.", "WEST, EAST", true);
		roomList.add(room);
		
		room = new Room(15, "2-6", "A room containing a variety of furnitures, there are four exits from this room.", "NORTH, SOUTH, WEST ,EAST", false);
		roomList.add(room);
		
		room = new Room(16, "2-7", "A room with a single old terminal on a desk. It seems to be working still.", "SOUTH", false);
		roomList.add(room);
		
		room = new Room(17, "2-8", "A room containing a walls with a different color. There is a total of three walls, going clockwise the colors are: [BLUE], [GREEN], and [RED].", "NORTH", false);
		roomList.add(room);
		
		room = new Room(18, "2-9", "A room containing alot of hula hoops and a table with dices.", "WEST, EAST", true);
		roomList.add(room);
		
		room = new Room(19, "3-1", "A square room with steel plating covering all surfaces. The only objects here are the doors leading to two exits.", "WEST, EAST", true);
		roomList.add(room);
		
		room = new Room(20, "3-2", "A room covered in steel plating. One of the overhead lights flicker at a frequency that doesn't actually diminish the amount of light in the room, it just causes an annoyance.", "SOUTH, WEST, EAST", false);
		roomList.add(room);
		
		room = new Room(21, "3-3", "A rectangular room filled with monitors and cabling; a rack of computers sits at the end. The lighting is dimmer than normal and as a slight blueish hue. The electronics put out a lot of heat, so while this room is lined with steel plating like the rooms before it. There is a case made of ballistic glass on the far wall.", "NORTH", false);
		roomList.add(room);
		
		room = new Room(22, "3-4", "A slightly rectangular room with a fork at the eastern end. The room's ceiling grows higher and wider as the room approaches the fork, and the rooms construction transitions from steel plating to exposed cave wall reinforced in places by rebar and I-beams. During this transition the lighting changes from overhead to standing work lights.", "NORTH, SOUTH, WEST", false);
		roomList.add(room);
		
		room = new Room(23, "3-5", "A moderately wide, short ledge that is semicircular in shape surrounding an underground lake's northern edge. The room has high ceilings that curve upwards toward the center of the lake. The ceiling is covered by medium-large stalactites. The room has exposed cave wall reinforced in places by rebar and I-beams. The room is lit from the ground by upward firing lights angled toward the wall.", "SOUTH, WEST, EAST", false);
		roomList.add(room);
		
		room = new Room(24, "3-6", "A moderately wide, short ledge that is semicircular in shape surrounding an underground lake's southern edge. The room has high ceilings that curve upwards toward the center of the lake. The ceiling is covered by medium-large stalactites. The room has exposed cave wall reinforced in places by rebar and I-beams. The room is lit from the ground by upward firing lights angled toward the wall.", "NORTH, WEST, EAST", false);
		roomList.add(room);
		
		room = new Room(25, "3-7", "The lake is both located deep underground and is deep itself. The water is incredibly clear and clean, and yet very deep in color as well. In what is more or less the center of the lake is an underwater plateau containing live piranhas.", "NORTH, SOUTH", false);
		roomList.add(room);
		
		room = new Room(26, "3-8", "A rectangular room with a fork at the western end. The room's ceiling lowers as the room moves away from the fork. The room has exposed cave wall reinforced in places by rebar and I-beams. The room is lit from the ground by upward firing lights angled toward the wall. There is a ventilation duct in the lower part of the ceiling partially obscured by small-medium sized stalactites.", "NORTH, SOUTH, EAST", false);
		roomList.add(room);
		
		room = new Room(27, "3-9", "A massive rectangular room with a wide chasm offset to the eastern side of it. Due to the room's sheer size, the lighting isn't quite adequate and so the room is a good bit darker than the other rooms on this floor. There is a stronger outcropping of rock above the chasm. Though barely visible, there are steel spikes at the bottom of the chasm.", "WEST, EAST", false);
		roomList.add(room);
		
		room = new Room(28, "3-10", "A small square room with high ceilings and a large, locked steel blast door on the eastern wall of the room. This room is well lit with both the floor lighting as described in rooms previous, and overhead lighting.", "WEST, EAST, SOUTH", true);
		roomList.add(room);
		
		room = new Room(29, "4-1", "The room is colored in a dark blue light with statues of lions adorned at the corner of each of the eight corners of the room. The floor a slick black concrete with a but dead in the center is a sloppily trail of green arrows leading to the door.", "WEST, EAST", true);
		roomList.add(room);
		
		room = new Room(30, "4-2", "The room is dimmed and is a straight hallway. With one door on the east side.", "WEST, EAST", false);
		roomList.add(room);
		
		room = new Room(31, "4-3", "A dark room with eyes drawn all over the walls, in the center of the room leading towards the exit is a picture of a brain. A bright green light shines directly into the room directly at the only door. Around it is a very sinisterly written 'HaHA' scrawled over the pictures on the wall.", "WEST, EAST", false);
		roomList.add(room);
		
		room = new Room(32, "4-4", "This room is lit in a bright green light with the single door to the east.  The recurring pattern of a brain is still scrawled along the wall. The door is old and very decrepit looking.", "WEST, EAST", false);
		roomList.add(room);
		
		room = new Room(33, "4-5", "The room is a large box with a majestic door to the south. The room is a majestic as the door with the sides being decorated with intricately designed patterns.", "WEST, SOUTH", false);
		roomList.add(room);
		
		room = new Room(34, "4-6", "The room continues to follow its majestically made pattern but it begins to look more sadistics. The paintings on the wall all despite it ornate frames hold pictures of clowns who are either screaming, crying, or looking sinisterly angry.", "NORTH, SOUTH, WEST", true);
		roomList.add(room);
		
		room = new Room(35, "4-7", "A room containing all the costumes generally worn during the circus performance. There is a newspaper on the desk. The paper titled The 'Clown Times' headline is on the rising price of clown college.", "WEST, EAST", false);
		roomList.add(room);
		
		room = new Room(36, "4-8", "This room seems to contain the animals of the circus, however it does not seem that anyone has attempted to cage them. Lions, elephants, monkeys, and more roam the open room. The lions have made their stake in the corner of the room and have amassed a large body count that has become their dinner.", "EAST", false);
		roomList.add(room);
		
		room = new Room(37, "4-9", "This room is boldly titled the Hall of Mirrors at the very top, and much as the name suggest, the room is filled with mirrors. Mirrors that make you long, mirrors that make you short, there was a just about every type of mirror  in this room.", "NORTH, WEST", false);
		roomList.add(room);

		room = new Room(38, "4-10", "This room contains a wide variety of small cars. Despite being clown cars many of these cars were very expensive looking. A wide variety of clown car muscle cars filled the room.", "WEST, EAST", false);
		roomList.add(room);
		
		room = new Room(39, "4-11", "The room is dark and narrow leading to the only door to the western section of the wall.", "WEST, EAST", false);
		roomList.add(room);
		
		room = new Room(40, "4-12", "This leads to torch lit room where in the center is blazing ring of fire. There is one exit on the westward wall and by it, a golden statue of a tiger. The wall has been turned into a mural of people jumping through flaming objects.", "WEST, EAST", false);
		roomList.add(room);
		
		room = new Room(41, "4-13", "A last room is a decrepit movie theater. There are no seats and the floor boards are all but destroyed. In the center of the the room is a curtain lavishly colored in the orange and red colors often seen at carnivals.", "WEST, EAST", true);
		roomList.add(room);

	}
	
}
