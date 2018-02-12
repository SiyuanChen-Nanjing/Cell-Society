package cells;

import javafx.scene.paint.Color;

public class BurningCell extends Cell {
	private int myBurningTime = 2;
	
	/**
	 * Default constructor for the Burning cells used in Fire
	 */
	public BurningCell(double x_pos, double y_pos, double width, double height, int i, int j) {
		super(x_pos, y_pos, width, height,i,j);
		isBurning = true;
		myType = "Burning";
		myRectangle.setFill(Color.FIREBRICK);
	}
	
	/**
	 * Start the process for a burning cell to be extinguished, i.e. switch to an empty cell
	 */
	public void startBurnTimer() {
		myBurningTime--;
	}
	
	/**
	 * @return myBurningTime
	 */
	public int getBurnTimer() {
		return myBurningTime;
	}

}
