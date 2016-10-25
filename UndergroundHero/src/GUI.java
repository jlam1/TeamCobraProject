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
	private Label playerLabel, roomNumberLabel, roomLabel, helpLabel;
	private Scene scene;
	private BorderPane borderpane;
	private TextField textParse;
	private Game game;

	public void start(Stage primaryStage) throws Exception
	{

		//load assets and objects
		Game game = new Game();
		Player player = new Player(10, 1, 1, 2);	//Player(hp, def, atk, spd)
		resourceManager = new ResourceManager();
		resourceManager.loadAssetToList();
		loadList();

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
		playerLabel = new Label(player.toString());		//TODO: update changes to character status
		roomNumberLabel = new Label(roomList.get(0).getName());
		roomLabel = new Label(roomList.get(0).getDescription());
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
	public void textParseHandling()
	{
		textParse.setOnKeyPressed(new EventHandler<KeyEvent>()
		{
			@Override
			public void handle(KeyEvent key)
			{
				if (key.getCode().equals(KeyCode.ENTER))
				{
					if (textParse.getText().equalsIgnoreCase("east"))
					{
						System.out.println("Room change to: " + roomList.get(5).getName());
						changeScene(roomList.get(5));
					}
					if (textParse.getText().equalsIgnoreCase("help") || (textParse.getText().equalsIgnoreCase("h")))
					{
						System.out.println("List of commands are:");
						System.out.println("Navigation: go north, go south, go east, go west, go up, go down");
						System.out.println("Room: look, view room");
					}
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

	public void loadList()
	{
		roomList = resourceManager.getRoomList();
		monsterList = resourceManager.getMonsterList();
		puzzleList = resourceManager.getPuzzleList();
		itemList = resourceManager.getItemList();
	}
}
