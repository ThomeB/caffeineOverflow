package application;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class HandcannonProjectile extends Projectile
{
	public static final float HANDCANNON_PROJECTILE_WIDTH = 180;
	public static final float HANDCANNON_PROJECTILE_HEIGHT = 180;
	public static final int HANDCANNON_PROJECTILE_DAMAGE = 5;
	
	private Image[] waterBullets;

	public HandcannonProjectile(float xpos, float ypos, float xVelocity, float yVelocity ) 
	{
		super(xpos, ypos - .5f, xVelocity, yVelocity, HANDCANNON_PROJECTILE_WIDTH, HANDCANNON_PROJECTILE_HEIGHT, Asset.bullet, HANDCANNON_PROJECTILE_DAMAGE);
		
		waterBullets = Asset.waterBullet;
		imgSelect = 0;
	}
	
	public void render( GraphicsContext gc )
	{
		imgSelect++;
		
		if( imgSelect > ( waterBullets.length - 1 ) * 40 )
		{
			imgSelect = 0;
		}
		
		img = waterBullets[ imgSelect / 40 ];
		
		width += 1;
		height += 1;
		
		gc.drawImage(img, xPos *Tile.TILEWIDTH - Camera.xOffset, yPos*Tile.TILEHEIGHT - Camera.yOffset, width, height);
	}

}
