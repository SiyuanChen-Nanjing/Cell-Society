package cells;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class Cell {
	
	protected boolean isEmpty = false;;
	protected Rectangle myRectangle;
	
	protected boolean isRed = false;
	protected boolean isBlue = false;
	
	protected boolean isFish = false;
	protected boolean isShark = false;
	
	private int myGridX;
	private int myGridY;

	public Cell(double x_pos, double y_pos, double width, double height) {
		myRectangle = new Rectangle(x_pos, y_pos, width, height);
		myRectangle.setStroke(Color.BLACK);
	}
	
	public Cell(double x_pos, double y_pos, double width, double height, int i, int j) {
		myRectangle = new Rectangle(x_pos, y_pos, width, height);
		myRectangle.setStroke(Color.BLACK);
		myGridX = i;
		myGridY = j;
	}

	public int getMyGridX() {
		return myGridX;
	}

	public int getMyGridY() {
		return myGridY;
	}

	public boolean isFish() {
		return isFish;
	}

	public boolean isShark() {
		return isShark;
	}
	
	public boolean isRed() {
		return isRed;
	}

	public boolean isBlue() {
		return isBlue;
	}

	public Rectangle getMyRectangle() {
		return myRectangle;
	}

	public boolean isEmpty() {
		return isEmpty;
	}
}
