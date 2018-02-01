package cells;

import java.util.ArrayList;

import javafx.scene.shape.Rectangle;

public abstract class Cell {
	private double myXPos;
	private double myYPos;
	private boolean isEmpty = false;;
	private ArrayList<Cell> myNeighbors;
	private Rectangle myRectangle;
	
	public Cell(double x_pos, double y_pos, double width, double height, ArrayList<Cell> neighbors) {
		myXPos = x_pos;
		myYPos = y_pos;
		myNeighbors = neighbors;
		myRectangle = new Rectangle(x_pos, y_pos, width, height);
	}

	public boolean isEmpty() {
		return isEmpty;
	}

	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}

	public Rectangle getMyRectangle() {
		return myRectangle;
	}
	
	
	
	
}
