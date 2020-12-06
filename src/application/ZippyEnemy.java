package application;

public class ZippyEnemy extends Enemy
{
	public static final int ZIPPY_HP = 50;
	public static final int ZIPPY_STR = 3;
	public static final int ZIPPY_DEF = 0;
	
	public static final float ZIPPY_SPEED = 0.03f;
	public static final float ZIPPY_WIDTH = 64;
	public static final float ZIPPY_HEIGHT = 90;
	public static final float ZIPPY_AGGRO_RANGE = 10;
	
	public static final double ZIPPY_MELEE_TIMER = 0.5;
	
	
	public ZippyEnemy(float xPos, float yPos ) 
	{
		super( ZIPPY_HP, ZIPPY_STR, ZIPPY_DEF, xPos, yPos, ZIPPY_WIDTH, ZIPPY_HEIGHT, ZIPPY_SPEED, Asset.dog, ZIPPY_MELEE_TIMER, ZIPPY_AGGRO_RANGE);
	
	}

}
