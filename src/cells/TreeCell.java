package cells;

import java.util.ArrayList;

import javafx.scene.paint.Color;

public class TreeCell extends Cell {
	
	public TreeCell(double x_pos, double y_pos, double width, double height) {
		super(x_pos, y_pos, width, height);
		isTree = true;
		myRectangle.setFill(Color.FORESTGREEN);
	}
}