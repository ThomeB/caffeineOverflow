package application;

import java.security.SecureRandom;

public class Shotgun extends Gun
{
	public static final float SHOTGUN_HEIGHT = 20;
	public static final float SHOTGUN_WIDTH = 40;
	public static final int SHOTGUN_AMMO_CAPACITY = 30;
	public static final float SHOTGUN_VELOCITY = .1f;
	public static final double SHOTGUN_FIRERATE = 0.7;
	public static final String SHOTGUN_NAME = "Shotgun";
	
	private SecureRandom sr;
	
	/*****************
	 *  Constructor  *
	 *****************/
	public Shotgun(float xpos, float ypos) 
	{
		super(xpos, ypos, SHOTGUN_WIDTH, SHOTGUN_HEIGHT, Asset.shotgunRight, SHOTGUN_AMMO_CAPACITY, SHOTGUN_FIRERATE, SHOTGUN_NAME, SHOTGUN_VELOCITY );
		sr = new SecureRandom();
		
	}
	
	
	/** FUNCTIONS **/
	
	public void fire( float xTarget, float yTarget ) 
	{
		
		if( !fireRateTimer.isOnCooldown() )
		{
			fireRateTimer.setOnCooldown( true );
			fireRateTimer.reset();
			
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
		    
		    if( isRightFacing )
		    {
		    	xPos += .5f;
		    	yPos -= .2f;
		    }
		    else
		    {
		    	xPos -= 0.3f;
		    	yPos -= .1f;
		    }
		
			projectiles.add( new ShotgunProjectile( xPos, yPos, (float) vx, (float) vy ) );
			
			
			angle -= 0.26175;
			
			//Slowly increase our angle, to fire in an arc
			for( int i = 0; i < 30; i++ )
			{
				double variation = -.05 + sr.nextDouble() * .07;
				angle += .01745;
				
				vx = Math.cos( angle ) * (maxVelocity + variation);
			    vy = Math.sin( angle ) * (maxVelocity + variation);
				projectiles.add( new ShotgunProjectile( xPos, yPos, (float) vx, (float) vy ) );
			}
		}
		
		
		
		
	}


}
