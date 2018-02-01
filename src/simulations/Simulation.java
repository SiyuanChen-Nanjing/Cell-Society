package simulations;

import java.util.ArrayList;

import cells.Cell;

public abstract class Simulation {
	
	double myParameter;
	int mySize;
	private ArrayList<ArrayList<Cell>> myCells;
	
	public ArrayList<ArrayList<Cell>> getMyCells() {
		return myCells;
	}

	public abstract void evolve(Cell c);
	
	public abstract ArrayList<ArrayList<Cell>> initialize();
}
