package fr.unicaen.iut.tp5;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.Observable;
import javafx.beans.WeakInvalidationListener;
import javafx.beans.binding.StringExpression;
import javafx.collections.MapChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class ControleurDemineur implements Initializable{
	
	ModeleDemineur modele = new ModeleDemineur(0, 0, 0); 
	
	StringExpression chaine;
	
	private @FXML TextField inconnues;
	private @FXML TextField marques;
	private @FXML ToggleGroup Diff;
	private @FXML GridPane tab;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		inconnues.textProperty().bind(modele.nbInconnuesProperty().asString());
		marques.textProperty().bind(modele.nbMarquesProperty().asString());
		
		Diff.selectedToggleProperty().addListener((obs, oldV, newV) -> {
			String userData = newV.getUserData().toString();
			initGrille(userData);
		});
	}
	
	public void initGrille(String string) {
		tab.getRowConstraints().clear();
		tab.getColumnConstraints().clear();
		tab.getChildren().clear(); 
		
		int[] donnees = modele.parseUserData(string);
		int nbLignes = donnees[0];
	    int nbColonnes = donnees[1];	
	    int nbMines = donnees[2];
	
		modele.setTaille(nbLignes, nbColonnes, nbMines);
		
		tab.setAlignment(Pos.CENTER);
		
		for (int i = 0; i < tab.getRowCount(); i++) {
			RowConstraints contrainte = new RowConstraints();
			contrainte.setPrefHeight(32);
			tab.getRowConstraints().add(contrainte);
		}
		
		for (int j = 0; j < tab.getColumnCount(); j++) {
			ColumnConstraints contrainte = new ColumnConstraints();
			contrainte.setPrefWidth(32);
			tab.getColumnConstraints().add(contrainte);
		}
		
		for (int y = 0; y < nbLignes; y++) {
	        for (int x = 0; x < nbColonnes; x++) {
	            javafx.scene.control.Label caseLabel = new javafx.scene.control.Label("?");
	            caseLabel.setPrefSize(32, 32);
	            caseLabel.setStyle("-fx-border-color: black; -fx-alignment: center;");
	            tab.add(caseLabel, x, y);
	        }
	    }
	}
}
