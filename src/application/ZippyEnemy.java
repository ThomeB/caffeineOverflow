package application;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class ZippyEnemy extends Enemy
{
	public static final int ZIPPY_HP = 40;
	public static final int ZIPPY_STR = 8;
	public static final int ZIPPY_DEF = 0;
	
	public static final float ZIPPY_SPEED = 0.06f;
	public static final float ZIPPY_WIDTH = 50;
	public static final float ZIPPY_HEIGHT = 70;
	public static final float ZIPPY_AGGRO_RANGE = 9;
	
	public static final double ZIPPY_MELEE_TIMER = 0.5;
	
	private Image[] attackImages;
	private Image[] walkRightImages;
	private Image[] walkLeftImages;
	private Image[] idleImages;
	
	public ZippyEnemy(float xPos, float yPos ) 
	{
		super( ZIPPY_HP, ZIPPY_STR, ZIPPY_DEF, xPos, yPos, ZIPPY_WIDTH, ZIPPY_HEIGHT, ZIPPY_SPEED, Asset.zippyAttack[0], ZIPPY_MELEE_TIMER, ZIPPY_AGGRO_RANGE);
		
		attackImages = Asset.zippyAttack;
		walkRightImages = Asset.zippyWalkRight;
		walkLeftImages = Asset.zippyWalkLeft;
		idleImages = Asset.zippyIdle;
	
	}
	
	public void render(GraphicsContext gc) 
	{
			
			imgSelect++;
			int duration = 20;
			
			if( imgSelect > (walkLeftImages.length - 1) * duration )
				imgSelect = 0;
			
			//See which image should be rendered
			if( movingLeft )
			{
				img = walkLeftImages[ imgSelect / duration ];
			}
			else if(movingRight)
			{
				img = walkRightImages[ imgSelect / duration ];
			}
			else if(attacking)
			{
				if( imgSelect > (attackImages.length - 1) * duration )
					imgSelect = 0;
				
				img = attackImages[ imgSelect / duration ];
			}
			else
			{
				if( imgSelect > (idleImages.length - 1) * (duration) )
					imgSelect = 0;
				
				img = idleImages[ imgSelect / (duration) ];
			}
			
			
			gc.drawImage(img, xPos *Tile.TILEWIDTH - Camera.xOffset, yPos*Tile.TILEHEIGHT - Camera.yOffset, width, height);
			
			if( tookDmg )
			{
				gc.setFill( Color.RED );
				gc.fillText( "" + dmgTaken, xPos * Tile.TILEWIDTH - Camera.xOffset, yPos * Tile.TILEHEIGHT - Camera.yOffset);
				gc.setFill(Color.BLACK);
			}
	}

}
