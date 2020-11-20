package application;

import javafx.scene.image.Image;

public class Enemy extends Entity{
	//class variables
	public static final int ENEMY_HP = 50;
	public static final int ENEMY_STR = 3;
	public static final int ENEMY_DEF = 0;
	
	public static final float ENEMY_WIDTH = 64;
	public static final float ENEMY_HEIGHT = 64;
	public static final float ENEMY_AGGRO_RANGE = 5;
	
	
	/******************
	 * 	Constructors  *
	 ******************/
	public Enemy( int xPos, int yPos)
	{
		super( ENEMY_HP, ENEMY_STR, ENEMY_DEF,  xPos, yPos, ENEMY_WIDTH, ENEMY_HEIGHT, Asset.ghostAlive );
	}
	
	public Enemy(int hp, int str, int def, float xpos, float ypos, int height, int width, Image img) {
		super(hp, str, def, xpos, ypos, height, width, img);
		// TODO Auto-generated constructor stub
	}
	

	/*******************************
	 *  Enemy Utility Methods      *  
	 * 	and overrides			   *
	 *******************************/
	@Override
	public void movement(float xPos, float yPos) {
		//something to do with pathfinding
	}

	@Override
	public void attack() {
		//guns and things
	}

}//close of enemy