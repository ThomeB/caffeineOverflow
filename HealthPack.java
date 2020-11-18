package application;

public class HealthPack /*extends Interactables*/{
	//class variables
	private int healthRestored;
	
	/*****************
	 *  Constructor  *
	 *****************/
	public HealthPack(int hpResto) {
		//super();
		this.healthRestored = hpResto;
	}
	
	//should be referenced when character picks up pack
	public int getHealth() {
		return healthRestored;
	}//close getter
}//close class
