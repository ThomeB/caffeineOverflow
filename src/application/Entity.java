package application;

import javafx.scene.image.Image;

public abstract class Entity extends GameObject {
	//class variables
	protected int hp = 1;
	protected int maxHp = 1;
	protected int str = 0;
	protected int def = 0;
	//protected Gun wpnEquip = null;
	private boolean isAlive = true;
	private float walkSpeed = 1.0f;
	
	public int counter = 0;
	
	/******************
	 * 	Constructors  *
	 ******************/
	public Entity(int hp, int str, int def, float xpos, float ypos, float width, float height, Image img) {
		super(xpos, ypos, height, width, img);
		this.hp = hp;
		this.maxHp = hp;
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
		if (hp > maxHp)
			hp = maxHp;
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
	//update methods that can be overwritten so that game can handle an arraylist of entity
	public void update() {}
	public void update(Character character) {}
	
	//what happens after they die
	public void death() {}
	
	//abstract movement method
	public abstract void movement(float xPos, float yPos);
	
	//deal damage
	public abstract void attack();
	
	//quick checker method
	public abstract boolean isATrap();
	
	//take damage
	private void takeDmg(int dmg) {
		hp-=dmg;
		if(hp <= 0) {
			if(isAlive) {
				isAlive = false;
				this.death();
			}
		}
	}
	//strategy: there is a private takeDmg that uses an int, otherwise takes args based on what is doing the damage (attacker or projectile)
	public void takeDmg(Entity attacker) {
		if (attacker != null) {
			takeDmg(attacker.str);
		}
	}
	
	public void takeDmg(Projectile projectile) {
		if (projectile != null) {
			takeDmg(projectile.damage);
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
		
		hitBox.setX(xPos*Tile.TILEWIDTH);
		hitBox.setY(yPos*Tile.TILEHEIGHT);
		
	}//close moveBox

	
}//close entity