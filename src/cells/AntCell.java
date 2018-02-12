package cells;

import java.util.ArrayList;

import javafx.scene.paint.Color;

public class AntCell extends Cell {
	private boolean myFood;
	
	public AntCell(double x_pos, double y_pos, double width, double height, int i, int j) {
		super(x_pos, y_pos, width, height, i,j);
		isAnt = true;
		myRectangle.setFill(Color.BROWN);
	}
	
	public boolean hasFoodItem(){
		return myFood;
	}
	
	public void getFood(){
		myFood = true;
	}
	
	public void dropFood() {
		myFood = false;
	}
	
	public ForagingAntCell getDirections(ArrayList<Cell> neighbors, boolean food){
		ForagingAntCell best = null;
		double mostPh = -1;
		for (Cell c: neighbors) {
			if (((ForagingAntCell) c).getPheromones(food) >= mostPh && !((ForagingAntCell)c).fullOfAnts()) {
				mostPh = ((ForagingAntCell)c).getPheromones(food);
				best = (ForagingAntCell) c;
			}
		}
		return best;
	}

	public ForagingAntCell getBestNeighbor(ArrayList<Cell> neighbors, boolean food) {
		// TODO Auto-generated method stub
		return null;
	}
}
