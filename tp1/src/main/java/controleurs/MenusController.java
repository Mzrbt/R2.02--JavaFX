package controleurs;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class MenusController{

    @FXML public ToggleGroup Epaisseur;
    @FXML public ToggleGroup Forme;
    @FXML public Label epaisseur;

    public Controleur controleur;
    public DessinController dessinControleur;
       
    public void setDessinControleur(DessinController c) {
    	this.dessinControleur = c;
    }
    
    public void setControleur(Controleur c) {
    	this.controleur = c;
    }
    
    @FXML
    public void onQuitte() {
    	if (Controleur.onQuitter()) {
    		Platform.exit();
    	}
    }
    
    @FXML
    public void sauvegarde() {
        dessinControleur.sauvegarde();
    }
    
    @FXML
    public void onCharger() {
        dessinControleur.charge();
    }

}
