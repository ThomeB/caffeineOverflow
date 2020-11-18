package application;

public class Door/*extends Interactables*/{
	//class variables
	private boolean isLocked = true;
	
	/*****************
	 *  Constructor  *
	 *****************/
	public Door() { //starts out locked. Only thing for it is calling parent
		//super();
	}//close constructor
	
	/*******************
	 *  Getter/Setter  *
	 *******************/
	public boolean getStatus() {
		return isLocked;
	}//close getter
	
	public void setStatus(boolean locked) {
		this.isLocked = locked;
	}//close setter
}//close class