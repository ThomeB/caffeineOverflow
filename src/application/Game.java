package application;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Game {
	
	private Stage stage;
	private double height;
	private double width;
	
	public Stage getStage() {
		return stage;
	}
	public double getHeight() {
		return height;
	}
	public double getWidth() {
		return width;
	}
	public Game(Stage stage, double height, double width) {
		this.stage = stage;
		this.height = height;
		this.width = width;
		//key listeners (removed for now)
		//see: https://stackoverflow.com/questions/29962395/how-to-write-a-keylistener-for-javafx
		//mouse listeners (removed for now)
		//see: https://stackoverflow.com/questions/16635514/how-to-get-location-of-mouse-in-javafx
		//also see: lambda expressions (cleaner code)
	}
	
	public void tick(double deltaTime) {
		//update objects in game
		
		//update graphics here
		
		//update game data here
	}
}

