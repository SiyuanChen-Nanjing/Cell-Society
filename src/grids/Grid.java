package grids;

import javafx.scene.shape.Polygon;

public interface Grid {	
		
    public abstract void setShape(Polygon shape, double width, double height, 
    		int row, int col, int numRows, int numCols);
    
}