package simulations;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import cells.*;
import javafx.scene.control.Slider;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public abstract class Simulation {

	protected int myNumCells;
	protected List<List<Cell>> myCells;

	protected String myCellType1;
	protected String myCellType2;

	protected int myCellCount1;
	protected int myCellCount2;

	/**
	 * Default constructor for a simulation
	 * @param numCells number of cells on one side of the grid
	 */
	public Simulation(int numCells) {
		myNumCells = numCells;
	}

	/**
	 *
	 * @return an unmodifiable list of cellular grid
	 */
	public List<List<Cell>> getMyCells() {
		return Collections.unmodifiableList(myCells);
	}

	/**
	 * how a grid updates itself
	 */
	public abstract void evolve();

	/**
	 * how the grid should be initialized
	 */
	public abstract void initialize() ;

	protected abstract void setCount();

	/**
	 * get 8 neighbors of a cell, edge cells are selected using the toroidal edge type
	 * @param i row number
	 * @param j cell number
	 * @return an arraylist of 8 neighbors
	 */
	public ArrayList<Cell> getNeighbors(int i, int j) {
		ArrayList<Cell> neighbors = new ArrayList<>();

		int maxRow = myCells.size();
		int maxCol = myCells.get(0).size();

  	int row = i;
		int col = j;
		int rowAbove = row - 1;
		int rowBelow = row + 1;
		int colAfter = col + 1;
		int colBefore = col - 1;

		if (col == 1) {
			colBefore = maxCol - 2;
		}
		if (col == maxCol - 2) {
			colAfter = 1;
		}
		if (row == 1) {
			rowAbove = maxRow - 2;
		}
		if (row == maxRow - 2) {
			rowBelow = 1;
		}
		neighbors.add(myCells.get(rowAbove).get(colBefore));
		neighbors.add(myCells.get(rowAbove).get(col));
		neighbors.add(myCells.get(rowAbove).get(colAfter));

		neighbors.add(myCells.get(row).get(colBefore));
		neighbors.add(myCells.get(row).get(colAfter));

		neighbors.add(myCells.get(rowBelow).get(colBefore));
		neighbors.add(myCells.get(rowBelow).get(col));
		neighbors.add(myCells.get(rowBelow).get(colAfter));

		return neighbors;
	}

	/**
	 * get 4 neighbors of a cell, edge cells are surrounded with pseudo-empty cells to make up the missing neighbors
	 * @param i row number
	 * @param j cell number
	 * @return an arraylist of 4 neighbors
	 */
	public ArrayList<Cell> getFourNeighbors(int i, int j) {
		ArrayList<Cell> neighbors = new ArrayList<>();
		neighbors.add(myCells.get(i-1).get(j));
		neighbors.add(myCells.get(i).get(j-1));
		neighbors.add(myCells.get(i).get(j+1));
		neighbors.add(myCells.get(i+1).get(j));
		return neighbors;
	}

	/**
	 * @return the myNumCells
	 */
	public int getMyNumCells() {
		return myNumCells;
	}

	/**
	 * @return the myCellType1
	 */
	public String getMyCellType1() {
		return myCellType1;
	}

	/**
	 * @return the myCellType2
	 */
	public String getMyCellType2() {
		return myCellType2;
	}

	/**
	 * @return the myCellCount1
	 */
	public int getMyCellCount1() {
		return myCellCount1;
	}

	/**
	 * @return the myCellCount2
	 */
	public int getMyCellCount2() {
		return myCellCount2;
	}

	/**
	 * dynamic changer of the size of the grid
	 */
	public Slider sizeBar(Text text) {
		Slider size = new Slider(10,50,myNumCells);
		size.valueProperty().addListener((observable, oldvalue, newvalue) ->
        {
            myNumCells = newvalue.intValue();
            myCellCount1 = 0;
            myCellCount2 = 0;
            initialize();
            text.setText("Size: " + myNumCells + "*" + myNumCells);
        } );
		size.setLayoutX(410);
		size.setLayoutY(310);
		return size;
	}

	/**
	 * read and set initial configuration from an XML file
	 */
	public abstract void readConfiguration(File file, Stage stage) throws SAXException, IOException, ParserConfigurationException;

	/**
	 * dynamic changer of a parameter (specific to a simulation)
	 */
	public abstract Slider parameter1Slider(Text text);


}
