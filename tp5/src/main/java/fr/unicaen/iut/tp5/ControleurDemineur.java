package fr.unicaen.iut.tp5;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.beans.WeakInvalidationListener;
import javafx.beans.binding.StringExpression;
import javafx.collections.MapChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;


public class ControleurDemineur implements Initializable{
	
	ModeleDemineur modele = new ModeleDemineur(0, 0, 0); 
	
	StringExpression chaine;
	
	private @FXML TextField inconnues;
	private @FXML TextField marques;
	private @FXML ToggleGroup Diff;
	private @FXML GridPane tab;
	
	private final Background inconnu = new Background(new BackgroundFill(Color.AQUA, new CornerRadii(0.2, true), Insets.EMPTY));
	private final Background libre = new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY));
	private final Background echec = new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY));
	private final Background marquee = new Background(new BackgroundFill(Color.LEMONCHIFFON, CornerRadii.EMPTY, Insets.EMPTY));

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
		
		for (int i = 0; i < nbLignes; i++) {
	        for (int j = 0; j < nbColonnes; j++) {
	        	Label label = new Label();
				label.setPrefSize(31, 31);
				label.setBackground(inconnu);
				label.setTextAlignment(TextAlignment.CENTER);
				label.textProperty().bind(modele.texteProperty(i,j));
				
				int x = i;
				int y = j;
				
				label.addEventHandler(MouseEvent.MOUSE_CLICKED, (evt) -> {
					if (evt.getButton() == MouseButton.PRIMARY) {
						modele.revele(x, y);
					}
					else if (evt.getButton() == MouseButton.SECONDARY) {
						modele.marque(x, y);
					}
					if (modele.getText(x, y).equals("?")) {label.setBackground(inconnu);}
					if (modele.getText(x, y).equals("P")) {label.setBackground(marquee);}
					if (modele.getText(x, y).equals("X")) {label.setBackground(echec);
					
						if (evt.getButton() == MouseButton.PRIMARY) {
							Alert alert = new Alert(Alert.AlertType.INFORMATION);
							alert.setTitle("Fin de partie");
							alert.setHeaderText(null);
							alert.setContentText("Vous avez perdu !");
							alert.setOnHidden(e -> initGrille(string));
							alert.show();
				    	}
					}
					
					else {label.setBackground(libre);}
				});
				
				tab.add(label, j, i);
	        }
	    }
	}
	
	public void onQuitter() {
		Platform.exit();
	}
}
