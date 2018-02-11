package simulations;

import java.util.ArrayList;
import java.util.List;

import cells.AliveCell;
import cells.Cell;
import cells.DeadCell;
import cells.EmptyCell;
import main.Main;

public class GameOfLife extends Simulation {

	private double myAliveDeadRatio = 2;
	private double myAlivePercent = myAliveDeadRatio/(myAliveDeadRatio+1);
	
	public GameOfLife(int numCells) {
		super(numCells);
	}

	private int countAliveNeighbors(int i, int j) {
		ArrayList<Cell> neighbors = getNeighbors(i,j);
		int count = 0;
		for (Cell c : neighbors) {
			if (c.isAlive()) {
				count++;
			}
		}
		return count;
	}
	
	public void setRatio(double ratio) {
		myAliveDeadRatio = ratio;
	}
	
	@Override
	public void evolve() {
		List<List<Cell>> updatedCells = new ArrayList<>(myCells);
		for (int i = 1; i < myCells.size()-1;i++) {
			for (int j = 1; j < myCells.size()-1;j++) {
				Cell current = myCells.get(i).get(j);
				int numAlive = countAliveNeighbors(i,j);
				if (current.isAlive()) {
					if (numAlive<2 || numAlive>3) {
						updatedCells.get(i).set(j, new DeadCell(current.getMyRectangle().getX(),current.getMyRectangle().getY(),
								current.getMyRectangle().getWidth(),current.getMyRectangle().getHeight(),i,j));
					}
				}
				else if (current.isDead() && numAlive==3) {
					updatedCells.get(i).set(j, new AliveCell(current.getMyRectangle().getX(),current.getMyRectangle().getY(),
							current.getMyRectangle().getWidth(),current.getMyRectangle().getHeight(),i,j));
				}
			}
		}
		myCells = updatedCells;
	}

	@Override
	public void initialize() {
		int numCells = myNumCells;
		double cell_size = Main.GRID_SIZE/(double)numCells;
		List<List<Cell>> cells = new ArrayList<>();
		for (int i = 0; i < numCells+2; i++) {
			ArrayList<Cell> row = new ArrayList<>();
			for (int j = 0; j < numCells+2; j++) {
				if (i==0 || i==numCells+1 || j==0 || j==numCells+1) {
					row.add(new EmptyCell(-1,-1,0,0,i,j));
				}
				else {
					double random = Math.random();
					if (random < myAlivePercent) {
						row.add(new AliveCell((i-1)*cell_size,(j-1)*cell_size,cell_size,cell_size,i,j));
					}
					else {
						row.add(new DeadCell((i-1)*cell_size,(j-1)*cell_size,cell_size,cell_size,i,j));
					}
				}
			}
			cells.add(row);
		}
		myCells = cells;
	}

}
