package application;

public class Door extends Interactable{
	//class variables
	private boolean isLocked = true;
	public static final float DEFAULT_DOOR_WIDTH = Tile.TILEWIDTH;
	public static final float DEFAULT_DOOR_HEIGHT = Tile.TILEHEIGHT;
	
	
	private Map map;
	
	/*****************
	 *  Constructor  *
	 *****************/
	public Door(float xpos, float ypos, Map map) { //starts out locked. Only thing for it is calling parent
		super(xpos, ypos, DEFAULT_DOOR_WIDTH, DEFAULT_DOOR_HEIGHT, Asset.doorImage);
		this.map = map;
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

	@Override
	public void pickup(Character hero) {
		if (!despawn && isLocked && hero.getKey()) {//if we are not marked for deletion, we are locked, and the hero has a key, we open
			isLocked = false;
			
			int x = (int) xPos;
			int y = (int) yPos;
			
			map.setTile(".", x, y);
			
			despawn = true;
		}
	}
}//close class