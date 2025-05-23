package controleurs;

import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Rectangle;

public class CouleursController {

	@FXML public ColorPicker colorpicker_choix_couleur;
	@FXML public TilePane pane_choix_couleur;
	@FXML public Rectangle blanc;
    @FXML public Rectangle bleu_clair;
    @FXML public Rectangle bleu_fonce;
    @FXML public Rectangle jaune;
    @FXML public Rectangle noir;
    @FXML public Rectangle rouge;
    @FXML public Rectangle vert;
    @FXML public Rectangle violet;
    
    public Controleur controleur;
    
    public void setControleur(Controleur c) {
    	this.controleur = c;
    }

}
