package application;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BruteEnemy extends Enemy
{
	public static final int BRUTE_HP = 100;
	public static final int BRUTE_STR = 30;
	public static final int BRUTE_DEF = 0;
	
	public static final float BRUTE_SPEED = 0.01f;
	public static final float BRUTE_WIDTH = 200;
	public static final float BRUTE_HEIGHT = 200;
	public static final float BRUTE_AGGRO_RANGE = 10;
	
	public static final double BRUTE_MELEE_TIMER = 4;
	
	private Image[] walkLeftImages;
	private Image[] walkRightImages;
	private Image[] attackImages;
	private Image[] idleImages;
	private int imgSelect;
	
	
	public BruteEnemy(float xPos, float yPos ) 
	{
		super( BRUTE_HP, BRUTE_STR, BRUTE_DEF, xPos, yPos, BRUTE_WIDTH, BRUTE_HEIGHT, BRUTE_SPEED, Asset.preacher, BRUTE_MELEE_TIMER, BRUTE_AGGRO_RANGE);
	
		imgSelect = 0;
		walkLeftImages = Asset.bruteWalkLeft;
		walkRightImages = Asset.bruteWalkRight;
		attackImages = Asset.bruteAttack;
		idleImages = Asset.bruteIdle;
		//Need to change hitbox a little bit, because the picture is too big
		hitBox = new Rectangle( xPos * Tile.TILEWIDTH, yPos * Tile.TILEHEIGHT, width - 40, height - 40 );
	}
	
	public void render(GraphicsContext gc) {
			
			imgSelect++;
			
			if( imgSelect > (walkLeftImages.length - 1) * 4 )
				imgSelect = 0;
			
			//See which image should be rendered
			if( movingLeft )
			{
				img = walkLeftImages[ imgSelect / 4 ];
			}
			else if(movingRight)
			{
				img = walkRightImages[ imgSelect / 4 ];
			}
			else if(attacking)
			{
				if( imgSelect > (attackImages.length - 1) * 4 )
					imgSelect = 0;
				
				img = attackImages[ imgSelect / 4 ];
			}
			else
			{
				if( imgSelect > ( idleImages.length - 1 ) * 4 )
					imgSelect = 0;
				
				img = idleImages[ imgSelect / 4 ];
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
