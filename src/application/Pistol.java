package application;

//not much to this class
public class Pistol extends Gun{
	//class variables
	final int fireRate = 1; //these are pretty set
	float bulletVelocity = 1.0f;
	
	/*****************
	 *  Constructor  *
	 *****************/
	public Pistol(int str, String firePattern, int ammoCapacity) {
		super(str, firePattern, ammoCapacity);
	}//close constructor

}//close pistol class
