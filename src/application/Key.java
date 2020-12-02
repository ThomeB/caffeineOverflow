package application;

public class Key extends Interactable {

	public static final float KEY_WIDTH = 20;
	public static final float KEY_HEIGHT = 15;
	
	public Key(float xpos, float ypos) {
		super(xpos, ypos, KEY_WIDTH, KEY_HEIGHT, Asset.key);
	}

	@Override
	public void pickup(Character hero) {
		if (!despawn && !hero.getKey()) {//give the hero the key IF we are not already used and the hero doesn't already have a key
			hero.setKey(true);
			despawn = true;
		}
	}

}
