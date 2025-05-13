package fr.unicaen.iut.tp5;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Demineur extends Application{

	private static ControleurDemineur controller;
	private static Scene scene;
	  
	public void start(Stage stage) throws IOException {
	  	
		FXMLLoader fxmlLoader = new FXMLLoader(Demineur.class.getResource("Vue.fxml"));
		ControleurDemineur controller = new ControleurDemineur();

		fxmlLoader.setController(controller);

		Scene scene = new Scene(fxmlLoader.load(), 800, 600);

		stage.setTitle("Demineur");
		stage.setScene(scene);
		stage.show();
	}
	  
	public static void main(String[] args) {
		  launch();
	}
}
