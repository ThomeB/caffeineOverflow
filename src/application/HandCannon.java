package application;


public class HandCannon extends Gun
{
	public static final float HANDCANNON_HEIGHT = 60;
	public static final float HANDCANNON_WIDTH = 90;
	public static final int HANDCANNON_AMMO_CAPACITY = 30;
	public static final float HANDCANNON_VELOCITY = .05f;
	public static final double HANDCANNON_FIRERATE = 1.0;
	public static final String HANDCANNON_NAME = "Hand Cannon";
	
	
	/*****************
	 *  Constructor  *
	 *****************/
	public HandCannon(float xpos, float ypos) 
	{
		super(xpos, ypos, HANDCANNON_WIDTH, HANDCANNON_HEIGHT, Asset.handCannonRight, HANDCANNON_AMMO_CAPACITY, HANDCANNON_FIRERATE, HANDCANNON_NAME, HANDCANNON_VELOCITY );
		
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
		    
		    //Changes where projectiles will shoot from
		    if( isRightFacing )
		    {
		    	projectiles.add( new HandcannonProjectile( xPos, yPos - .1f, (float) vx, (float) vy ) );
		    }
		    else
		    {
		    	projectiles.add( new HandcannonProjectile( xPos - .9f, yPos - .1f, (float) vx, (float) vy ) );
		    }
			
		}
		
		
	}
	
	public void update(Map map, boolean rightFacing)
	{
		isRightFacing = rightFacing;
		//Used to see if we can shoot again
		fireRateTimer.tick();
		
		
		for( int i = 0; i < projectiles.size(); i++ )
		{
			Projectile p = projectiles.get( i );
			
			if( p != null )
			{
				p.update();
			}
			
			//first check: should we despawn from distance?
			if( Utility.getDistance( p.initialXPos, p.initialYPos, p.xPos, p.yPos ) > 5 )
			{
				projectiles.remove( i );
			}//then check: did we hit an enemy?
			else {
				boolean didHit = false;
				for (Entity enemy : Game.enemies) {
					//if the enemy exists, is alive, and we collide with it, then remove the projectile and damage the enemy
					if ( enemy != null && enemy.isAlive() && Utility.collidesWithGameObject(p, enemy)) {
						enemy.takeDmg(p);//takeDmg is in Entity
						//projectiles.remove(i);
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
	};
}
