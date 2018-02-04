package grid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cells.Cell;

public abstract class Grid {
	private ArrayList<Cell> myCellList;
	private int myRows;
	private int myColumns;
		
	public Grid(ArrayList<Cell> cellList) {
		myCellList = cellList;
		myRows = cellList.size();
		myColumns = cellList.size();
	}
	
	public ArrayList<Cell> getGrid(){
		return myCellList;
	}
	
	public int getRows() {
		return myRows;
	}
	
	public int getColumns(){
		return myColumns;
	}


}