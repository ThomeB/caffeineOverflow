package application;

public class Gun /*extends Interactables*/ {
	//class variables
	protected int str;
	protected String firePattern;
	protected int ammoCapacity;
	//Projectile projectile;
	
	/*****************
	 *  Constructor  *
	 *****************/
	public Gun(int str, String firePattern, int ammoCapacity) {
		//super();
		this.str = str;
		this.firePattern = firePattern;
		this.ammoCapacity = ammoCapacity;
	}//close constructor
	
	/*****************
	 *  Gun Actions  *
	 *****************/
	public void fire() {
		//will use the position of the mouse cursor to get position to fire at
	}//close fire method
}//close gun class
