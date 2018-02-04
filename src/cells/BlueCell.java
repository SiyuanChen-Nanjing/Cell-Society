package cells;

import java.util.ArrayList;

import javafx.scene.paint.Color;

public class BlueCell extends Cell {

	private boolean isRed = false;
	private boolean isBlue = true;
	
	public BlueCell(double x_pos, double y_pos, double width, double height, ArrayList<Cell> neighbors) {
		super(x_pos, y_pos, width, height, neighbors);
		myRectangle.setFill(Color.CORNFLOWERBLUE);
	}

	public boolean isRed() {
		return isRed;
	}

	public boolean isBlue() {
		return isBlue;
	}

}
