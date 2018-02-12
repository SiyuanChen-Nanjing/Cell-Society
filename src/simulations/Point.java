package simulations;

public class Point {

	private int myRow;
	private int myCol;
	
	/**
	 * A point representing the relative location of a cell in a grid
	 * @param i row number
	 * @param j column number
	 */
	public Point(int i, int j) {
		myRow = i;
		myCol = j;
	}

	/**
	 * 
	 * @return my row number
	 */
	public int getMyRow() {
		return myRow;
	}

	/**
	 * 
	 * @return my column number
	 */
	public int getMyCol() {
		return myCol;
	}
	
	
}
