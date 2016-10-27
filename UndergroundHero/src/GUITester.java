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
import javafx.stage.Stage;

/*ImageView Related
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;*/

//CLASS IS JUST FOR TESTING, WILL NOT INCLUDE IN FINAL PRODUCT

public class GUITester extends Application{
	
	private ResourceManager resourceManager;
	private List<Room> roomList; 
	private List<Monster> monsterList;
	private List<Puzzle> puzzleList; 
	private List<Item> itemList;
	private Label playerLabel, roomNumberLabel, roomLabel;
	private Scene scene;
	private BorderPane borderpane;
	private TextField textParse;
	private Stage primaryStage;
	
	public void start(Stage primaryStage){
		
		//load assets and objects
//		Game game = new Game();
		Player player = new Player(50, 1, 1, 2);	//Player(hp, def, atk, spd)
		resourceManager = new ResourceManager();
		resourceManager.loadAssetToList();
		loadList();
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////////
		primaryStage.setTitle("Underground Hero");
	    primaryStage.setResizable(false);
		
		borderpane = new BorderPane();
		
		HBox dialogueHBox = new HBox(10);		//box for main dialogue and description of rooms, items, everything
		HBox textParseHBox = new HBox(10);		//box for text parsing
		HBox roomNumberHBox = new HBox(10);		//box for displaying room floor-number
		VBox playerStatusVBox = new VBox(10);	//box for character status
		
		//setting border styles
		dialogueHBox.setStyle		("-fx-border-style: solid inside;" +
								 	 "-fx-border-color: black;" +
								 	 "-fx-border-width: 1;" );
		playerStatusVBox.setStyle	("-fx-border-style: solid inside;" +
									 "-fx-border-color: black;" +
									 "-fx-border-width: 1;" );
		textParseHBox.setStyle		("-fx-border-style: solid inside;" +
									 "-fx-border-color: black;" +
									 "-fx-border-width: 1;" );
		roomNumberHBox.setStyle		("-fx-border-style: solid inside;" +
									 "-fx-border-color: black;" +
									 "-fx-border-width: 1;" );
		
		//make nodes
		textParse = new TextField();
		textParse.setPromptText("Enter a command");
		textParse.setPrefSize(200, 35);
		textParse.setFocusTraversable(false);
		playerLabel = new Label(player.toString());		//TODO: update changes to character status
		roomNumberLabel = new Label(roomList.get(0).getRoomNumber());
		roomLabel = new Label(roomList.get(0).getRoomDescription());
		
		textParseHandling();
		
		scene = new Scene(borderpane);

		//set padding and dimensions for nodes
		roomLabel.setMaxWidth(450);
		roomLabel.setWrapText(true);
		playerLabel.setPadding(new Insets(15));
		roomLabel.setPadding(new Insets(15));
		roomNumberLabel.setPadding(new Insets(10));
		textParse.setPadding(new Insets(5));

		//add label nodes to boxes
		dialogueHBox.getChildren().add(roomLabel);
		textParseHBox.getChildren().add(textParse);
		roomNumberHBox.getChildren().add(roomNumberLabel);
		playerStatusVBox.getChildren().add(playerLabel);
		
		//set the layout for all boxes
		borderpane.setCenter(dialogueHBox);
		borderpane.setBottom(textParseHBox);
		borderpane.setTop(roomNumberHBox);
		borderpane.setRight(playerStatusVBox);	
		
	    //create and set scene
	    primaryStage.setScene(scene);
	    primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void changeScene(Room room){
		roomNumberLabel.setText(room.getRoomNumber());
		roomLabel.setText(room.getRoomDescription());
	}
	
	/*
	 * a test example of what the text parser will look like
	 * user presses enter key then it checks if the command is valid
	 * 
	 * TODO: navigation check; navigating to rooms that exists instead of hard coding
	 */
	public void textParseHandling(){
		textParse.setOnKeyPressed(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent key) {
				if(key.getCode().equals(KeyCode.ENTER)){
					if(textParse.getText().equalsIgnoreCase("east")){
						System.out.println("todo: changing scene");
						changeScene(roomList.get(1));
						textParse.setText("");
					}else if(textParse.getText().equalsIgnoreCase("help") || (textParse.getText().equalsIgnoreCase("h"))){
						System.out.println("List of commands are:");
						System.out.println("Navigation: go north, go south, go east, go west, go up, go down");
						System.out.println("Room: look, view room");
						textParse.setText("");
					}else{
						textParse.setText("");
					}
				}
			}	
		});
	}
	
	public void loadList(){
		roomList 	= resourceManager.getRoomList();
		monsterList = resourceManager.getMonsterList();
		puzzleList 	= resourceManager.getPuzzleList();
		itemList	= resourceManager.getItemList();
	}
}