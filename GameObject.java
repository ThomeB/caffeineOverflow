package application;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class GameObject {
	//class variables
	protected float xPos;
	protected float yPos;
	protected float height;
	protected float width;
	private Image img;
	protected float[][] hitBoxCorners;
	/*[0] = top left [1] = top right [2] = bottom left [3] = bottom right*/
	
	/******************
	 * 	Constructors  *
	 ******************/
	public GameObject(float xpos, float ypos, float height, float width, Image img) {
		//allocate memory for hitbox upon creation
		this.hitBoxCorners = new float[4][2];
		this.yPos = ypos;
		this.xPos = xpos;
		this.height = height;
		this.width = width;
		this.img = img;
		
		//might not need this here at all, less duplicate code if we don't need it
		//set them upon creation - movement methods will take care of update
		/*hitBoxCorners[0][0] = xPos;
		hitBoxCorners[0][1] = yPos;
		hitBoxCorners[1][0] = xPos + width;
		hitBoxCorners[1][1] = yPos;
		hitBoxCorners[2][0] = xPos;
		hitBoxCorners[2][1] = yPos + height;
		hitBoxCorners[3][0] = xPos + width;
		hitBoxCorners[3][1] = yPos + height;*/
	}//close constructor

	/*************
	 *  Getters  *
	 *************/
	public float getxPos() {
		return xPos;
	}
	public float getyPos() {
			return yPos;
	}
	public float getWidth() {
		return width;
	}
	public float getHeight() {
		return height;
	}
	
	/*************
	 *  Setters  *
	 *************/
	public void setxPos(float xPos) {
		this.xPos = xPos;
	}
	public void setyPos(float yPos) {
		this.yPos = yPos;
	}
	
	/*************************************
	 *  Draw Whatever the GameObject Is  *
	 *************************************/
	public void render(GraphicsContext gc) {
		gc.drawImage(img, xPos *Tile.TILEWIDTH - Camera.xOffset, yPos*Tile.TILEHEIGHT - Camera.yOffset, width*Tile.TILEWIDTH, height*Tile.TILEHEIGHT);
	}//close render method
}//close GameObject