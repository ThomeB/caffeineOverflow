package application;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class Utility {

	//returns true if a hitbox is colliding with another hitbox
	//any GameObject should be fine to use with this method
	//"The Rectangle Method"
	public static boolean collidesWithGameObject(GameObject a, GameObject b) {
		return a.hitBox.getBoundsInParent().intersects(b.hitBox.getBoundsInParent());
	}
	
	//returns true if a hitbox is colliding with a wall. 
	//Never use this with an actual hitbox, instead use a predictor hitbox so that movement is smooth.
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
	
	//returns a double for the distance between two GameObjects. This distance is based on the map coordinates, not pixels.
	//the value of 1 indicates 1 tile away, not one pixel away.
	public static double getDistance(GameObject a, GameObject b) {
		double ax = a.getxPos();
		double ay = a.getyPos();
		double bx = b.getxPos();
		double by = b.getyPos();
		return Math.sqrt(Math.pow(ax-bx,2)+Math.pow(ay-by,2));
	}
	
	//this but for a coordinate and an object
	public static double getDistance(GameObject a, float[] coords) {
		double ax = a.getxPos();
		double ay = a.getyPos();
		double bx = coords[0];
		double by = coords[1];
		return Math.sqrt(Math.pow(ax-bx,2)+Math.pow(ay-by,2));
	}
	
	public static double getDistance( double x1, double y1, double x2, double y2 )
	{
		return Math.sqrt( Math.pow( x1 - x2, 2) + Math.pow( y1 - y2, 2) );
	}
	
	//helper methods for pathfinding
	private static String coordsToString(float[] coords) {
		return coords[0] + "," + coords[1];
	}
	private static ArrayList<float[]> getNeighbors(float[] coords, Map map){
		ArrayList<float[]> neighbors = new ArrayList<float[]>(4);
		
		int[][] toCheck = {
				{1,0},
				{-1,0},
				{0,1},
				{0,-1}
		};
		//loop around the coordinates in every direction to get all neighbors (might need to exclude diagonals)
		for (int[] check : toCheck) {
			int x = check[0];
			int y = check[1];
			int adjustedX = (int)Math.floor(coords[0]+x + .5);
			int adjustedY = (int)Math.floor(coords[1]+y + .5);
			if (adjustedX > -1 && adjustedX < map.getWidth() && adjustedY > -1 && adjustedY < map.getHeight() ) {
				String tileType = map.getTile(adjustedX, adjustedY);
				if (tileType.equals(".")||tileType.equals("D")) {
					float[] neighborCoords = {adjustedX, adjustedY};
					neighbors.add(neighborCoords);
				}
			}
		}
		return neighbors;
	}
	
	//D* Algorithm: like A* but faster and more concise, maybe.
	//takes two coordinate args: start and end + the map. The result is a list of steps to take to get to the start from the end coords (backwards).
	public static ArrayList<float[]> dStar(float[] startCoords, float[] endCoords, Map map) {
		
		ArrayList<float[]> steps = new ArrayList<float[]>(5);
		
		ArrayList<dStarEntry> inSet = new ArrayList<dStarEntry>(20);
		ArrayList<dStarEntry> checkSet = new ArrayList<dStarEntry>(20);
		
		startCoords[0] = (int) Math.floor(startCoords[0]+.5);
		startCoords[1] = (int) Math.floor(startCoords[1]+.5);
		
		dStarEntry current = new dStarEntry(0, null, startCoords, coordsToString(startCoords));
		
		inSet.add(current);
		endCoords[0] = (int) endCoords[0];
		endCoords[1] = (int) endCoords[1];
		String goalString = coordsToString(endCoords);
		
		int round = 0;
		boolean pathFound = false;
		
		if (startCoords[0] == endCoords[0] && startCoords[1] == endCoords[1]) {
			return steps;
		}
 		
		while (round < 100 && !pathFound) {
			round++;
			//add neighbors to check set
			for (float [] neighborCoords : getNeighbors(current.coords,map)) {
				String key = coordsToString(neighborCoords);
				boolean validEntry = true;
				for (dStarEntry entry : inSet) {
					if (entry!=null && entry.id.equals(key)) {
						validEntry = false;
					}
				}
				for (dStarEntry entry : checkSet) {
					if (entry!=null && entry.id.equals(key)) {
						validEntry = false;
					}
				}
				if (validEntry) {
					checkSet.add(new dStarEntry(Float.MAX_VALUE, current, neighborCoords, coordsToString(neighborCoords)));
				}
			}
			//check the check set for best path, weight-wise
			for (dStarEntry entry : checkSet) {
				boolean validEntry = true;
				for (dStarEntry inSetEntry : inSet) {
					if (inSetEntry!=null && inSetEntry.id.equals(entry.id)){
						validEntry = false;
					}
				}
				if (validEntry) {
					float addedWeight = current.weight + 1;// CUSTOM WEIGHT VALUES CAN GO HERE!
					if (addedWeight < entry.weight) {
						entry.weight = addedWeight;
						entry.parent = current;
					}
				}
			}
			
			dStarEntry best = null;
			float lowestWeight = Float.MAX_VALUE;
			for (dStarEntry entry : checkSet) {
				if (entry.weight < lowestWeight) {
					lowestWeight = entry.weight;
					best = entry;
				}
			}
			
			if (best!=null) {
				if (coordsToString(best.coords).equals(goalString)) {
					pathFound = true;
				}
				inSet.add(best);
				current = best;
				checkSet.remove(best);
			}
			
		}
		
		if (pathFound) {
			//int pathLength = -1;
			do {
				steps.add(current.coords);
				//pathLength++;
				current = current.parent;
			}while(current != null);
			//System.out.println("Path found in " + pathLength + " steps! ");
		}else {
			//System.out.println("Pathfinding failed! (" + startCoords[0] + ", " + startCoords[1] + ") -> (" + endCoords[0] + ", " + endCoords[1] + ")" );
			return null;
		}
		
		return steps;
		
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

class dStarEntry{
	public float weight;
	public dStarEntry parent;
	public float [] coords;
	public String id;
	
	public dStarEntry(float weight, dStarEntry parent, float[] coords, String id) {
		this.weight = weight;
		this.parent = parent;
		this.coords = coords;
		this.id = id;
	}
	
}