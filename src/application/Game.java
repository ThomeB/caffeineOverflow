package application;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Game {
	
	private Stage stage;
	private Scene gameScene;
	
	private Map map;
	private Canvas canvas;
	private Camera camera;
	private ProgressBar healthBar;
	private Text healthText;
	//Paint component that should be passed to any render method
	
	public static GraphicsContext gc;
	public static Character character;
	//made static to make accessible to gun classes
	public static ArrayList<Entity> enemies;
	public static ArrayList<Interactable> interactables;
	
	private double height;
	private double width;
	
	Timer timer = new Timer( 6 );
	
	private boolean [] keysPressed = {
			false,//W [0]
			false,//A [1]
			false,//S [2]
			false,//D [3]
			false //F [4]
	};
	
	/*************************************************************
	 *                Constructors                               *
	 ************************************************************/
	
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
		camera = new Camera( 1200, 600, map.getPixelWidth(), map.getPixelHeight() );
		character = new Character(1, 1, camera );
		
		//Create 5 enemies and link them to our enemies ArrayList
		enemies = new ArrayList<Entity>(5);
		Entity e1 = new Enemy( 2 , 3 );
		enemies.add( e1 );
		Entity e2 = new Enemy( 5, 7  );
		enemies.add( e2 );
		Entity e3 = new Enemy( 10, 10 );
		enemies.add( e3 );
		Entity e4 = new Enemy( 8, 4 );
		enemies.add( e4 );
		Entity e5 = new Enemy( 3 , 6  );
		enemies.add( e5 );
		Entity e6 = new Enemy( 10 , 11  );
		enemies.add( e6 );
		Entity e7 = new Enemy( 9 , 8  );
		enemies.add( e7 );
		Entity e8 = new ExplodingBarrel(2, 2);
		enemies.add(e8);
		Entity e9 = new ExplodingBarrel(2, 5);
		enemies.add(e9);
		Entity e10 = new ExplodingBarrel(2, 8);
		enemies.add(e10);
		
		
		
		//Create a gun on the map
		
		interactables = new ArrayList<Interactable>(10);
		Key i1 = new Key( 10, 10 );
		interactables.add( i1 );
		Door i2 = new Door( 44, 20, map );
		interactables.add( i2 );
		HealthPack i3 = new HealthPack( 8, 8 );
		interactables.add( i3 );
		
		Pistol pistol1 = new Pistol( 1, 1 );
		interactables.add( pistol1 );
		
		
		
		
		createScenes();
		stage.setScene( gameScene );

		
		
	}//close constructor
	
	/*************************************************************
	 *                FUNCTIONS                                  *
	 ************************************************************/
	
	public void tick(double deltaTime) {
		
		update();
		render();
	}
	
	
	
	public void update() {
		//--- UPDATE OBJECT VARIABLES ---
		
				//update the character
				character.update(keysPressed, map);
				
				//update the enemies
				for (Entity e : enemies) {
					if (e.isAlive()) {
						e.update(character);
					}
				}
				
				//check for interactions
				for (int i = 0; i < interactables.size(); i++) {
					
					Interactable interactable = interactables.get(i);
					//check first if we should remove the interactable. Is it tagged to be removed? is it null? 
					if (interactable == null || interactable.despawn) {
						interactables.remove(i);
					}else {
						//if the key[4] is true, F is being held down. Then do a complicated distance equation to get the centers of entities and characters so the top right of the image isn't the only thing being factored in. (Comparing centers)
						if (keysPressed[4] == true && Utility.getDistance(character.xPos + character.getWidth()/Tile.TILEWIDTH/2, character.yPos + character.getHeight()/Tile.TILEHEIGHT/2, interactable.xPos + interactable.getWidth()/Tile.TILEWIDTH/2, interactable.yPos + interactable.getHeight()/Tile.TILEHEIGHT/2) < 1) {
							interactable.pickup(character);
						}
					}
					
				}
				
				//Update health bar and text
				healthBar.setProgress( (double) character.getHp() / (double) character.getMaxHP() );
				healthText.setText( "" + character.getHp() + " / " + character.getMaxHP() );
					
	}
	
	
	
	public void render() {
		
		//--- RENDER GRAPHICS ---
		
				//clears the graphics on the canvas
				//gc.clearRect( 0, 0, canvas.getWidth(), canvas.getHeight() );
				
				//Render the map
				gc.fillRect(0 , 0, canvas.getWidth(), canvas.getHeight() );
				map.render( gc );
				
				for( int i = 0; i < enemies.size(); i++ )
				{
					Entity enemy = enemies.get(i);
					if( enemy != null && enemy.isAlive() ) //if we want bodies to stay after death, get rid of the isAlive part and instead just change the image somewhere
						enemy.render( gc );
					else if (enemy != null) {
						if(enemy.counter <= 120 && enemy.isATrap()) { //change this to timer class when made -- this is located in Entity as a public int
							enemy.render(gc);
							enemy.counter++;
						} else {
							enemy = null;
							enemies.remove(i);
						}
					}
				}
				
				for ( int i = 0; i < interactables.size(); i++ )
				{
					Interactable interactable = interactables.get(i);
					if (interactable != null && !interactable.despawn)
						interactable.render( gc );
					
				}
				
				
				character.render(gc);
				
		
	}
	
	
	
	public void createScenes() {
		
		//******* STRUCTURE FOR GAME SCENE *************
		
		BorderPane gameRoot = new BorderPane();
		
		//where map and all game objects should be rendered at
		canvas = new Canvas( camera.viewWidth, camera.viewHeight );
		gc = canvas.getGraphicsContext2D();
		gc.setFont( Font.font( "Verdana", 40 ) );
		gc.setStroke( Color.RED );
		
	
		
		
		gameRoot.setCenter( canvas );
		
		//Add bottom node for UI
		StackPane ui = new StackPane();
		
		//Create and add healthbar
		healthBar = new ProgressBar( 100 );
		healthBar.setStyle( "-fx-accent: red;" );
		healthBar.setPrefWidth(300);
		healthBar.setPrefHeight(50);
		StackPane.setMargin( healthBar, new Insets( 0, 0, 700, 0) );
		//Create txt for healthbar
		healthText = new Text( " -- / -- " );
		healthText.setFont( Font.font( "Verdana", 20 ) );
		StackPane.setMargin( healthText, new Insets( 0, 0, 700, 0) );
		
		
		ui.getChildren().addAll( healthBar, healthText );
		
		
		gameRoot.setBottom( ui );
		gameRoot.setBackground( new Background( new BackgroundFill( Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY ) ) );
				
		
		gameScene = new Scene( gameRoot, width, height , Color.BLACK);
		
		//mouse listeners (removed for now)
		//see: https://stackoverflow.com/questions/16635514/how-to-get-location-of-mouse-in-javafx
		//also see: lambda expressions (cleaner code)
		canvas.setOnMousePressed(new EventHandler<MouseEvent>() {
		    @Override 
		    public void handle(MouseEvent mouseEvent) {
		      
		    	if( character.getGun() != null )
		    		character.getGun().fire( (float) mouseEvent.getX() + Camera.xOffset, (float) mouseEvent.getY() + Camera.yOffset );
		     
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

		

		//*********************************************
	}
	
	/*************************************************************
	 *                Getters / Setters                          *
	 ************************************************************/
	
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