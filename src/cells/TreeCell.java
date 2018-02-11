package cells;

import javafx.scene.paint.Color;

public class TreeCell extends Cell {
	
	public TreeCell(double x_pos, double y_pos, double width, double height, int i, int j) {
		super(x_pos, y_pos, width, height,i,j);
		isTree = true;
		myType = "Tree";
		myRectangle.setFill(Color.FORESTGREEN);
	}
}