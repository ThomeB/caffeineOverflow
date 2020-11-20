package application;

import javafx.scene.image.Image;

public class Character extends Entity {
	//class variables
	private String name;
	private int keys;
	private float velocityX = 0;
	private float velocityY = 0;
	private final float ACCELERATION = 1;//0.1f;
	
	private Camera camera;
	
	/******************
	 * 	Constructors  *
	 ******************/
	public Character(int hp, int str, int def, float xpos, float ypos, float height, float width, String name, float walkSpeed, Image img, Camera camera ) {
		super(hp, str, def, xpos, ypos, height, width, img);
		this.name = name;
		this.setWalkSpeed(walkSpeed);
		keys = 0;
		this.camera = camera;
		
	}
	
	/*******************************
	 *  Character Utility Methods  *  
	 * 	and overrides			   *
	 *******************************/
	//pickup and heal will be carried out by interactable
	
	public void movement(float xPos, float yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.moveBox(); //located in entity
	}//close movement

	@Override
	public void attack() {
		if(/*this.wpnEquip != null*/ true) {
			//shoot projectile
		}else {
			//punch
		}
	}//close attack
	
	private void moveInput(boolean [] keysPressed,Map map) {
		//explanation: movement is gradually increased as the character moves continuously in one direction
		//the acceleration variable controls how fast the character accelerates to its max speed value, which is the walkspeed variable
		//lower values for acceleration make the character feel more slippery. If you opt to use higher values for acceleration it may be smart to reduce the "walkspeed" value
		//setting acceleration to 1 essentially sets the character to always move at walkspeed (with no buildup)
		
		// calculations for character movement go here
		float speed = getWalkSpeed();
		//looks scary but we are just adding move directions based on which keys are pressed
		float dx = (keysPressed[1] ? -1 : 0) + (keysPressed[3] ? 1 : 0);
		float dy = (keysPressed[0] ? -1 : 0) + (keysPressed[2] ? 1 : 0);
		float moveX = Math.abs(dx) * velocityX * speed;
		float moveY = Math.abs(dy) * velocityY * speed;
		boolean canMove = true;
		//perform a deep copy so we can modify the hitbox table without changing the actual hitbox (in case the move fails)
		float [][] newHitbox = (float[][]) Utility.deepCopy(hitBoxCorners);
		for (int i = 0; i < newHitbox.length; i++) {
			newHitbox[i][0] += moveX;
			newHitbox[i][1] += moveY;
		}
		
		//perform a scan for walls in all directions around the character (rather than checking every single tile on the map for collision)
		for (int i = -2; i < 3; i++) {
			for (int j = -2; j < 3; j++) {
				int coordX = (int) (xPos + i);
				int coordY = (int) (yPos + j);
				if (coordX < 0)
					coordX = 0;
				if (coordX > map.getWidth()-1)
					coordX = map.getWidth()-1;
				if (coordY < 0)
					coordY = 0;
				if (coordY > map.getHeight()-1)
					coordY = map.getHeight()-1;
				String tileType = map.getTile( coordX , coordY );
				if (!tileType.equals(".") && !tileType.equals("D")) {// we ignore certain wall types for collision checking
					canMove = !Utility.collidesWithWall(newHitbox, coordX, coordY); //if we don't collide with a wall, we can still move (inverse relationship)
					if (!canMove) {
						break;
					}
				}
			}
			if (!canMove)
				break;
		}
		if (canMove) {
			movement(xPos + moveX, yPos + moveY);
			camera.centerOnCharacter( this );
		}
		else {
			dx = 0;
			dy = 0;
		}
		
		//velocity handling (very basic and not based on real life)
		//intention: character doesn't immediately go fast, allows for more control of character
		//if this doesn't work/feel right, then it can be removed by simply making velX and velY the same as dx and dy immediately after dx and dy are declared
		// OR setting acceleration to be 1.0f (speed may need to be reduced)
		// x-coordinates
		float accel = ACCELERATION * speed;
		if (dx > velocityX && dx != 0)
			setVelocityX(velocityX+accel);
		else if (dx < velocityX && dx != 0)
			setVelocityX(velocityX-accel);
		else if (dx == 0)
			setVelocityX(velocityX/2);
		// y-coordinates
		if (dy > velocityY && dy != 0)
			setVelocityY(velocityY+accel);
		else if (dy < velocityY && dy != 0)
			setVelocityY(velocityY-accel);
		else if (dy == 0)
			setVelocityY(velocityY/2);
	}
	
	public float getVelocityX() {
		return velocityX;
	}
	public void setVelocityX(float n) {
		if (n > 1)
			n = 1;
		else if (n < -1)
			n = -1;	
		velocityX = n;
	}
	public float getVelocityY() {
		return velocityY;
	}
	public void setVelocityY(float n) {
		if (n > 1)
			n = 1;
		else if (n < -1)
			n = -1;
		velocityY = n;
	}
	
	public void update(boolean [] keysPressed, Map map) {
		moveInput(keysPressed, map);
		//keysPressed[4] is for interaction (F key)
		//put interaction function here, maybe?
	}
	
}//close character