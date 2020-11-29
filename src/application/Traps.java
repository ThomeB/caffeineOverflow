package application;

import javafx.scene.image.Image;

public class Traps extends Entity{
	/*********************
	 *  Class Variables  *
	 *********************/
	public static final int TRAP_HP = 1;
	public static final int TRAP_DEF = 0;
	public static final int TRAP_BLAST_RADIUS = 8;
	
	/*****************
	 *  Constructor  *
	 *****************/
	//specific traps will need to implement their Strength, Position, Size and Image
	public Traps(int str, float xpos, float ypos, float width, float height, Image img) {
		super(TRAP_HP, str, TRAP_DEF, xpos, ypos, width, height, img);
	}
	
	/*********************
	 *  Utility Methods  *
	 *********************/
	@Override
	public void death() {
		for(int x = 0; x < Game.enemies.size(); x++) {
			if(Game.enemies.get(x) != null && Game.enemies.get(x).isAlive() && !Game.enemies.get(x).equals(this) && Utility.getDistance(Game.enemies.get(x), this) < TRAP_BLAST_RADIUS )
				Game.enemies.get(x).takeDmg(this);
			
		}
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
