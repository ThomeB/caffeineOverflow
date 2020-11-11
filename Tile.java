package application;

import java.awt.image.BufferedImage;
import java.util.HashMap;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Tile 
{
	public static HashMap<String, Tile> tiles = new HashMap<String, Tile>(20);
	public static Tile grassTile = new Tile( Asset.grassImage, "." );
	
	
	public static final int TILEWIDTH = 32;
	public static final int TILEHEIGHT = 32;
	
	private Image texture;
	private String hashCode;
	
	public Tile( Image texture, String hashCode )
	{
		this.texture = texture;
		this.hashCode = hashCode;
		
		tiles.put( hashCode, this );
		
		
	}
	
	public void render( GraphicsContext gc, int x, int y )
	{
		gc.drawImage( texture,  x * TILEWIDTH, y * TILEHEIGHT, TILEWIDTH, TILEHEIGHT );
	}
	
}
