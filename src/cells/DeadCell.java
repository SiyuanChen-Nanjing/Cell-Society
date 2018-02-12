package cells;

import javafx.scene.paint.Color;

public class DeadCell extends Cell {

	/**
	 * Default constructor for the Dead cells used in GameOfLife
	 */
	public DeadCell(double x_pos, double y_pos, double width, double height, int i, int j) {
		super(x_pos, y_pos, width, height,i,j);
		isDead = true;
		myType = "Dead";
		myRectangle.setFill(Color.DIMGRAY);
	}

}
