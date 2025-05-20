package tp1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import modele.Dessin;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    
    private double prevX, prevY;
    
    public static Controller controller;
    
    @Override
    public void start(Stage stage) throws IOException {
    	
    	Dessin dessin = new Dessin();
     	controller = new Controller(dessin);
    	
        scene = new Scene(loadFXML("CadreGribouille"), 640, 480);
        stage.setScene(scene);
        stage.show();
        
        stage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, event -> {
        	if (!Dialogues.confirmation()) {
        		event.consume();
        	}
        });
       
    }	

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        fxmlLoader.setController(controller);
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}