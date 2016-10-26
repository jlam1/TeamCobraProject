import java.util.List;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

//CLASS IS JUST FOR TESTING, WILL NOT INCLUDE IN FINAL PRODUCT

//		stage.initModality(Modality.APPLICATION_MODAL); //locks stage

public class GUI extends Application
{

	private ResourceManager resourceManager;
	private List<Room> roomList;
	private List<Monster> monsterList;
	private List<Puzzle> puzzleList;
	private List<Item> itemList;
	private Player player;
	private Room currentRoom;
	private Label playerLabel, roomNumberLabel, roomLabel, helpLabel;
	private Scene scene;
	private BorderPane borderpane;
	private TextField textParse;
	private Game game;

	public static void main(String[] args)
	{
		launch(args);
	}
	
	/**
	 * @method setting the stage
	 */
	public void start(Stage primaryStage) throws Exception
	{

		//load assets and objects
		game = new Game();
		player = new Player(10, 1, 3, 2);
		resourceManager = new ResourceManager();
		loadAsset();

		primaryStage.setTitle("Underground Hero");
		primaryStage.setResizable(false);

		//create and set scene
		scene = new Scene(roomPane());
	    primaryStage.setScene(scene);
	    primaryStage.show();
	    
	    //set current room to room 1 and start main game logic(textParse)
	    currentRoom = room(1);
	    textParseHandling();
	}

	/**
	 * @method responsible for changing labels in scene
	 * @param currentRoom
	 */
	public void changeScene(Room currentRoom)
	{
		roomNumberLabel.setText(currentRoom.getName());
		roomLabel.setText(currentRoom.getDescription() + "\n\n" + currentRoom.getExits());
	}

	/*
	 * a test example of what the text parser will look like
	 * user presses enter key then it checks if the command is valid
	 * 
	 * TODO: check current room's existing exits, if its locked, and if its possible to traverse(check elicitation for each room conditions)
	 */
	public void textParseHandling(){
		textParse.setOnKeyPressed(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent key) {
				if(key.getCode().equals(KeyCode.ENTER)){
					
					//ROOM, TODO: add logic to monster encounters, puzzle encounters, and items
//					double spawnChance = (Math.random() * 100);
					String command = textParse.getText();
					if(validDirectionInput(command)){
						roomLogic(command);
						//TODO: This is where all room, puzzle, monster logic will be in
					}
					
					if(textParse.getText().equalsIgnoreCase("look")){
						roomLabel.setText(currentRoom.getDescription() + "\n\n" + currentRoom.getExits());
					}

					if(textParse.getText().equalsIgnoreCase("help")){
						helpLabel.setText(game.help());
					}
					
					//SAVE AND LOAD
					if (textParse.getText().equalsIgnoreCase("save"))
					{
					
						//use the class saveLoadData to save values in to binary file
						saveLoadData data = new saveLoadData();
						data.setRoomNumber(roomNumberLabel.getText());
						data.setRoomDescription(roomLabel.getText());
						//TODO: need to save the player stats, save already solve puzzle, save items in bag, save room boolean, and save already defeated monsters
						try
						{
							ResourceManager.saveGame(data, "UndergroundHero.dat");
						}
						catch (Exception e)
						{
							System.out.println("error saving");
							e.printStackTrace();
						}
						textParse.clear();
					}

					if (textParse.getText().equalsIgnoreCase("load"))
					{
						//use the class saveLoadData to load values in the binary file

						try
						{
							saveLoadData data = (saveLoadData) ResourceManager.loadGame("UndergroundHero.dat");
							roomNumberLabel.setText(data.getRoomNumber());
							roomLabel.setText(data.getRoomDescription());
							//TODO: need to load the player stats, load already solve puzzle, load items in bag, load room boolean and load already defeated monsters

						}
						catch (Exception e)
						{
							System.out.println("error loading");
							e.printStackTrace();
						}

					}
					
					
					textParse.clear();
				}
			}
		});
	}

	/**
	 * @method Responsible for traversing rooms
	 */
	private void roomLogic(String direction){
		Room nextRoom = currentRoom.nextRoom(direction);
		
		if(nextRoom == null){
			roomLabel.setText("Theres no exit that way, try another direction.");
		}else if(nextRoom.getLocked() == true){
			sleep();
			roomLabel.setText(currentRoom.getDescription() + "\n\n\nDoor is locked.");
		}else{
			sleep();
			currentRoom = nextRoom;
			changeScene(currentRoom);
			System.out.println("Room number: " + currentRoom.getName());
		}
	}
	
	public void battleScene(){
		//TODO: should contain battle logic when encountering a monster
	}
	
	/**
	 * @method Method checks for valid input
	 * @param input
	 * @return
	 */
	private boolean validDirectionInput(String input){
		if(input.equalsIgnoreCase("EAST") || input.equalsIgnoreCase("WEST") || input.equalsIgnoreCase("NORTH") || input.equalsIgnoreCase("SOUTH")){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * @method Load all lists, connect all rooms, assign monsters to rooms, assign puzzles to rooms
	 * TODO: assign items to rooms
	 * TODO: assign items to monsters
	 */
	private void loadAsset(){
		roomList 	= resourceManager.getRoomList();
		monsterList = resourceManager.getMonsterList();
		puzzleList 	= resourceManager.getPuzzleList();
		itemList	= resourceManager.getItemList();
		
		connectRooms();
		setRoomMonsters();
		setRoomPuzzles();
		
	}
	
	/**
	 * @method Add monsters to rooms with the exception of rooms that contains a puzzle(maybe to be change)
	 * 
	 */
	private void setRoomMonsters(){
		for(int i = 2; i < roomList.size(); i++){
			
			switch(i){
				case 1:
					break;
					
				case 8: room(i).setRoomMonster(monster(0));
					break;
					
				case 9:
					break;
					
				case 12:
					break;
				
				case 14:
					break;
					
				case 16:
					break;
					
				case 18: room(i).setRoomMonster(monster(2));
					break;
					
				case 21:
					break;
				
				case 25: room(i).setRoomMonster(monster(3));
					break;
					
				case 27:
					break;
					
				case 28: room(i).setRoomMonster(monster(4));
					break;
				
				case 33:
					break;
					
				case 40: room(i).setRoomMonster(monster(5));		//SPECIAL CASE: this room contains both a puzzle and a boss monster
					break;
				
				case 41: room(i).setRoomMonster(monster(6));
					break;
				
				default: 
					room(i).setRoomMonster(monster(1));
//					room(i).setRoomMonster(monster(7));
					break;
			
			}
		}
	}
	
	/**
	 * @method Add puzzles to rooms
	 */
	private void setRoomPuzzles(){
		room(1).setRoomPuzzle(puzzle(0));
		room(9).setRoomPuzzle(puzzle(1));
		room(12).setRoomPuzzle(puzzle(2));
		room(14).setRoomPuzzle(puzzle(3));
		room(16).setRoomPuzzle(puzzle(4));
		room(27).setRoomPuzzle(puzzle(5));
		room(21).setRoomPuzzle(puzzle(6));
		room(33).setRoomPuzzle(puzzle(7));
		room(40).setRoomPuzzle(puzzle(8));
	}
	
	/**
	 * @method Sets existing exits for all rooms in order of NORTH, EAST, SOUTH, WEST
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
	
	/**
	 * @method creates border pane and fill with boxes and children nodes
	 * @return
	 */
	private BorderPane roomPane(){
		borderpane = new BorderPane();
		borderpane.setMaxWidth(750);

		HBox dialogueHBox = new HBox(10);		//box for main dialogue and description of rooms, items, everything
		HBox textParseHBox = new HBox(10);		//box for text parsing
		HBox roomNumberHBox = new HBox(10);		//box for displaying room floor-number
		VBox playerStatusVBox = new VBox(10);	//box for character status
		VBox playerHelpVBox = new VBox(10);	    //box for character help/inventory

		//setting border styles
		dialogueHBox.setStyle("-fx-border-style: solid inside;" + "-fx-border-color: black;" + "-fx-border-width: 1;");
		playerStatusVBox.setStyle("-fx-border-style: solid inside;" + "-fx-border-color: black;" + "-fx-border-width: 1;");
		textParseHBox.setStyle("-fx-border-style: solid inside;" + "-fx-border-color: black;" + "-fx-border-width: 1;");
		roomNumberHBox.setStyle("-fx-border-style: solid inside;" + "-fx-border-color: black;" + "-fx-border-width: 1;");
		playerHelpVBox.setStyle("-fx-border-style: solid inside;" + "-fx-border-color: black;" + "-fx-border-width: 1;");

		//make nodes
		textParse = new TextField();
		textParse.setPromptText("Enter a command");
		textParse.setPrefSize(borderpane.getMaxWidth() + 10, 35);
		textParse.setFocusTraversable(false);
		playerLabel = new Label(player.toString());
		roomNumberLabel = new Label(roomList.get(1).getName());
		roomLabel = new Label(roomList.get(1).getDescription());
		helpLabel = new Label(game.help());
		
		//set padding and dimensions for nodes
		roomLabel.setMaxWidth(450);
		roomLabel.setWrapText(true);
		helpLabel.setPadding(new Insets(15));
		helpLabel.setMaxWidth(150);
		helpLabel.setWrapText(true);
		playerLabel.setPadding(new Insets(15));
		roomLabel.setPadding(new Insets(15));
		roomNumberLabel.setPadding(new Insets(10));
		textParse.setPadding(new Insets(5));

		//add label nodes to boxes
		dialogueHBox.getChildren().add(roomLabel);
		textParseHBox.getChildren().add(textParse);
		roomNumberHBox.getChildren().add(roomNumberLabel);
		playerStatusVBox.getChildren().add(playerLabel);
		playerHelpVBox.getChildren().add(helpLabel);

		//set the layout for all boxes
		borderpane.setCenter(dialogueHBox);
		borderpane.setBottom(textParseHBox);
		borderpane.setTop(roomNumberHBox);
		borderpane.setRight(playerStatusVBox);
		borderpane.setLeft(playerHelpVBox);
		
		return borderpane;
	}
	
	/**
	 * @method Easier method to get room index
	 * @param index
	 * @return
	 */
	private Room room(int index){
		return roomList.get(index);
	}
	
	/**
	 * @method Easier method to get monster index
	 * @param index
	 * @return
	 */
	private Monster monster(int index){
		return monsterList.get(index);
	}
	
	/**
	 * @method Easier method to get puzzle index
	 * @param index
	 * @return
	 */
	private Puzzle puzzle(int index){
		return puzzleList.get(index);
	}
	
	/**
	 * @method A simple thread sleep method
	 */
	private void sleep(){
		try{
			Thread.sleep(140);
		}catch(InterruptedException e){
			
		}
	}
}
