package application;
	
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	AnimTimerExt t;
	Game game;
	private double defaultHeight = 400.0;
	private double defaultWidth = 400.0;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,defaultHeight,defaultWidth);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
			game = new Game(primaryStage, defaultHeight, defaultWidth);
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