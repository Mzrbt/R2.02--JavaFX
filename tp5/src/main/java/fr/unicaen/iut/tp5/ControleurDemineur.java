package fr.unicaen.iut.tp5;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.Observable;
import javafx.beans.WeakInvalidationListener;
import javafx.beans.binding.StringExpression;
import javafx.collections.MapChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
		GridPane.clearConstraints(tab);
		
		int[] donnees = modele.parseUserData(string);
	
		modele.setTaille(donnees[0], donnees[1], donnees[2]);
		
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
	}
}
