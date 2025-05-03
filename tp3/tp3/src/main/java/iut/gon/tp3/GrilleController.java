package iut.gon.tp3;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class GrilleController implements Initializable{
	
	@FXML
	private GridPane grille;
	
	private GrilleModel model;
	
	public GrilleController(GrilleModel model) {
		this.model = model;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		grille.setStyle("-fx-background-color: seashell");
		for (int lg = 0; lg < 3; lg++) {
            for (int col = 0; col < 3; col++) {
            	
            	int ligne = lg;
            	int colonne = col;
            	
            	model.setCase(lg, col, String.format("H3110"));
                
                Label label = new Label();
                
                label.textProperty().bind(model.getCase(ligne, colonne));
                label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                label.setAlignment(javafx.geometry.Pos.CENTER);
                label.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
                
                label.setOnMouseClicked(event -> {
                	model.setCase(ligne, colonne, "Bonjour");
                });
                
                grille.add(label, col, lg);
            }
        }
	}
	
	
}
