package application;

import javafx.scene.canvas.GraphicsContext;

public class Projectile extends GameObject
{
	public static final float DEFAULT_PROJECTILE_WIDTH = 50;
	public static final float DEFAULT_PROJECTILE_HEIGHT = 30;
	public static final int DEFAULT_PROJECTILE_DAMAGE = 5;

	
	protected float maxVelocity;
	protected float xVelocity;
	protected float yVelocity;
	
	
	
	protected int damage;


	public Projectile(float xpos, float ypos, float xVelocity, float yVelocity ) 
	{
		super(xpos, ypos, DEFAULT_PROJECTILE_WIDTH, DEFAULT_PROJECTILE_HEIGHT, Asset.bullet);
		
		this.xVelocity = xVelocity;
		this.yVelocity = yVelocity;
		damage = DEFAULT_PROJECTILE_DAMAGE;
		
	}
	
	
	public void update()
	{
		
		xPos += xVelocity;
		yPos += yVelocity;
		hitBox.setX(xPos*Tile.TILEWIDTH);
		hitBox.setY(yPos*Tile.TILEHEIGHT);
		
	}
	
	public void render( GraphicsContext gc )
	{
		gc.drawImage(img, xPos *Tile.TILEWIDTH - Camera.xOffset, yPos*Tile.TILEHEIGHT - Camera.yOffset, width, height);
	}

}