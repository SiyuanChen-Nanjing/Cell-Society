package main;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import cells.Cell;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import simulations.Simulation;

public class Main extends Application {

	public static final int GRID_SIZE = 400;
    public static final int SCENE_WIDTH = GRID_SIZE;
    public static final int SCENE_HEIGHT = GRID_SIZE + 200;
    public static final Paint BACKGROUND = Color.WHITE;
	
    public static final int FRAME_RATE = 3;
    public static final int MILLISECOND_DELAY = 1000 / FRAME_RATE;
    public static final double SECOND_DELAY = 1.0 / FRAME_RATE;
    public static final String XML_ERROR_MSG = "The file you have chosen has the wrong format.";
    
    private Stage myStage;
    private Simulation mySimulation;
    private List<List<Cell>> myCells;
    private Scene myScene;
    private Group myButtonRoot;
    private Group myGridRoot;
    private Timeline myAnimation;
    private File myCurrentFile;
    
    private Button myStartButton;
    private Button myPauseButton;
    private Button myResumeButton;
    private Button myStepButton;
    private Button myLoadButton;
    private Button myFasterButton;
    private Button mySlowerButton;
    private Button myRestartButton;
    private Button myRecordButton;


	@Override
	/**
	 * Initialize animation by reading the XML file and create a scene according to information stored in the 
	 * file
	 */
	public void start(Stage stage) throws SAXException, IOException, ParserConfigurationException {
		FileChooser fc = new FileChooser();
		File file = fc.showOpenDialog(stage);
		myCurrentFile = file;
		Simulation simulation = XMLReader.setupSimulation(file);
		myScene = setupScene(SCENE_WIDTH, SCENE_HEIGHT, simulation);
		String title = XMLReader.getTitle(file);

		stage.setScene(myScene);
        stage.setTitle(title);
        stage.show();
        myStage = stage;
        // attach "game loop" to timeline to play it
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
                                      e -> step(SECOND_DELAY));
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        myAnimation = animation;

	}

	// setup the main scene with given Simulation input object
	private Scene setupScene(int width, int height, Simulation simulation) {
		Group root = new Group();
		Group buttonRoot = new Group();
		Group gridRoot = new Group();
		
		root.getChildren().add(buttonRoot);
		root.getChildren().add(gridRoot);
		
		Scene scene = new Scene(root, width, height, BACKGROUND);

		myCells = simulation.getMyCells();
		mySimulation = simulation;
		for (int i=1;i<myCells.size()-1;i++) {
			for (int j = 1; j<myCells.size()-1;j++) {
				gridRoot.getChildren().add(myCells.get(i).get(j).getMyRectangle());
			}
		}
		myButtonRoot = buttonRoot;
		myGridRoot = gridRoot;
		
		setupUI("assets.ButtonText");
		loadUI();

		return scene;
	}

	// update grid in each cycle
	private void step(double timeElapsed) {
		mySimulation.evolve();
		myGridRoot.getChildren().clear();
		myCells = mySimulation.getMyCells();
		for (int i=1;i<myCells.size()-1;i++) {
			for (int j = 1; j<myCells.size()-1;j++) {
				myGridRoot.getChildren().add(myCells.get(i).get(j).getMyRectangle());
			}
		}
	}
	
	// load all the UI nodes into the group containing all the buttons
	private void loadUI() {
		myButtonRoot.getChildren().add(myStartButton);
		myButtonRoot.getChildren().add(myPauseButton);
		myButtonRoot.getChildren().add(myResumeButton);
		myButtonRoot.getChildren().add(myStepButton);
		myButtonRoot.getChildren().add(myLoadButton);
		myButtonRoot.getChildren().add(myFasterButton);
		myButtonRoot.getChildren().add(mySlowerButton);
		myButtonRoot.getChildren().add(myRestartButton);
		myButtonRoot.getChildren().add(myRecordButton);
	}
	
	private void setupUI(String filename) {
		ResourceBundle rb = ResourceBundle.getBundle(filename);
		myStartButton = createStartButton(rb.getString("StartKey"));
		myPauseButton = createPauseButton(rb.getString("PauseKey"));
		myResumeButton = createResumeButton(rb.getString("ResumeKey"));
		myStepButton = createStepButton(rb.getString("StepKey"));
		myLoadButton = createLoadButton(rb.getString("LoadKey"));
		myFasterButton = createFasterButton(rb.getString("FasterKey"));
		mySlowerButton = createSlowerButton(rb.getString("SlowerKey"));
		myRestartButton = createRestartButton(rb.getString("RestartKey"));
		myRecordButton = createRecordButton(rb.getString("RecordKey"));
	}
	
	private Button createStartButton(String txt) {
		Button start = new Button(txt);
		start.setLayoutX(50);
		start.setLayoutY(500);
		start.setOnMouseClicked(e -> {
			myAnimation.play();
		});
		return start;
	}
	
	private Button createPauseButton(String txt) {
		Button pause = new Button(txt);
		pause.setLayoutX(100);
		pause.setLayoutY(500);
		pause.setOnMouseClicked(e -> {
			myAnimation.pause();
		});
		return pause;
	}
	
	private Button createResumeButton(String txt) {
		Button resume = new Button(txt);
		resume.setLayoutX(170);
		resume.setLayoutY(500);
		resume.setOnMouseClicked(e -> {
			myAnimation.play();
		});
		return resume;
	}
	
	private Button createStepButton(String txt) {
		Button stop = new Button(txt);
		stop.setLayoutX(250);
		stop.setLayoutY(500);
		stop.setOnMouseClicked(e -> {
			myAnimation.pause();
			step(SECOND_DELAY);
		});
		return stop;
	}
	
	private Button createLoadButton(String txt) {
		Button load = new Button(txt);
		load.setLayoutX(300);
		load.setLayoutY(500);
		load.setOnMouseClicked(e -> {
			myAnimation.stop();
			FileChooser fc = new FileChooser();
			fc.setTitle("Load Configuration XML File");
			File file = fc.showOpenDialog(myStage);
			try {
				changeSimulation(file);
			} catch (SAXException | IOException | ParserConfigurationException e1) {
				throw new IllegalArgumentException(XML_ERROR_MSG);
			}
		});
		return load;
	}
	
	private Button createRestartButton(String txt) {
		Button restart = new Button(txt);
		restart.setLayoutX(180);
		restart.setLayoutY(450);
		restart.setOnMouseClicked(e -> {
			try {
				changeSimulation(myCurrentFile);
				myAnimation.pause();
			} catch (SAXException | IOException | ParserConfigurationException e1) {
				throw new IllegalArgumentException(XML_ERROR_MSG);
			}
		});
		return restart;
	}
	
	private Button createFasterButton(String txt) {
		Button faster = new Button(txt);
		faster.setLayoutX(100);
		faster.setLayoutY(550);
		faster.setOnMouseClicked(e -> {
			myAnimation.setRate(myAnimation.getRate()*1.5);
		});
		return faster;
	}
	
	private Button createSlowerButton(String txt) {
		Button slower = new Button(txt);
		slower.setLayoutX(200);
		slower.setLayoutY(550);
		slower.setOnMouseClicked(e -> {
			myAnimation.setRate(myAnimation.getRate()*0.5);
		});
		return slower;
	}
	
	private Button createRecordButton(String txt) {
		Button record = new Button(txt);
		record.setLayoutX(270);
		record.setLayoutY(550);
		record.setOnMouseClicked(e -> {
			File file = new FileChooser().showSaveDialog(myStage);
			try {
				writeConfig(file, mySimulation.getMyCells());
			} catch (ParserConfigurationException | TransformerException e1) {
				throw new IllegalArgumentException("Cannot write the current configuration into indicated file");
			}
		});
		return record;
	}
	
	private static void writeConfig(File file, List<List<Cell>> cells) throws ParserConfigurationException, TransformerException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = factory.newDocumentBuilder();
		Document doc = db.newDocument();
		Element root = doc.createElement("Configuration");
		doc.appendChild(root);
		
		for (List<Cell> column: cells) {
			for (Cell c: column) {
				Element cell = doc.createElement("Cell");
				root.appendChild(cell);
				
				Element name = doc.createElement("Type");
				name.appendChild(doc.createTextNode(c.getType()));
				cell.appendChild(name);
				
				Element xpos = doc.createElement("XPos");
				xpos.appendChild(doc.createTextNode(c.getMyRectangle().getX() + ""));
				cell.appendChild(xpos);
				
				Element ypos = doc.createElement("YPos");
				ypos.appendChild(doc.createTextNode(c.getMyRectangle().getY() + ""));
				cell.appendChild(ypos);
				
				Element row = doc.createElement("Row");
				row.appendChild(doc.createTextNode(c.getMyGridX() + ""));
				cell.appendChild(row);
				
				Element col = doc.createElement("Column");
				col.appendChild(doc.createTextNode(c.getMyGridY() + ""));
				cell.appendChild(col);
			}
		}
		
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(file);
		
		transformer.transform(source, result);
	}
	
	private void changeSimulation(File file) throws SAXException, IOException, ParserConfigurationException {
		Simulation simulation = XMLReader.setupSimulation(file);
		simulation.initialize();
		myScene = setupScene(SCENE_WIDTH, SCENE_HEIGHT, simulation);
		myStage.setScene(myScene);
		myCurrentFile = file;
	}
	
	/**
	 * start game
	 */
	public static void main(String[] args) {
		launch(args);
	}

}