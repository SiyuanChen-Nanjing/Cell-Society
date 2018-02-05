//		https://github.com/meb100/Cell-Society/blob/b41f4846744bb860a4550123f2a35257e3e61916/src/simulation/SettingsPanel.java
//		https://github.com/shduke/Cell-Society/search?utf8=%E2%9C%93&q=toolbar&type=

package main;

import java.util.ArrayList;

import cells.Cell;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import simulations.Segregation;
import simulations.Simulation;
import simulations.FireSimulation;
import simulations.WaTor;

public class Main extends Application {

	public static final int GRID_SIZE = 400;
	public static final int SCENE_WIDTH = GRID_SIZE;
	public static final int SCENE_HEIGHT = GRID_SIZE + 250;
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
	private Slider prob;
	private Slider grid_size;
	private Slider delay;
	private Label prob_label;
	private Label prob_value;
	private Label grid_label;
	private Label grid_value;
	private Label delay_label;
	private Label delay_value;
	private int simulation_size;
	private VBox sliders;
	
	@Override
	public void start(Stage stage) {

		mySimulation = new FireSimulation(30);
		myScene = setupScene(SCENE_WIDTH, SCENE_HEIGHT, mySimulation);

		//Simulation simulation = new Segregation(30);
		//simulation.setMyMinSatisfaction(0.5);
		//Simulation simulation = new WaTor(30);
		//simulation.initialize();

		//myScene = setupScene(SCENE_WIDTH, SCENE_HEIGHT, simulation);

//		GridPane grid = new GridPane();
//		grid.setVgap(10);
//		grid.setHgap(70);
//		grid.getChildren().add(mySimulation);
//		myScene.setRoot(grid);
		

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
			for (int j = 1; j<myCells.size()-1;j++) {
				root.getChildren().add(myCells.get(i).get(j).getMyRectangle());
			}
		}
		myRoot = root;
		setSettings(myRoot, scene);

		return scene;
	}

	private void step(double timeElapsed) {	
		mySimulation.evolve();
		myRoot.getChildren().clear();
//		setSettings here will make it reset everytime..here.
		myCells = mySimulation.getMyCells();
		for (int i=1;i<myCells.size()-1;i++) 
			for (int j = 1; j<myCells.size()-1;j++) myRoot.getChildren().add(myCells.get(i).get(j).getMyRectangle());
	}
	
	

	public void setSettings(Group root, Scene scene) {
		GridPane grid = new GridPane();
		
	    grid.setPadding(new Insets(10, 10, 10, 10));
	    grid.setVgap(10);
	    grid.setHgap(50);
	    
	    prob_label = new Label("probability: ");
	    prob = new Slider();
		prob.setMin(0.0);
		prob.setMax(1.0);
		prob.setBlockIncrement(0.05);
		prob_value = new Label(Double.toString(prob.getValue()));
        GridPane.setConstraints(prob_label, 0, 2);
		grid.getChildren().add(prob_label);
		GridPane.setConstraints(prob, 1, 2);
		grid.getChildren().add(prob);
		GridPane.setConstraints(prob_value, 2, 2);
        grid.getChildren().add(prob_value);

        grid.setVgap(10);

		grid_label = new Label("grid size n x n: ");
		grid_size = new Slider();
		grid_size.setMin(10);
		grid_size.setMax(50);
		grid_size.setBlockIncrement(5);
		grid_value = new Label(Double.toString(grid_size.getValue()));
        GridPane.setConstraints(grid_label, 0, 2);
		grid.getChildren().add(grid_label);
		GridPane.setConstraints(grid_size, 1, 2);
		grid.getChildren().add(grid_size);
		GridPane.setConstraints(grid_value, 2, 2);
        grid.getChildren().add(grid_value);
        
        grid.setVgap(10);
        
	    delay_label = new Label("delay: ");
		delay = new Slider();
		delay.setMin(0.0);
		delay.setMax(3000);
		delay.setBlockIncrement(200);
		delay_value = new Label(Double.toString(delay.getValue()));
		GridPane.setConstraints(delay_label, 0, 3);
		grid.getChildren().add(delay_label);
		GridPane.setConstraints(delay, 1, 3);
		grid.getChildren().add(delay);
		GridPane.setConstraints(delay_value, 2, 3);
        grid.getChildren().add(delay_value);
		
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


        grid.setVgap(200);
        grid.setHgap(11);
        grid.add(start, 0, 3, 1, 1);

        grid.add(reset, 1, 3, 1, 1);
        grid.add(stop, 2, 3, 1, 1);
        
        grid.add(step, 3, 3, 1, 1);
        grid.add(load, 4, 3, 1, 1);

	}


	
	public static void main(String[] args) {
		launch(args);
	}

}
