package simulations;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import cells.AntCell;
import cells.BurningCell;
import cells.Cell;
import cells.EmptyCell;
import cells.ForagingAntCell;
import cells.TreeCell;
import javafx.scene.control.Slider;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.Main;

public class ForagingAnts extends Simulation{
	
	private ArrayList<AntCell> myAnts;
	private ArrayList<AntCell> myNextAnts;
	private ForagingAntCell myNests;
	private ArrayList<Point> myFoodCells;
	private double myFoodPh;
	private double myHomePh;
	private double maxPh;
	
	public ForagingAnts(int numCells) {
		super(numCells);
	}
	
	public void forage (AntCell ant, boolean food) {
		if (ant.hasFoodItem()) {
			returnToNest(ant);
		} else {
			findFoodSource(ant, food);
		}
	}
	
	private ForagingAntCell getDirections(AntCell ant, boolean food) {
		ArrayList<Cell> neighbors = new ArrayList<>();
		ForagingAntCell bestCell  = null;
		return ant.getBestNeighbor(neighbors, food);
	}
	
	public void returnToNest(AntCell ant) {
		if (isAtFoodSource(ant)) {
			ant.getFood();
		}
		
	}
	
	//pseudocode
	public void findFoodSource(AntCell ant, boolean food){
		if (isAtNest(ant)) {
		}
		Point x = selectLocation(forward locations);
		if (x == null) {
			x = selectLocation(neighbor locations);
		}
		if (x != null) {
			dropPheromones(ant, food);
			if (isAtFoodSource(ant)) {
				hasFoodItem = true;
			}
		}
	}
	
	//pseudocode
	public Point selectLocation(ArrayList<Point> locSet) {
		locSet = locSet - obstacles;
		locSet = locSet - locationsWithTooManyAnts;
		if (locSet == null) {
			return null;
		} else {
			select a location from locSet where each location is
			chosen with probability (k = foodPheromnesAtLocation)
		}
	}
	


	public void dropPheromones(AntCell ant, boolean food) {	
		ForagingAntCell cell = new ForagingAntCell(maxPh, myCellCount1, maxPh, maxPh, maxPh, maxPh, maxPh, myCellCount1, myCellCount1);

		if (isAtFoodSource(ant)) {
			if (isAtNest(ant)) {
				cell.setMaxPh(false);
			} else {
				cell.addPh(false);
			}
		} else {
			if (isAtFoodSource(ant)) {
				cell.setMaxPh(true);
			} else {
				cell.addPh(true);
			}
		}
	}
	
	private boolean isAtNest(AntCell ant) {
		return (myNests.getMyGridX()==(ant.getMyGridX())) && (myNests.getMyGridY() == (ant.getMyGridY()));
	}
	
	private boolean isAtFoodSource(AntCell ant) {
		return myFoodCells.contains(ant.getMyGridX()) && myFoodCells.contains(ant.getMyGridY());
	}
	
	
	@Override
	public void evolve() {
		List<List<Cell>> updatedCells = new ArrayList<>(myCells);
		for (int i = 1; i < myCells.size()-1;i++) {
			for (int j = 1; j < myCells.size()-1;j++) {
				Cell current = myCells.get(i).get(j);
				if (current.isAnt()) {
				} else if (current.isForaging()) {
				}
			}
		}
		myCells = updatedCells;
		
	}
	
	@Override
	public void initialize() {
		int numCells = myNumCells;
		double cell_size = Main.GRID_SIZE/(double)numCells;
		List<List<Cell>> cells = new ArrayList<>();
		for (int i = 0; i < numCells+2; i++) {
			ArrayList<Cell> row = new ArrayList<Cell>();
			for (int j = 0; j < numCells+2; j++) {
				if ((i==5 && j == 10) || (i == 20 && j == 40)) {
					row.add(new ForagingAntCell((i-1)*cell_size,j, (j-1)*cell_size,cell_size,cell_size, i ,j, j, j));
				}
				else  {
					row.add(new AntCell((i-1)*cell_size,(j-1)*cell_size,cell_size,cell_size, i , j));
				}
			}
			cells.add(row);
		}
		myCells = cells;
		
	}

	@Override
	protected void setCount() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void readConfiguration(File file, Stage stage)
			throws SAXException, IOException, ParserConfigurationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Slider parameter1Slider(Text text) {
		// TODO Auto-generated method stub
		return null;
	}
}
