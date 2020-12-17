/********************
 * @author Jake Rhoads
 * @dateCreated 11/05/2020
 * @lastEditedBy Jake Rhoads
 * @program ZombieGame
 ********************/
package application;


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
	private String[][] map;
	private String[] tokens;
	
	
	public Map( String path )
	{
		tokens = Utility.loadTokens(path);
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
				//Check the hashMap to see if it contains a symbol associated with a tile type
				//Can find or add tile types in the Tile Class
				if( Tile.tiles.containsKey( map[y][x] ) )
					Tile.tiles.get( map[y][x] ).render( gc, x, y );
			}
		}
		
	}
	
	private void loadMap()
	{
		
		width = Integer.parseInt( tokens[0] );
		height = Integer.parseInt( tokens[1] );
		
		map = new String[height][width];
		
		for( int y = 0; y < height; y++ )
		{
			for( int x = 0; x < width; x++ )
			{
				map[y][x] = tokens[ (x + y * width) + 2 ].toString();
			}
		}
		
		
	}
	
	//Will return an individual tile
	public String getTile( int x, int y )
	{
		return map[y][x];
	}
	
	//Can change an individual tile
	public void setTile( String tileID, int x, int y )
	{
		map[y][x] = tileID;
	}
	
	//GETTERS AND SETTERS
	public float getPixelHeight()
	{
		return height * Tile.TILEHEIGHT;
	}
	
	public float getPixelWidth()
	{
		return width * Tile.TILEWIDTH;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public int getWidth()
	{
		return width;
	}
	
}
