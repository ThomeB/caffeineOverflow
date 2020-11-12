package application;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
		} catch (FileNotFoundException e) 
		{
			System.out.println( "Path to a texture file couldn't be found." );
			e.printStackTrace();
		}
		
		return null;
		
	}
}


