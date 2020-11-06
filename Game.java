package application;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class Game {
	
	private boolean isInitialized = false;
	
	public void intialize(Scene scene) {
		if (isInitialized)
			return;
		else
			isInitialized = true;
		
		//key listeners
		//see: https://stackoverflow.com/questions/29962395/how-to-write-a-keylistener-for-javafx
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    
                }
            }
        });
		
		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    
                }
            }
        });
		//end key listeners
		
		//mouse listeners
		//see: https://stackoverflow.com/questions/16635514/how-to-get-location-of-mouse-in-javafx
		scene.setOnMouseMoved(new EventHandler<MouseEvent>() {
		      @Override 
		      public void handle(MouseEvent event) {
		        String msg =
		          "(x: "       + event.getX()      + ", y: "       + event.getY()       + ") -- " +
		          "(sceneX: "  + event.getSceneX() + ", sceneY: "  + event.getSceneY()  + ") -- " +
		          "(screenX: " + event.getScreenX()+ ", screenY: " + event.getScreenY() + ")";
		      }
		});
		
		scene.setOnMousePressed(new EventHandler<MouseEvent>() {
		      @Override 
		      public void handle(MouseEvent event) {
		        
		      }
		});
		
		scene.setOnMouseReleased(new EventHandler<MouseEvent>() {
		      @Override 
		      public void handle(MouseEvent event) {
		    	  
		      }
		});
		
		//end mouse listeners
	}
	
	public void tick(double deltaTime) {
		//update objects in game
	}
}

