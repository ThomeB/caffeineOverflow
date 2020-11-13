package application;

public abstract class Entity {
	//class variables
	private int hp = 1;
	private int str = 0;
	private int def = 0;
	private float walkSpeed = 1.0f;
	private Gun wpnEquip = null;
	private boolean isAlive = true;
	
	/******************
	 * 	Constructors  *
	 ******************/
	public Entity(int hp, int str, int def) {
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
	public Gun getWpnEquip() {
		return wpnEquip;
	}
	public boolean isAlive() {
		return isAlive;
	}//close of getters
	
	/*************
	 *  Setters  *
	 *************/
	public void setWpnEquip(Gun wpnEquip) {
		this.wpnEquip = wpnEquip;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	public void setDef(int def) {
		this.def = def;
	}//close of setters
	
	/****************************
	 *  Entity Utility Methods  
	 * @return *
	 ****************************/
	//abstract movement method
	public abstract void movement();
	
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
}//close entity
