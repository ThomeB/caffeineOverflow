package application;


public abstract class Interactable extends GameObject
{
	public static final float DEFAULT_INTERACTABLE_WIDTH = 30;
	public static final float DEFAULT_INTERACTABLE_HEIGHT = 30;

	public Interactable(float xpos, float ypos ) 
	{
		super(xpos, ypos, DEFAULT_INTERACTABLE_WIDTH, DEFAULT_INTERACTABLE_HEIGHT, Asset.grayPotion);


	}
	
	public abstract void pickup();

}
