package application;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Game {
	
	private Stage stage;
	private Scene gameScene;
	private HBox topBox;
	private HBox botBox;
	
	private BackgroundFill blackFill;
	private Background blackBackground;
	
	
	private Map map;
	private Character character;
	private Canvas canvas;
	//Paint component that should be passed to any render method
	private GraphicsContext gc;
	
	private double height;
	private double width;
	
	public Game(Stage stage, double height, double width) {
		
		this.stage = stage;
		this.height = height;
		this.width = width;
		
		//Loads in all textures for the game
		Asset.init();
		map = new Map( "resources/maps/testMap.txt" );
		character = new Character(1,1,1,1,1,1,1,"Bob", 1, Asset.bigASSKNIGHT);
		
		createScenes();
		
		stage.centerOnScreen();
		stage.setTitle( "Zombie Game" );
		stage.setScene( gameScene );
		
		//key listeners (removed for now)
		//see: https://stackoverflow.com/questions/29962395/how-to-write-a-keylistener-for-javafx
		//mouse listeners (removed for now)
		//see: https://stackoverflow.com/questions/16635514/how-to-get-location-of-mouse-in-javafx
		//also see: lambda expressions (cleaner code)
	}
	
	
	
	public void tick(double deltaTime) {
		//update objects in game
		
		//update graphics here
		map.render( gc );
		character.render(gc);
		
		//update game data here
	}
	
	public void createScenes() {
		
		//******* STRUCTURE FOR GAME SCENE *************
		
		BorderPane gameRoot = new BorderPane();
		
		
		//Background used for top and bottom of screen
		blackFill = new BackgroundFill( Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY );
		blackBackground = new Background( blackFill );
		
		//HBox will align all nodes horizontally
		topBox = new HBox();
		topBox.setPrefHeight( 200 );
		topBox.setPrefWidth( 400 );
		topBox.setBackground( blackBackground );
		gameRoot.setTop( topBox );
		
		
		botBox = new HBox();
		botBox.setPrefHeight( 200 );
		botBox.setPrefWidth( 400 );
		botBox.setBackground( blackBackground );
		gameRoot.setBottom( botBox );
		
		//where map and all game objects should be rendered at
		canvas = new Canvas( map.getWidth(), map.getHeight() );
		gc = canvas.getGraphicsContext2D();
		
		gameRoot.setCenter( canvas );
		
		
		gameScene = new Scene( gameRoot, width, height );
		

		//*********************************************
	}
	
	public Stage getStage() {
		return stage;
	}
	public double getHeight() {
		return height;
	}
	public double getWidth() {
		return width;
	}
}

