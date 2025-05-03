package tp1;
import java.net.URL;
import java.util.ResourceBundle;

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
    
    private double prevX, prevY;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		canva_cadre_dessin.heightProperty().bind(pane_cadre_dessin.heightProperty());
		canva_cadre_dessin.widthProperty().bind(pane_cadre_dessin.widthProperty());
	}
	
	public void onMousePressed(MouseEvent evt) {
		prevX = evt.getX();
		prevY = evt.getY();
		val_x.setText(String.valueOf(prevX));
		val_y.setText(String.valueOf(prevY));
		
	}
	
	public void onMouseDragged(MouseEvent evt) {
    	prevX = evt.getX();
    	prevY = evt.getY();
    	val_x.setText(String.valueOf(prevX));
		val_y.setText(String.valueOf(prevY));
	}

}
