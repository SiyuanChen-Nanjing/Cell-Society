package cells;

import java.util.ArrayList;

import javafx.scene.paint.Color;

public class ForagingAntCell extends Cell {

	private ArrayList<AntCell> myAnts;
	private ArrayList<AntCell> myNextAnts;
	private double myFoodPheromones;
	private double myHomePheromones;
	private double myMaxPheromones;
	private int myMaxAnts;

	
	public ForagingAntCell(double maxPh, int maxAnts, double maxPheromones, double x_pos, double y_pos, double width, double height, int i, int j) {
		super(x_pos, y_pos, width, height, i,j);
		myAnts = new ArrayList<>();
		myNextAnts = new ArrayList<>();
		myMaxPheromones = maxPheromones;
		isForaging = true;
		myRectangle.setFill(Color.AQUA);
	}

    public boolean fullOfAnts () {
        return (myAnts.size() + myNextAnts.size()) > myMaxAnts;
    }
    
    public double getPheromones(boolean food) {
    	if (food) {
    		return myFoodPheromones;
    	} else {
    		return myHomePheromones;
    	}
    }

	public void setMaxPh(boolean food) {
		if (food) {
			myFoodPheromones = myMaxPheromones;
		} else {
			myHomePheromones = myMaxPheromones;
		}
	}
}
