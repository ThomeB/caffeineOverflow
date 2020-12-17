package application;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class BossBigProjectile extends Projectile
{
	public static final float BOSS_BIG_PROJECTILE_WIDTH = 100;
	public static final float BOSS_BIG_PROJECTILE_HEIGHT = 100;
	public static final int BOSS_BIG_PROJECTILE_DAMAGE = 100;

	private Image[] waterBullets;
	
	public BossBigProjectile(float xpos, float ypos, float xVelocity, float yVelocity ) 
	{
		super(xpos, ypos, xVelocity, yVelocity, BOSS_BIG_PROJECTILE_WIDTH, BOSS_BIG_PROJECTILE_HEIGHT, Asset.bullet2, BOSS_BIG_PROJECTILE_DAMAGE);
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
		
		gc.drawImage(img, xPos *Tile.TILEWIDTH - Camera.xOffset, yPos*Tile.TILEHEIGHT - Camera.yOffset, width, height);
	}
}
