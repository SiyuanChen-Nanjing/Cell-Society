package simulations;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import cells.*;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import javafx.scene.control.Slider;
import javafx.scene.text.Text;

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
	
	/**
	public void readConfiguration(File file) throws SAXException, IOException, ParserConfigurationException {
		double cell_size = Main.GRID_SIZE/(double)myNumCells;
		
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
			int row_num = Integer.parseInt(row.item(i).getFirstChild().getNodeValue());
			int col_num = Integer.parseInt(col.item(i).getFirstChild().getNodeValue());
			String code = "cells.get(row_num).set(col_num, new " 
					+ type.item(i).getFirstChild().getNodeValue() 
					+ "Cell(Double.parseDouble(xpos.item(i).getFirstChild().getNodeValue()), Double.parseDouble(xpos.item(i).getFirstChild().getNodeValue()),cell_size, cell_size, row_num, col_num))";
			shell.evaluate(code);
		}
		myCells = cells;
	}
	**/
	
	public int getMyNumCells() {
		return myNumCells;
	}

}
