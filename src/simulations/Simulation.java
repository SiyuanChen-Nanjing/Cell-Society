package simulations;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import cells.Cell;
import main.XMLReader;

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
	
	/**
	public void readConfiguration(File file) throws SAXException, IOException, ParserConfigurationException {
		Document doc = XMLReader.read(file);
		List<List<Cell>> cells = new ArrayList<>(myCells);
		NodeList type = doc.getElementsByTagName("Type");
		NodeList xpos = doc.getElementsByTagName("XPos");
		NodeList ypos = doc.getElementsByTagName("YPos");
		NodeList row = doc.getElementsByTagName("Row");
		NodeList col = doc.getElementsByTagName("Column");
		for (int i=0;i<type.getLength();i++) {
			Binding binding = new Binding();
			GroovyShell shell = new GroovyShell(binding);
			cells.get(Integer.parseInt(row.item(i).getFirstChild().getNodeValue())).set(index, element)

		}
	}
	**/
}
