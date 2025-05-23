package controleurs;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;

public class DessinController {

	@FXML public Pane pane_cadre_dessin;
	@FXML public Canvas canva_cadre_dessin;
	
	public Controleur controleur;
    
    public void setControleur(Controleur c) {
    	this.controleur = c;
    }
}
