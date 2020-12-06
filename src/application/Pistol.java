package application;

import javafx.scene.canvas.GraphicsContext;

//not much to this class
public class Pistol extends Gun{
	
	public static final float PISTOL_HEIGHT = 20;
	public static final float PISTOL_WIDTH = 30;
	public static final int PISTOL_AMMO_CAPACITY = 30;
	public static final float PISTOL_VELOCITY = .3f;
	
	/*****************
	 *  Constructor  *
	 *****************/
	public Pistol(float xpos, float ypos) 
	{
		super(xpos, ypos, PISTOL_WIDTH, PISTOL_HEIGHT, Asset.pistol, PISTOL_AMMO_CAPACITY, PISTOL_VELOCITY );
		
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

		projectiles.add( new PistolProjectile( xPos, yPos, (float) vx, (float) vy ) );
		
		
		angle -= 1.8;
		
		//Slowly increase our angle, to fire in an arc
		/*
		for( int i = 0; i < 30; i++ )
		{
			angle += .12;
			
			vx = Math.cos( angle ) * maxVelocity;
		    vy = Math.sin( angle ) * maxVelocity;
			projectiles.add( new Projectile( xPos, yPos, (float) vx, (float) vy ) );
		}
		*/
		
		
		
	}



}//close pistol class