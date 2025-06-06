package controleurs;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class DessinController implements Initializable{

	@FXML public Pane pane_cadre_dessin;
	@FXML public Canvas canva_cadre_dessin;
	
	private Controleur controleur;
    
    public void setControleur(Controleur c) {
    	this.controleur = c;
    	
    	canva_cadre_dessin.setOnMouseMoved(this::onMouseMove);
    	canva_cadre_dessin.setOnMousePressed(this::onMousePress);
    	canva_cadre_dessin.setOnMouseDragged(this::onMouseDrag);
    	    	
    	canva_cadre_dessin.heightProperty().bind(pane_cadre_dessin.heightProperty());
		canva_cadre_dessin.widthProperty().bind(pane_cadre_dessin.widthProperty());
		
		canva_cadre_dessin.heightProperty().addListener((observableValue, oldValue, newValue) -> controleur.remakeCanva());
		canva_cadre_dessin.widthProperty().addListener((observableValue, oldValue, newValue) -> controleur.remakeCanva());
    }
   
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	pane_cadre_dessin.layoutBoundsProperty().addListener((observable, oldValue, newValue) -> {
			pane_cadre_dessin.setPrefHeight(newValue.getHeight());
			pane_cadre_dessin.setPrefWidth(newValue.getWidth());
		});
    }
    
    public void efface() {
    	canva_cadre_dessin.getGraphicsContext2D().clearRect(0, 0, canva_cadre_dessin.getWidth(), canva_cadre_dessin.getHeight());
    }
    
    public void trace(double x1, double y1, double x2, double y2) {
    	canva_cadre_dessin.getGraphicsContext2D().strokeLine(x1, y1, x2, y2);
    }
    
    @FXML
    public void onMousePress(MouseEvent evt) {
        if (controleur != null) {
            controleur.onMousePress(evt);
        }
    }

    @FXML
    public void onMouseMove(MouseEvent evt) {
        if (controleur != null) {
            controleur.onMouseMove(evt);
        }
    }

    @FXML
    public void onMouseDrag(MouseEvent evt) {
        if (controleur != null) {
            controleur.onMouseDrag(evt);
        }
    }

}
