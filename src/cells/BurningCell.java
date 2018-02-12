package cells;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class BurningCell extends Cell {
	private int myBurningTime = 2;
	
	public BurningCell(double x_pos, double y_pos, double width, double height) {
		super(x_pos, y_pos, width, height);
		isBurning = true;
		getMyShape().setFill(Color.FIREBRICK);
	}
	
	public BurningCell(Polygon shape, double width, double height, 
			int row, int col, int numRows, int numCols){	
		super(shape, width, height, row, col, numRows, numCols);

	}
	
	public void startBurnTimer() {
		myBurningTime--;
	}
	
	public void setBurnTimer(int burningTime) {
		this.myBurningTime = burningTime;
	}
	
	public int getBurnTimer() {
		return myBurningTime;
	}
	
	
	
	
}
