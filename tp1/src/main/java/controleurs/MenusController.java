package controleurs;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;

public class MenusController {

    @FXML public ToggleGroup Epaisseur;
    @FXML public ToggleGroup Forme;
    @FXML public Label epaisseur;

    public Controleur controleur;
    
    public void setControleur(Controleur c) {
    	this.controleur = c;
    }
    
    @FXML
    public void onQuitte() {
    	if (Controleur.onQuitter()) {
    		Platform.exit();
    	}
    }
}
