package application;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Projectile extends GameObject
{
	protected float initialXPos;
	protected float initialYPos;
	protected float maxVelocity;
	protected float xVelocity;
	protected float yVelocity;
	
	protected int damage;


	public Projectile(float xpos, float ypos, float xVelocity, float yVelocity, float width, float height, Image img, int damage ) 
	{
		super(xpos, ypos, width, height, img);
		
		this.initialXPos = xpos;
		this.initialYPos = ypos;
		this.xVelocity = xVelocity;
		this.yVelocity = yVelocity;
		this.damage = damage;
		
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