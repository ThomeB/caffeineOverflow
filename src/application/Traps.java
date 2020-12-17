package application;

import javafx.scene.image.Image;

public class Traps extends Entity{
	/*********************
	 *  Class Variables  *
	 *********************/
	public static final int TRAP_HP = 1;
	public static final int TRAP_DEF = 0;
	public static final float TRAP_WALKSPEED = 0.0f;
	
	protected float blastRadius;
	
	
	/*****************
	 *  Constructor  *
	 *****************/
	//specific traps will need to implement their Strength, Position, Size and Image
	public Traps(int str, float xpos, float ypos, float width, float height, float blastRadius, Image img) {
		super(TRAP_HP, str, TRAP_DEF, xpos, ypos, width, height, TRAP_WALKSPEED, img);
		
		this.blastRadius = blastRadius;
	}
	
	/*********************
	 *  Utility Methods  *
	 *********************/
	@Override
	public void death() {
		for(int x = 0; x < Game.enemies.size(); x++) {
			if(Game.enemies.get(x) != null && Game.enemies.get(x).isAlive() && !Game.enemies.get(x).equals(this) && Utility.getDistance(Game.enemies.get(x), this) <= blastRadius )
				Game.enemies.get(x).takeDmg(this);
			
		}
		
		if( Game.character.isAlive() && Utility.getDistance( Game.character, this ) <= blastRadius )
			Game.character.takeDmg( this );
	}
	
	public boolean isATrap() {
		return true;
	}


	/*******************************************
	 *  Fluff Methods Overwritten From Entity  *
	 *******************************************/
	@Override
	public void movement(float xPos, float yPos) {/*Do Nothing*/}
	public void attack() {/*Do Nothing*/}
}//close class
