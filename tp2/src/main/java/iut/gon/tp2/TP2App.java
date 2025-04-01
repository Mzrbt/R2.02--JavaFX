package iut.gon.tp2;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class TP2App extends Application {

  private BorderPane contenu;
  private ListView<String> gauche;
  private ListView<String> droite;
  private Button versGauche;
  private Button versDroite;
  private Button retireTout;
  private Button ajouteTout;

  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(TP2App.class.getResource("Tp2.fxml"));
    contenu = fxmlLoader.load();
    Scene scene = new Scene(contenu);
    extraitIds(scene);

    prepareMenus((MenuBar) scene.lookup("#menus"));
    prepareListe();
    prepareBoutons();
    prepareFermeture(stage);

    stage.setTitle("Gestion de groupe");
    stage.setScene(scene);
    stage.show();
  }

  /** Prépare la fenêtre pour demander confirmation avant fermeture */
  private void prepareFermeture(Stage stage) {
    stage.setOnCloseRequest(event -> {
      //TODO confirmer ou consommer l'événement
    });
  }

  /** Prépare les actions des boutons */
  private void prepareBoutons() {
    ajouteTout.setOnAction(this::onAjouteTout);
    retireTout.setOnAction(this::onRetireTout);
    
    versGauche.setOnAction(event -> {
    	int index = droite.getSelectionModel().getSelectedIndex();
    	if(!(index == -1)) {
    		String Personne = droite.getItems().remove(index);
    		gauche.getItems().add(Personne);
    		droite.getSelectionModel().clearSelection(index);
    	}
    	
    	if (droite.getSelectionModel().isEmpty()) {
    		versGauche.setDisable(true);
    	}
    });
    
    versDroite.setOnAction(event -> {
    	int index = gauche.getSelectionModel().getSelectedIndex();
    	if(!(index == -1)) {
    		String Personne = gauche.getItems().remove(index);
    		droite.getItems().add(Personne);
    		gauche.getSelectionModel().clearSelection(index);
    	}
    	
    	if (gauche.getSelectionModel().isEmpty()) {
    		versDroite.setDisable(true);
    	}
    });
  }

  /** Ajoute tous les éléments de gauche dans la liste de droite
   Active le bouton "Retirer tout" et désactive le bouton "Ajouter tout" */
  private void onAjouteTout(ActionEvent actionEvent) {
    droite.getItems().addAll(gauche.getItems());
    gauche.getItems().clear();
    
    ajouteTout.setDisable(true);
    retireTout.setDisable(droite.getItems().isEmpty());
  }

  /** Ajoute tous les éléments de droite dans la liste de gauche
   Active le bouton "Ajouter tout" et désactive le bouton "Retirer tout" */
  private void onRetireTout(ActionEvent actionEvent) {
	  gauche.getItems().addAll(droite.getItems());
	  droite.getItems().clear();
	  
	  retireTout.setDisable(true);
	  ajouteTout.setDisable(gauche.getItems().isEmpty());
  }

  /** Prépare les menus et leurs événements */
  private void prepareMenus(MenuBar menus) {
	  Menu menuFichier = new Menu("_Fichiers");
	  Menu menuAide = new Menu("_Aide");
	  
	  menus.getMenus().addAll(menuFichier,menuAide);
  
	  MenuItem menuItemQuitter = new MenuItem("Quitter");
	  MenuItem menuItemAPropos = new MenuItem("A propos");
	  
	  menuFichier.getItems().addAll(menuItemQuitter);
	  menuAide.getItems().addAll(menuItemAPropos);
	  
	  menuItemQuitter.setOnAction(event -> {
		  Platform.exit();
	  });
	  
	  menuItemAPropos.setOnAction(event -> {
		  Alert alert = new Alert(Alert.AlertType.NONE, "Fait par Marin Jabet", ButtonType.CLOSE);
		  alert.setTitle("A propos");
		  alert.show();
	  });
  }

  /**
   Remplit la liste de gauche avec des valeurs
   Active le bouton "Ajouter tout"
   */
  private void prepareListe() {
	  gauche.getItems().add("Eleve 1");
	  gauche.getItems().add("Eleve 2");
	  gauche.getItems().add("Eleve 3");
	  
	  ajouteTout.setDisable(false);
  }

  private void extraitIds(Scene scene) {
    gauche = (ListView<String>) scene.lookup("#gauche");
    droite = (ListView<String>) scene.lookup("#droite");
    versGauche = (Button) scene.lookup("#versGauche");
    versDroite = (Button) scene.lookup("#versDroite");
    retireTout = (Button) scene.lookup("#retireTout");
    ajouteTout = (Button) scene.lookup("#ajouteTout");
  }

  public static void main(String[] args) {
    launch();
  }
}
