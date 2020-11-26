package application;

import java.util.ArrayList;

import javafx.scene.image.Image;

public class Enemy extends Entity{
	//class variables
	public static final int ENEMY_HP = 50;
	public static final int ENEMY_STR = 3;
	public static final int ENEMY_DEF = 0;
	
	public static final float ENEMY_SPEED = 0.05f;
	public static final float ENEMY_WIDTH = 64;
	public static final float ENEMY_HEIGHT = 64;
	public static final float ENEMY_AGGRO_RANGE = 5;
	
	public static Map map; //one time setup, could've been in the constructor but this might make things quicker
	
	//path related variables
	private ArrayList<float[]> path;
	private int iterator;
	private float [] goalCoords = new float [2];
	
	/******************
	 * 	Constructors  *
	 ******************/
	public Enemy( float xPos, float yPos)
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
		this.xPos = xPos;
		this.yPos = yPos;
		this.moveBox();
	}

	@Override
	public void attack() {
		//guns and things
	}
	
	public void update(Character character) {
		double distance = Utility.getDistance(character, this);
		if (distance < ENEMY_AGGRO_RANGE) {//if we are within the range of sight
			boolean usePath = false;
			if (distance > 2) {//we have to justify using path finding. If we are too close then don't use it.
				if (path == null) { //if we don't have a path, make one.
					float [] myCoords = {xPos, yPos};
					float [] charCoords = {character.xPos, character.yPos};
					ArrayList<float[]> path = Utility.dStar(myCoords, charCoords, map); //calls the algorithm, does the math, returns an array of points (starting with end point, so iterate backwards)
					if (path!=null && path.size()>1) { //if our path has enough data
						usePath = true;
						iterator = path.size()-2; //basically, get the first relevant point
						goalCoords = path.get(iterator); 
						this.path = path;
						
					}
					else {
						goalCoords[0] = charCoords[0];
						goalCoords[1] = charCoords[1];
					}
				}else {//if we've already calculated a path, use it
					usePath = true;
					double distanceToGoal = Utility.getDistance(this, goalCoords);
					if (distanceToGoal < .5) { //if we are close to the point we were headed to, move to next point along our path
						iterator--;
						goalCoords = path.get(iterator);
					}
					if (iterator == 0) { //once we reach the end of our path, clear it
						path = null;
					}
				}
			}else if (distance > .5) { //if we are close to the character then just move directly towards it
				goalCoords[0] = character.xPos;
				goalCoords[1] = character.yPos;
				path = null;
			}else { //if we are practically touching the character, don't move at all (prevents jitter)
				goalCoords[0] = xPos;
				goalCoords[1] = yPos;
			}
			
			float dx = goalCoords[0] - xPos; //get difference in x position
			float dy = goalCoords[1] - yPos; //get difference in y position
			float [] direction = {dx, dy}; //turn that into a vector
			float magnitude = (float) Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2)); //get the magnitude of the vector
			if (magnitude > 0) { //if magnitude == 0, we are not moving, so we can ignore
				direction[0] = (dx / magnitude) * ENEMY_SPEED; //normalize the vector, then multiply it by the speed
				direction[1] = (dy / magnitude) * ENEMY_SPEED; //same but for y
				if (!usePath) { //we can ignore walls if using paths because our paths already check for walls
					String xTile = map.getTile((int) (xPos + direction[0]), (int) yPos); //check our x updated position to see if it's bumping into a wall
					String yTile = map.getTile((int) xPos, (int) (yPos + direction[1])); //check our y updated position to see if it's bumping into a wall
					if (!xTile.equals(".")&&!xTile.equals("D")) { //if we are walking into something like a wall, stop movement in that direction
						direction[0] = 0;
					}
					if (!yTile.equals(".")&&!yTile.equals("D")) { //same but for Y coordinate
						direction[0] = 0;
					}
				}
				movement(xPos+direction[0],yPos+direction[1]); //call the movement function (sets x + y positions, updates hit box)
			}
			
		}
	}

}//close of enemy