package cells;

import javafx.scene.paint.Color;

public class SharkCell extends Cell {

	private int myRoundsSurvived = 0;
	private int myEnergy = 2;
	private int fishEnergyGain = 2;

	public SharkCell(double x_pos, double y_pos, double width, double height, int i, int j) {
		super(x_pos, y_pos, width, height,i,j);
		isShark = true;
		myRectangle.setFill(Color.CADETBLUE);
	}

	public int getMyRoundsSurvived() {
		return myRoundsSurvived;
	}

	public void setMyRoundsSurvived(int roundsSurvived) {
		myRoundsSurvived = roundsSurvived;
	}

	public int getMyEnergy() {
		return myEnergy;
	}

	public void setMyEnergy(int energy) {
		myEnergy = energy;
	}
	
	public int getFishEnergyGain() {
		return fishEnergyGain;
	}

	public void setFishEnergyGain(int fishEnergyGain) {
		this.fishEnergyGain = fishEnergyGain;
	}
}
