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
	
	public String getMyCellType1() {
		return myCellType1;
	}

	public String getMyCellType2() {
		return myCellType2;
	}

	public Simulation(int numCells) {
		myNumCells = numCells;
	}
	
	public List<List<Cell>> getMyCells() {
		return Collections.unmodifiableList(myCells);
	}
	
	public abstract void evolve();
	
	public abstract void initialize() ;
	
	protected abstract void setCount();
	
	public ArrayList<Cell> getNeighbors(int i, int j) {
		ArrayList<Cell> neighbors = new ArrayList<>();
		neighbors.add(myCells.get(i-1).get(j-1)); 
		neighbors.add(myCells.get(i-1).get(j)); 
		neighbors.add(myCells.get(i-1).get(j+1));
		neighbors.add(myCells.get(i).get(j-1)); 
		neighbors.add(myCells.get(i).get(j+1));
		neighbors.add(myCells.get(i+1).get(j-1)); 
		neighbors.add(myCells.get(i+1).get(j)); 
		neighbors.add(myCells.get(i+1).get(j+1));
		return neighbors;
	}
	
	public ArrayList<Cell> getFourNeighbors(int i, int j) {
		ArrayList<Cell> neighbors = new ArrayList<>();
		neighbors.add(myCells.get(i-1).get(j));
		neighbors.add(myCells.get(i).get(j-1)); 
		neighbors.add(myCells.get(i).get(j+1));
		neighbors.add(myCells.get(i+1).get(j));
		return neighbors;
	}

	public int getMyCellCount1() {
		return myCellCount1;
	}

	public int getMyCellCount2() {
		return myCellCount2;
	}
	
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
		size.setMinorTickCount(1);
		return size;
	}
	
	public abstract void readConfiguration(File file, Stage stage) throws SAXException, IOException, ParserConfigurationException;
	
	public int getMyNumCells() {
		return myNumCells;
	}

}
