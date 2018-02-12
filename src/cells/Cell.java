package cells;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class Cell {
	
	protected boolean isEmpty = false;;
	protected Rectangle myRectangle;
	
	protected boolean isRed = false;
	protected boolean isBlue = false;

	protected boolean isBurning = false;
	protected boolean isTree = false;

	protected boolean isFish = false;
	protected boolean isShark = false;
	
	protected boolean isAlive = false;
	protected boolean isDead = false;
	
	protected String myType;
	
	private int myGridX;
	private int myGridY;
	
	/**
	 * Default constructor for Cell superclass
	 * @param x_pos x coordinate of the cell
	 * @param y_pos y coordinate of the cell
	 * @param width width of the cell
	 * @param height height of the cell
	 * @param i row number
	 * @param j column number
	 */
	public Cell(double x_pos, double y_pos, double width, double height, int i, int j) {
		myRectangle = new Rectangle(x_pos, y_pos, width, height);
		myRectangle.setStroke(Color.BLACK);
		myGridX = i;
		myGridY = j;
	}

	/**
	 * @return the myGridX
	 */
	public int getMyGridX() {
		return myGridX;
	}

	/**
	 * @return the myGridY
	 */
	public int getMyGridY() {
		return myGridY;
	}

	/**
	 * @return the isEmpty
	 */
	public boolean isEmpty() {
		return isEmpty;
	}

	/**
	 * @return the isRed
	 */
	public boolean isRed() {
		return isRed;
	}

	/**
	 * @return the isBlue
	 */
	public boolean isBlue() {
		return isBlue;
	}

	/**
	 * @return the isBurning
	 */
	public boolean isBurning() {
		return isBurning;
	}

	/**
	 * @return the isTree
	 */
	public boolean isTree() {
		return isTree;
	}

	/**
	 * @return the isFish
	 */
	public boolean isFish() {
		return isFish;
	}

	/**
	 * @return the isShark
	 */
	public boolean isShark() {
		return isShark;
	}

	/**
	 * @return the isAlive
	 */
	public boolean isAlive() {
		return isAlive;
	}

	/**
	 * @return the isDead
	 */
	public boolean isDead() {
		return isDead;
	}

	/**
	 * @return the myRectangle
	 */
	public Rectangle getMyRectangle() {
		return myRectangle;
	}

	/**
	 * @return the myType
	 */
	public String getMyType() {
		return myType;
	}

}
