package simulations;

import java.util.ArrayList;
import java.util.Random;

import cells.Cell;
import cells.EmptyCell;
import cells.RedCell;
import cells.YellowCell;
import cells.BlueCell;
import cells.BurningCell;
import cells.TreeCell;
import main.Main;

public class FireSimulation extends Simulation{
	private double probCatch = 0.5;
	private int burningTime;

	private ArrayList<Point> emptyPoints;


	public FireSimulation(int numCells) {
		super(numCells);
	}

	public boolean hasBurningNeighbor (int i, int j) {
		for (Cell c: getNeighbors(i,j)) {
			if (c.isBurning()) {
				System.out.println(i + ", " + j);
				return true;
			}
		}
		return false;
	}


	@Override
	public void evolve() {
		ArrayList<ArrayList<Cell>> updatedCells = new ArrayList<>(myCells);
		for (int i = 1; i < myCells.size()-1; i++) {
			for (int j = 1; j < myCells.size() - 1; j++) {
				Cell current = myCells.get(i).get(j);
				Random randomNumber = new Random();
				Point point = emptyPoints.get((int)(1*(emptyPoints.size()-1)));
				System.out.println(point.getMyRow());
				Cell c = myCells.get(point.getMyRow()).get(point.getMyCol());

				//If empty, remains empty at the next time step.  
				if (current.isBurntEmpty() || current.isBurning()) {
					updatedCells.get(point.getMyRow()).set(point.getMyCol(), 
							new YellowCell(c.getMyRectangle().getX(),c.getMyRectangle().getY(),c.getMyRectangle().getWidth(),c.getMyRectangle().getHeight()));

				}
				//If burning, next empty
				else if (current.isBurning()) {
//					((BurningCell)current).startBurnTimer();
					updatedCells.get(point.getMyRow()).set(point.getMyCol(), 
							new YellowCell(c.getMyRectangle().getX(),c.getMyRectangle().getY(),c.getMyRectangle().getWidth(),c.getMyRectangle().getHeight()));

				}
				//If cell value TREE, the tree may or may not catch fire (value BURNING or TREE, respectively) due to fire at a neighboring site.
				else if (current.isTree() && hasBurningNeighbor(i, j)) {
					if (randomNumber.nextInt(100) < probCatch) {
						updatedCells.get(point.getMyRow()).set(point.getMyCol(), 
								new YellowCell(c.getMyRectangle().getX(),c.getMyRectangle().getY(),c.getMyRectangle().getWidth(),c.getMyRectangle().getHeight()));

					} else {
						updatedCells.get(point.getMyRow()).set(point.getMyCol(), 
								new BurningCell(c.getMyRectangle().getX(),c.getMyRectangle().getY(),c.getMyRectangle().getWidth(),c.getMyRectangle().getHeight()));
//						((BurningCell)current).setBurnTimer(burningTime);
					}
				}
				emptyPoints.remove(point);
				emptyPoints.add(new Point(i,j));
			}	

		}
		myCells = updatedCells;
	}




	//		ArrayList<ArrayList<Cell>> updatedCells = new ArrayList<>(myCells);
	//		for (int i = 1; i < myCells.size()-1;i++) {
	//			for (int j = 1; j < myCells.size()-1;j++) {
	//				Cell current = myCells.get(i).get(j);
	//				double satisfaction = 0;
	//				if (current.isRed()) satisfaction = countRedInNeighborsPercent(i,j);
	//				else if (current.isBlue()) satisfaction = countBlueInNeighborsPercent(i,j);
	//				else satisfaction = 100; // eliminate the possibility to move empty cells
	//				if (satisfaction < myParameter) {
	//					Point point = emptyPoints.get((int)(Math.random()*(emptyPoints.size()-1)));
	//					Cell c = myCells.get(point.getMyRow()).get(point.getMyCol());
	//					if (current.isBlue()) {
	//						updatedCells.get(point.getMyRow()).set(point.getMyCol(), 
	//								new BlueCell(c.getMyRectangle().getX(),c.getMyRectangle().getY(),c.getMyRectangle().getWidth(),c.getMyRectangle().getHeight()));
	//					}
	//					else if (current.isRed()) {
	//						updatedCells.get(point.getMyRow()).set(point.getMyCol(), 
	//								new RedCell(c.getMyRectangle().getX(),c.getMyRectangle().getY(),c.getMyRectangle().getWidth(),c.getMyRectangle().getHeight()));
	//					}
	//					updatedCells.get(i).set(j, new EmptyCell(current.getMyRectangle().getX(),current.getMyRectangle().getY(),current.getMyRectangle().getWidth(),current.getMyRectangle().getHeight()));
	//					emptyPoints.remove(point);
	//					emptyPoints.add(new Point(i,j));
	//				}
	//			}
	//		}
	//		myCells = updatedCells;




	@Override
	public void initialize(int numCells) {
		emptyPoints = new ArrayList<>();
		//		this.probCatch = 
		//		this.burnTime = 
		double cell_size = Main.GRID_SIZE/(double)numCells;
		ArrayList<ArrayList<Cell>> cells = new ArrayList<>();
		for (int i = -1; i < numCells+1; i++) {
			ArrayList<Cell> row = new ArrayList<Cell>();
			for (int j = -1; j < numCells+1; j++) {
				if (i==0 || i==numCells-1 || j==0 || j==numCells-1) {
					row.add(new YellowCell(i*cell_size, j*cell_size, cell_size, cell_size));
					emptyPoints.add(new Point(i+1, j+1));
				}
				else if (i == numCells/2 && j == numCells/2-1 || i == numCells/2 -1&& j == numCells/2-1) {
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
