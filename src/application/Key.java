package application;

public class Key extends Interactable {

	public static final float KEY_WIDTH = 64;
	public static final float KEY_HEIGHT = 64;
	
	public Key(float xpos, float ypos) {
		super(xpos, ypos, KEY_WIDTH, KEY_HEIGHT, Asset.grayPotion);
	}

	@Override
	public void pickup(Character hero) {
		if (!despawn && !hero.getKey()) {//give the hero the key IF we are not already used and the hero doesn't already have a key
			hero.setKey(true);
			despawn = true;
		}
	}

}
