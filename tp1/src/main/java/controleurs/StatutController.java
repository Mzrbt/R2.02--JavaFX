package controleurs;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class StatutController {

	@FXML public GridPane barre_inferieure;
    @FXML public Label val_x;
    @FXML public Label val_y;
    @FXML public Label couleur;
    @FXML public Label epaisseur;
    @FXML public Label outil;
    
    public Controleur controleur;
    
    public void setControleur(Controleur c) {
    	this.controleur = c;
    }
    
}
