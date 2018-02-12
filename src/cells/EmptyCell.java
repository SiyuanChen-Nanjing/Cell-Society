package cells;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class EmptyCell extends Cell {
	
	public EmptyCell(double x_pos, double y_pos, double width, double height) {
		super(x_pos, y_pos, width, height);
		isEmpty = true;
		getMyShape().setFill(Color.WHITE);
	}
	
	public EmptyCell(double x_pos, double y_pos, double width, double height, int i, int j) {
		super(x_pos, y_pos, width, height, i,j);
		isEmpty = true;
		getMyShape().setFill(Color.YELLOW);
	}
	
	public EmptyCell(Polygon shape, double width, double height, 
			int row, int col, int numRows, int numCols){	
		super(shape, width, height, row, col, numRows, numCols);
	}
}
