package application;

import javafx.scene.image.Image;

public class Enemy extends Entity{
	//class variables
	private float aggroRange;
	
	/******************
	 * 	Constructors  *
	 ******************/
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
