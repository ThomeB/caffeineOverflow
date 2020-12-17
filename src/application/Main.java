package application;
	
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class Main extends Application {
	
	public static AnimTimerExt t;
	Game game;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
			primaryStage.setX( bounds.getMaxX() );
			primaryStage.setY( bounds.getMaxY() );
			
			game = new Game(primaryStage, primaryStage.getMaxWidth() , primaryStage.getMaxHeight() );
			
			//These settings must be set after the game is instantiated 
			primaryStage.setMaximized( true );
			primaryStage.setFullScreen( true );
			primaryStage.show();
			
			//GAME LOOP:
			t = new AnimTimerExt() {
				@Override
				public void handle(long now) {
					if (lastTime == 0L){//wait for first frame
						lastTime = now;
					}else {
						double deltaTime = (now-lastTime)*speed;
						lastTime = now;
						game.tick(deltaTime);
					}
				}
			};
			//END GAME LOOP
			t.start();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}


class AnimTimerExt extends AnimationTimer{
	long lastTime = 0L;
	double speed = 1.0;
	public void setSpeed(double newSpeed) {
		speed = newSpeed;
	}
	@Override
	public void handle(long now) {
		//Hack-- this will be overwritten
	}
	
}