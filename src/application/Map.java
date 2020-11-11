/********************
 * @author Jake Rhoads
 * @dateCreated 11/05/2020
 * @lastEditedBy Jake Rhoads
 * @program ZombieGame
 ********************/
package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javafx.scene.canvas.GraphicsContext;


/**
 * This class will take in a string path to a file and is expecting the first two numbers to be the
 * width and height of the map. 
 * 
 * Then creates String[][] Array called tiles and can be referenced at any time to know what the map looks like.
 */

public class Map 
{
	private int width;
	private int height;
	private String[][] tiles;
	private String path = "";
	
	public Map( String path )
	{
		this.path = path;
		loadMap();
	}
	
	public void update()
	{
		
	}
	
	public void render( GraphicsContext gc )
	{
		for( int y = 0; y < height; y++ )
		{
			for( int x = 0; x < width; x++ )
			{
				gc.drawImage( Asset.grassImage, x * Tile.TILEWIDTH, y*Tile.TILEHEIGHT, Tile.TILEWIDTH, Tile.TILEHEIGHT);
			}
		}
		
	}
	
	private void loadMap()
	{
		StringBuilder builder = new StringBuilder();
		
		//load map as a string
		try 
		{
			BufferedReader br = new BufferedReader( new FileReader( path ) );
			String line;
			
			while( ( line = br.readLine() ) != null )
				builder.append( line + "\n" );
			
			br.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			System.out.println( "Unable to load map file." );
			System.exit( 1 );
		}
		
		//Iterate through string and add tileID's to String[][] tiles
		String file = builder.toString();
		String[] tokens = file.split( "\\s+" );
		width = Integer.parseInt( tokens[0] );
		height = Integer.parseInt( tokens[1] );
		
		tiles = new String[height][width];
		
		for( int y = 0; y < height; y++ )
		{
			for( int x = 0; x < width; x++ )
			{
				tiles[y][x] = tokens[ (x + y * width) + 2 ].toString();
			}
		}
		
		
	}
	
	//Will return an individual tile
	public String getTile( int x, int y )
	{
		return tiles[y][x];
	}
	
	//Can change an individual tile
	public void setTile( String tileID, int x, int y )
	{
		tiles[y][x] = tileID;
	}
	
	//GETTERS AND SETTERS
	public int getHeight()
	{
		return height * Tile.TILEHEIGHT;
	}
	
	public int getWidth()
	{
		return width * Tile.TILEWIDTH;
	}
	
	//------ CODE FOR TESTING -------
	public void printMap( )
	{
		
		for(int y = 0; y < height; y++)
		{
			for(int x = 0; x < width; x++)
			{
				System.out.print( tiles[y][x] );
			}
			System.out.println();
		}
	}
	
}