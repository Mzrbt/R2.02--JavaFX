package controleurs;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.WindowEvent;
import modele.Dessin;
import modele.Figure;
import modele.Trace;
import tp1.Dialogues;

public class Controleur implements Initializable{

	public final Dessin dessin1 = new Dessin();
	public Figure figure = null;
	public final SimpleDoubleProperty prevX = new SimpleDoubleProperty();
    public final SimpleDoubleProperty prevY = new SimpleDoubleProperty();
    
    public final SimpleIntegerProperty epaisseur = new SimpleIntegerProperty(1);
	public final SimpleObjectProperty<Color> couleur = new SimpleObjectProperty<Color>(Color.BLACK);
	
	private boolean figureEnCoursAjoutee = false;
		
	@FXML private MenusController menusController;
	@FXML private DessinController dessinController;
	@FXML private StatutController statutController;
	@FXML private CouleursController couleursController;
	
	public void initialize(URL location, ResourceBundle resources) {
		
		menusController.setControleur(this);
		dessinController.setControleur(this);
		statutController.setControleur(this);
		couleursController.setControleur(this);
		
		statutController.val_x.textProperty().bind(prevX.asString("X: %.2f"));
	    statutController.val_y.textProperty().bind(prevY.asString("Y: %.2f"));
	    statutController.epaisseur.textProperty().bind(epaisseur.asString("%d"));
	    statutController.couleur.textProperty().bind(couleur.asString());
	    
	    menusController.Epaisseur.selectedToggleProperty().addListener((obVal, oldVal, newVal) -> {
			RadioMenuItem choix = (RadioMenuItem) newVal;
			int val = Integer.parseInt(choix.getText());
			epaisseur.set(val);
			statutController.epaisseur.setText(choix.getText());
		});	    
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
	
	public void remakeCanva() {
		dessinController.efface();
		for (Figure f : dessin1.getFigures()) {
			for (int i = 1; i < f.getPoints().size(); i++) {
				double x0 = f.getPoints().get(i-1).getX();
				double y0 = f.getPoints().get(i-1).getY();
				double x1 = f.getPoints().get(i).getX();
				double y1 = f.getPoints().get(i).getY();
				
				dessinController.trace(x0, y0, x1, y1);
			}
		}
	}
	
	public void dessine() {
		dessinController.setEpaisseur();
		GraphicsContext g = dessinController.canva_cadre_dessin.getGraphicsContext2D();
		g.clearRect(0, 0, g.getCanvas().getWidth(), g.getCanvas().getHeight());
		for (Figure f : dessin1.getFigures()) {
			g.setLineWidth(f.getEpaisseur());
			for (int i = 1; i < f.getPoints().size(); i++) {
				double x0 = f.getPoints().get(i-1).getX();
				double y0 = f.getPoints().get(i-1).getY();
    			double x1 = f.getPoints().get(i).getX();
    			double y1 = f.getPoints().get(i).getY();
    			g.strokeLine(x0, y0, x1, y1);
			}
		}
	}
	
	public void onMousePress(MouseEvent evt) {
	    figure = new Trace(epaisseur.get(), couleur.toString(), evt.getX(), evt.getY());
	    figure.addPoint(evt.getX(), evt.getY());
	    prevX.set(evt.getX());
	    prevY.set(evt.getY());
	    figureEnCoursAjoutee = false;
	}

	public void onMouseDrag(MouseEvent evt) {
	    figure.addPoint(evt.getX(), evt.getY());
	    dessinController.trace(prevX.get(), prevY.get(), evt.getX(), evt.getY());
	    prevX.set(evt.getX());
	    prevY.set(evt.getY());
	    if (!figureEnCoursAjoutee) {
	        dessin1.addFigure(figure);
	        figureEnCoursAjoutee = true;
	    }
	    //System.out.println("trace");
	}

	public void onMouseMove(MouseEvent evt) {
	    prevX.set(evt.getX());
	    prevY.set(evt.getY());
	}
	
	public void setEpaisseur() {
		
	}
}