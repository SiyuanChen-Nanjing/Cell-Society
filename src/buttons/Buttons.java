package buttons;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.input.*;
import javafx.scene.Node;

public class Buttons {
	private static final double MIN_SLIDER_VALUE = 0;
    private static final double MAX_SLIDER_VALUE = 2.0;
    private final ResourceBundle myResources;
    private Slider mySlider;
    private Button myPause;
    private Button myStep;
    private Button myLoadXMLbutton;
    private HBox myBar;

	public Buttons() {
		String initFile = "resources";
        String fileName = "/English";
        myResources = ResourceBundle.getBundle(initFile + fileName);
      }
	
	public void initButton (int height, int width) {
        myBar = new HBox(height);
        mySlider = new Slider(MIN_SLIDER_VALUE, MAX_SLIDER_VALUE, 1);
        myPause = new Button(myResources.getString("PlayCommand"));
        myStep = new Button(myResources.getString("StepCommand"));
        myLoadXMLbutton = new Button(myResources.getString("LoadXML"));
        myBar.getChildren().addAll(mySlider, myPause, myStep, myLoadXMLbutton);
    }
	
	public Node getButton () {
        return myBar;
    }
	
    public double getSpeed () {
        return mySlider.getValue();
    }
    
	public Button getPause () {
        return myPause;
    }

    public void pauseButton (EventHandler<MouseEvent> event) {
        myPause.setOnMouseClicked(event);
    }


    public void stepButton (EventHandler<MouseEvent> event) {
        myStep.setOnMouseClicked(event);
    }


    public void loadXMLButton (EventHandler<MouseEvent> event) {
        myLoadXMLbutton.setOnMouseClicked(event);
    }
	
}


