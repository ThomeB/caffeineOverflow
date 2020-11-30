package application;

public class HealthPack extends Interactable{
	//class variables
	public static final float HEALTHPACK_WIDTH = 20;
	public static final float HEALTHPACK_HEIGHT = 20;
	public static final int HEAL_AMOUNT = 50;
	
	private int healthRestored;
	
	
	/*****************
	 *  Constructor  *
	 *****************/
	public HealthPack(float xpos, float ypos ) {
		super(xpos, ypos, HEALTHPACK_WIDTH, HEALTHPACK_HEIGHT, Asset.grayPotion);
		this.healthRestored = HEAL_AMOUNT;
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