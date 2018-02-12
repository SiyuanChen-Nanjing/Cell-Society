package cells;

import javafx.scene.paint.Color;

public class EmptyCell extends Cell {
	
	/**
	 * Default constructor for the Empty cells used in all simulations
	 */
	public EmptyCell(double x_pos, double y_pos, double width, double height, int i, int j) {
		super(x_pos, y_pos, width, height, i,j);
		isEmpty = true;
		myType = "Empty";
		myRectangle.setFill(Color.WHITE);
	}
	
}
