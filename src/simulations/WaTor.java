package simulations;

import java.util.ArrayList;

import cells.Cell;
import cells.EmptyCell;
import cells.FishCell;
import cells.SharkCell;
import main.Main;

public class WaTor extends Simulation {

	private double myEmptyPercent = 0.3;
	private double myFishSharkRatio = 20;
	private double myFishPercent = (1-myEmptyPercent)*(myFishSharkRatio/(myFishSharkRatio+1));
	private double mySharkPercent = 1-myEmptyPercent-myFishPercent;
	
	private int myFishRoundsToReproduce = 4;
	private int mySharkRoundsToReproduce = 10;
	
	public WaTor(int numCells) {
		super(numCells);
	}

	@Override
	public void evolve() {
		ArrayList<ArrayList<Cell>> updatedCells = new ArrayList<>(myCells);
		for (int i = 1; i < myCells.size()-1;i++) {
			for (int j = 1; j < myCells.size()-1;j++) {
				Cell current = myCells.get(i).get(j);
				if (current.isFish()) fishMove(i,j, updatedCells);
				else if (current.isShark()) sharkMove(i,j, updatedCells);
			}
		}
		myCells = updatedCells;
	}

	private void fishMove(int i, int j, ArrayList<ArrayList<Cell>> updatedCells) {
		FishCell current = (FishCell) myCells.get(i).get(j);
		FishCell updated = (FishCell) updatedCells.get(i).get(j);
		ArrayList<Point> empty = getMyEmptyNeighbors(i,j);
		current.setMyRoundsSurvived(current.getMyRoundsSurvived()+1);
		updated.setMyRoundsSurvived(current.getMyRoundsSurvived()+1);
		if (!empty.isEmpty()) {
			Point point = empty.get((int)(Math.random()*(empty.size()-1)));
			Cell destination = myCells.get(point.getMyRow()).get(point.getMyCol());
			FishCell moved = new FishCell(destination.getMyRectangle().getX(), destination.getMyRectangle().getY(), 
					destination.getMyRectangle().getWidth(), destination.getMyRectangle().getHeight(), point.getMyRow(), point.getMyCol());
			moved.setMyRoundsSurvived(current.getMyRoundsSurvived());
			
			if (moved.getMyRoundsSurvived()>=myFishRoundsToReproduce) {
				updatedCells.get(i).set(j, new FishCell(current.getMyRectangle().getX(), current.getMyRectangle().getY(), 
						current.getMyRectangle().getWidth(), current.getMyRectangle().getHeight(), i,j));
				moved.setMyRoundsSurvived(0);
			}
			else {
				updatedCells.get(i).set(j, new EmptyCell(current.getMyRectangle().getX(), current.getMyRectangle().getY(), 
						current.getMyRectangle().getWidth(), current.getMyRectangle().getHeight(), i,j));
			}
			updatedCells.get(point.getMyRow()).set(point.getMyCol(), moved);
		}
	}
	
	private void sharkMove(int i, int j, ArrayList<ArrayList<Cell>> updatedCells) {
		SharkCell current = (SharkCell) myCells.get(i).get(j);
		SharkCell updated = (SharkCell) updatedCells.get(i).get(j);
		ArrayList<Point> fish = getMyFishNeighbors(i,j);
		ArrayList<Point> empty = getMyEmptyNeighbors(i,j);
		current.setMyEnergy(current.getMyEnergy()-1);
		updated.setMyEnergy(current.getMyEnergy()-1);
		current.setMyRoundsSurvived(current.getMyRoundsSurvived()+1);
		updated.setMyRoundsSurvived(current.getMyRoundsSurvived()+1);
		
		if (!fish.isEmpty()) {
			Point fishPoint = fish.get((int)(Math.random()*(fish.size()-1)));
			Cell fishDestination = myCells.get(fishPoint.getMyRow()).get(fishPoint.getMyCol());
			SharkCell fishMoved = new SharkCell(fishDestination.getMyRectangle().getX(), fishDestination.getMyRectangle().getY(), 
					fishDestination.getMyRectangle().getWidth(), fishDestination.getMyRectangle().getHeight(), fishPoint.getMyRow(), fishPoint.getMyCol());
			fishMoved.setMyRoundsSurvived(current.getMyRoundsSurvived());
			fishMoved.setMyEnergy(current.getMyEnergy()+current.getFishEnergyGain());
			
			if (fishMoved.getMyRoundsSurvived()==mySharkRoundsToReproduce) {
				updatedCells.get(i).set(j, new SharkCell(current.getMyRectangle().getX(), current.getMyRectangle().getY(), 
						current.getMyRectangle().getWidth(), current.getMyRectangle().getHeight(), i,j));
				fishMoved.setMyRoundsSurvived(0);
			}
			else {
				updatedCells.get(i).set(j, new EmptyCell(current.getMyRectangle().getX(), current.getMyRectangle().getY(), 
						current.getMyRectangle().getWidth(), current.getMyRectangle().getHeight(), i,j));
			}
			updatedCells.get(fishPoint.getMyRow()).set(fishPoint.getMyCol(), fishMoved);
		}
		else if (!empty.isEmpty()) {
			if (current.getMyEnergy()<=0) {
				updatedCells.get(i).set(j, new EmptyCell(current.getMyRectangle().getX(), current.getMyRectangle().getY(), 
							current.getMyRectangle().getWidth(), current.getMyRectangle().getHeight(), i,j));
			}
			else {
				Point point = empty.get((int)(Math.random()*(empty.size()-1)));
				Cell destination = myCells.get(point.getMyRow()).get(point.getMyCol());
				SharkCell moved = new SharkCell(destination.getMyRectangle().getX(), destination.getMyRectangle().getY(), 
						destination.getMyRectangle().getWidth(), destination.getMyRectangle().getHeight(), point.getMyRow(), point.getMyCol());
				moved.setMyRoundsSurvived(current.getMyRoundsSurvived());
			
				if (moved.getMyRoundsSurvived()==mySharkRoundsToReproduce) {
					updatedCells.get(i).set(j, new SharkCell(current.getMyRectangle().getX(), current.getMyRectangle().getY(), 
							current.getMyRectangle().getWidth(), current.getMyRectangle().getHeight(), i,j));
					moved.setMyRoundsSurvived(0);
				}
				else {
					updatedCells.get(i).set(j, new EmptyCell(current.getMyRectangle().getX(), current.getMyRectangle().getY(), 
							current.getMyRectangle().getWidth(), current.getMyRectangle().getHeight(), i,j));
				}
				updatedCells.get(point.getMyRow()).set(point.getMyCol(), moved);
			}
		}
		else {
			if (current.getMyEnergy()<=0) {
				updatedCells.get(i).set(j, new EmptyCell(current.getMyRectangle().getX(), current.getMyRectangle().getY(), 
							current.getMyRectangle().getWidth(), current.getMyRectangle().getHeight(), i,j));
			}
		}
	}
	
	private ArrayList<Point> getMyEmptyNeighbors(int i, int j) {
		ArrayList<Point> empty = new ArrayList<>();
		ArrayList<Cell> neighbors = getFourNeighbors(i,j);
		if (i==1) {
			if (j==1) {
				if (neighbors.get(3).isEmpty()) empty.add(new Point(neighbors.get(3).getMyGridX(),neighbors.get(3).getMyGridY()));
				if (neighbors.get(2).isEmpty()) empty.add(new Point(neighbors.get(2).getMyGridX(),neighbors.get(2).getMyGridY()));
			}
			else if (j==myCells.size()-2) {
				if (neighbors.get(1).isEmpty()) empty.add(new Point(neighbors.get(1).getMyGridX(),neighbors.get(1).getMyGridY()));
				if (neighbors.get(3).isEmpty()) empty.add(new Point(neighbors.get(3).getMyGridX(),neighbors.get(3).getMyGridY()));
			}
			else {
				if (neighbors.get(3).isEmpty()) empty.add(new Point(neighbors.get(3).getMyGridX(),neighbors.get(3).getMyGridY()));
				if (neighbors.get(2).isEmpty()) empty.add(new Point(neighbors.get(2).getMyGridX(),neighbors.get(2).getMyGridY()));
				if (neighbors.get(1).isEmpty()) empty.add(new Point(neighbors.get(1).getMyGridX(),neighbors.get(1).getMyGridY()));
			}
		}
		else if (i==myCells.size()-2) {
			if (j==1) {
				if (neighbors.get(0).isEmpty()) empty.add(new Point(neighbors.get(0).getMyGridX(),neighbors.get(0).getMyGridY()));
				if (neighbors.get(2).isEmpty()) empty.add(new Point(neighbors.get(2).getMyGridX(),neighbors.get(2).getMyGridY()));
			}
			else if (j==myCells.size()-2) {
				if (neighbors.get(1).isEmpty()) empty.add(new Point(neighbors.get(1).getMyGridX(),neighbors.get(1).getMyGridY()));
				if (neighbors.get(0).isEmpty()) empty.add(new Point(neighbors.get(0).getMyGridX(),neighbors.get(0).getMyGridY()));
			}
			else {
				if (neighbors.get(1).isEmpty()) empty.add(new Point(neighbors.get(1).getMyGridX(),neighbors.get(1).getMyGridY()));
				if (neighbors.get(2).isEmpty()) empty.add(new Point(neighbors.get(2).getMyGridX(),neighbors.get(2).getMyGridY()));
				if (neighbors.get(0).isEmpty()) empty.add(new Point(neighbors.get(0).getMyGridX(),neighbors.get(0).getMyGridY()));
			}
		}
		else if (j==1) {
			if (neighbors.get(0).isEmpty()) empty.add(new Point(neighbors.get(0).getMyGridX(),neighbors.get(0).getMyGridY()));
			if (neighbors.get(3).isEmpty()) empty.add(new Point(neighbors.get(3).getMyGridX(),neighbors.get(3).getMyGridY()));
			if (neighbors.get(2).isEmpty()) empty.add(new Point(neighbors.get(2).getMyGridX(),neighbors.get(2).getMyGridY()));
		}
		else if (j==myCells.size()-2) {
			if (neighbors.get(1).isEmpty()) empty.add(new Point(neighbors.get(1).getMyGridX(),neighbors.get(1).getMyGridY()));
			if (neighbors.get(3).isEmpty()) empty.add(new Point(neighbors.get(3).getMyGridX(),neighbors.get(3).getMyGridY()));
			if (neighbors.get(0).isEmpty()) empty.add(new Point(neighbors.get(0).getMyGridX(),neighbors.get(0).getMyGridY()));
		}
		else {
			for (int k = 0; k < 4; k++) {
				if (neighbors.get(k).isEmpty()) empty.add(new Point(neighbors.get(k).getMyGridX(),neighbors.get(k).getMyGridY()));
			}
		}
		return empty;
	}
	
	private ArrayList<Point> getMyFishNeighbors(int i, int j) {
		ArrayList<Point> fish = new ArrayList<>();
		ArrayList<Cell> neighbors = getFourNeighbors(i,j);
		if (i==1) {
			if (j==1) {
				if (neighbors.get(3).isFish()) fish.add(new Point(neighbors.get(3).getMyGridX(),neighbors.get(3).getMyGridY()));
				if (neighbors.get(2).isFish()) fish.add(new Point(neighbors.get(2).getMyGridX(),neighbors.get(2).getMyGridY()));
			}
			else if (j==myCells.size()-2) {
				if (neighbors.get(1).isFish()) fish.add(new Point(neighbors.get(1).getMyGridX(),neighbors.get(1).getMyGridY()));
				if (neighbors.get(3).isFish()) fish.add(new Point(neighbors.get(3).getMyGridX(),neighbors.get(3).getMyGridY()));
			}
			else {
				if (neighbors.get(3).isFish()) fish.add(new Point(neighbors.get(3).getMyGridX(),neighbors.get(3).getMyGridY()));
				if (neighbors.get(2).isFish()) fish.add(new Point(neighbors.get(2).getMyGridX(),neighbors.get(2).getMyGridY()));
				if (neighbors.get(1).isFish()) fish.add(new Point(neighbors.get(1).getMyGridX(),neighbors.get(1).getMyGridY()));
			}
		}
		else if (i==myCells.size()-2) {
			if (j==1) {
				if (neighbors.get(0).isFish()) fish.add(new Point(neighbors.get(0).getMyGridX(),neighbors.get(0).getMyGridY()));
				if (neighbors.get(2).isFish()) fish.add(new Point(neighbors.get(2).getMyGridX(),neighbors.get(2).getMyGridY()));
			}
			else if (j==myCells.size()-2) {
				if (neighbors.get(1).isFish()) fish.add(new Point(neighbors.get(1).getMyGridX(),neighbors.get(1).getMyGridY()));
				if (neighbors.get(0).isFish()) fish.add(new Point(neighbors.get(0).getMyGridX(),neighbors.get(0).getMyGridY()));
			}
			else {
				if (neighbors.get(1).isFish()) fish.add(new Point(neighbors.get(1).getMyGridX(),neighbors.get(1).getMyGridY()));
				if (neighbors.get(2).isFish()) fish.add(new Point(neighbors.get(2).getMyGridX(),neighbors.get(2).getMyGridY()));
				if (neighbors.get(0).isFish()) fish.add(new Point(neighbors.get(0).getMyGridX(),neighbors.get(0).getMyGridY()));
			}
		}
		else if (j==1) {
			if (neighbors.get(0).isFish()) fish.add(new Point(neighbors.get(0).getMyGridX(),neighbors.get(0).getMyGridY()));
			if (neighbors.get(3).isFish()) fish.add(new Point(neighbors.get(3).getMyGridX(),neighbors.get(3).getMyGridY()));
			if (neighbors.get(2).isFish()) fish.add(new Point(neighbors.get(2).getMyGridX(),neighbors.get(2).getMyGridY()));
		}
		else if (j==myCells.size()-2) {
			if (neighbors.get(1).isFish()) fish.add(new Point(neighbors.get(1).getMyGridX(),neighbors.get(1).getMyGridY()));
			if (neighbors.get(3).isFish()) fish.add(new Point(neighbors.get(3).getMyGridX(),neighbors.get(3).getMyGridY()));
			if (neighbors.get(0).isFish()) fish.add(new Point(neighbors.get(0).getMyGridX(),neighbors.get(0).getMyGridY()));
		}
		else {
			for (int k = 0; k < 4; k++) {
				if (neighbors.get(k).isFish()) fish.add(new Point(neighbors.get(k).getMyGridX(),neighbors.get(k).getMyGridY()));
			}
		}
		return fish;
	}
	
	@Override
	public void initialize() {
		int numCells = myNumCells;
		double cell_size = Main.GRID_SIZE/(double)numCells;
		ArrayList<ArrayList<Cell>> cells = new ArrayList<>();
		for (int i = 0; i < numCells+2; i++) {
			ArrayList<Cell> row = new ArrayList<Cell>();
			for (int j = 0; j < numCells+2; j++) {
				if (i==0 || i==numCells+1 || j==0 || j==numCells+1) row.add(new EmptyCell(-1,-1,0,0));
				else {
					double random = Math.random();
					if (random < myFishPercent) 
						row.add(new FishCell((i-1)*cell_size,(j-1)*cell_size,cell_size,cell_size,i,j));
					else if (random < myFishPercent + mySharkPercent)
						row.add(new SharkCell((i-1)*cell_size,(j-1)*cell_size,cell_size,cell_size,i,j));
					else {
						row.add(new EmptyCell((i-1)*cell_size,(j-1)*cell_size,cell_size,cell_size,i,j));
					}
				}
			}
			cells.add(row);
		}
		myCells = cells;
	}
	
	public void setEmptyPercent(double empty) { myEmptyPercent = empty;}
	
	public void setRatio(double ratio) { myFishSharkRatio = ratio;}
	
	public void setReproductionRounds(int fish, int shark) {
		myFishRoundsToReproduce = fish;
		mySharkRoundsToReproduce = shark;
	}
}
