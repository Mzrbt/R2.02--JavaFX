package controleurs;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import modele.Dessin;
import modele.Figure;

public class Controleur {

	public final Dessin dessin = new Dessin();
	private Figure figure;
	public final SimpleDoubleProperty prevX = new SimpleDoubleProperty();
    public final SimpleDoubleProperty prevY = new SimpleDoubleProperty();
    
    public final SimpleIntegerProperty epaisseur = new SimpleIntegerProperty(1);
	public final SimpleObjectProperty<Color> couleur = new SimpleObjectProperty<Color>(Color.BLACK);
	
	@FXML public MenusController menusController;
	@FXML public DessinController dessinController;
	@FXML public StatutController statutController;
	@FXML public CouleursController couleursController;
	
	public void initialize(URL location, ResourceBundle resources) {
		
		menusController.setControleur(this);
		dessinController.setControleur(this);
		statutController.setControleur(this);
		couleursController.setControleur(this);

	}
	
}