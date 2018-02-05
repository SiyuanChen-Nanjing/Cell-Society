package cells;

import java.util.ArrayList;

import javafx.scene.paint.Color;

public class YellowCell extends Cell {

	public YellowCell(double x_pos, double y_pos, double width, double height) {
		super(x_pos, y_pos, width, height);
		isBurntEmpty = true;
		myRectangle.setFill(Color.YELLOW);
	}	
}
