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

    @FXML
    private ToggleGroup Epaisseur;

    @FXML
    private ToggleGroup Forme;

    @FXML
    private GridPane barre_inferieure;

    @FXML
    private Rectangle blanc;

    @FXML
    private Rectangle bleu_clair;

    @FXML
    private Rectangle bleu_fonce;

    @FXML
    private Canvas canva_cadre_dessin;

    @FXML
    private ColorPicker colorpicker_choix_couleur;

    @FXML
    private Label couleur;

    @FXML
    private Label epaisseur;

    @FXML
    private Rectangle jaune;

    @FXML
    private Rectangle noir;

    @FXML
    private Label outil;

    @FXML
    private Pane pane_cadre_dessin;

    @FXML
    private TilePane pane_choix_couleur;

    @FXML
    private BorderPane principal;

    @FXML
    private Rectangle rouge;

    @FXML
    private Label val_x;

    @FXML
    private Label val_y;

    @FXML
    private Rectangle vert;

    @FXML
    private Rectangle violet;
    
    private SimpleDoubleProperty prevX = new SimpleDoubleProperty();
    private SimpleDoubleProperty prevY = new SimpleDoubleProperty();
    
    private Dessin dessin;
    
    private Trace trace;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		pane_cadre_dessin.layoutBoundsProperty().addListener((observable, oldValue, newValue) -> {
			pane_cadre_dessin.setPrefHeight(newValue.getHeight());
			pane_cadre_dessin.setPrefWidth(newValue.getWidth());
		});
		
		canva_cadre_dessin.heightProperty().bind(pane_cadre_dessin.heightProperty());
		canva_cadre_dessin.widthProperty().bind(pane_cadre_dessin.widthProperty());
		
		canva_cadre_dessin.heightProperty().addListener((observableValue, oldValue, newValue) -> remakeCanva());
		canva_cadre_dessin.widthProperty().addListener((observableValue, oldValue, newValue) -> remakeCanva());
		
		val_x.textProperty().bind(Bindings.format("%.2f", prevX));
	    val_y.textProperty().bind(Bindings.format("%.2f", prevY));
	}
	
	public Controller(Dessin dessin) {
		this.dessin = dessin;
	}
	
	public void onMousePressed(MouseEvent evt) {
		prevX.set(evt.getX());
		prevY.set(evt.getY());
		trace = new Trace(1, "noir", prevX.get(), prevY.get());
		dessin.addFigure(trace);
		
	}
	
	public void onMouseDragged(MouseEvent evt) {
		canva_cadre_dessin.getGraphicsContext2D().strokeLine(prevX.get(), prevY.get(), evt.getX(), evt.getY());
    	trace.addPoint(new Point(prevX.get(), prevY.get()));
    	prevX.set(evt.getX());
		prevY.set(evt.getY());
	}
	
	private void remakeCanva() {
		canva_cadre_dessin.getGraphicsContext2D().clearRect(0, 0, canva_cadre_dessin.getWidth(), canva_cadre_dessin.getHeight());
		for (Figure f : dessin.getFigures()) {
			for (int i = 1; i < f.getPoints().size(); i++) {
				double x0 = f.getPoints().get(i-1).getX();
				double y0 = f.getPoints().get(i-1).getY();
				double x1 = f.getPoints().get(i).getX();
				double y1 = f.getPoints().get(i).getY();
				
				canva_cadre_dessin.getGraphicsContext2D().strokeLine(x0, y0, x1, y1);
			}
		}
	}
	
	public void onMouseMoved(MouseEvent evt) {
		prevX.set(evt.getX());
		prevY.set(evt.getY());
	}
}
