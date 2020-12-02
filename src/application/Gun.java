package application;

import java.util.ArrayList;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public abstract class Gun extends Interactable 
{
	protected int ammoCapacity;
	protected float maxVelocity;
	protected ArrayList<Projectile> projectiles;
	
	/** CONSTRUCTOR **/
		
	public Gun(float xpos, float ypos, float width, float height, Image image, int ammoCapacity, float maxVelocity ) 
	{
		super( xpos, ypos , width, height, image);
		
		this.ammoCapacity = ammoCapacity;
		this.maxVelocity = maxVelocity;
		projectiles = new ArrayList<Projectile>();
		
	}
	
	/** FUNCTIONS **/
	public abstract void update(Map map);
	
	public abstract void render( GraphicsContext gc );
	
	
	public abstract void fire( float xTarget, float yTarget );


	@Override
	public abstract void pickup(Character hero);
	
	
	
	/** GETTERS AND SETTERS **/
	
}