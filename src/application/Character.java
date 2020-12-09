package application;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Character extends Entity {
	
	
	//Default hero values
	public static final float CHARACTER_WIDTH = 64;
	public static final float CHARACTER_HEIGHT = 64;
	public static final float CHARACTER_WALKSPEED = 0.04f;
	public static final String CHARACTER_NAME = "Bob";
	public static final int CHARACTER_HEALTH = 100;
	public static final int CHARACTER_STR = 5;
	public static final int CHARACTER_DEF = 1;
	
	//class variables
	private String name;
	private float velocityX = 0;
	private float velocityY = 0;
	private boolean stopped;
	private boolean leftFacing;
	private Gun gun;
	private final float ACCELERATION = 1;//0.1f;
	private boolean hasKey = false;
	private Camera camera;
	
	/******************
	 * 	Constructors  *
	 ******************/
	public Character( float xpos, float ypos, Camera camera ) {
		
		super(CHARACTER_HEALTH, CHARACTER_STR, CHARACTER_DEF, xpos, ypos, CHARACTER_WIDTH, CHARACTER_HEIGHT, CHARACTER_WALKSPEED, Asset.heroR[0]);
		this.name = CHARACTER_NAME;
		this.camera = camera;
		
		right = Asset.heroR;
		left = Asset.heroL;
		stopped = true;
		leftFacing = false;
		t = new Timer(0.1);
		imgSelect = 0;
		//gun = new Pistol( xPos, yPos );
		
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
	
	public void swapGun(Gun toGet) {
		gun = toGet;
	}
	
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
				if (!tileType.equals(".") && !tileType.equals(",")) {// we ignore certain wall types for collision checking
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
		if(dx == 0 && dy == 0) {
			stopped = true;
		} else {stopped = false;}
		
		if(dx > 0) {
			leftFacing = false;
		} else if (dx < 0) {
			leftFacing = true;
		}
		
		float accel = ACCELERATION * speed;
		if (dx > velocityX && dx != 0) {
			setVelocityX(velocityX+accel);
		}
		else if (dx < velocityX && dx != 0) {
			setVelocityX(velocityX-accel);
		}
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
	
	public boolean getKey() {
		return hasKey;
	}
	public void setKey(boolean keyVal) {
		hasKey = keyVal;
	}
	
	public void update(boolean [] keysPressed, Map map) {
		
		//update damage taken timer
		updateDmgTakenTimer();
		
		moveInput(keysPressed, map);
		//keysPressed[4] is for interaction (F key)
		//put interaction function here, maybe?
		
		if( gun != null )
		{
			if( gun instanceof HandCannon && leftFacing)
			{
				gun.img = Asset.handCannonLeft;
				gun.setxPos( xPos - 0.3f );
				gun.setyPos( yPos + 0.12f );
				gun.update( map );
			} else if (gun instanceof HandCannon) {
				gun.img = Asset.handCannonRight;
				gun.setxPos( xPos + 0.3f );
				gun.setyPos( yPos + 0.12f );
				gun.update( map );
			}
			
			if( gun instanceof Pistol && leftFacing)
			{
				gun.img = Asset.rifleLeft;
				gun.setxPos( xPos - 0.25f );
				gun.setyPos( yPos + 0.12f );
				gun.update( map );
			} else if (gun instanceof Pistol) {
				gun.img = Asset.rifleRight;
				gun.setxPos( xPos + 0.45f );
				gun.setyPos( yPos + 0.12f );
				gun.update( map );
			}
			
			if( gun instanceof Shotgun && leftFacing )
			{
				gun.img = Asset.shotgunLeft;
				gun.setxPos( xPos - 0.6f );
				gun.setyPos( yPos + 0.12f );
				gun.update( map );
			} else if (gun instanceof Shotgun) {
				gun.img = Asset.shotgunRight;
				gun.setxPos( xPos + 0.6f );
				gun.setyPos( yPos + 0.12f );
				gun.update( map );
			}
		}
		
		//If character is dead, change texture
		//if( !this.isAlive() )
			//System.exit(0);
			
	}
	
	public void render( GraphicsContext gc )
	{
		if(velocityX == 0 && velocityY == 0 || stopped) {
			gc.drawImage(img, xPos *Tile.TILEWIDTH - Camera.xOffset, yPos*Tile.TILEHEIGHT - Camera.yOffset, width, height);
		}
		
		if(!leftFacing && !stopped) {
			img = right[0];
			if(!t.isOnCooldown()) {
				imgSelect++;
				if(imgSelect >= right.length)
					imgSelect = 0;
				gc.drawImage(right[imgSelect], xPos *Tile.TILEWIDTH - Camera.xOffset, yPos*Tile.TILEHEIGHT - Camera.yOffset, width, height);
				t.setOnCooldown(true);
			} else {
				gc.drawImage(right[imgSelect], xPos *Tile.TILEWIDTH - Camera.xOffset, yPos*Tile.TILEHEIGHT - Camera.yOffset, width, height);
			}
		}
		
		if(leftFacing && !stopped) {
			img = left[0];
			if(!t.isOnCooldown()) {
				imgSelect++;
				if(imgSelect >= left.length)
					imgSelect = 0;
				gc.drawImage(left[imgSelect], xPos *Tile.TILEWIDTH - Camera.xOffset, yPos*Tile.TILEHEIGHT - Camera.yOffset, width, height);
				t.setOnCooldown(true);
			} else {
				gc.drawImage(left[imgSelect], xPos *Tile.TILEWIDTH - Camera.xOffset, yPos*Tile.TILEHEIGHT - Camera.yOffset, width, height);
			}
		}
		t.tick();
		
		if( gun != null )
			gun.render( gc );
		
		if( tookDmg )
		{
			gc.setFill( Color.RED );
			gc.fillText( "" + dmgTaken, xPos * Tile.TILEWIDTH - Camera.xOffset, yPos * Tile.TILEHEIGHT - Camera.yOffset);
			gc.setFill( Color.BLACK );
		}
	}
	
	/*****************************
	 *        GETTERS / SETTERS
	 ****************************/
	
	public Gun getGun()
	{
		return gun;
	}
	
	public boolean isATrap() {
		return false;
	}
	
}//close character








