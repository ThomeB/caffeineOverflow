package application;


//not much to this class
public class Pistol extends Gun{
	
	public static final float PISTOL_HEIGHT = 30;
	public static final float PISTOL_WIDTH = 60;
	public static final int PISTOL_AMMO_CAPACITY = 30;
	public static final float PISTOL_VELOCITY = .3f;
	public static final double PISTOL_FIRERATE = 0.01;
	public static final String PISTOL_NAME = "Pistol";
	
	/*****************
	 *  Constructor  *
	 *****************/
	public Pistol(float xpos, float ypos) 
	{
		super(xpos, ypos, PISTOL_WIDTH, PISTOL_HEIGHT, Asset.rifleRight, PISTOL_AMMO_CAPACITY, PISTOL_FIRERATE, PISTOL_NAME, PISTOL_VELOCITY );
		
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
		    if( isRightFacing )
	    	{
	    		xPos += .4f;
	    		yPos -= 0f;
	    	}
		    else
	    	{
	    		xPos -= 0f;
	    		yPos -= 0f;
	    	}
			 
		    vx = Math.cos( angle ) * maxVelocity;
		    vy = Math.sin( angle ) * maxVelocity;
	
			projectiles.add( new PistolProjectile( xPos, yPos, (float) vx, (float) vy ) );
			
		    vx = Math.cos( angle ) * (maxVelocity - .05);
		    vy = Math.sin( angle ) * (maxVelocity - .05);
			projectiles.add( new PistolProjectile( xPos, yPos, (float) vx, (float) vy ) );
			
			vx = Math.cos( angle ) * (maxVelocity - .1);
			vy = Math.sin( angle ) * (maxVelocity - .1);
			projectiles.add( new PistolProjectile( xPos, yPos, (float) vx, (float) vy ) );
		}
		
		
	}



}//close pistol class