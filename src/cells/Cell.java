package cells;

import java.util.ArrayList;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class Cell {
	
	protected boolean isEmpty = false;;
	protected Rectangle myRectangle;
	
	protected boolean isRed = false;
	protected boolean isBlue = false;
	
	protected boolean isBurning = false;
	protected boolean isTree = false;
	
	public Cell(double x_pos, double y_pos, double width, double height) {
		myRectangle = new Rectangle(x_pos, y_pos, width, height);
		myRectangle.setStroke(Color.BLACK);
	}

	public boolean isRed() {
		return isRed;
	}

	public boolean isBlue() {
		return isBlue;
	}
	
	public boolean isBurning() {
		return isBurning;
	}
	
	public boolean isTree() {
		return isTree;
	}

	public Rectangle getMyRectangle() {
		return myRectangle;
	}

	public boolean isEmpty() {
		return isEmpty;
	}
}
