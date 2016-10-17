import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**Class name: GUI.java
 * @author King Lo
 * @version 1.0
 * Course: ITEC 3150 Fall 2016
 * Written: Oct 9, 2016
 *
 * 
 * This class �now describe what the class does
 * 
 * Purpose: �Describe the purpose of this class 
 * 
 * This will act as template for our game's interface
 */
public class GUI extends Application
{

	Stage window;
	Scene scene1, scene2, scene3;
	private ResourceManager resourceManager;
	private List<Room> roomList; 
	private List<Monster> monsterList;
	private List<Puzzle> puzzleList; 
	private List<Item> itemList;

	public void start(Stage primaryStage)
	{	
		loadAssets();
		
		window = primaryStage;

		//Button 1
		Label label1 = new Label(viewRoom(roomList.get(0)));	//example of what viewRoom command might look like
		
		Button button1 = new Button("Next Scene");
		button1.setOnAction(e -> window.setScene(scene2));
		
		//Layout 1 - children laid out in vertical column
		VBox layout1 = new VBox(20);
		layout1.getChildren().addAll(label1, button1);
		scene1 = new Scene(layout1, 400, 200);
		
		//vbox - for player's stat
		VBox playerStatBox = new VBox(30);
		Label playerLabel = new Label("Player Stats");
		playerStatBox.getChildren().add(playerLabel);

		//Button 2
		Label label2 = new Label("I am warning you. Please Leave Now. "
				+ "\n Something very bad will hapen on the next scene.");
		Button button2 = new Button("Go Back Now!");
		
		button2.setOnAction(e -> window.setScene(scene1));
		Button button3 = new Button("Next Scene");

		//Layout 2
		GridPane layout2 = new GridPane();
		layout2.add(label2, 0, 0);
		layout2.add(button2, 0 ,1);
		layout2.add(button3, 0, 1);
		layout2.add(playerStatBox, 1, 0); //column, row
		scene2 = new Scene(layout2, 400, 100);
		GridPane.setHalignment(button2, HPos.LEFT);
		GridPane.setHalignment(button3, HPos.RIGHT);
		//GridPane.setColumnSpan(label2, 1);

		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL); //locks stage
		stage.setTitle("");
		Label label3 = new Label("YOUR COMPUTER IS INFECTED WITH A VIRUS! "
				+ "\n PLEASE SCAN YOU COMPUTER!"
				+ "\n PLEASE TRY TO CLOSE THE OTHER SCREEN BEFORE THIS ONE!");
		label3.setTextFill(Color.RED);
		Group root = new Group();
		root.getChildren().add(label3);
		scene3 = new Scene(root, 500, 200, Color.BLACK);
		stage.setScene(scene3);
		button3.setOnAction(e -> stage.show());

		//Display scene 1 at first
		window.setScene(scene1);
		window.show();
	}
	
	//depending on room change, changes room description
	//TODO: create v/hboxs, add children nodes, return scene
//	public Scene(Label roomDescription, Label playerStat){
//		
//	}
	
	
	public static void main(String[] args)
	{
		launch(args);
	}
	
	/*
	 * Writes all assets from text files to a list.
	 */
	public void loadAssets(){
		resourceManager = new ResourceManager();
		resourceManager.writeToRoomList();
		resourceManager.writeToMonsterList();
		resourceManager.writeToItemList();
		resourceManager.writeToPuzzleList();
		
		roomList 	= resourceManager.getRoomList();
		monsterList = resourceManager.getMonsterList();
		puzzleList 	= resourceManager.getPuzzleList();
		itemList	= resourceManager.getItemList();
	}
	
	/*
	 * Takes in user input "view room", "look", etc to view a description of a room
	 */
	public String viewRoom(Room currentRoom){
		return "ROOM " + currentRoom.getRoomNumber() + "\n" + currentRoom.getRoomDescription() + "\nEXITS: " + currentRoom.getExits();
	}
	
	public void navigateCommand(String command){
		//TODO
	}

}