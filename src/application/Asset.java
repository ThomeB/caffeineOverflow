/********************
 * @author Jake Rhoads
 * @dateCreated 11/05/2020
 * @lastEditedBy Jake Rhoads
 * @program ZombieGame
 ********************/

package application;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


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
	public static Image bullet2;
	public static Image barrel;
	public static Image key;
	public static Image handCannon;
	public static Image shotgun2;
	public static Image shotgun;
	
	public static Image[] torchLight;
	public static Image[] barrelExplosion;
	public static Image[] heroR;
	public static Image[] heroL;
	public static Image[] zombieWalkLeft;
	public static Image[] zombieWalkRight;
	public static Image[] zombieAttack;
	public static Image[] zombieIdle;
	public static Image[] bruteWalkLeft;
	public static Image[] bruteWalkRight;
	public static Image[] bruteAttack;
	public static Image[] bruteIdle;
	public static Image[] waterBullet;
	public static Image dog;
	public static Image preacher;
	
	public static MediaPlayer hauntedForest;
	public static MediaPlayer zombieMusic;
	
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
		dog = loadImage("resources/textures/wolf.png");
		preacher = loadImage("resources/textures/preacher.png");
		handCannon = loadImage("resources/textures/HandCannon.png");
		bullet2 = loadImage("resources/textures/bullet2.png");
		shotgun2 = loadImage("resources/textures/shotgun2.png");
		shotgun = loadImage("resources/textures/shotgun.png");
		
		
		torchLight = new Image[9];
		for(int x = 0; x < torchLight.length; x++) {
			torchLight[x] = loadImage("resources/textures/torch" + (x+1) + ".png");
		}
		
		barrelExplosion = new Image[9];
		for(int x = 0; x < barrelExplosion.length; x++) {
			barrelExplosion[x] = loadImage("resources/textures/explo" + (x+1) + ".png");
		}
		
		heroR = new Image[11];
		heroL = new Image[11];
		for(int x = 0; x < heroR.length; x++) {
			heroR[x] = loadImage("resources/textures/charr" + (x+1) + ".png");
			heroL[x] = loadImage("resources/textures/charl" + (x+1) + ".png");
		}
		
		zombieWalkLeft = new Image[100];
		for( int i = 0; i < zombieWalkLeft.length; i++ )
		{
			zombieWalkLeft[i] = loadImage("resources/textures/zomebieWalkLeft/zombieWalkLeft" + "_" + (i + 1) + ".png" );
		}
		
		zombieWalkRight = new Image[100];
		for( int i = 0; i < zombieWalkRight.length; i++ )
		{
			zombieWalkRight[i] = loadImage("resources/textures/zombieWalkRight/zombieWalkRight" + "_" + (i + 1) + ".png" );
		}
		
		zombieAttack = new Image[92];
		for( int i = 0; i < zombieAttack.length; i++ )
		{
			zombieAttack[i] = loadImage("resources/textures/zombieAttack/zombieAttack" + "_" + (i + 1) + ".png" );
		}
		
		zombieIdle = new Image[100];
		for( int i = 0; i < zombieIdle.length; i++ )
		{
			zombieIdle[i] = loadImage("resources/textures/zombieIdle/zombieIdle" + "_" + (i + 1) + ".png" );
		}
		
		bruteWalkLeft = new Image[126];
		for( int i = 0; i < bruteWalkLeft.length; i++ )
		{
			if( i < 10 )
				bruteWalkLeft[i] = loadImage("resources/textures/bruteWalkLeft/Armature_run_00" + i + ".png" );
			else if ( i < 100 )
				bruteWalkLeft[i] = loadImage("resources/textures/bruteWalkLeft/Armature_run_0" + i + ".png" );
			else
				bruteWalkLeft[i] = loadImage("resources/textures/bruteWalkLeft/Armature_run_" + i + ".png" );
		}
		
		bruteWalkRight = new Image[126];
		for( int i = 0; i < bruteWalkRight.length; i++ )
		{
			if( i < 10 )
				bruteWalkRight[i] = loadImage("resources/textures/bruteWalkRight/Armature_run_00" + i + ".png" );
			else if ( i < 100 )
				bruteWalkRight[i] = loadImage("resources/textures/bruteWalkRight/Armature_run_0" + i + ".png" );
			else
				bruteWalkRight[i] = loadImage("resources/textures/bruteWalkRight/Armature_run_" + i + ".png" );
		}
		
		bruteIdle = new Image[61];
		for( int i = 0; i < bruteIdle.length; i++ )
		{
			if( i < 10 )
				bruteIdle[i] = loadImage("resources/textures/bruteIdle/Armature_idle2_0" + i + ".png" );
			else
				bruteIdle[i] = loadImage("resources/textures/bruteIdle/Armature_idle2_" + i + ".png" );
		}
		
		bruteAttack = new Image[151];
		for( int i = 0; i < bruteAttack.length; i++ )
		{
			if( i < 10 )
				bruteAttack[i] = loadImage("resources/textures/bruteAttack/Armature_swing_00" + i + ".png" );
			else if ( i < 100 )
				bruteAttack[i] = loadImage("resources/textures/bruteAttack/Armature_swing_0" + i + ".png" );
			else
				bruteAttack[i] = loadImage("resources/textures/bruteAttack/Armature_swing_" + i + ".png" );
		}
		
		waterBullet = new Image[4];
		for( int i = 0; i < waterBullet.length; i++ )
		{
			waterBullet[i] = loadImage("resources/textures/HandcannonProjectile/water" + (i + 1) + ".png" );
		}
		
		//SOUND BYTES
		hauntedForest = loadMedia("resources/music/HauntedForest.mp3");
		zombieMusic = loadMedia("resources/music/ZombieMusic.mp3");
		
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
	
	
	  public static MediaPlayer loadMedia( String path ) 
	  { 
			  return new MediaPlayer( new Media( new File(path).toURI().toString() ) ); 
	  }
	 
}

