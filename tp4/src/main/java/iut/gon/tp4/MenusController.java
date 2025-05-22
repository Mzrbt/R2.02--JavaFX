package iut.gon.tp4;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.GridPane;

public class MenusController {
	
	  private GrilleModel modele;
	  private Scores table;
	  @FXML private GridPane grille;
	  @FXML private MenuBar menuBar;
	 	  
	  public void setParams(GrilleModel modele, Scores table) {
			this.modele = modele;
			this.table = table;
	  }
	  
	  public void setScore(Scores s) {
		  this.table = s;
	  }
	  
	  public void setGrille(GridPane grille) {
		    this.grille = grille;
		}

	  @FXML
	  public void onMenuNouvelle(ActionEvent evt) {
	    modele.nouvellePartie();
	  }
	 
	  @FXML
	  public void onMenuTable(ActionEvent evt) {
	      try {
	          FXMLLoader fxmlLoader = new FXMLLoader(Morpion.class.getResource("table.fxml"));
	          Parent tableRoot = fxmlLoader.load();
	          TableController tableController = fxmlLoader.getController();
	      
	          tableController.setScores(table);
	          tableController.setJeu(menuBar.getScene().getRoot());
	          
	          menuBar.getScene().setRoot(tableRoot);

	      } catch (IOException e) {
	          e.printStackTrace();
	      }
	  }


	  @FXML
	  public void onMenuQuitter(ActionEvent evt) {
	    Platform.exit();
	  }
}
