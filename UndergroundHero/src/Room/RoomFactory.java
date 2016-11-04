package Room;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import Generator.*;
import Character.Monster;
import Item.Item;
import Puzzle.Puzzle;

/**
 * 
 * This class is responsible for setting items, monsters, and puzzles in each individual room. Each room is then added to an ArrayList<Room>
 * Takes ArrayList<Monster> from MonsterGenerator
 * Takes ArrayList<Item> from ItemGenerator
 * Takes ArrayList<Puzzle> from PuzzleGenerator
 * @author John
 *
 */
public class RoomFactory implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7894271087328509321L;
	//new ArrayList to put new objects into
	private ArrayList<Room> roomFactoryList = new ArrayList<Room>();
	private ArrayList<Room> roomList;
	
	public RoomFactory() {
		roomList = new RoomGenerator().getRoomList();
	}

	/**
	 * @method Sets monster in each room
	 */
	private void generateRoomMonster() {
		ArrayList<Monster> monsterList = new MonsterGenerator().getMonsterList();
		
		Room room;
		int roomID;
		
		for(int i = 0; i < roomList.size(); i++){
			
			room = roomList.get(i);
			roomID = room.getId();
			
			//puzzler add to room 1-9
			if(roomID == 8){
				room.setRoomMonster(monsterList.get(0));
				roomFactoryList.add(room);
			}
			//pogo add to room 2-9
			else if(roomID == 18){
				room.setRoomMonster(monsterList.get(1));
				roomFactoryList.add(room);
			}
			//giant bull shark add to room 3-7
			else if(roomID == 25){
				room.setRoomMonster(monsterList.get(2));
				roomFactoryList.add(room);
			}
			//sentry robot add to room 3-10
			else if(roomID == 28){
				room.setRoomMonster(monsterList.get(3));
				roomFactoryList.add(room);
			}
			//quinn har add to room 4-12
			else if(roomID == 40){
				room.setRoomMonster(monsterList.get(4));
				roomFactoryList.add(room);
			}
			//joe ker add to room 4-13
			else if(roomID == 41){
				room.setRoomMonster(monsterList.get(5));
				roomFactoryList.add(room);
			}
			//floor 2 add all hula hooper
			else if(roomID >= 10 && roomID <= 17){
				room.setRoomMonster(monsterList.get(6));		
				roomFactoryList.add(room);
			}
			//floor 3 and 4 add wild hyena
			else if(roomID >= 21 && roomID <= 41 && roomID != 38 && roomID != 40){
				room.setRoomMonster(monsterList.get(7));
				roomFactoryList.add(room);
			}
			else{
				room.setRoomMonster(null);
				roomFactoryList.add(room);
			}
		}
			
			
	}
	
	/**
	 * @method Sets puzzles in each room
	 */
	private void generateRoomPuzzle() {
		ArrayList<Puzzle> puzzleList = new PuzzleGenerator().getPuzzleList();
				
		Room room;
		int roomID;
		
		for(int i = 0; i < roomList.size(); i++) {
			
			room = roomList.get(i);
			roomID = room.getId();
			
			//room 1-4 add blood puzzle
			if(roomID == 4){
				room.setRoomPuzzle(puzzleList.get(0));
				roomFactoryList.add(room);
			}
			//room 1-9 add painting puzzle
			else if(roomID == 9){
				room.setRoomPuzzle(puzzleList.get(1));
				roomFactoryList.add(room);
			}
			//room 2-3 add statues puzzle
			else if(roomID == 12){
				room.setRoomPuzzle(puzzleList.get(2));
				roomFactoryList.add(room);
			}
			//room 2-2 add laser puzzle
			else if(roomID == 11){
				room.setRoomPuzzle(puzzleList.get(3));
				roomFactoryList.add(room);
			}
			//room 2-7 add colored button puzzle
			else if(roomID == 16){
				room.setRoomPuzzle(puzzleList.get(4));
				roomFactoryList.add(room);
			}
			//room 3-3 add security bypass puzzle
			else if(roomID == 21){
				room.setRoomPuzzle(puzzleList.get(6));
				roomFactoryList.add(room);
			}
			//room 3-9 add jump the chasm puzzle
			else if(roomID == 27){
				room.setRoomPuzzle(puzzleList.get(5));
				roomFactoryList.add(room);
			}
			//room 4-5 add riddle 1 puzzle
			else if(roomID == 33){
				room.setRoomPuzzle(puzzleList.get(7));
				roomFactoryList.add(room);
			}
			//room 4-10 add riddle 2 puzzle
			else if(roomID == 40){
				room.setRoomPuzzle(puzzleList.get(8));
				roomFactoryList.add(room);
			}
			else{
				room.setRoomPuzzle(null);
				roomFactoryList.add(room);
			}
		}
	}
	
	/**
	 * @method Sets item in each room
	 */
	private void generateRoomItem() {
		ArrayList<Item> itemList = new ItemGenerator().getItemList();
		
		Room room;
		int roomID;
		
		for(int i = 0; i < roomList.size(); i++) {
			
			room = roomList.get(i);
			roomID = room.getId();
			
			if(roomID == 16){
				room.setRoomItem(itemList.get(1));		//chain mail
				roomFactoryList.add(room);
			}
			else if(roomID == 11 || roomID == 17){
				room.setRoomItem(itemList.get(4));		//adrenaline shot
				roomFactoryList.add(room);
			}
			else{
				room.setRoomItem(null);
				roomFactoryList.add(room);
			}
		}
		
	}

	/**
	 * @method Connect exits to each room
	 */
	private void connectRooms(){
		//N E S W	
		//connect floor 1, 10 rooms
		room(0).setExits(null, room(1), null, null);					//1-0
		room(1).setExits(null, room(2), null, room(0));					//1-1
		room(2).setExits(null, room(3), null, room(1));					//1-2
		room(3).setExits(null, room(4), null, room(2));					//1-3
		room(4).setExits(null, null, room(5), room(3));					//1-4
		room(5).setExits(room(4), room(9), null, room(6));				//1-5
		room(6).setExits(null, room(5), null, room(7));					//1-6
		room(7).setExits(null, room(6), null, room(8));					//1-7
		room(8).setExits(null, room(7), null, room(10));				//1-8
		room(9).setExits(null, null, null, room(5));					//1-9
		
		//connect floor 2, 9 rooms
		room(10).setExits(null, room(11), null, room(8));				//2-1
		room(11).setExits(room(12), room(14), room(13), room(10));		//2-2
		room(12).setExits(null, null, room(11), null);					//2-3
		room(13).setExits(room(11), null, null, null);					//2-4
		room(14).setExits(null, room(15), null, room(11));				//2-5
		room(15).setExits(room(16), room(18), room(17), room(14));		//2-6
		room(16).setExits(null, null, room(15), null);					//2-7
		room(17).setExits(room(15), null, null, null);					//2-8
		room(18).setExits(null, room(19), null, room(15));				//2-9
				
		//connect floor 3, 10 rooms
		room(19).setExits(null, room(20), room(27), room(18));			//3-1
		room(20).setExits(null, room(22), room(21), room(19));			//3-2
		room(21).setExits(room(20), null, room(27), null);				//3-3
		room(22).setExits(room(23), null, room(24), null);				//3-4
		room(23).setExits(null, room(26), room(25), room(22));			//3-5
		room(24).setExits(room(25), room(26), null, room(22));			//3-6
		room(25).setExits(room(23), null, room(24), null);				//3-7
		room(26).setExits(room(23), room(27), room(24), null);			//3-8
		room(27).setExits(null, room(28), room(21), room(26));			//3-9
		room(28).setExits(null, room(29), room(19), room(27));			//3-10

		//connect floor 4, 13 rooms
		room(29).setExits(null, room(30), null, room(28));				//4-1
		room(30).setExits(null, room(31), null, room(29));				//4-2
		room(31).setExits(null, room(32), null, room(30));				//4-3
		room(32).setExits(null, room(33), null, room(31));				//4-4
		room(33).setExits(null, null, room(34), room(32));				//4-5
		room(34).setExits(room(33), null, room(37), room(35));			//4-6
		room(35).setExits(null, room(34), null, room(36));				//4-7
		room(36).setExits(null, room(35), null, null);					//4-8
		room(37).setExits(room(34), null, null, room(38));				//4-9
		room(38).setExits(null, room(37), null, room(39));				//4-10                              
		room(39).setExits(null, room(38), null, room(40));				//4-11
		room(40).setExits(null, room(39), null, room(41));				//4-12
		room(41).setExits(null, room(40), null, null);					//4-13

	}
	
	/**
	 * @method Generates all room types
	 */
	private void generateAllRooms(){
		generateRoomPuzzle();
		generateRoomMonster();
		generateRoomItem();
		connectRooms();
	}

	/**
	 * @method Returns an ordered list of connect, generated rooms.
	 * @return roomFactoryList<Room>
	 */
	public ArrayList<Room> getRoomFactoryList() {
		generateAllRooms();
		Set<Room> factoryList = new HashSet<Room>(roomFactoryList);
		ArrayList<Room> newFactoryList = new ArrayList<Room>(factoryList);
		Collections.sort(newFactoryList);
		return newFactoryList;
	}
	
	/**
	 * @method Searches through room list
	 * @param index
	 * @return Room
	 */
	private Room room(int index){
		return roomFactoryList.get(index);
	}
	
}
