/********************
 * @author Jake Rhoads
 * @dateCreated 11/18/2020
 * @lastEditedBy Jake Rhoads
 * @program ZombieGame
 ********************/

package application;

/**
 * This class should only be instantiated once and should follow the main player.
 * 
 * The View Height/Width is how much you want to allow the player to see,
 * whereas the Offsets should be based on how large the actual map is, subtracted 
 * from your View Height/Width.
 * 
 * xOffset and yOffset are static variables that must be subtracted from the
 * x and y positions for all object that are being rendered on the Canvas,
 * can look at the Tile class for an example on how it's implemented.
 */

public class Camera 
{
	
	public static float xOffset;
	public static float yOffset;
	
	public float viewHeight;
	public float viewWidth;
	
	public float maxXOffset;
	public float minXOffset;
	public float maxYOffset;
	public float minYOffset;
	
	public Camera(float viewWidth, float viewHeight, float mapWidth, float mapHeight )
	{
		this.viewWidth = viewWidth;
		this.viewHeight = viewHeight;
		
		maxXOffset = mapWidth - viewWidth;
		maxYOffset = mapHeight - viewHeight;
		minXOffset = 0;
		minYOffset = 0;
	}
	
	
	public void centerOnCharacter( Character hero )
	{
		xOffset = hero.getxPos() * Tile.TILEWIDTH - viewWidth / 2 - hero.getWidth() / 2;
		yOffset = hero.getyPos() * Tile.TILEHEIGHT - viewHeight / 2 - hero.getHeight() / 2;
		
		
		if( xOffset > maxXOffset )
			xOffset = maxXOffset;
		
		if( xOffset < minXOffset )
			xOffset = minXOffset;
		
		if( yOffset > maxYOffset )
			yOffset = maxYOffset;
		
		if( yOffset < minYOffset )
			yOffset = minYOffset;
		
	}
}
