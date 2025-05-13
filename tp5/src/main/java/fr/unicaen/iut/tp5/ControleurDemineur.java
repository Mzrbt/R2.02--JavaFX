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
import javafx.scene.layout.GridPane;

public class ControleurDemineur implements Initializable{
	
	ModeleDemineur modele; 
	
	StringExpression chaine;
	
	private @FXML TextField inconnues;
	private @FXML TextField marques;
	private @FXML ToggleGroup Diff;
	private @FXML GridPane tab;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		modele.nbInconnuesProperty().asString(inconnues.getText());
		modele.nbMarquesProperty().asString(marques.getText());
		
		Diff.selectedToggleProperty().addListener((obs, oldV, newV) -> {
			String userData = newV.getUserData().toString();
			initGrille(userData);
		});
	}
	
	public void initGrille(String string) {
		GridPane.clearConstraints(tab);
		
		int[] donnees = modele.parseUserData(string);
	
		modele.setTaille(donnees[0], donnees[1], donnees[2]);
	}
}
