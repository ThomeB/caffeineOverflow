package application;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Utility {

	public static boolean collisionDetection(float [][] hitboxCorners, float [][] checkHitboxCorners) {
		//warning: unused and untested, might be useful for projectiles but probably has faulty logic
		float x1 = hitboxCorners[0][0];
		float y1 = hitboxCorners[0][1];
		float x2 = hitboxCorners[1][0];
		float y2 = hitboxCorners[2][1];
		
		float cx1 = checkHitboxCorners[0][0];
		float cy1 = checkHitboxCorners[0][1];
		float cx2 = checkHitboxCorners[1][0];
		float cy2 = checkHitboxCorners[2][1];
		
		if (x1 > cx1 && y1 > cy1 && x1 < cx2 && y1 < cy2) {
			return true;
		}
		
		return false;
	}
	
	public static boolean collidesWithWall(float [][] hitboxCorners, int mapX, int mapY) {
		float xp = hitboxCorners[0][0];    //x position
		float yp = hitboxCorners[0][1];    //y position
		float xs = (hitboxCorners[1][0]-xp) / Tile.TILEWIDTH; //x size
		float ys = (hitboxCorners[2][1]-yp) / Tile.TILEHEIGHT; //y height
		
		float cxp = mapX; //tile x position
		float cyp = mapY; //tile y position
		float cxs = 1;    //tile x width 
		float cys = 1;    //tile y height
		return ((xp < cxp + cxs && xp + xs > cxp) && (yp < cyp + cys && yp + ys > cyp)); //ugly but it works! (AABB Test);
	}
	
	//https://www.journaldev.com/17129/java-deep-copy-object
	//gets a deep copy of an object (allows me to duplicate tables while not worrying about modifying the old one)
	public static Object deepCopy(Object obj) {
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream os = new ObjectOutputStream(bos);
			os.writeObject(obj);
			ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
			ObjectInputStream is = new ObjectInputStream(bis);
			return is.readObject();
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}