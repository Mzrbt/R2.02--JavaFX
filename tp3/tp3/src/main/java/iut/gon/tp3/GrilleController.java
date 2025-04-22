package iut.gon.tp3;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class GrilleController implements Initializable{
	
	private @FXML GridPane grille;
	
	private Label[][] labels = new Label[3][3];

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		grille.setStyle("-fx-background-color: seashell");
		for (int lg = 0; lg < 3; lg++) {
            for (int col = 0; col < 3; col++) {
                Label label = new Label(String.format("L%dC%d", lg, col));
                labels[lg][col] = label;
                grille.add(label, col, lg);
                
                label.setOnMouseClicked(event -> {
                	label.setText("Bonjour");
                });
                
                label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                
                label.setAlignment(javafx.geometry.Pos.CENTER);
                label.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
            }
        }
	}
}
