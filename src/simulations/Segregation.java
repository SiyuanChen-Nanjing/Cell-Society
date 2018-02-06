package simulations;

import java.util.ArrayList;

import cells.BlueCell;
import cells.Cell;
import cells.EmptyCell;
import cells.RedCell;
import main.Main;

public class Segregation extends Simulation {
	
	private double myEmptyPercent = 0.1;
	private double myRedBlueRatio = 1;
	private double myRedPercent = (1-myEmptyPercent)*(myRedBlueRatio/(myRedBlueRatio+1));
	private double myBluePercent = 1-myEmptyPercent-myRedPercent;
	private double myMinSatisfaction;
	
	private ArrayList<Point> emptyPoints;

	public Segregation(int numCells) {
		super(numCells);
	}
	
	@Override
	public void evolve() {
		ArrayList<ArrayList<Cell>> updatedCells = new ArrayList<>(myCells);
		for (int i = 1; i < myCells.size()-1;i++) {
			for (int j = 1; j < myCells.size()-1;j++) {
				Cell current = myCells.get(i).get(j);
				double satisfaction = 0;
				if (current.isRed()) satisfaction = countRedInNeighborsPercent(i,j);
				else if (current.isBlue()) satisfaction = countBlueInNeighborsPercent(i,j);
				else satisfaction = 100; // eliminate the possibility to move empty cells
				if (satisfaction < myMinSatisfaction) {
					Point point = emptyPoints.get((int)(Math.random()*(emptyPoints.size()-1)));
					Cell c = myCells.get(point.getMyRow()).get(point.getMyCol());
					if (current.isBlue()) {
						updatedCells.get(point.getMyRow()).set(point.getMyCol(), 
								new BlueCell(c.getMyRectangle().getX(),c.getMyRectangle().getY(),c.getMyRectangle().getWidth(),c.getMyRectangle().getHeight()));
					}
					else if (current.isRed()) {
						updatedCells.get(point.getMyRow()).set(point.getMyCol(), 
								new RedCell(c.getMyRectangle().getX(),c.getMyRectangle().getY(),c.getMyRectangle().getWidth(),c.getMyRectangle().getHeight()));
					}
					updatedCells.get(i).set(j, new EmptyCell(current.getMyRectangle().getX(),current.getMyRectangle().getY(),current.getMyRectangle().getWidth(),current.getMyRectangle().getHeight()));
					emptyPoints.remove(point);
					emptyPoints.add(new Point(i,j));
				}
			}
		}
		myCells = updatedCells;
	}
	
	public void setMyMinSatisfaction(double minSatisfaction) {
		myMinSatisfaction = minSatisfaction;
	}

	private double countRedInNeighborsPercent(int i, int j) {
		double count = 0;
		double total = 0;
		for (Cell c: getNeighbors(i,j)) {
			if (!c.isEmpty()) {
				total++;
				if (c.isRed()) count++;
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
				if (c.isBlue()) count++;
			}
		}
		return count/total;
	}
	
	@Override
	public void initialize() {
		int numCells = myNumCells;
		emptyPoints = new ArrayList<>();
		double cell_size = Main.GRID_SIZE/(double)numCells;
		ArrayList<ArrayList<Cell>> cells = new ArrayList<>();
		for (int i = 0; i < numCells+2; i++) {
			ArrayList<Cell> row = new ArrayList<Cell>();
			for (int j = 0; j < numCells+2; j++) {
				if (i==0 || i==numCells+1 || j==0 || j==numCells+1) row.add(new EmptyCell(-1,-1,0,0));
				else {
					double random = Math.random();
					if (random < myRedPercent) 
						row.add(new RedCell((i-1)*cell_size,(j-1)*cell_size,cell_size,cell_size));
					else if (random < myRedPercent + myBluePercent)
						row.add(new BlueCell((i-1)*cell_size,(j-1)*cell_size,cell_size,cell_size));
					else {
						row.add(new EmptyCell((i-1)*cell_size,(j-1)*cell_size,cell_size,cell_size));
						emptyPoints.add(new Point(i,j));
					}
				}
			}
			cells.add(row);
		}
		myCells = cells;
	}

}