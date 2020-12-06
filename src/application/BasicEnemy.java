package application;

public class BasicEnemy extends Enemy
{
	public static final int ENEMY_HP = 50;
	public static final int ENEMY_STR = 3;
	public static final int ENEMY_DEF = 0;
	
	public static final float ENEMY_SPEED = 0.05f;
	public static final float ENEMY_WIDTH = 64;
	public static final float ENEMY_HEIGHT = 64;
	public static final float ENEMY_AGGRO_RANGE = 5;
	
	public static final double ENEMY_MELEE_TIMER = 3;
	
	
	public BasicEnemy(float xPos, float yPos ) 
	{
		super( ENEMY_HP, ENEMY_STR, ENEMY_DEF, xPos, yPos, ENEMY_WIDTH, ENEMY_HEIGHT, ENEMY_SPEED, Asset.ghostAlive, ENEMY_MELEE_TIMER, ENEMY_AGGRO_RANGE);
	
	}

}
