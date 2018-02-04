package grid;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.input.*;
import javafx.scene.Node;

public class Buttons {
    private final ResourceBundle myResources;
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
        myPause = new Button(myResources.getString("PlayCommand"));
        myStep = new Button(myResources.getString("StepCommand"));
        myLoadXMLbutton = new Button(myResources.getString("LoadXML"));
        myBar.getChildren().addAll(myPause, myStep, myLoadXMLbutton);
    }
	
	public Node getButton () {
        return myBar;
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


