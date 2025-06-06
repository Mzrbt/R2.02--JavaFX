package controleurs;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class DessinController implements Initializable{

	@FXML public Pane pane_cadre_dessin;
	@FXML public Canvas canva_cadre_dessin;
	
	private Controleur controleur;
	
	private Stage stage;
    
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
    	canva_cadre_dessin.getGraphicsContext2D().setLineWidth(controleur.epaisseur.get());
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
    
    public void setEpaisseur() {
    	canva_cadre_dessin.getGraphicsContext2D().setLineWidth(controleur.epaisseur.get());
    }
    
    public void setCouleur(Color c) {
    	GraphicsContext gc = canva_cadre_dessin.getGraphicsContext2D();
        gc.setStroke(c);
    }
    
    public void sauvegarde() {
    	FileChooser f = new FileChooser();
    	f.setTitle("Enregistrer le dessin");
    	f.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers dessin", "*.grb"));

        File fichierChoisi = f.showSaveDialog(stage);
        
        Stage stage = (Stage) pane_cadre_dessin.getScene().getWindow();

        if (fichierChoisi != null) {
            controleur.dessin1.sauveSous(fichierChoisi.getAbsolutePath());

            stage.setTitle(fichierChoisi.getName());
        }
    }
    
    public void setStage(Stage stage) {
        this.stage = stage;
        
        if (stage != null) {
		    stage.titleProperty().bind(
		        Bindings.when(controleur.dessin1.estModifieProperty())
		                .then(Bindings.concat(controleur.dessin1.nomDuFichierProperty(), " *"))
		                .otherwise(Bindings.concat(controleur.dessin1.nomDuFichierProperty()))
		    );
		}
    }
    
    public void charge() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Charger un dessin");

        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            controleur.dessin1.charge(file.getAbsolutePath());
            controleur.dessine();
        }
    }

}
