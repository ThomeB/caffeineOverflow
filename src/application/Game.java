package application;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
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
	private Camera camera;
	//Paint component that should be passed to any render method
	private GraphicsContext gc;
	
	private double height;
	private double width;
	
	
	private boolean [] keysPressed = {
			false,//W [0]
			false,//A [1]
			false,//S [2]
			false,//D [3]
			false //F [4]
	};
	
	public Game(Stage stage, double height, double width) {
		
		this.stage = stage;
		this.height = height;
		this.width = width;
		
		//Loads in all textures for the game
		Asset.init();
		map = new Map( "resources/maps/testMap.txt" );
		camera = new Camera( 1200, 900, map.getPixelWidth(), map.getPixelHeight() );
		character = new Character(1,1,1,1,1,1f,1f,"Bob", 0.1f, Asset.bigASSKNIGHT, camera );
		
		
		
		
		
		createScenes();
		
		
		stage.setScene( gameScene );

		//mouse listeners (removed for now)
		//see: https://stackoverflow.com/questions/16635514/how-to-get-location-of-mouse-in-javafx
		//also see: lambda expressions (cleaner code)
		
		//Key Listeners: Stores keys in a table
		Scene scene = stage.getScene();
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
            	String code = event.getCode().toString();
                switch (code) {
                	case "W":
                		keysPressed[0] = true;
                		break;
                	case "A":
                		keysPressed[1] = true;
                		break;
                	case "S":
                		keysPressed[2] = true;
                		break;
                	case "D":
                		keysPressed[3] = true;
                		break;
                	case "F":
                		keysPressed[4] = true;
                		break;
                	default:
                		break;
                }
                		
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
            	String code = event.getCode().toString();
                switch (code) {
                	case "W":
                		keysPressed[0] = false;
                		break;
                	case "A":
                		keysPressed[1] = false;
                		break;
                	case "S":
                		keysPressed[2] = false;
                		break;
                	case "D":
                		keysPressed[3] = false;
                		break;
                	case "F":
                		keysPressed[4] = false;
                		break;
                	default:
                		break;
                }
            }
        });
		
	}
	
	public void tick(double deltaTime) {
		//--- UPDATE OBJECT VARIABLES ---
		
		character.update(keysPressed, map);
		camera.centerOnCharacter( character );
		
		
		
		//--- RENDER GRAPHICS ---
		
		//clears the graphics on the canvas
		gc.clearRect( 0, 0, canvas.getWidth(), canvas.getHeight() );
		
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
		/*
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
		*/
		//where map and all game objects should be rendered at
		canvas = new Canvas( 1200, 900 );
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
	
	public GraphicsContext getGC()
	{
		return gc;
	}
}
