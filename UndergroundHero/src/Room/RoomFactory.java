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
	private ArrayList<Monster> monsterFactoryList;
	private ArrayList<Puzzle> puzzleList;
	
	private ItemController ic;
	private MonsterController mc;
	private PuzzleController pc;
	
	public RoomFactory() {
		roomList = new RoomGenerator().getRoomList();
		itemList = new ItemGenerator().getItemList();
		monsterFactoryList = new MonsterGenerator().getMonsterList();
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
	}

	public ArrayList<Room> getRoomFactoryList() {
		generateRoomPuzzle();
		return roomFactoryList;
	}
	
	private Room room(int index){
		return roomList.get(index);
	}

	
}
