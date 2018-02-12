package simulations;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import cells.AliveCell;
import cells.Cell;
import cells.DeadCell;
import cells.EmptyCell;
import javafx.scene.control.Alert;
import javafx.scene.control.Slider;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.Main;
import main.XMLReader;

public class GameOfLife extends Simulation {

	private double myAliveDeadRatio = 2;
	private double myAlivePercent = myAliveDeadRatio/(myAliveDeadRatio+1);
	
	public GameOfLife(int numCells) {
		super(numCells);
		myCellType1 = "Alive";
		myCellType2 = "Dead";
	}

	private int countAliveNeighbors(int i, int j) {
		ArrayList<Cell> neighbors = getNeighbors(i,j);
		int count = 0;
		for (Cell c : neighbors) {
			if (c.isAlive()) {
				count++;
			}
		}
		return count;
	}
	
	public void setRatio(double ratio) {
		myAliveDeadRatio = ratio;
	}
	
	@Override
	public void evolve() {
		List<List<Cell>> updatedCells = new ArrayList<>(myCells);
		for (int i = 1; i < myCells.size()-1;i++) {
			for (int j = 1; j < myCells.size()-1;j++) {
				Cell current = myCells.get(i).get(j);
				int numAlive = countAliveNeighbors(i,j);
				if (current.isAlive()) {
					if (numAlive<2 || numAlive>3) {
						updatedCells.get(i).set(j, new DeadCell(current.getMyRectangle().getX(),current.getMyRectangle().getY(),
								current.getMyRectangle().getWidth(),current.getMyRectangle().getHeight(),i,j));
						myCellCount1--;
						myCellCount2++;
					}
				}
				else if (current.isDead() && numAlive==3) {
					updatedCells.get(i).set(j, new AliveCell(current.getMyRectangle().getX(),current.getMyRectangle().getY(),
							current.getMyRectangle().getWidth(),current.getMyRectangle().getHeight(),i,j));
					myCellCount1++;
					myCellCount2--;
				}
			}
		}
		myCells = updatedCells;
	}
	
	protected void setCount() {
		for (List<Cell> col:myCells) {
			for (Cell c: col) {
				if (c.isDead()) myCellCount2++;
				else if (c.isAlive()) myCellCount1++;
			}
		}
	}

	@Override
	public void initialize() {
		int numCells = myNumCells;
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
					if (random < myAlivePercent) {
						row.add(new AliveCell((i-1)*cell_size,(j-1)*cell_size,cell_size,cell_size,i,j));
					}
					else {
						row.add(new DeadCell((i-1)*cell_size,(j-1)*cell_size,cell_size,cell_size,i,j));
					}
				}
			}
			cells.add(row);
		}
		myCells = cells;
		setCount();
	}

	@Override
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
			else if (type.item(i).getFirstChild().getNodeValue().equals("Alive")) cells.get(row_num).set(col_num, new AliveCell(Double.parseDouble(xpos.item(i).getFirstChild().getNodeValue()), Double.parseDouble(ypos.item(i).getFirstChild().getNodeValue()),cell_size, cell_size, row_num, col_num));
			else if (type.item(i).getFirstChild().getNodeValue().equals("Dead")) cells.get(row_num).set(col_num, new DeadCell(Double.parseDouble(xpos.item(i).getFirstChild().getNodeValue()), Double.parseDouble(ypos.item(i).getFirstChild().getNodeValue()),cell_size, cell_size, row_num, col_num));
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
	public Slider parameter1Slider(Text text) {
		return null;
	}
	
}
