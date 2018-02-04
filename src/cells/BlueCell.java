package cells;

import java.util.ArrayList;

import javafx.scene.paint.Color;

public class BlueCell extends Cell {
	
	public BlueCell(double x_pos, double y_pos, double width, double height) {
		super(x_pos, y_pos, width, height);
		isBlue = true;
		myRectangle.setFill(Color.CORNFLOWERBLUE);
	}

}
