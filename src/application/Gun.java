package application;

import java.util.ArrayList;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public abstract class Gun extends Interactable 
{
	protected int ammoCapacity;
	protected float maxVelocity;
	protected String gunName;
	protected Timer fireRateTimer;
	
	protected ArrayList<Projectile> projectiles;
	protected boolean isRightFacing;
	
	/** CONSTRUCTOR **/
		
	public Gun(float xpos, float ypos, float width, float height, Image image, int ammoCapacity, double fireRate, String gunName, float maxVelocity ) 
	{
		super( xpos, ypos , width, height, image);
		
		this.ammoCapacity = ammoCapacity;
		this.maxVelocity = maxVelocity;
		this.gunName = gunName;
		projectiles = new ArrayList<Projectile>();
		fireRateTimer = new Timer( fireRate );
		
	}
	
	public abstract void fire( float xTarget, float yTarget );
	
	/** FUNCTIONS **/
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
	};
	

	@Override
	public void pickup(Character hero)
	{
		if (!Game.pickupDebounceTimer.isOnCooldown()) {
		
		//if (!despawn) { //if we are not marked for deletion and the gun the character is holding is not what we are trying to pick up
			boolean canPickUp = false;
			
			if (hero.getGun() == null || !hero.getGun().equals(this))
				canPickUp = true;
			
			if (canPickUp) {
				hero.swapGun(this);
				Game.pickupDebounceTimer.setOnCooldown( true );
				Game.pickupDebounceTimer.reset();
			}
		}
		//}
	};
	
	
	
	/** GETTERS AND SETTERS **/
	
}