package main;

import java.util.ArrayList;

import cells.Cell;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import simulations.Simulation;

public class Main extends Application {

	public static final String TITLE = "Example JavaFX";
    public static final int SIZE = 400;
    public static final int FRAMES_PER_SECOND = 60;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	
    private Simulation mySimulation;
    private ArrayList<ArrayList<Cell>> myCells = mySimulation.getMyCells();
    private Scene myScene;
    private Group myRoot;
    
	@Override
	public void start(Stage stage) {
		//setup main scene
		stage.setScene(myScene);
        stage.setTitle(TITLE);
        stage.show();
        // attach "game loop" to timeline to play it
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
                                      e -> step(SECOND_DELAY));
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
	}
	
	private Scene setupScene(int width, int height, ArrayList<ArrayList<Cell>> cells) {
		Group root = new Group();
		Scene scene = new Scene(root, height, width);
		
		
		for (ArrayList<Cell> column : cells) 
			for (Cell c: column) root.getChildren().add(c.getMyRectangle());
		
		myScene = scene;
		myRoot = root;
		myCell = cells;
		return scene;
	}
	
	
	private void step(double timeElapsed) {
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
