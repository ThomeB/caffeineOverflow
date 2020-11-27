package application;

public class HealthPack extends Interactable{
	//class variables
	private int healthRestored;
	public static final float HEALTHPACK_WIDTH = 32;
	public static final float HEALTHPACK_HEIGHT = 32;
	/*****************
	 *  Constructor  *
	 *****************/
	public HealthPack(float xpos, float ypos, int healthAmt) {
		super(xpos, ypos, HEALTHPACK_WIDTH, HEALTHPACK_HEIGHT, Asset.grayPotion);
		this.healthRestored = healthAmt;
	}
	
	//should be referenced when character picks up pack
	public int getHealth() {
		return healthRestored;
	}//close getter

	@Override
	public void pickup(Character hero) {
		if (!despawn) {
			despawn = true;
			hero.setHp(hero.getHp()+healthRestored);
		}
	}
}//close class