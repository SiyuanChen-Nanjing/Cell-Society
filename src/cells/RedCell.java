package cells;

import java.util.ArrayList;

import javafx.scene.paint.Color;

public class RedCell extends Cell {
	
	public RedCell(double x_pos, double y_pos, double width, double height) {
		super(x_pos, y_pos, width, height);
		isRed = true;
		myRectangle.setFill(Color.TOMATO);
	}

}
