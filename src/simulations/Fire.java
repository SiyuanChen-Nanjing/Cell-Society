package simulations;

import java.util.ArrayList;

import cells.Cell;
import cells.EmptyCell;
import cells.BurningCell;
import cells.TreeCell;
import main.Main;

public class Fire extends Simulation{
	private double probCatch = 0.5;

	public Fire(int numCells) {
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
		for (int i = 1; i < myCells.size()-1; i++) {
			for (int j = 1; j < myCells.size()-1; j++) {
				Cell current = myCells.get(i).get(j);
				if (current.isBurning()) {
					BurningCell fire = (BurningCell) current;
					fire.startBurnTimer();
					if (fire.getBurnTimer()<=0) {
						updatedCells.get(i).set(j, new EmptyCell(current.getMyRectangle().getX(),current.getMyRectangle().getY(),
								current.getMyRectangle().getWidth(),current.getMyRectangle().getHeight(),i,j));
					}
				}
				else if (current.isTree() && hasBurningNeighbor(i,j) && Math.random() < probCatch) {
					updatedCells.get(i).set(j, new BurningCell(current.getMyRectangle().getX(),current.getMyRectangle().getY(),
						current.getMyRectangle().getWidth(),current.getMyRectangle().getHeight()));
				}
			}
		}
		myCells = updatedCells;
	}


	@Override
	public void initialize() {
		int numCells = myNumCells;

		double cell_size = Main.GRID_SIZE/(double)numCells;
		ArrayList<ArrayList<Cell>> cells = new ArrayList<>();
		for (int i = 0; i < numCells+2; i++) {
			ArrayList<Cell> row = new ArrayList<Cell>();
			for (int j = 0; j < numCells+2; j++) {
				if (i==0 || i==numCells+1 || j==0 || j==numCells+1) {
					row.add(new EmptyCell(-1, -1, 0, 0));
				}
				else if (i == numCells/2 && j == numCells/2-1 ){
					row.add(new BurningCell((i-1)*cell_size, (j-1)*cell_size, cell_size, cell_size));
				}
				else {
					row.add(new TreeCell((i-1)*cell_size, (j-1)*cell_size, cell_size, cell_size));
				}
			}
			cells.add(row);
		}
		myCells = cells;
	}

	public void setProbCatch(double prob) {
		this.probCatch = prob;
	}
	
}
