package cells;

import javafx.scene.paint.Color;

public class SharkCell extends Cell {

	private int myRoundsSurvived = 0;
	private int myEnergy = 2;
	private int fishEnergyGain = 2;

	/**
	 * Default constructor for the Shark cells used in Wator
	 */
	public SharkCell(double x_pos, double y_pos, double width, double height, int i, int j) {
		super(x_pos, y_pos, width, height,i,j);
		isShark = true;
		myType = "Shark";
		myRectangle.setFill(Color.CADETBLUE);
	}
	
	/**
	 * @return rounds survived
	 */
	public int getMyRoundsSurvived() {
		return myRoundsSurvived;
	}

	/**
	 * @param roundsSurvived input rounds survived for a shark
	 */
	public void setMyRoundsSurvived(int roundsSurvived) {
		myRoundsSurvived = roundsSurvived;
	}

	/**
	 * @return myEnergy
	 */
	public int getMyEnergy() {
		return myEnergy;
	}

	/**
	 * @param energy input energy of a shark
	 */
	public void setMyEnergy(int energy) {
		myEnergy = energy;
	}
	
	/**
	 * @return energy gained after eating a fish
	 */
	public int getFishEnergyGain() {
		return fishEnergyGain;
	}

}
