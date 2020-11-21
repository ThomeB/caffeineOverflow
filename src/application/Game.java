package application;

import java.util.ArrayList;

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
	
	private Map map;
	private Canvas canvas;
	private Camera camera;
	//Paint component that should be passed to any render method
	private GraphicsContext gc;
	
	private Character character;
	private Gun gun;
	private ArrayList<Enemy> enemies;
	
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
		Enemy.map = map;
		/**Camera takes in width and height that we want our 
		   canvas size to be, this is what will be visible to the player*/
		camera = new Camera( 1200, 700, map.getPixelWidth(), map.getPixelHeight() );
		character = new Character(1,1,1,1,1,64,64,"Bob", 0.1f, Asset.bigASSKNIGHT, camera );
		
		//Create 5 enemies and link them to our enemies ArrayList
		enemies = new ArrayList<Enemy>(5);
		Enemy e1 = new Enemy( 2 , 3 );
		enemies.add( e1 );
		Enemy e2 = new Enemy( 5, 7  );
		enemies.add( e2 );
		Enemy e3 = new Enemy( 10, 10 );
		enemies.add( e3 );
		Enemy e4 = new Enemy( 25, 15 );
		enemies.add( e4 );
		Enemy e5 = new Enemy( 3 * Tile.TILEWIDTH, 6 * Tile.TILEHEIGHT );
		enemies.add( e5 );
		
		//Create a gun on the map
		gun = new Gun( 3, 1 );
		
		
		createScenes();
		stage.setScene( gameScene );

		//mouse listeners (removed for now)
		//see: https://stackoverflow.com/questions/16635514/how-to-get-location-of-mouse-in-javafx
		//also see: lambda expressions (cleaner code)
		canvas.setOnMousePressed(new EventHandler<MouseEvent>() {
		    @Override 
		    public void handle(MouseEvent mouseEvent) {
		      
		    	gun.fire( (float) mouseEvent.getX(), (float) mouseEvent.getY() );
		     
		    }
		  });
		
		//Key Listeners: Stores keys in a table
		gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
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
        gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
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
		for (Enemy e : enemies) {
			e.update(character);
		}
		
		gun.update();
		
		//--- RENDER GRAPHICS ---
		
		//clears the graphics on the canvas
		gc.clearRect( 0, 0, canvas.getWidth(), canvas.getHeight() );
		//Canvas Background
		gc.fillRect(0 , 0, canvas.getWidth(), canvas.getHeight() );
		map.render( gc );
		
		for( int i = 0; i < enemies.size(); i++ )
		{
			if( enemies.get( i ) != null )
				enemies.get( i ).render( gc );
		}
		
		gun.render( gc );
		
		character.render(gc);
		
		//update game data here
	}
	
	public void createScenes() {
		
		//******* STRUCTURE FOR GAME SCENE *************
		
		BorderPane gameRoot = new BorderPane();
		
		//where map and all game objects should be rendered at
		canvas = new Canvas( camera.viewWidth, camera.viewHeight );
		gc = canvas.getGraphicsContext2D();
		
		gameRoot.setCenter( canvas );
		
		gameScene = new Scene( gameRoot, width, height , Color.BLACK);
	
		

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