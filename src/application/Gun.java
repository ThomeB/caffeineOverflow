package application;

import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;

public class Gun extends Interactable 
{
	
	public static final int DEFAULT_AMMO_CAPACITY = 30;
	public static final float DEFAULT_GUN_HEIGHT = 20;
	public static final float DEFAULT_GUN_WIDTH = 30;
	
	protected int ammoCapacity;
	protected ArrayList<Projectile> projectiles;
	
	/** CONSTRUCTOR **/
		
	public Gun(float xpos, float ypos ) 
	{
		super( xpos, ypos );
		
		height = DEFAULT_GUN_HEIGHT;
		width = DEFAULT_GUN_WIDTH;
		ammoCapacity = DEFAULT_AMMO_CAPACITY;
		img = Asset.pistol;
		projectiles = new ArrayList<Projectile>();
		
	}
	
	/** FUNCTIONS **/
	
	public void render( GraphicsContext gc )
	{
		gc.drawImage(img, xPos *Tile.TILEWIDTH - Camera.xOffset, yPos*Tile.TILEHEIGHT - Camera.yOffset, width, height);
		
		for( int i = 0; i < projectiles.size(); i++ )
		{
			if( projectiles.get( i ) != null )
				projectiles.get( i ).render( gc );
		}
	}
	
	public void update()
	{
		for( int i = 0; i < projectiles.size(); i++ )
		{
			if( projectiles.get( i ) != null )
				projectiles.get( i ).update();
		}
	}
	
	
	public void fire( float xTarget, float yTarget ) 
	{
		projectiles.add( new Projectile( xPos, yPos, xTarget, yTarget ) );
	}


	@Override
	public void pickup() 
	{
		
		
	}
	
	/** GETTERS AND SETTERS **/
	
}
