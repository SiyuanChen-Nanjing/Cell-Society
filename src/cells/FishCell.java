package cells;

import javafx.scene.paint.Color;

public class FishCell extends Cell {
	
	private int myRoundsSurvived = 0;

	/**
	 * Default constructor for the Fish cells used in Wator
	 */
	public FishCell(double x_pos, double y_pos, double width, double height, int i, int j) {
		super(x_pos, y_pos, width, height, i,j);
		isFish = true;
		myType = "Fish";
		myRectangle.setFill(Color.CHARTREUSE);
	}
	
	/**
	 * @return myRoundsSurvived
	 */
	public int getMyRoundsSurvived() {
		return myRoundsSurvived;
	}

	/**
	 * @param roundsSurvived input rounds survived for a fish
	 */
	public void setMyRoundsSurvived(int roundsSurvived) {
		myRoundsSurvived = roundsSurvived;
	}
}
