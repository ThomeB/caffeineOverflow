package application;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class GameObject {
	
	
	//class variables
	protected float xPos;
	protected float yPos;
	protected float height;
	protected float width;
	protected Image img;
	public Image[] images;
	public Timer t;
	public int imgSelect;
	protected float[][] hitBoxCorners;
	protected Rectangle hitBox;
	/*[0] = top left [1] = top right [2] = bottom left [3] = bottom right*/
	
	/******************
	 * 	Constructors  *
	 ******************/
	public GameObject(float xpos, float ypos, float width, float height, Image img) {
		//allocate memory for hitbox upon creation
		this.hitBoxCorners = new float[4][2];
		this.yPos = ypos;
		this.xPos = xpos;
		this.height = height;
		this.width = width;
		this.img = img;
		this.hitBox = new Rectangle(xPos*Tile.TILEWIDTH,yPos*Tile.TILEHEIGHT,width,height);
	}//close constructor
	
	//constructor for a gameObject that will have a dynamic image
	public GameObject(float xpos, float ypos, float width, float height, Image[] img, Double interval) {
		//allocate memory for hitbox upon creation
		this.hitBoxCorners = new float[4][2];
		this.hitBox = new Rectangle(xPos*Tile.TILEWIDTH,yPos*Tile.TILEHEIGHT,width,height);
		this.yPos = ypos;
		this.xPos = xpos;
		this.height = height;
		this.width = width;
		
		images = img;
		t = new Timer(interval);
		this.imgSelect = (int)(images.length * Math.random());
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
		gc.drawImage(img, xPos *Tile.TILEWIDTH - Camera.xOffset, yPos*Tile.TILEHEIGHT - Camera.yOffset, width, height);
	}//close render method
	
	//overloaded render dealing with dynamic images
	public void dynamicRender(GraphicsContext gc) {
			if(!t.isOnCooldown()) {
				imgSelect++;
				
				if(imgSelect >= images.length)
					imgSelect = 0;
				
				gc.drawImage(images[imgSelect], xPos *Tile.TILEWIDTH - Camera.xOffset, yPos*Tile.TILEHEIGHT - Camera.yOffset, width, height);
				t.setOnCooldown(true);
			} else {
				gc.drawImage(images[imgSelect], xPos *Tile.TILEWIDTH - Camera.xOffset, yPos*Tile.TILEHEIGHT - Camera.yOffset, width, height);
			}
			t.tick();
	}//close render method
}//close GameObject