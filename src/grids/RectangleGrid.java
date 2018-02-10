package grids;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class RectangleGrid implements Grid{


	@Override
	public void setShape(Polygon shape, double width, double height, int row, int col, int numRows, int numCols) {
		shape.getPoints().clear();
		double padding = 1;
		double shapeWidth = width / (numCols) - padding;
		double shapeHeight = height / (numRows) - padding;
		double leftSide = col * (shapeWidth + padding);
		double rightSide = leftSide + shapeWidth;
		double top = row * (shapeHeight + padding);
		double bottom = top + shapeHeight;
		shape.getPoints().addAll(
				new Double[] { leftSide, top, rightSide, top, rightSide,
						bottom, leftSide, bottom });	
		shape.setStroke(Color.BLACK);

	}

}
