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
	private Room currentRoom;
	private Label playerLabel, roomNumberLabel, roomLabel, helpLabel;
	private Scene scene;
	private BorderPane borderpane;
	private TextField textParse;
	private Game game;

	public void start(Stage primaryStage) throws Exception
	{

		//load assets and objects
		Game game = new Game();
		Room room = new Room();
		Player player = new Player(10, 1, 1, 2);
		resourceManager = new ResourceManager();
		loadAsset();
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////////
		primaryStage.setTitle("Underground Hero");
		primaryStage.setResizable(false);

		borderpane = new BorderPane();
		borderpane.setMaxWidth(750);

		HBox dialogueHBox = new HBox(10);		//box for main dialogue and description of rooms, items, everything
		HBox textParseHBox = new HBox(10);		//box for text parsing
		HBox roomNumberHBox = new HBox(10);		//box for displaying room floor-number
		VBox playerStatusVBox = new VBox(10);	//box for character status
		VBox playerHelpVBox = new VBox(10);	    //box for character help/inventory

		//setting border styles
		dialogueHBox.setStyle("-fx-border-style: solid inside;" + "-fx-border-color: black;" + "-fx-border-width: 1;");
		playerStatusVBox
				.setStyle("-fx-border-style: solid inside;" + "-fx-border-color: black;" + "-fx-border-width: 1;");
		textParseHBox.setStyle("-fx-border-style: solid inside;" + "-fx-border-color: black;" + "-fx-border-width: 1;");
		roomNumberHBox
				.setStyle("-fx-border-style: solid inside;" + "-fx-border-color: black;" + "-fx-border-width: 1;");
		playerHelpVBox
				.setStyle("-fx-border-style: solid inside;" + "-fx-border-color: black;" + "-fx-border-width: 1;");

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

		//create and set scene
		scene = new Scene(borderpane);
	    primaryStage.setScene(scene);
	    primaryStage.show();
	    
	    currentRoom = room(1);
	    
	    textParseHandling();
	}

	public static void main(String[] args)
	{
		launch(args);
	}

	public void changeScene(Room room)
	{
		roomNumberLabel.setText(room.getName());
		roomLabel.setText(room.getDescription());
	}

	/*
	 * a test example of what the text parser will look like
	 * user presses enter key then it checks if the command is valid
	 * 
	 * TODO: check current room's existing exits, if its locked, and if its possible to traverse(check elicitation for each room conditions)
	 */
	private void textParseHandling(){
		textParse.setOnKeyPressed(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent key) {
				if(key.getCode().equals(KeyCode.ENTER)){
					String userInput = textParse.getText();
					if(validDirectionInput(userInput)){
						Room nextRoom = currentRoom.nextRoom(userInput);
						
						if(nextRoom == null){
							System.out.println("No exits");
						}else{
							currentRoom = nextRoom;
							changeScene(currentRoom);
							System.out.println("Room number: " + currentRoom.getName());
						}
						textParse.setText("");
						
					}else if(textParse.getText().equalsIgnoreCase("LOOK")){
						System.out.println(currentRoom.getName());
						textParse.setText("");
						
					}else{
						System.out.println("Not a valid command");
						textParse.setText("");
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

	
	private boolean validDirectionInput(String input){
		if(input.equalsIgnoreCase("EAST") || input.equalsIgnoreCase("WEST") || input.equalsIgnoreCase("NORTH") || input.equalsIgnoreCase("SOUTH")){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * This method will load all lists and connect all rooms
	 */
	public void loadAsset(){
		roomList 	= resourceManager.getRoomList();
		monsterList = resourceManager.getMonsterList();
		puzzleList 	= resourceManager.getPuzzleList();
		itemList	= resourceManager.getItemList();
		
		connectRooms();
		
	}
	
	private Room room(int index){
		return roomList.get(index);
	}
	
	/**
	 * This method sets the exits for all rooms
	 */
	private void connectRooms(){
		//connect floor 1, 10 rooms
		room(0).setExits(null, room(1), null, null);
		room(1).setExits(null, room(2), null, room(0));
		room(2).setExits(null, room(3), null, room(1));
		room(3).setExits(null, room(4), room(6), room(2));
		room(4).setExits(null, null, room(5), room(3));
		room(5).setExits(room(4), room(9), null, room(6));
		room(6).setExits(room(3), room(5), null, room(7));
		room(7).setExits(null, room(6), null, room(8));
		room(8).setExits(null, room(7), null, null);
		room(9).setExits(null, room(10), null, room(5));
		
		//connect floor 2, 9 rooms
		room(10).setExits(null, room(11), null, room(9));
		room(11).setExits(room(12), room(14), room(13), room(10));
		room(12).setExits(null, null, room(11), null);
		room(13).setExits(room(11), null, null, null);
		room(14).setExits(null, room(15), null, room(11));
		room(15).setExits(room(16), room(18), room(17), room(14));
		room(16).setExits(null, null, room(15), null);
		room(17).setExits(room(15), null, null, null);
		room(18).setExits(null, room(19), null, room(15));
		
		//connect floor 3, 10 rooms
		room(19).setExits(null, room(20), room(27), room(18));
		room(20).setExits(null, room(22), room(21), room(19));
		room(21).setExits(room(20), null, room(27), null);
		room(22).setExits(room(23), null, room(24), null);
		room(23).setExits(null, room(26), room(25), room(22));
		room(24).setExits(room(25), room(26), null, room(22));
		room(25).setExits(room(23), null, room(24), null);
		room(26).setExits(room(23), room(27), room(24), null);
		room(27).setExits(null, room(28), room(21), null);
		room(28).setExits(null, room(29), room(19), room(27));

		//connect floor 4, 13 rooms
		room(29).setExits(null, room(30), null, room(28));
		room(30).setExits(null, room(31), null, room(29));
		room(31).setExits(null, room(32), null, room(30));
		room(32).setExits(null, room(33), null, room(31));
		room(33).setExits(null, null, room(34), room(32));
		room(34).setExits(room(33), null, room(37), room(35));
		room(35).setExits(null, room(34), null, room(36));
		room(36).setExits(null, room(35), null, null);
		room(37).setExits(room(34), null, null, room(38));
		room(38).setExits(null, room(37), null, room(39));
		room(39).setExits(null, room(38), null, room(40));
		room(40).setExits(null, room(39), null, room(41));
		room(41).setExits(null, room(40), null, null);

	}
}
