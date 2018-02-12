package cells;

import javafx.scene.paint.Color;

public class AliveCell extends Cell {
	
	/**
	 * Default constructor for the Alive cells used in GameOfLife
	 */
	public AliveCell(double x_pos, double y_pos, double width, double height,int i, int j) {
		super(x_pos, y_pos, width, height,i,j);
		isAlive = true;
		myType = "Alive";
		myRectangle.setFill(Color.AZURE);
	}

}
