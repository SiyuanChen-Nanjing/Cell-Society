package cells;

import javafx.scene.paint.Color;

public class BurningCell extends Cell {
	private int myBurningTime = 2;
	
	public BurningCell(double x_pos, double y_pos, double width, double height) {
		super(x_pos, y_pos, width, height);
		isBurning = true;
		myRectangle.setFill(Color.FIREBRICK);
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
