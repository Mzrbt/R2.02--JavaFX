package controleurs;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class CouleursController implements Initializable{

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
    @FXML public VBox vbox_principal;
    
    public Controleur controleur;
    public DessinController dessinController;
    
    private Rectangle rectPrecedent = null;

    public void setDessinController(DessinController dessinController) {
        this.dessinController = dessinController;
    }
    
    public void setControleur(Controleur c) {
    	this.controleur = c;
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		vbox_principal.setOnMouseClicked(event -> {
	        if (event.getTarget() instanceof Rectangle) {
	            Rectangle rect = (Rectangle) event.getTarget();
	            
	            if (rectPrecedent != null) {
	                rectPrecedent.setArcWidth(5);
	                rectPrecedent.setArcHeight(5);
	                rectPrecedent.setStrokeWidth(1);
	            }
	           
	            Paint p = rect.getFill();
	            controleur.setCouleur((Color) p);
	            rect.arcWidthProperty().set(10);
	            rect.arcHeightProperty().set(10);
	            rect.strokeWidthProperty().set(5);
	            
	            rectPrecedent = rect;
	        }
	    });
	}

}
