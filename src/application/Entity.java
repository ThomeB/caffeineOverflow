package application;

import javafx.scene.image.Image;

public abstract class Entity extends GameObject {
	//class variables
	protected int hp = 1;
	protected int str = 0;
	protected int def = 0;
	//protected Gun wpnEquip = null;
	private boolean isAlive = true;
	private float walkSpeed = 1.0f;
	
	/******************
	 * 	Constructors  *
	 ******************/
	public Entity(int hp, int str, int def, float xpos, float ypos, int height, int width, Image img) {
		super(xpos, ypos, height, width, img);
		this.hp = hp;
		this.str = str;
		this.def = def;
	}//close of entity constructor
	
	/*************
	 *  Getters  *
	 *************/
	public int getHp() {
		return hp;
	}
/*	public Gun getWpnEquip() {
		return wpnEquip;
	}
*/	public boolean isAlive() {
		return isAlive;
	}
	public float getWalkSpeed() {
		return walkSpeed;
	}//close of getters
	
	/*************
	 *  Setters  *
	 *************/
/*	public void setWpnEquip(Gun wpnEquip) {
		this.wpnEquip = wpnEquip;
	}
*/	public void setHp(int hp) {
		this.hp = hp;
	}
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	public void setDef(int def) {
		this.def = def;
	}
	public void setWalkSpeed(float walkSpeed) {
		this.walkSpeed = walkSpeed;
	}//close of setters
	
	/****************************
	 *  Entity Utility Methods  *
	 * @return 					*
	 ****************************/
	//abstract movement method
	public abstract void movement(float xPos, float yPos);
	
	//deal damage
	public abstract void attack();
	
	//take damage
	public void takeDmg(Entity attacker) {
		if(attacker != null) {
			this.hp -= attacker.str;
		}
		
		if(this.hp <= 0) {
			isAlive = false;
		}
	}//close of damage
	
	//update hitbox - called when movement occurs
	public void moveBox() {
		hitBoxCorners[0][0] = xPos;
		hitBoxCorners[0][1] = yPos;
		hitBoxCorners[1][0] = xPos + width;
		hitBoxCorners[1][1] = yPos;
		hitBoxCorners[2][0] = xPos;
		hitBoxCorners[2][1] = yPos + height;
		hitBoxCorners[3][0] = xPos + width;
		hitBoxCorners[3][1] = yPos + height;
	}//close moveBox
}//close entity