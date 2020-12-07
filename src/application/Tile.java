/********************
 * @author Jake Rhoads
 * @dateCreated 11/05/2020
 * @lastEditedBy Jake Rhoads
 * @program ZombieGame
 ********************/

package application;

import java.util.HashMap;

/**
 * This class should never be instantiated in any other class file,
 * each tile has its own hash code that is put into a hashmap
 * and will represent a different image in textures. All images
 * are initially loaded in the Assets file.
 * 
 * The Tiles HashMap is what the Map class reads to determine which
 * image to render.
 */

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Tile 
{
	public static HashMap<String, Tile> tiles = new HashMap<String, Tile>(20);
	
	public static Tile floorTile = new Tile( Asset.floorImage, ".");
	public static Tile grassTile = new Tile( Asset.grassImage, ",");
	public static Tile topWallTile = new Tile( Asset.topWallImage, "T");
	public static Tile cornerTile = new Tile( Asset.cornerImage, "C");
	public static Tile bottomWallTile = new Tile( Asset.bottomWallImage, "B");
	public static Tile rightWallTile = new Tile( Asset.rightWallImage, "R");
	public static Tile leftWallTile = new Tile( Asset.leftWallImage, "L");
	public static Tile vertWallTile = new Tile( Asset.vertWallImage, "V");
	public static Tile horzWallTile = new Tile( Asset.horzWallImage, "H");
	public static Tile doorTile = new Tile( Asset.doorImage, "D");
	
	
	public static final float TILEWIDTH = 100;
	public static final float TILEHEIGHT = 100;
	
	private Image texture;
	
	public Tile( Image texture, String hashCode )
	{
		this.texture = texture;
		
		tiles.put( hashCode, this );
		
		
	}
	
	public void render( GraphicsContext gc, int x, int y )
	{
		gc.drawImage( texture,  x * TILEWIDTH - Camera.xOffset, y * TILEHEIGHT - Camera.yOffset, TILEWIDTH, TILEHEIGHT );
	}
	
}
