package cells;

import java.util.ArrayList;

import javafx.scene.paint.Color;

public class RedCell extends Cell {

	private boolean isRed = true;
	private boolean isBlue = false;
	
	public RedCell(double x_pos, double y_pos, double width, double height, ArrayList<Cell> neighbors) {
		super(x_pos, y_pos, width, height, neighbors);
		myRectangle.setFill(Color.TOMATO);
	}

	public boolean isRed() {
		return isRed;
	}

	public boolean isBlue() {
		return isBlue;
	}

}
