package Room;

import java.util.ArrayList;

import Character.Monster;
import Character.MonsterController;
import Generator.ItemGenerator;
import Generator.MonsterGenerator;
import Generator.PuzzleGenerator;
import Generator.RoomGenerator;
import Item.*;
import Puzzle.Puzzle;
import Puzzle.PuzzleController;

/**
 * 
 * This class is responsible for setting Item, Monster, and Puzzle Object to Room Object.
 * Takes ArrayList<Monster> from MonsterFactory
 * Takes ArrayList<Item> from ItemGenerator
 * Takes ArrayList<Puzzle> from PuzzleGenerator
 * @author John
 *
 */
public class RoomFactory {
	
	//new ArrayList to put new objects into
	private ArrayList<Room> roomFactoryList;
	
	private ArrayList<Room> roomList;
	
	private ArrayList<Item> itemList;
	private ArrayList<Monster> monsterList;
	private ArrayList<Puzzle> puzzleList;
	
	private ItemController ic;
	private MonsterController mc;
	private PuzzleController pc;
	
	public RoomFactory() {
		roomList = new RoomGenerator().getRoomList();
		itemList = new ItemGenerator().getItemList();
		monsterList = new MonsterGenerator().getMonsterList();
		puzzleList = new PuzzleGenerator().getPuzzleList();
	}

	private void generateRoomMonster() {
		roomList = new RoomGenerator().getRoomList();
		
		for(int i = 0; i < roomList.size(); i++){
			
		}
		
	}
	
	private void generateRoomPuzzle() {
		roomFactoryList = new ArrayList<Room>();
		puzzleList = new PuzzleGenerator().getPuzzleList();
		pc = new PuzzleController();
		
		Room room;
		
		for(int i = 0; i < roomList.size(); i++) {
			
			room = roomList.get(i);
			
			if(room == room(8)){
				room.setRoomPuzzle(pc.puzzle("A Blood Type"));
				roomFactoryList.add(room);
			}
			else if(room == room(9)){
				room.setRoomPuzzle(pc.puzzle("Painting"));
				roomFactoryList.add(room);
			}
			else if(room == room(12)){
				room.setRoomPuzzle(pc.puzzle("The Animal Statues"));
				roomFactoryList.add(room);
			}
			else if(room == room(14)){
				room.setRoomPuzzle(pc.puzzle("The Laser Hallway"));
				roomFactoryList.add(room);
			}
			else if(room == room(16)){
				room.setRoomPuzzle(pc.puzzle("The Colored Buttons"));
				roomFactoryList.add(room);
			}
			else if(room == room(21)){
				room.setRoomPuzzle(pc.puzzle("Security Bypass"));
				roomFactoryList.add(room);
			}
			else if(room == room(27)){
				room.setRoomPuzzle(pc.puzzle("Jump The Chasm"));
				roomFactoryList.add(room);
			}
			else if(room == room(33)){
				room.setRoomPuzzle(pc.puzzle("Riddle 1"));
				roomFactoryList.add(room);
			}
			else if(room == room(40)){
				room.setRoomPuzzle(pc.puzzle("Riddle 2"));
				roomFactoryList.add(room);
			}
			else{
				room.setRoomPuzzle(null);
				roomFactoryList.add(room);
			}
		}
		
	}
	
	private void generateRoomItem() {
		roomList = new ArrayList<Room>();
		ic = new ItemController();
		
		for(int i = 0; i < roomList.size(); i++) {
			
		}
		
	}

	private void generateAllRooms(){
//		generateRoomMonster();
		generateRoomPuzzle();
//		generateRoomItem();
//		connectRooms();
	}

	public void connectRooms(){
		//N E S W
		
		//connect floor 1, 10 rooms
		room(0).setExits(null, room(1), null, null);					//1-0
		room(1).setExits(null, room(2), null, room(0));					//1-1
		room(2).setExits(null, room(3), null, room(1));					//1-2
		room(3).setExits(null, room(4), null, room(2));					//1-3
		room(4).setExits(null, null, room(5), room(3));					//1-4
		room(5).setExits(room(4), room(9), null, room(6));				//1-5
		room(6).setExits(room(3), room(5), null, room(7));				//1-6
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

	public ArrayList<Room> getRoomFactoryList() {
		generateAllRooms();
		return roomFactoryList;
	}
	
	private Room room(int index){
		return roomList.get(index);
	}
	
	

	
}
