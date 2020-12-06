package application;

public class BruteEnemy extends Enemy
{
	public static final int BRUTE_HP = 100;
	public static final int BRUTE_STR = 30;
	public static final int BRUTE_DEF = 0;
	
	public static final float BRUTE_SPEED = 0.01f;
	public static final float BRUTE_WIDTH = 140;
	public static final float BRUTE_HEIGHT = 140;
	public static final float BRUTE_AGGRO_RANGE = 10;
	
	public static final double BRUTE_MELEE_TIMER = 4;
	
	
	public BruteEnemy(float xPos, float yPos ) 
	{
		super( BRUTE_HP, BRUTE_STR, BRUTE_DEF, xPos, yPos, BRUTE_WIDTH, BRUTE_HEIGHT, BRUTE_SPEED, Asset.preacher, BRUTE_MELEE_TIMER, BRUTE_AGGRO_RANGE);
	}
	

}
