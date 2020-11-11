package application;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import javafx.scene.image.Image;


public class Asset 
{
	//list of all of our assets, all assets should be in resources folder
	public static Image grassImage;
	
	//Made it static so we don't have to instantiate this class to load all of our images in memory
	public static void init()
	{
		grassImage = loadImage( "resources/textures/grass.png" );
	}
	
	
	
	public static Image loadImage( String path )
	{
		try {
			return new Image( new FileInputStream( path ) );
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
}


