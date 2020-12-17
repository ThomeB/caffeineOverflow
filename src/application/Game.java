package application;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
import javafx.scene.media.MediaPlayer;
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
	private ImageView keyView;
	private ImageView gunView;
	private ImageView youDiedView;
	//Paint component that should be passed to any render method
	
	private MediaPlayer backgroundMusic;
	private MediaPlayer bossMusic;
	private MediaPlayer victoryMusic;
	private MediaPlayer youDiedMusic;
	private MediaPlayer fuckYou;
	
	public static GraphicsContext gc;
	public static Character character;
	//made static to make accessible to gun classes
	public static ArrayList<Entity> enemies;
	public static ArrayList<Entity> pendingEnemies;
	public static ArrayList<Interactable> interactables;
	public static ArrayList<GameObject> torch;
	
	
	private double height;
	private double width;
	
	protected static Timer pickupDebounceTimer;
	
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
		pickupDebounceTimer = new Timer( 2 );
		pendingEnemies = new ArrayList<Entity>(5);
		//Create 5 enemies and link them to our enemies ArrayList
		enemies = new ArrayList<Entity>();
		torch = new ArrayList<GameObject>(30);
		interactables = new ArrayList<Interactable>(40);
		createGameObjects("resources/maps/enemyMap.txt");
//		
		Entity e11 = new Boss(50, 14);
		enemies.add(e11);
		
		//Create a gun on the map
		
		/*interactables = new ArrayList<Interactable>(10);
		Key i1 = new Key( 10, 10 );
		interactables.add( i1 );
		Key i5 = new Key( 2, 3 );
		interactables.add( i5 );
		Door i2 = new Door( 40, 12, map );
		interactables.add( i2 );
		HealthPack i3 = new HealthPack( 8, 8 );
		interactables.add( i3 );
		Door i4 = new Door( 55, 12, map );
		interactables.add( i4 );
		Door i7 = new Door( 3, 6, map );
		interactables.add( i7 );
		
		Gun pistol1 = new Pistol( 2, 2 );
		interactables.add( pistol1 );
		Gun shotgun = new Shotgun( 4, 4 );
		interactables.add( shotgun );
		Gun handcannon = new HandCannon( 1, 4 );
		interactables.add( handcannon );*/
		
		
		backgroundMusic = Asset.hauntedForest;
		backgroundMusic.setAutoPlay(true);
		backgroundMusic.setCycleCount(MediaPlayer.INDEFINITE);
		backgroundMusic.setVolume( .05 );
		System.out.println( backgroundMusic.getVolume() );
		
		bossMusic = Asset.zombieMusic;
		bossMusic.setAutoPlay(false);
		bossMusic.setCycleCount(MediaPlayer.INDEFINITE);
		
		victoryMusic = Asset.victoryMusic;
		victoryMusic.setAutoPlay(false);
		victoryMusic.setCycleCount(MediaPlayer.INDEFINITE);
		
		youDiedMusic = Asset.youDied;
		youDiedMusic.setAutoPlay(false);
		
		fuckYou = Asset.fuckYou;
		
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
		
		//See if character is dead
		if( !character.isAlive() )
			characterDied();
		
		//update the character
		character.update(keysPressed, map);
		pickupDebounceTimer.tick();
		//update the enemies
		
		for (Entity e : pendingEnemies) {
			if (e != null){
				enemies.add(e);
			}
		}
		pendingEnemies.removeAll(pendingEnemies);
		
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
		
		//Change music if near boss
		if( character.getxPos() > 41 && character.getyPos() > 6 && character.getyPos() < 19 )
		{
			backgroundMusic.setMute( true );
			bossMusic.setAutoPlay(true);
			//fuckYou.setAutoPlay(true);
			map.setTile("V", 40, 12);
		}
		
		//Change music if escape the prison
		if( character.getxPos() > 56 )
		{
			bossMusic.setMute(true);
			victoryMusic.setAutoPlay(true);
		}
		
		//Update health bar and text
		healthBar.setProgress( (double) character.getHp() / (double) character.getMaxHP() );
		healthText.setText( "" + character.getHp() + " / " + character.getMaxHP() );
		
		//Update key UI
		if( character.getKey() )
			keyView.setImage( Asset.key );
		else
			keyView.setImage( null );
		
		//Change gun UI
		changeGunView();
		
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
				if(enemy.isATrap() && enemy.t != null) { //change this to timer class when made -- this is located in Entity as a public int
					enemy.dynamicRender(gc);
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
		
		
		/*
		 * Torches
		 */
		for(int x = 0; x < torch.size(); x++) {
			if( torch.get(x) != null )
				torch.get(x).dynamicRender(gc);
		}
		
		character.render(gc);
				
		
	}//close render
	
	
	
	public void createScenes() {
		
		//******* STRUCTURE FOR GAME SCENE *************
		
		BorderPane gameRoot = new BorderPane();
		
		//where map and all game objects should be rendered at
		canvas = new Canvas( camera.viewWidth, camera.viewHeight );
		gc = canvas.getGraphicsContext2D();
		gc.setFont( Font.font( "Verdana", 40 ) );
		gc.setStroke( Color.RED );
		
		gameRoot.setCenter( canvas );
		//gameRoot.getCenter().setManaged(false);
		
		//Add bottom node for UI
		StackPane ui = new StackPane();
		
		//Create and add healthbar
		healthBar = new ProgressBar( 100 );
		healthBar.setStyle( "-fx-accent: red;" );
		healthBar.setPrefWidth(300);
		healthBar.setPrefHeight(50);
		StackPane.setMargin( healthBar, new Insets( 0, 0, 700, 0 ) );
		//Create txt inside healthbar
		healthText = new Text( " -- / -- " );
		healthText.setFont( Font.font( "Verdana", 20 ) );
		StackPane.setMargin( healthText, new Insets( 0, 0, 700, 0 ) );
		
		//Create and add an indicator if hero has a key or not
		Image keyImage = null;
		keyView = new ImageView( keyImage );
		keyView.setSmooth( true );
		keyView.setScaleX( 2.0 );
		keyView.setScaleY( 2.0 );
		StackPane.setMargin( keyView, new Insets( 0, 500, 700, 0 ) );
		
		//Add an indicator for type of gun the hero has
		Image gunImage = null;
		gunView = new ImageView( gunImage );
		gunView.setSmooth(true);
		gunView.setManaged(false);
		
		//Add an image to be displayed if die
		Image youDied = null;
		youDiedView = new ImageView(youDied);
		youDiedView.setSmooth(true);
		youDiedView.setManaged(false);
		youDiedView.setScaleX(.3);
		youDiedView.setScaleY(.3);
		
		
		//ui.setManaged( false );
		ui.getChildren().addAll( healthBar, healthText, keyView, gunView, youDiedView );
		gameRoot.setBottom( ui );
		gameRoot.setBackground( new Background( new BackgroundFill( Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY ) ) );
				
		
		gameScene = new Scene( gameRoot, width, height , Color.BLACK);
		
		//mouse listeners (removed for now)
		//see: https://stackoverflow.com/questions/16635514/how-to-get-location-of-mouse-in-javafx
		//also see: lambda expressions (cleaner code)
		canvas.setOnMousePressed(new EventHandler<MouseEvent>() {
		    @Override 
		    public void handle(MouseEvent mouseEvent) {
		      
		    	if( character.getGun() != null && character.isAlive() )
		    	{
		    		character.getGun().fire( (float) mouseEvent.getX() + Camera.xOffset, (float) mouseEvent.getY() + Camera.yOffset );
		    	}
		     
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
	
	public void changeGunView()
	{
		//Update gun UI based on what character is holding
		if(character.getGun() instanceof HandCannon )
		{
			gunView.setScaleX( 1.0 );
			gunView.setScaleY( 0.8 );
			gunView.setImage( Asset.handCannonRight );
			gunView.setX( 1550 );
			gunView.setY( -20 );
		}
		
		if( character.getGun() instanceof Pistol )
		{
			gunView.setScaleX( 0.2 );
			gunView.setScaleY( 0.2 );
			gunView.setImage( Asset.rifleRight );
			gunView.setX( 1000 );
			gunView.setY( -170 );
		}
		
		if( character.getGun() instanceof Shotgun )
		{
			gunView.setScaleX( 0.8 );
			gunView.setScaleY( 0.8 );
			gunView.setImage( Asset.shotgunRight );
			gunView.setX( 1550 );
			gunView.setY( 0 );
		}
	}
	
	public void characterDied()
	{
		
		for( int i = 0; i < keysPressed.length; i ++ )
			keysPressed[i] = false;
		youDiedView.setX( 1100 );
		youDiedView.setY( -400 );
		character.img = Asset.charDead;
		backgroundMusic.setMute( true );
		bossMusic.setMute( true );
		victoryMusic.setMute(true);
		youDiedMusic.setAutoPlay(true);
		youDiedView.setImage(Asset.youDiedTxt);
		
		if( youDiedView.getScaleX() < 2.6 )
		{
			youDiedView.setScaleX( youDiedView.getScaleX() + .003);
			youDiedView.setScaleY( youDiedView.getScaleY() + .003);
		}else {
			System.exit(0);//close the game
		}
	}
	
	public void createGameObjects( String path )
	{
		
		
		String[] tokens = Utility.loadTokens(path);
		
		int width = Integer.parseInt( tokens[0] );
		int height = Integer.parseInt( tokens[1] );
		
		String[][] map = new String[height][width];
		
		for( int y = 0; y < height; y++ )
		{
			for( int x = 0; x < width; x++ )
			{
				map[y][x] = tokens[ (x + y * width) + 2 ].toString();
			}
		}
		
		//We have our symbols, now let's see where to load enemies
		for( int y = 0; y < height; y++ )
		{
			for( int x = 0; x < width; x++ )
			{
				//add enemies
				if(map[y][x].equals("Z") )
					enemies.add( new ZippyEnemy( x, y) );
				if(map[y][x].equals("E") )
					enemies.add( new BasicEnemy( x, y) );
				if(map[y][x].equals("A") )
					enemies.add( new BruteEnemy( x, y) );
				if(map[y][x].equals("P") )
					enemies.add( new ExplodingBarrel(x + .5f, y +.5f) );
				
				//add interactables
				if(map[y][x].equals("K") )
					interactables.add( new Key(x + .5f, y +.5f ) );
				if(map[y][x].equals("+") )
					interactables.add( new HealthPack(x + .5f, y +.5f ) );
				if(map[y][x].equals("D") )
					interactables.add( new Door(x, y, this.map ) );
				if(map[y][x].equals("1") )
					interactables.add( new HandCannon(x + .5f, y +.5f ) );
				if(map[y][x].equals("2") )
					interactables.add( new Shotgun(x + .5f, y +.5f) );
				if(map[y][x].equals("3") )
					interactables.add( new Pistol(x + .5f, y +.5f) );
				
				//Add torches
				if(map[y][x].equals("X" ) )
					torch.add( new GameObject( x + 0.5f, y, 16, 45, Asset.torchLight, 0.05) );
				
			}
		}
		
		
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