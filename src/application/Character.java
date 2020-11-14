package application;

import javafx.scene.image.Image;

public class Character extends Entity {
	//class variables
	private String name;
	private int keys;

	/******************
	 * 	Constructors  *
	 ******************/
	public Character(int hp, int str, int def, float xpos, float ypos, int height, int width, String name, float walkSpeed, Image img) {
		super(hp, str, def, xpos, ypos, height, width, img);
		this.name = name;
		this.setWalkSpeed(walkSpeed);
		keys = 0;
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
}//close character
