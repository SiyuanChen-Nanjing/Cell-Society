package cells;

import java.util.ArrayList;

import javafx.scene.paint.Color;

public class EmptyCell extends Cell {

	public EmptyCell(double x_pos, double y_pos, double width, double height) {
		super(x_pos, y_pos, width, height);
		isEmpty = true;
		myRectangle.setFill(Color.WHITE);
	}	
}