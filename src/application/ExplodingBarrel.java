package application;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class ExplodingBarrel extends Traps{
	/*********************
	 *  Class Variables  *
	 *********************/
	public static final float BARREL_HEIGHT = 60;
	public static final float BARREL_WIDTH = 60;
	public static final int BARREL_STR = 5000;
	public static final Image BARREL_IMAGE = Asset.barrel;
	public static final Image BARREL_EXPLODED = Asset.bigASSKNIGHT;

	/*****************
	 * 	Constructor  *
	 *****************/
	public ExplodingBarrel(float xpos, float ypos) {
		super(BARREL_STR, xpos, ypos, BARREL_WIDTH, BARREL_HEIGHT, BARREL_IMAGE);
	}
	
	@Override
	public void render(GraphicsContext gc) {
		if(this.isAlive())
			gc.drawImage(BARREL_IMAGE, xPos *Tile.TILEWIDTH - Camera.xOffset, yPos*Tile.TILEHEIGHT - Camera.yOffset, width, height);
		else
			gc.drawImage(BARREL_EXPLODED, xPos *Tile.TILEWIDTH - Camera.xOffset, yPos*Tile.TILEHEIGHT - Camera.yOffset, width, height);
	}//close render method
}//close class








