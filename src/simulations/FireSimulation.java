package simulations;

import java.util.ArrayList;
import java.util.Random;

import cells.Cell;
import cells.EmptyCell;
import cells.RedCell;
import cells.BlueCell;
import cells.BurningCell;
import cells.TreeCell;
import main.Main;

public class FireSimulation extends Simulation{
	private double probCatch = 0.7;
	private int burningTime;

	private ArrayList<Point> emptyPoints;


	public FireSimulation(int numCells) {
		super(numCells);
	}

	public boolean hasBurningNeighbor (int i, int j) {
		for (Cell c: getFourNeighbors(i,j)) {
			if (c.isBurning()) {
				return true;
			}
		}
		return false;
	}


	@Override
	public void evolve() {
		ArrayList<ArrayList<Cell>> updatedCells = new ArrayList<>(myCells);
		double cell_size = Main.GRID_SIZE/(double)30;
//		System.out.println("size: "+myCells.size());
		for (int i = 1; i < myCells.size()-1; i++) {
			for (int j = 1; j < myCells.size() -1; j++) {
				Cell current = myCells.get(i).get(j);
				Random randomNumber = new Random();
				Point point = emptyPoints.get((int)((emptyPoints.size()-1)));
				Cell c = myCells.get(point.getMyRow()).get(point.getMyCol());

				//If empty, remains empty at the next time step.  
				if (current.isEmpty() || current.isBurning()) {
//					updatedCells.get(i).get(j).set(new YellowCell());
					updatedCells.get(point.getMyRow()).set(point.getMyCol(), 
							new EmptyCell(c.getMyRectangle().getX(),c.getMyRectangle().getY(),c.getMyRectangle().getWidth(),c.getMyRectangle().getHeight()));

				}
				//If burning, next empty
//				else if (current.isBurning()) {
////					((BurningCell)current).startBurnTimer();
//					updatedCells.get(point.getMyRow()).set(point.getMyCol(), 
//							new YellowCell(c.getMyRectangle().getX(),c.getMyRectangle().getY(),c.getMyRectangle().getWidth(),c.getMyRectangle().getHeight()));
//
//				}
				//If tree, may or may not catch fire 
				else if (current.isTree() && hasBurningNeighbor(i, j)) {
//					System.out.println(i +", "+j);
					// catch fire
					if (randomNumber.nextDouble() < probCatch) {
						updatedCells.get(point.getMyRow()).set(point.getMyCol(), 
								new BurningCell(c.getMyRectangle().getX(),c.getMyRectangle().getY(),c.getMyRectangle().getWidth(),c.getMyRectangle().getHeight()));

					} else {
						updatedCells.get(point.getMyRow()).set(point.getMyCol(), 
								new TreeCell(c.getMyRectangle().getX(),c.getMyRectangle().getY(),c.getMyRectangle().getWidth(),c.getMyRectangle().getHeight()));
//						((BurningCell)current).setBurnTimer(burningTime);
					}
				}
				emptyPoints.remove(point);
				emptyPoints.add(new Point(i,j));
			}	

		}
		myCells = updatedCells;
	}


	@Override
	public void initialize() {
		int numCells = myNumCells;

		double cell_size = Main.GRID_SIZE/(double)numCells;
		ArrayList<ArrayList<Cell>> cells = new ArrayList<>();
		for (int i = -1; i < numCells+1; i++) {
			ArrayList<Cell> row = new ArrayList<Cell>();
			for (int j = -1; j < numCells+1; j++) {
				if (i==0 || i==numCells-1 || j==0 || j==numCells-1) {
					row.add(new EmptyCell(i*cell_size, j*cell_size, cell_size, cell_size));
					emptyPoints.add(new Point(i+1, j+1));
				}
				else if (i == numCells/2 && j == numCells/2-1 ){
//					|| i == numCells/2 -1&& j == numCells/2-1) {
				
					row.add(new BurningCell(i*cell_size+1, j*cell_size, cell_size, cell_size));
				}
				else {
					row.add(new TreeCell(i*cell_size, j*cell_size, cell_size, cell_size));
				}
			}
			cells.add(row);
		}
		myCells = cells;
	}

}
