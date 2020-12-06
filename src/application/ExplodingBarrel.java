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
	public static final int BARREL_BLAST_RADIUS = 8;
	public static final Image BARREL_IMAGE = Asset.barrel;
	public static final Image BARREL_EXPLODED = Asset.bigASSKNIGHT;

	/*****************
	 * 	Constructor  *
	 *****************/
	public ExplodingBarrel(float xpos, float ypos, Double interval) {
		super(BARREL_STR, xpos, ypos, BARREL_WIDTH, BARREL_HEIGHT, BARREL_BLAST_RADIUS, BARREL_IMAGE);
		images = Asset.barrelExplosion;
		t = new Timer(interval);
		imgSelect = 0;
	}
	
	@Override
	public void render(GraphicsContext gc) {
		if(this.isAlive())
			gc.drawImage(BARREL_IMAGE, xPos *Tile.TILEWIDTH - Camera.xOffset, yPos*Tile.TILEHEIGHT - Camera.yOffset, width, height);
		else
			this.dynamicRender(gc);
	}//close render method
	
	//overloaded render dealing with dynamic images
		public void dynamicRender(GraphicsContext gc) {
				if(!t.isOnCooldown() && imgSelect < images.length) {
					gc.drawImage(images[imgSelect], xPos *Tile.TILEWIDTH - Camera.xOffset - (width*2.5), yPos*Tile.TILEHEIGHT - Camera.yOffset - (width*2.5), width*6, height*6);
					t.setOnCooldown(true);
					imgSelect++;
				} else {
					gc.drawImage(images[imgSelect], xPos *Tile.TILEWIDTH - Camera.xOffset - (width*2.5), yPos*Tile.TILEHEIGHT - Camera.yOffset - (width*2.5), width*6, height*6);
				}
				
				if(imgSelect >= images.length)
					t = null;
				
				if(t != null)
					t.tick();
		}//close render method
}//close class








