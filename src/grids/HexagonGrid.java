package grids;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class HexagonGrid implements Grid{

	@Override
	public void setShape(Polygon shape, double width, double height, int row, int col, int numRows, int numCols) {
		shape.getPoints().clear();
		double shapeWidth = width / (numCols + 1);
		double shapeHeight = height / numRows - (0.5) * (shapeWidth / 2)
				* Math.tan(30 * Math.PI / 180);
		double centerX = shapeWidth * col + shapeWidth / 2;
		if (row % 2 == 1) {
			centerX += shapeWidth / 2;
		}
		double centerY = shapeHeight * row + shapeHeight / 2;
		double sideLength = shapeWidth / (2 * Math.cos(30 * Math.PI / 180));

		int numPoints = 6 * 2;
		Double[] points = new Double[numPoints];
		double angleBetweenPoints = 2 * Math.PI / (numPoints / 2);
		double currentAngle = 0;
		for (int i = 0; i < numPoints; i += 2) {
			points[i] = centerX
					+ Math.sqrt(Math.pow(shapeWidth / 2, 2)
							+ Math.pow(sideLength / 2, 2))
					* Math.cos(currentAngle + Math.PI / 2);
			points[i + 1] = centerY
					+ Math.sqrt(Math.pow(shapeWidth / 2, 2)
							+ Math.pow(sideLength / 2, 2))
					* Math.sin(currentAngle + Math.PI / 2);
			currentAngle += angleBetweenPoints;
		}
		shape.getPoints().addAll(points);	
		shape.setStroke(Color.BLACK);
	}

	

}
