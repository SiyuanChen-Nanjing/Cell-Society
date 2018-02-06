package cells;

import javafx.scene.paint.Color;

public class AliveCell extends Cell {

	public AliveCell(double x_pos, double y_pos, double width, double height) {
		super(x_pos, y_pos, width, height);
		isAlive = true;
		myRectangle.setFill(Color.AZURE);
	}

}
