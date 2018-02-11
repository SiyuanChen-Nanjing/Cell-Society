package cells;

import javafx.scene.paint.Color;

public class BlueCell extends Cell {
	
	public BlueCell(double x_pos, double y_pos, double width, double height, int i, int j) {
		super(x_pos, y_pos, width, height,i,j);
		isBlue = true;
		myType = "Blue";
		myRectangle.setFill(Color.CORNFLOWERBLUE);
	}

}
