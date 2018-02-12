package simulations;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import cells.BlueCell;
import cells.Cell;
import cells.EmptyCell;
import cells.RedCell;
import javafx.scene.control.Alert;
import javafx.scene.control.Slider;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.Main;
import main.XMLReader;

public class Segregation extends Simulation {
	
	private double myEmptyPercent = 0.1;
	private double myRedBlueRatio = 1;
	private double myRedPercent = (1-myEmptyPercent)*(myRedBlueRatio/(myRedBlueRatio+1));
	private double myBluePercent = 1-myEmptyPercent-myRedPercent;
	private double myMinSatisfaction;
	
	private ArrayList<Point> emptyPoints;

	/**
	 * Default constructor for Segregation Simulation
	 * @param numCells number of cells on one side of a grid
	 */
	public Segregation(int numCells) {
		super(numCells);
		myCellType1 = "Red";
		myCellType2 = "Blue";
	}
	
	@Override
	/**
	 * how a grid of segregation cells update based on segregation rules
	 */
	public void evolve() {
		List<List<Cell>> updatedCells = new ArrayList<>(myCells);
		for (int i = 1; i < myCells.size()-1;i++) {
			for (int j = 1; j < myCells.size()-1;j++) {
				Cell current = myCells.get(i).get(j);
				double satisfaction = 0;
				if (current.isRed()) {
					satisfaction = countRedInNeighborsPercent(i,j);
				}
				else if (current.isBlue()) {
					satisfaction = countBlueInNeighborsPercent(i,j);
				}
				else {
					satisfaction = 100; // eliminate the possibility to move empty cells
				}
				if (satisfaction < myMinSatisfaction) {
					Point point = emptyPoints.get((int)(Math.random()*(emptyPoints.size()-1)));
					Cell c = myCells.get(point.getMyRow()).get(point.getMyCol());
					if (current.isBlue()) {
						updatedCells.get(point.getMyRow()).set(point.getMyCol(), 
								new BlueCell(c.getMyRectangle().getX(),c.getMyRectangle().getY(),c.getMyRectangle().getWidth(),c.getMyRectangle().getHeight(),i,j));
					}
					else if (current.isRed()) {
						updatedCells.get(point.getMyRow()).set(point.getMyCol(), 
								new RedCell(c.getMyRectangle().getX(),c.getMyRectangle().getY(),c.getMyRectangle().getWidth(),c.getMyRectangle().getHeight(),i,j));
					}
					updatedCells.get(i).set(j, new EmptyCell(current.getMyRectangle().getX(),current.getMyRectangle().getY(),current.getMyRectangle().getWidth(),current.getMyRectangle().getHeight(),i,j));
					emptyPoints.remove(point);
					emptyPoints.add(new Point(i,j));
				}
			}
		}
		myCells = updatedCells;
	}
	
	/**
	 * 
	 * @param minSatisfaction input fraction that is the barrier of satisfaction
	 */
	public void setMyMinSatisfaction(double minSatisfaction) {
		myMinSatisfaction = minSatisfaction;
	}

	private double countRedInNeighborsPercent(int i, int j) {
		double count = 0;
		double total = 0;
		for (Cell c: getNeighbors(i,j)) {
			if (!c.isEmpty()) {
				total++;
				if (c.isRed()) {
					count++;
				}
			}
		}
		return count/total;
	}
	
	private double countBlueInNeighborsPercent(int i, int j) {
		double count = 0;
		double total = 0;
		for (Cell c: getNeighbors(i,j)) {
			if (!c.isEmpty()) {
				total++;
				if (c.isBlue()) {
					count++;
				}
			}
		}
		return count/total;
	}
	
	protected void setCount() {
		for (List<Cell> col:myCells) {
			for (Cell c: col) {
				if (c.isBlue()) myCellCount2++;
				else if (c.isRed()) myCellCount1++;
			}
		}
	}
	
	@Override
	/**
	 * how a segregation simulation initializes the grid based on percentage of each type of cells
	 */
	public void initialize() {
		int numCells = myNumCells;
		emptyPoints = new ArrayList<>();
		double cell_size = Main.GRID_SIZE/(double)numCells;
		List<List<Cell>> cells = new ArrayList<>();
		for (int i = 0; i < numCells+2; i++) {
			ArrayList<Cell> row = new ArrayList<>();
			for (int j = 0; j < numCells+2; j++) {
				if (i==0 || i==numCells+1 || j==0 || j==numCells+1) {
					row.add(new EmptyCell(-1,-1,0,0,i,j));
				}
				else {
					double random = Math.random();
					if (random < myRedPercent) {
						row.add(new RedCell((i-1)*cell_size,(j-1)*cell_size,cell_size,cell_size,i,j));
					}
					else if (random < myRedPercent + myBluePercent) {
						row.add(new BlueCell((i-1)*cell_size,(j-1)*cell_size,cell_size,cell_size,i,j));
					}
					else {
						row.add(new EmptyCell((i-1)*cell_size,(j-1)*cell_size,cell_size,cell_size,i,j));
						emptyPoints.add(new Point(i,j));
					}
				}
			}
			cells.add(row);
		}
		myCells = cells;
		setCount();
	}
	
	/**
	 * 
	 * @param empty input percentage of empty cells
	 */
	public void setEmptyPercent(double empty) {
		myEmptyPercent = empty;
	}
	
	/**
	 * 
	 * @param ratio input ratio of red and blue cells
	 */
	public void setRatio(double ratio) {
		myRedBlueRatio = ratio;
	}
	
	@Override
	/**
	 * read and set initial configuration from an XML file
	 */
	public void readConfiguration(File file, Stage stage) throws SAXException, IOException, ParserConfigurationException {
		double cell_size = Main.GRID_SIZE/(double)myNumCells;
		Document doc = XMLReader.read(file);
		List<List<Cell>> cells = new ArrayList<>(myCells);
		NodeList type = doc.getElementsByTagName("Type");
		NodeList xpos = doc.getElementsByTagName("XPos");
		NodeList ypos = doc.getElementsByTagName("YPos");
		NodeList row = doc.getElementsByTagName("Row");
		NodeList col = doc.getElementsByTagName("Column");
		for (int i=0;i<type.getLength();i++) {
			int row_num = Integer.parseInt(row.item(i).getFirstChild().getNodeValue());
			int col_num = Integer.parseInt(col.item(i).getFirstChild().getNodeValue());
			if (type.item(i).getFirstChild().getNodeValue().equals("Empty")) cells.get(row_num).set(col_num, new EmptyCell(Double.parseDouble(xpos.item(i).getFirstChild().getNodeValue()), Double.parseDouble(ypos.item(i).getFirstChild().getNodeValue()),cell_size, cell_size, row_num, col_num));
			else if (type.item(i).getFirstChild().getNodeValue().equals("Red")) cells.get(row_num).set(col_num, new RedCell(Double.parseDouble(xpos.item(i).getFirstChild().getNodeValue()), Double.parseDouble(ypos.item(i).getFirstChild().getNodeValue()),cell_size, cell_size, row_num, col_num));
			else if (type.item(i).getFirstChild().getNodeValue().equals("Blue")) cells.get(row_num).set(col_num, new BlueCell(Double.parseDouble(xpos.item(i).getFirstChild().getNodeValue()), Double.parseDouble(ypos.item(i).getFirstChild().getNodeValue()),cell_size, cell_size, row_num, col_num));
			else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setContentText("The cell state you indicated does not match the current simulation.");
				alert.showAndWait();
				FileChooser fc = new FileChooser();
				File f = fc.showOpenDialog(stage);
				readConfiguration(f,stage);
				return;
			}
		}
		myCells = cells;
		setCount();
	}
	
	@Override
	/**
	 * dynamic changer of minSatisfaction
	 */
	public Slider parameter1Slider(Text text) {
		Slider satisfaction = new Slider(0,1,myMinSatisfaction);
		satisfaction.valueProperty().addListener((observable, oldvalue, newvalue) ->
        {
            myMinSatisfaction = (int)(newvalue.doubleValue()*1000)/1000.0;
            text.setText("Minimum satisfaction: " + myMinSatisfaction);
        } );
		satisfaction.setLayoutX(410);
		satisfaction.setLayoutY(370);
		return satisfaction;
	}

	/**
	 * 
	 * @return myMinSatisfaction
	 */
	public double getMyMinSatisfaction() {
		return myMinSatisfaction;
	}
}
