package grid;
import com.sun.javafx.geom.Rectangle;

import javafx.scene.Group;

public class GridView {
	private Group myRoot = new Group();
	private Grid myGrid;
	private int myGridSize;
	
	public GridView(Grid grid) {
		this.myGrid = grid;
		
	}
	
	public void displayGrid() {
		double cellWidth =  myGrid.getRows();
        double cellHeight = myGrid.getColumns();
        for (int r = 0; r < myGrid.getRows(); r++) {
            for (int c = 0; c < myGrid.getColumns(); c++) {
                Double xView = r * cellWidth;
                Double yView = c * cellHeight;
                Rectangle gridCellDisplay = new Rectangle();
//                myRoot.getChildren().add(gridCellDisplay);
            }
        }
	}

	public void updateView(){
		myRoot.getChildren().clear();
	}
	
	public Group getRoot(){
		return myRoot;
	}

}
