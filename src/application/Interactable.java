package application;

import java.util.HashMap;

import javafx.scene.image.Image;

public abstract class Interactable extends GameObject
{
	
	public boolean despawn = false; //flag to tell the game that this interactable is not needed
	
	public Interactable(float xpos, float ypos, float width, float height, Image image) 
	{
		super(xpos, ypos, width, height, image);


	}
	
	public abstract void pickup(Character hero);

}