package main;

import java.io.File;
import java.util.ArrayList;

import cells.Cell;
import exceptions.XMLException;
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
import javafx.stage.FileChooser;
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
	private boolean isRunning = true;
	private Simulation mySimulation;
	private ArrayList<ArrayList<Cell>> myCells;
	private Scene myScene;
	private Group myRoot;
	private Slider prob;
	private Slider gridSize;
	private Slider delay;
	private Label probLabel;
	private Label probValue;
	private Label gridLabel;
	private Label gridValue;
	private Label delayLabel;
	private Label delayValue;
	private int simulation_size;
	private File myFile;


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
		if (isRunning) {
			// attach "game loop" to timeline to play it
			KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
					e -> step(SECOND_DELAY));

			Timeline animation = new Timeline();
			animation.setCycleCount(Timeline.INDEFINITE);
			animation.getKeyFrames().add(frame);
			animation.play();
		}
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
		setSettings(myRoot, myScene);

		return scene;
	}

	private void step(double timeElapsed) {
		if (isRunning) {
			mySimulation.evolve();
			myRoot.getChildren().clear();
			//		setSettings here will make it reset everytime..here.
			myCells = mySimulation.getMyCells();
			for (int i=1;i<myCells.size()-1;i++) 
				for (int j = 1; j<myCells.size()-1;j++) myRoot.getChildren().add(myCells.get(i).get(j).getMyRectangle());
			setSettings(myRoot, myScene);
		}
		setSettings(myRoot, myScene);		
	}


	public void setSettings(Group root, Scene scene) {
		GridPane sliders = new GridPane();
		sliders.setPadding(new Insets(10, 10, 10, 10));
		sliders.setVgap(200);
		sliders.setHgap(50);
		//	    sliders.setGridLinesVisible(true);

		probLabel = new Label("probability: ");
		prob = new Slider();
		prob.setMin(0.0);
		prob.setMax(1.0);
		prob.setBlockIncrement(0.05);
		probValue = new Label(Double.toString(prob.getValue()));
		GridPane.setConstraints(probLabel, 0, 44);
		sliders.getChildren().add(probLabel);
		GridPane.setConstraints(prob, 1, 44);
		sliders.getChildren().add(prob);
		GridPane.setConstraints(probValue, 2, 44);
		sliders.getChildren().add(probValue);

		sliders.setVgap(10);

		gridLabel = new Label("grid size n x n: ");
		gridSize = new Slider();
		gridSize.setMin(10);
		gridSize.setMax(50);
		gridSize.setBlockIncrement(5);
		gridValue = new Label(Double.toString(gridSize.getValue()));
		GridPane.setConstraints(gridLabel, 0, 48);
		sliders.getChildren().add(gridLabel);
		GridPane.setConstraints(gridSize, 1, 48);
		sliders.getChildren().add(gridSize);
		GridPane.setConstraints(gridValue, 2, 48);
		sliders.getChildren().add(gridValue);

		sliders.setVgap(10);

		delayLabel = new Label("delay: ");
		delay = new Slider();
		delay.setMin(0.0);
		delay.setMax(3000);
		delay.setBlockIncrement(200);
		delayValue = new Label(Double.toString(delay.getValue()));
		GridPane.setConstraints(delayLabel, 0, 52);
		sliders.getChildren().add(delayLabel);
		GridPane.setConstraints(delay, 1, 52);
		sliders.getChildren().add(delay);
		GridPane.setConstraints(delayValue, 2, 52);
		sliders.getChildren().add(delayValue);

		myRoot.getChildren().add(sliders);


		GridPane buttons = new GridPane();

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
				isRunning = false; 
				setupScene(SCENE_WIDTH, SCENE_HEIGHT, mySimulation);
				isRunning = true;
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
		load.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				isRunning = false;
				openFileChooser(new FileChooser());
			}
		});


		buttons.setVgap(200);
		buttons.setHgap(11);
		buttons.add(start, 0, 3, 1, 1);

		buttons.add(reset, 1, 3, 1, 1);
		buttons.add(stop, 2, 3, 1, 1);

		buttons.add(step, 3, 3, 1, 1);
		buttons.add(load, 4, 3, 1, 1);
		myRoot.getChildren().add(buttons);

	}


	private void openFileChooser (FileChooser chooseFile) {
		myFile = chooseFile.showOpenDialog(new Stage());
		if (myFile != null) {
			openFile(myFile);
		}
	}

	public void openFile (File file) throws XMLException {
		try {
			String filePath = file.getAbsolutePath();

		}
		catch (XMLException xmlexcept) {
			throw new XMLException(xmlexcept, "XMLException");
		}
	}
	public File getFile() {
		return myFile;
	}

	public static void main(String[] args) {
		launch(args);
	}

}



