package main;

import java.util.ArrayList;

import cells.Cell;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;
import simulations.Simulation;
import simulations.FireSimulation;

public class Main extends Application {

	public static final int GRID_SIZE = 400;
    public static final int SCENE_WIDTH = GRID_SIZE;
    public static final int SCENE_HEIGHT = GRID_SIZE + 200;
    public static final String TITLE = "Cell Society";
    public static final Paint BACKGROUND = Color.WHITE;
    public static final int FRAMES_PER_SECOND = 5;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	
    private Simulation mySimulation;
    private ArrayList<ArrayList<Cell>> myCells;
    private Scene myScene;
    private Group myRoot;
    private Timeline myAnimation;

	@Override
	public void start(Stage stage) {

		mySimulation = new FireSimulation(30);
		myScene = setupScene(SCENE_WIDTH, SCENE_HEIGHT, mySimulation);

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
        myAnimation = animation;

	}

	private Scene setupScene(int width, int height, Simulation simulation) {
		Group root = new Group();
		Scene scene = new Scene(root, width, height, BACKGROUND);

		myCells = simulation.getMyCells();
		mySimulation = simulation;

		for (int i=1;i<myCells.size()-1;i++) {
			for (int j = 1; j<myCells.size()-1;j++) {
				root.getChildren().add(myCells.get(i).get(j).getMyRectangle());
			}
		}
		myRoot = root;

		return scene;
	}

	private void step(double timeElapsed) {
		mySimulation.evolve();
		myRoot.getChildren().clear();
		myCells = mySimulation.getMyCells();
		for (int i=1;i<myCells.size()-1;i++) 
			for (int j = 1; j<myCells.size()-1;j++) myRoot.getChildren().add(myCells.get(i).get(j).getMyRectangle());
	}

	public static void main(String[] args) {
		launch(args);
	}

}
