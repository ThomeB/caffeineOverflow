package application;


public class Camera 
{
	public static float xOffset;
	public static float yOffset;
	
	public float mapHeight;
	public float mapWidth;
	
	public float maxXOffset;
	public float minXOffset = 0;
	public float maxYOffset;
	public float minYOffset = 0;
	
	public Camera( float mapWidth, float mapHeight )
	{
		this.mapHeight = mapHeight;
		this.mapWidth = mapWidth;
		
		maxXOffset = mapWidth;
		maxYOffset = mapHeight;
	}
	
	public void update( float xMove, float yMove )
	{
		xOffset += xMove;
		yOffset += yMove;
		
		
		
		
	}
	
	public void centerOnCharacter( Character hero )
	{
		xOffset = hero.getxPos() * Tile.TILEWIDTH - mapWidth / 2 - hero.getWidth() / 2;
		yOffset = hero.getyPos() * Tile.TILEHEIGHT - mapHeight / 2 - hero.getHeight() / 2;
		
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
