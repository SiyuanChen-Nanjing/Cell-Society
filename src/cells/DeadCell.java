package cells;

import javafx.scene.paint.Color;

public class DeadCell extends Cell {

	public DeadCell(double x_pos, double y_pos, double width, double height) {
		super(x_pos, y_pos, width, height);
		isDead = true;
		myRectangle.setFill(Color.DIMGRAY);
	}

}
