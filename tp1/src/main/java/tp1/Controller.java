package tp1;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Rectangle;
import modele.Dessin;
import modele.Figure;
import modele.Point;
import modele.Trace;

public class Controller implements Initializable{

	@FXML public BorderPane principal;
    
    private SimpleDoubleProperty prevX = new SimpleDoubleProperty();
    private SimpleDoubleProperty prevY = new SimpleDoubleProperty();
    
    private Dessin dessin;
    
    private Trace trace;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		val_x.textProperty().bind(Bindings.format("%.2f", prevX));
	    val_y.textProperty().bind(Bindings.format("%.2f", prevY));
	}
	
	public Controller(Dessin dessin) {
		this.dessin = dessin;
	}
	
	
}
