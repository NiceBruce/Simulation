package code.rtfmyoumust.simulation;

import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class HelloController {

//    Vector v = new Vector();
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}