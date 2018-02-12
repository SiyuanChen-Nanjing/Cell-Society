package cells;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Polygon;
import grids.Grid;
import grids.HexagonGrid;
import grids.RectangleGrid;
import grids.TriangleGrid;

public abstract class Cell {
	
	protected boolean isEmpty = false;;
	protected Rectangle myRectangle;
	
	protected boolean isBurning = false;
	protected boolean isTree = false;
	
	protected boolean isRed = false;
	protected boolean isBlue = false;
	
	protected boolean isFish = false;
	protected boolean isShark = false;
	
	protected boolean isAlive = false;
	protected boolean isDead = false;
	
	protected boolean isAnt = false;
	protected boolean isForaging = false;
	
	private int myGridX;
	private int myGridY;

	private Grid myGrid;
	protected Polygon myShape;
	
	
	
	public Cell(double x_pos, double y_pos, double width, double height) {
//		myShape = new Polygon();
//		myGrid = new RectangleGrid();
		myRectangle = new Rectangle(x_pos, y_pos, width, height);
		myRectangle.setStroke(Color.ALICEBLUE);
//		myGrid.setShape(myShape, 300.0, 300.0, 30, 30, 30, 30);
//		myShape.setStroke(Color.ROSYBROWN);

	}
	
	public Cell(double x_pos, double y_pos, double width, double height, int i, int j) {
		myRectangle = new Rectangle(x_pos, y_pos, width, height);
		myRectangle.setStroke(Color.BLACK);
		myGridX = i;
		myGridY = j;
	}
	
	public Cell(Polygon shape, double width, double height, 
			int row, int col, int numRows, int numCols) {
		myShape =  new Polygon();
		myGrid = new TriangleGrid();
		myGrid.setShape(myShape, width, height, row, col, numRows, numCols);
		myShape.setStroke(Color.BLACK);
	}
	
	
	public void setGridModel (Grid grid) {
		myGrid = grid;
		if (grid instanceof RectangleGrid) {
			myGrid = new RectangleGrid();
		} else if (grid instanceof TriangleGrid) {
			myGrid = new TriangleGrid();
		} else if (grid instanceof HexagonGrid) {
			myGrid = new HexagonGrid();
		}
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

	public boolean isAlive() {
		return isAlive;
	}

	public boolean isDead() {
		return isDead;
	}
	
	public boolean isBurning() {
		return isBurning;
	}
	
	public boolean isTree() {
		return isTree;
	}
	
	public boolean isAnt() {
		return isAnt;
	}
	
	public boolean isForaging(){
		return isForaging;
	}

	public Polygon getMyShape() {
		return myShape;
	}

	public void setMyShape(Polygon myShape) {
		this.myShape = myShape;
	}

}
