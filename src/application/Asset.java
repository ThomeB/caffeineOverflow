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
	public static Image ghostAlive;
	public static Image pistol;
	public static Image grayPotion;
	public static Image bullet;
	public static Image bullet2;
	public static Image barrel;
	public static Image key;
	public static Image handCannonLeft;
	public static Image handCannonRight;
	public static Image shotgunLeft;
	public static Image shotgunRight;
	public static Image rifleRight;
	public static Image rifleLeft;
	public static Image charDead;
	public static Image youDiedTxt;
	public static Image wizardForm1;
	public static Image wizardForm2;
	public static Image wizardForm3;

	
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
	public static Image[] zippyAttack;
	public static Image[] zippyWalkRight;
	public static Image[] zippyWalkLeft;
	public static Image[] zippyIdle;
	
	public static MediaPlayer hauntedForest;
	public static MediaPlayer zombieMusic;
	public static MediaPlayer victoryMusic;
	public static MediaPlayer youDied;
	
	//Made it static so we don't have to instantiate this class to load all of our images in memory
	public static void init()
	{
		grassImage = loadImage( "resources/textures/mapTextures/grass.png" );
		floorImage = loadImage( "resources/textures/mapTextures/floor.png" );
		topWallImage = loadImage( "resources/textures/mapTextures/topWall1.png" );
		cornerImage = loadImage( "resources/textures/mapTextures/corner.png" );
		bottomWallImage = loadImage( "resources/textures/mapTextures/bottomWall.png" );
		rightWallImage = loadImage( "resources/textures/mapTextures/rightWall.png" );
		leftWallImage = loadImage( "resources/textures/mapTextures/leftWall.png" );
		vertWallImage = loadImage( "resources/textures/mapTextures/vertWall.png" );
		horzWallImage = loadImage( "resources/textures/mapTextures/horzWall.png" );
		doorImage = loadImage( "resources/textures/mapTextures/door.png" );
		ghostAlive = loadImage("resources/textures/zombie/ghostAlive.png");
		pistol = loadImage("resources/textures/guns/pistol.png");
		grayPotion = loadImage("resources/textures/interactableTextures/grayPotion.png");
		bullet = loadImage("resources/textures/guns/bullets/fireball.png");
		barrel = loadImage("resources/textures/barrel/Barrel.png");
		key = loadImage("resources/textures//interactableTextures/key.png");
		handCannonLeft = loadImage("resources/textures/guns/handCannonLeft.png");
		handCannonRight = loadImage("resources/textures/guns/handCannonRight.png");
		bullet2 = loadImage("resources/textures/guns/bullets/bullet2.png");
		shotgunLeft = loadImage("resources/textures/guns/pistolLeft.png");
		shotgunRight = loadImage("resources/textures/guns/pistolRight.png");
		rifleRight = loadImage("resources/textures/guns/rifle.png");
		rifleLeft = loadImage("resources/textures/guns/rifleLeft.png");
		charDead = loadImage("resources/textures/character/charDead.png");
		youDiedTxt = loadImage("resources/textures/mapTextures/youDied.png");
		wizardForm1 = loadImage("resources/textures/wizard/Wizard_Form1.png");
		wizardForm2 = loadImage("resources/textures/wizard/Wizard_Form2.png");
		wizardForm3 = loadImage("resources/textures/wizard/Wizard_Form3.png");
		
		
		torchLight = new Image[9];
		for(int x = 0; x < torchLight.length; x++) {
			torchLight[x] = loadImage("resources/textures/mapTextures/Torchlight/torch" + (x+1) + ".png");
		}
		
		barrelExplosion = new Image[9];
		for(int x = 0; x < barrelExplosion.length; x++) {
			barrelExplosion[x] = loadImage("resources/textures/barrel/Explosion/explo" + (x+1) + ".png");
		}
		
		heroR = new Image[11];
		heroL = new Image[11];
		for(int x = 0; x < heroR.length; x++) {
			heroR[x] = loadImage("resources/textures/character/charWalkRight/charr" + (x+1) + ".png");
			heroL[x] = loadImage("resources/textures/character/charWalkLeft/charl" + (x+1) + ".png");
		}
		
		zombieWalkLeft = new Image[100];
		for( int i = 0; i < zombieWalkLeft.length; i++ )
		{
			zombieWalkLeft[i] = loadImage("resources/textures/zombie/zomebieWalkLeft/zombieWalkLeft" + "_" + (i + 1) + ".png" );
		}
		
		zombieWalkRight = new Image[100];
		for( int i = 0; i < zombieWalkRight.length; i++ )
		{
			zombieWalkRight[i] = loadImage("resources/textures/zombie/zombieWalkRight/zombieWalkRight" + "_" + (i + 1) + ".png" );
		}
		
		zombieAttack = new Image[92];
		for( int i = 0; i < zombieAttack.length; i++ )
		{
			zombieAttack[i] = loadImage("resources/textures/zombie/zombieAttack/zombieAttack" + "_" + (i + 1) + ".png" );
		}
		
		zombieIdle = new Image[100];
		for( int i = 0; i < zombieIdle.length; i++ )
		{
			zombieIdle[i] = loadImage("resources/textures/zombie/zombieIdle/zombieIdle" + "_" + (i + 1) + ".png" );
		}
		
		bruteWalkLeft = new Image[126];
		for( int i = 0; i < bruteWalkLeft.length; i++ )
		{
			if( i < 10 )
				bruteWalkLeft[i] = loadImage("resources/textures/brute/bruteWalkLeft/Armature_run_00" + i + ".png" );
			else if ( i < 100 )
				bruteWalkLeft[i] = loadImage("resources/textures/brute/bruteWalkLeft/Armature_run_0" + i + ".png" );
			else
				bruteWalkLeft[i] = loadImage("resources/textures/brute/bruteWalkLeft/Armature_run_" + i + ".png" );
		}
		
		bruteWalkRight = new Image[126];
		for( int i = 0; i < bruteWalkRight.length; i++ )
		{
			if( i < 10 )
				bruteWalkRight[i] = loadImage("resources/textures/brute/bruteWalkRight/Armature_run_00" + i + ".png" );
			else if ( i < 100 )
				bruteWalkRight[i] = loadImage("resources/textures/brute/bruteWalkRight/Armature_run_0" + i + ".png" );
			else
				bruteWalkRight[i] = loadImage("resources/textures/brute/bruteWalkRight/Armature_run_" + i + ".png" );
		}
		
		bruteIdle = new Image[61];
		for( int i = 0; i < bruteIdle.length; i++ )
		{
			if( i < 10 )
				bruteIdle[i] = loadImage("resources/textures/brute/bruteIdle/Armature_idle2_0" + i + ".png" );
			else
				bruteIdle[i] = loadImage("resources/textures/brute/bruteIdle/Armature_idle2_" + i + ".png" );
		}
		
		bruteAttack = new Image[151];
		for( int i = 0; i < bruteAttack.length; i++ )
		{
			if( i < 10 )
				bruteAttack[i] = loadImage("resources/textures/brute/bruteAttack/Armature_swing_00" + i + ".png" );
			else if ( i < 100 )
				bruteAttack[i] = loadImage("resources/textures/brute/bruteAttack/Armature_swing_0" + i + ".png" );
			else
				bruteAttack[i] = loadImage("resources/textures/brute/bruteAttack/Armature_swing_" + i + ".png" );
		}
		
		waterBullet = new Image[4];
		for( int i = 0; i < waterBullet.length; i++ )
		{
			waterBullet[i] = loadImage("resources/textures/guns/bullets/HandcannonProjectile/water" + (i + 1) + ".png" );
		}
		
		zippyAttack = new Image[4];
		for( int i = 0; i < zippyAttack.length; i++ )
		{
			zippyAttack[i] = loadImage("resources/textures/zippy/dogAttack/dogAttack_" + (i + 1) + ".png" );
		}
		
		zippyWalkRight = new Image[7];
		for( int i = 0; i < zippyWalkRight.length; i++ )
		{
			zippyWalkRight[i] = loadImage("resources/textures/zippy/dogRunRight/dogRunRight_" + (i + 1) + ".png" );
		}
		
		zippyWalkLeft = new Image[7];
		for( int i = 0; i < zippyWalkLeft.length; i++ )
		{
			zippyWalkLeft[i] = loadImage("resources/textures/zippy/dogRunLeft/dogRunLeft_" + (i + 1) + ".png" );
		}
		
		zippyIdle = new Image[16];
		for( int i = 0; i < zippyIdle.length; i++ )
		{
			zippyIdle[i] = loadImage("resources/textures/zippy/dogIdle/dogIdle_" + (i + 1) + ".png" );
		}
		
		//SOUND BYTES
		hauntedForest = loadMedia("resources/music/HauntedForest.mp3");
		zombieMusic = loadMedia("resources/music/ZombieMusic.mp3");
		victoryMusic = loadMedia("resources/music/VictoryMusic.mp3");
		youDied = loadMedia("resources/music/YouDied.mp3");
		
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

