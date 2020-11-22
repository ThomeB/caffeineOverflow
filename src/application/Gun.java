package application;

import java.util.ArrayList;


import javafx.scene.canvas.GraphicsContext;


public class Gun extends Interactable 
{
	
	public static final int DEFAULT_AMMO_CAPACITY = 30;
	public static final float DEFAULT_GUN_VELOCITY = 0.06f;
	public static final float DEFAULT_GUN_HEIGHT = 20;
	public static final float DEFAULT_GUN_WIDTH = 30;
	
	protected int ammoCapacity;
	protected float maxVelocity;
	protected ArrayList<Projectile> projectiles;
	
	/** CONSTRUCTOR **/
		
	public Gun(float xpos, float ypos ) 
	{
		super( xpos, ypos );
		
		height = DEFAULT_GUN_HEIGHT;
		width = DEFAULT_GUN_WIDTH;
		ammoCapacity = DEFAULT_AMMO_CAPACITY;
		maxVelocity = DEFAULT_GUN_VELOCITY;
		img = Asset.pistol;
		projectiles = new ArrayList<Projectile>();
		
	}
	
	/** FUNCTIONS **/
	public void update()
	{
		for( int i = 0; i < projectiles.size(); i++ )
		{
			Projectile p = projectiles.get( i );
			
			if( p != null )
			{
				p.update();
			}
			
			if( Utility.getDistance( this, p) > 8 )
			{
				projectiles.remove( i );
			}
		}
	}
	
	public void render( GraphicsContext gc )
	{
		gc.drawImage(img, xPos *Tile.TILEWIDTH - Camera.xOffset, yPos*Tile.TILEHEIGHT - Camera.yOffset, width, height);
		
		
		
		//Render all bullets that have been fired
		for( int i = 0; i < projectiles.size(); i++ )
		{
			Projectile p = projectiles.get( i );
			
			if( p != null )
			{
				p.render( gc );
			}
			
		}
	}
	
	
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

		projectiles.add( new Projectile( xPos, yPos, (float) vx, (float) vy ) );
		
		
		angle -= 1.8;
		
		//Slowly increase our angle, to fire in an arc
		for( int i = 0; i < 30; i++ )
		{
			angle += .12;
			
			vx = Math.cos( angle ) * maxVelocity;
		    vy = Math.sin( angle ) * maxVelocity;
			projectiles.add( new Projectile( xPos, yPos, (float) vx, (float) vy ) );
		}
		
		
		
	}


	@Override
	public void pickup() 
	{
		
		
	}
	
	
	
	/** GETTERS AND SETTERS **/
	
}
