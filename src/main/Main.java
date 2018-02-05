package main;

import java.util.ArrayList;

import cells.Cell;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;
import simulations.Segregation;
import simulations.Simulation;
import simulations.FireSimulation;
import simulations.WaTor;

public class Main extends Application {

	public static final int GRID_SIZE = 400;
    public static final int SCENE_WIDTH = GRID_SIZE;
    public static final int SCENE_HEIGHT = GRID_SIZE + 200;
    public static final String TITLE = "Cell Society";
    public static final Paint BACKGROUND = Color.WHITE;
    public static final int FRAMES_PER_SECOND = 1;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    private int BUTTON_WIDTH = 70;
    private boolean isRunning = false;
    private Simulation mySimulation;
    private ArrayList<ArrayList<Cell>> myCells;
    private Scene myScene;
    private Group myRoot;
    
	@Override
	public void start(Stage stage) {

//		Simulation simulation = new Segregation(30);
		mySimulation = new FireSimulation(30);
		myScene = setupScene(SCENE_WIDTH, SCENE_HEIGHT, mySimulation);

		//Simulation simulation = new Segregation(30);
		//simulation.setMyMinSatisfaction(0.5);
		Simulation simulation = new WaTor(30);
		simulation.initialize();

		myScene = setupScene(SCENE_WIDTH, SCENE_HEIGHT, simulation);

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
	
	private Scene setupScene(int width, int height, Simulation simulation) {
		Group root = new Group();
		Scene scene = new Scene(root, width, height, BACKGROUND);
		
		simulation.initialize();
		//simulation.setMyParameter(0.5);

		myCells = simulation.getMyCells();
		mySimulation = simulation;
		
		for (int i=1;i<myCells.size()-1;i++) {
			for (int j = 1; j<myCells.size()-1;j++) root.getChildren().add(myCells.get(i).get(j).getMyRectangle());
		}
		myRoot = root;
		
		setSettings();
		
		myRoot = root;
		return scene;
	}
	
	private void step(double timeElapsed) {	
		mySimulation.evolve();

		myRoot.getChildren().clear();
		myCells = mySimulation.getMyCells();
		for (int i=1;i<myCells.size()-1;i++) 
			for (int j = 1; j<myCells.size()-1;j++) myRoot.getChildren().add(myCells.get(i).get(j).getMyRectangle());
		setSettings();
	}
	
	public void setSettings() {
		GridPane grid = new GridPane();
//		grid.setMinSize(SCENE_WIDTH, SCENE_HEIGHT+200);
        myRoot.getChildren().add(grid);

		Button start = new Button("START");
        start.setMinWidth(BUTTON_WIDTH);
        start.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                isRunning = true;
            }
        });
        
        
        
		Button reset = new Button("RESET");
        reset.setMinWidth(BUTTON_WIDTH);
        reset.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                isRunning = true; //keep
                setupScene(SCENE_WIDTH, SCENE_HEIGHT, mySimulation);
            }
        });
        
		Button stop = new Button("STOP");
        stop.setMinWidth(BUTTON_WIDTH);
        stop.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                isRunning = false;
            }
        });

        
		Button step = new Button("STEP");
        step.setMinWidth(BUTTON_WIDTH);
        step.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                isRunning = false;
                step(SECOND_DELAY);
            }
        });
            
		Button load = new Button("LOAD");
        load.setMinWidth(BUTTON_WIDTH);


        grid.setVgap(285);
        grid.setHgap(11);
        grid.add(start, 0, 2, 1, 1);

        grid.add(reset, 1, 2, 1, 1);
        grid.add(stop, 2, 2, 1, 1);
        
        grid.add(step, 3, 2, 1, 1);
        grid.add(load, 4, 2, 1, 1);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
