package application;

//not much to this class
public class Pistol extends Gun{
	
	public static final float GUN_HEIGHT = 20;
	public static final float GUN_WIDTH = 30;
	
	public Pistol(float xpos, float ypos) {
		super(xpos, ypos, GUN_WIDTH, GUN_HEIGHT, Asset.pistol);
	}
	//class variables
	final int fireRate = 1; //these are pretty set
	float bulletVelocity = 1.0f;
	
	/*****************
	 *  Constructor  *
	 *****************/
	

}//close pistol class