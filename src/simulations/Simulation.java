package simulations;

import java.util.ArrayList;

import cells.Cell;

public abstract class Simulation {
	
	protected int myNumCells;
	protected ArrayList<ArrayList<Cell>> myCells;
	
	public Simulation(int numCells) {
		myNumCells = numCells;
	}
	
	public ArrayList<ArrayList<Cell>> getMyCells() {
		return myCells;
	}
	
	public abstract void evolve();
	
	public abstract void initialize();
	
	public ArrayList<Cell> getNeighbors(int i, int j) {
		ArrayList<Cell> neighbors = new ArrayList<>();
		neighbors.add(myCells.get(i-1).get(j-1)); neighbors.add(myCells.get(i-1).get(j)); neighbors.add(myCells.get(i-1).get(j+1));
		neighbors.add(myCells.get(i).get(j-1)); neighbors.add(myCells.get(i).get(j+1));
		neighbors.add(myCells.get(i+1).get(j-1)); neighbors.add(myCells.get(i+1).get(j)); neighbors.add(myCells.get(i+1).get(j+1));
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
}
