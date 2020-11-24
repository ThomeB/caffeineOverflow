package application;

import java.util.ArrayList;


import javafx.scene.canvas.GraphicsContext;


public class Gun extends Interactable 
{
	
	public static final int DEFAULT_AMMO_CAPACITY = 30;
	public static final float DEFAULT_GUN_VELOCITY = 0.15f;
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
	public void update(Map map)
	{
		for( int i = 0; i < projectiles.size(); i++ )
		{
			Projectile p = projectiles.get( i );
			
			if( p != null )
			{
				p.update();
			}
			
			//first check: should we despawn from distance?
			if( Utility.getDistance( p.initialXPos, p.initialYPos, p.xPos, p.yPos ) > 6 )
			{
				projectiles.remove( i );
			}//then check: did we hit an enemy?
			else {
				boolean didHit = false;
				for (Enemy enemy : Game.enemies) {
					//if the enemy exists, is alive, and we collide with it, then remove the projectile and damage the enemy
					if ( enemy != null && enemy.isAlive() && Utility.collidesWithGameObject(p, enemy)) {
						enemy.takeDmg(p);//takeDmg is in Entity
						projectiles.remove(i);
						didHit = true;
						break;
					}
				}
				if (!didHit) {//didn't hit an enemy? Let's see if we are in a wall
					String tileOn = map.getTile((int)p.xPos, (int)p.yPos);//potential room for error here if we are out of bounds for some reason
					if (!tileOn.equals(".")) {//if we are not in one of these tiles, then remove the projectile
						projectiles.remove(i);
					}
				}
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


	@Override
	public void pickup() 
	{
		
		
	}
	
	
	
	/** GETTERS AND SETTERS **/
	
}