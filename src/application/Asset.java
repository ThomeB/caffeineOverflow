/********************
 * @author Jake Rhoads
 * @dateCreated 11/05/2020
 * @lastEditedBy Jake Rhoads
 * @program ZombieGame
 ********************/

package application;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;

/**
 * This class is simply used to load in texture that will be used for our game,
 * they should always be static variables and should only be instantiated
 * once. The path should always originate from the resources folder.
 */


public class Asset 
{
	//list of all of our assets, all assets should be in resources folder
	public static Image grassImage;
	public static Image floorImage;
	public static Image topWallImage;
	public static Image cornerImage;
	public static Image bottomWallImage;
	public static Image rightWallImage;
	public static Image leftWallImage;
	public static Image vertWallImage;
	public static Image horzWallImage;
	public static Image	doorImage;
	public static Image bigASSKNIGHT;
	public static Image ghostAlive;
	public static Image pistol;
	public static Image grayPotion;
	public static Image bullet;
	public static Image barrel;
	public static Image key;
	public static Image[] torchLight;
	public static Image torchLight1;
	public static Image torchLight2;
	public static Image torchLight3;
	public static Image torchLight4;
	public static Image torchLight5;
	public static Image torchLight6;
	public static Image torchLight7;
	public static Image torchLight8;
	
	
	//Made it static so we don't have to instantiate this class to load all of our images in memory
	public static void init()
	{
		grassImage = loadImage( "resources/textures/grass.png" );
		floorImage = loadImage( "resources/textures/floor.png" );
		topWallImage = loadImage( "resources/textures/topWall1.png" );
		cornerImage = loadImage( "resources/textures/corner.png" );
		bottomWallImage = loadImage( "resources/textures/bottomWall.png" );
		rightWallImage = loadImage( "resources/textures/rightWall.png" );
		leftWallImage = loadImage( "resources/textures/leftWall.png" );
		vertWallImage = loadImage( "resources/textures/vertWall.png" );
		horzWallImage = loadImage( "resources/textures/horzWall.png" );
		doorImage = loadImage( "resources/textures/door.png" );
		bigASSKNIGHT = loadImage("resources/textures/knight.png");
		ghostAlive = loadImage("resources/textures/ghostAlive.png");
		pistol = loadImage("resources/textures/pistol.png");
		grayPotion = loadImage("resources/textures/grayPotion.png");
		bullet = loadImage("resources/textures/fireball.png");
		barrel = loadImage("resources/textures/Barrel.png");
		key = loadImage("resources/textures/key.png");
		torchLight = new Image[9];
		for(int x = 0; x < torchLight.length; x++) {
			torchLight[x] = loadImage("resources/textures/torch" + (x+1) + ".png");
		}
		
	}
	
	
	//Takes in a string file path to an image file and returns an Image file
	//using a FileInputStream. Image class is the only type of object that
	//the GraphicsContext component is able to draw with.
	public static Image loadImage( String path )
	{
		try {
			return new Image( new FileInputStream( path ) );
		} catch (FileNotFoundException e) 
		{
			System.out.println( "Path to a texture file couldn't be found." );
			e.printStackTrace();
		}
		
		return null;
		
	}
}

