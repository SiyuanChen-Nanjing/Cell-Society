package grids;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class TriangleGrid implements Grid {
	
	@Override
	public void setShape(Polygon shape, double prefWidth, double prefHeight, 
			int row, int col, int numRows, int numCols) {
		shape.getPoints().clear();
		double padding = 1;
		Double[] corners = new Double[6];
		double shapeHeight = prefHeight / numRows - padding;
		double shapeWidth = prefWidth / ((numCols + 2) / 2) - padding;
		if (row % 2 == 0) {
			if (col % 2 == 0) {
				corners[0] = (shapeWidth + padding) * col / 2;
				corners[1] = (shapeHeight + padding) * row;
				corners[2] = corners[0] + shapeWidth;
				corners[3] = corners[1];
				corners[4] = corners[0] + shapeWidth / 2;
				corners[5] = corners[1] + shapeHeight;
			} else {
				// upside down
				corners[0] = (shapeWidth + padding) * col / 2;
				corners[1] = (shapeHeight + padding) * row + shapeHeight;
				corners[2] = corners[0] + shapeWidth;
				corners[3] = corners[1];
				corners[4] = corners[0] + shapeWidth / 2;
				corners[5] = corners[1] - shapeHeight;
			}
		} else {
			if (col % 2 == 0) {
				// upside down
				corners[0] = (shapeWidth + padding) * col / 2;
				corners[1] = (shapeHeight + padding) * row + shapeHeight;
				corners[2] = corners[0] + shapeWidth;
				corners[3] = corners[1];
				corners[4] = corners[0] + shapeWidth / 2;
				corners[5] = corners[1] - shapeHeight;
			} else {
				corners[0] = (shapeWidth + padding) * col / 2;
				corners[1] = (shapeHeight + padding) * row;
				corners[2] = corners[0] + shapeWidth;
				corners[3] = corners[1];
				corners[4] = corners[0] + shapeWidth / 2;
				corners[5] = corners[1] + shapeHeight;
			}
		}
		shape.getPoints().addAll(corners);
		shape.setStroke(Color.BLACK);
	}

	
}