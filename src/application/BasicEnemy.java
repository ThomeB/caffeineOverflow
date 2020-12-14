package application;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class BasicEnemy extends Enemy
{
	public static final int ENEMY_HP = 50;
	public static final int ENEMY_STR = 3;
	public static final int ENEMY_DEF = 0;
	
	public static final float ENEMY_SPEED = 0.02f;
	public static final float ENEMY_WIDTH = 64;
	public static final float ENEMY_HEIGHT = 64;
	public static final float ENEMY_AGGRO_RANGE = 6;
	
	public static final double ENEMY_MELEE_TIMER = .8;
	
	private Image[] walkLeftImages;
	private Image[] walkRightImages;
	private Image[] attackImages;
	private Image[] idleImages;
	private int imgSelect;
	
	public BasicEnemy(float xPos, float yPos ) 
	{
		super( ENEMY_HP, ENEMY_STR, ENEMY_DEF, xPos, yPos, ENEMY_WIDTH, ENEMY_HEIGHT, ENEMY_SPEED, Asset.ghostAlive, ENEMY_MELEE_TIMER, ENEMY_AGGRO_RANGE);
		
		imgSelect = 0;
		walkLeftImages = Asset.zombieWalkLeft;
		walkRightImages = Asset.zombieWalkRight;
		attackImages = Asset.zombieAttack;
		idleImages = Asset.zombieIdle;
	
	}
	
	public void render(GraphicsContext gc) {
		
		imgSelect++;
		
		if( imgSelect > walkLeftImages.length - 1 )
			imgSelect = 0;
		
		//See which image should be rendered
		if( movingLeft )
		{
			img = walkLeftImages[ imgSelect ];
		}
		else if(movingRight)
		{
			img = walkRightImages[ imgSelect ];
		}
		else if(attacking)
		{
			if( imgSelect > attackImages.length - 1 )
				imgSelect = 0;
			
			img = attackImages[ imgSelect ];
		}
		else
		{
			if( imgSelect > idleImages.length - 1 )
				imgSelect = 0;
			
			img = idleImages[ imgSelect ];
		}
		
		gc.drawImage(img, xPos *Tile.TILEWIDTH - Camera.xOffset, yPos*Tile.TILEHEIGHT - Camera.yOffset, width, height);
		
		if( tookDmg )
		{
			gc.setFill( Color.RED );
			gc.fillText( "" + dmgTaken, xPos * Tile.TILEWIDTH - Camera.xOffset, yPos * Tile.TILEHEIGHT - Camera.yOffset);
			gc.setFill(Color.BLACK);
		}
	}//close render method

}
