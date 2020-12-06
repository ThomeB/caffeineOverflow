package application;

import java.security.SecureRandom;

public class HandCannon extends Gun
{
	public static final float HANDCANNON_HEIGHT = 60;
	public static final float HANDCANNON_WIDTH = 90;
	public static final int HANDCANNON_AMMO_CAPACITY = 30;
	public static final float HANDCANNON_VELOCITY = .08f;
	
	private SecureRandom sr;
	
	/*****************
	 *  Constructor  *
	 *****************/
	public HandCannon(float xpos, float ypos) 
	{
		super(xpos, ypos, HANDCANNON_WIDTH, HANDCANNON_HEIGHT, Asset.handCannon, HANDCANNON_AMMO_CAPACITY, HANDCANNON_VELOCITY );
		sr = new SecureRandom();
		
	}
	
	
	/** FUNCTIONS **/
	
	public void fire( float xTarget, float yTarget ) 
	{
		double vx = 0;
		double vy = 0;
		
		//Gets the middle position
		double xPosition = ( xPos * Tile.TILEWIDTH ) + ( width / 2);
		double yPosition = ( yPos * Tile.TILEHEIGHT ) + ( height / 2);
		
		//Find the distance between the mouse and gun
		double xDiff = xTarget - xPosition;
		double yDiff = yTarget - yPosition;
		
		//Finds the angle between both points
		double angle = Math.atan2( yDiff, xDiff );
		
		
		//Separate the angle into cos(x)/sin(y) components 
		//Then calculate the velocity vectors based on the max velocity
		//everything is calculated in radians
	    vx = Math.cos( angle ) * maxVelocity;
	    vy = Math.sin( angle ) * maxVelocity;

		projectiles.add( new HandcannonProjectile( xPos, yPos, (float) vx, (float) vy ) );
		
		
	}
}
