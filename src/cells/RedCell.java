package cells;

import javafx.scene.paint.Color;

public class RedCell extends Cell {
	
	/**
	 * Default constructor for the Red cells used in Segregation
	 */
	public RedCell(double x_pos, double y_pos, double width, double height, int i , int j) {
		super(x_pos, y_pos, width, height,i,j);
		isRed = true;
		myType = "Red";
		myRectangle.setFill(Color.TOMATO);
	}

}
