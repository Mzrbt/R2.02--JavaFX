package controleurs;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import modele.Figure;
import modele.Point;
import modele.Trace;

public class DessinController {

	@FXML public Pane pane_cadre_dessin;
	@FXML public static Canvas canva_cadre_dessin;
	
	public Controleur controleur;
    
    public void setControleur(Controleur c) {
    	this.controleur = c;
    }
    
    public void initialize(URL location, ResourceBundle resources) {
    	
    	pane_cadre_dessin.layoutBoundsProperty().addListener((observable, oldValue, newValue) -> {
			pane_cadre_dessin.setPrefHeight(newValue.getHeight());
			pane_cadre_dessin.setPrefWidth(newValue.getWidth());
		});
		
		canva_cadre_dessin.heightProperty().bind(pane_cadre_dessin.heightProperty());
		canva_cadre_dessin.widthProperty().bind(pane_cadre_dessin.widthProperty());
		
		canva_cadre_dessin.heightProperty().addListener((observableValue, oldValue, newValue) -> controleur.remakeCanva());
		canva_cadre_dessin.widthProperty().addListener((observableValue, oldValue, newValue) -> controleur.remakeCanva());
    	
    }
    
    public void efface() {
    	canva_cadre_dessin.getGraphicsContext2D().clearRect(0, 0, canva_cadre_dessin.getWidth(), canva_cadre_dessin.getHeight());
    }
    
    public void trace(double x1, double y1, double x2, double y2) {
    	canva_cadre_dessin.getGraphicsContext2D().strokeLine(x1, y1, x2, y2);
    }
    
    public void onMousePress(MouseEvent evt) {
		
	}
	
	public void onMouseDrag(MouseEvent evt) {
		
	}
		
	public void onMouseMove(MouseEvent evt) {
		controleur.prevX.setValue(evt.getX());
		controleur.prevY.setValue(evt.getY());
	}
}
