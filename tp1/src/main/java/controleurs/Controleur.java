package controleurs;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import javafx.stage.WindowEvent;
import modele.Dessin;
import modele.Figure;
import tp1.Dialogues;

public class Controleur {

	public final static Dessin dessin = new Dessin();
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
	
	public static boolean onQuitter() {
		if (Dialogues.confirmation()) {
			return true;
		}
	return false;
	}
	
	public static void onCloseRequest(WindowEvent evt) {
		if (!onQuitter()) {
			evt.consume();
		}
	}
	
	public static void remakeCanva() {
		DessinController.canva_cadre_dessin.getGraphicsContext2D().clearRect(0, 0, DessinController.canva_cadre_dessin.getWidth(), DessinController.canva_cadre_dessin.getHeight());
		for (Figure f : dessin.getFigures()) {
			for (int i = 1; i < f.getPoints().size(); i++) {
				double x0 = f.getPoints().get(i-1).getX();
				double y0 = f.getPoints().get(i-1).getY();
				double x1 = f.getPoints().get(i).getX();
				double y1 = f.getPoints().get(i).getY();
				
				DessinController.canva_cadre_dessin.getGraphicsContext2D().strokeLine(x0, y0, x1, y1);
			}
		}
	}
}