package cells;

import javafx.scene.paint.Color;

public class EmptyCell extends Cell {
	
	public EmptyCell(double x_pos, double y_pos, double width, double height, int i, int j) {
		super(x_pos, y_pos, width, height, i,j);
		isEmpty = true;
		myType = "Empty";
		myRectangle.setFill(Color.WHITE);
	}
	
}
