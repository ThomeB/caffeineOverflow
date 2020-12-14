package application;

import java.util.ArrayList;

import javafx.scene.image.Image;

public abstract class Enemy extends Entity{
	//class variables
	
	
	public static Map map; //one time setup, could've been in the constructor but this might make things quicker
	
	protected Timer meleeAbilityTimer;
	protected float aggroRange;
	
	//path related variables
	private ArrayList<float[]> path;
	private int iterator;
	private float [] goalCoords = new float [2];
	
	protected boolean movingLeft;
	protected boolean movingRight;
	protected boolean attacking;
	protected boolean idle;
	
	/******************
	 * 	Constructors  *
	 ******************/
	public Enemy( int health, int str, int def, float xPos, float yPos, float width, float height, float speed, Image img, double timerDuration, float aggroRange )
	{
		super( health, str, def,  xPos, yPos, width, height, speed, img );
		
		meleeAbilityTimer = new Timer( timerDuration );
		this.aggroRange = aggroRange;
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
	
	//what to do when enemy dies
	public void death() {
	if (Math.random() > .9) {
		HealthPack healthPack = new HealthPack( xPos, yPos );
		Game.interactables.add( healthPack );
	}
	}
	
	public boolean isATrap() {
		return false;
	}
	
	@Override
	public void update(Character character) {
		
		//Tick our ability timer and dmg taken timer
		meleeAbilityTimer.tick();
		updateDmgTakenTimer();
		
		//Zombie will be idle if not near player
		idle = true;
		movingLeft = false;
		movingRight = false;
		attacking = false;
		
		double distance = Utility.getDistance(character, this);
		if (distance < aggroRange && character.isAlive() ) {//if we are within the range of sight
			
			//Check to see if use left or right images array
			if( character.xPos < xPos ) {
				movingLeft = true;
				movingRight = false;
				attacking = false;
				idle = false;
			}
			else {
				movingRight = true;
				movingLeft = false;
				attacking = false;
				idle = false;
			}
			
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
				
				attacking = true;
				movingLeft = false;
				movingRight = false;
				idle = false;
				
				//enemies are also close enough to damage the character
				if( !meleeAbilityTimer.isOnCooldown() )
				{
					character.takeDmg( this );
					
					//set ability on cooldown as well
					meleeAbilityTimer.setOnCooldown( true );
				}
			}
			
			float dx = goalCoords[0] - xPos; //get difference in x position
			float dy = goalCoords[1] - yPos; //get difference in y position
			float [] direction = {dx, dy}; //turn that into a vector
			float magnitude = (float) Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2)); //get the magnitude of the vector
			if (magnitude > 0) { //if magnitude == 0, we are not moving, so we can ignore
				direction[0] = (dx / magnitude) * walkSpeed; //normalize the vector, then multiply it by the speed
				direction[1] = (dy / magnitude) * walkSpeed; //same but for y
				if (!usePath) { //we can ignore walls if using paths because our paths already check for walls
					String xTile = map.getTile((int) (xPos + direction[0]), (int) yPos); //check our x updated position to see if it's bumping into a wall
					String yTile = map.getTile((int) xPos, (int) (yPos + direction[1])); //check our y updated position to see if it's bumping into a wall
					if (!xTile.equals(".")&&!xTile.equals("D")) { //if we are walking into something like a wall, stop movement in that direction
						direction[0] = 0;
					}
					if (!yTile.equals(".")&&!yTile.equals("D")) { //same but for Y coordinate
						direction[1] = 0;
					}
				}
				movement(xPos+direction[0],yPos+direction[1]); //call the movement function (sets x + y positions, updates hit box)
			}
			
		}
	}

}//close of enemy