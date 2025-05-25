package fr.iutgon.tp6;

import java.text.DecimalFormat;

import javafx.css.PseudoClass;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;

public class FactureCellFactory<T> extends TableCell<T, Number> {
	
	PseudoClass negatifCSS = PseudoClass.getPseudoClass("negatif");
	DecimalFormat formatter = new DecimalFormat("#0.00");
	
	public FactureCellFactory() {
		setAlignment(Pos.CENTER_RIGHT);
	}
	
	public void updateItem(Number item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            super.setText(null);
            super.setGraphic(null);
            pseudoClassStateChanged(negatifCSS, false);
        } else {
            double value = item.doubleValue();
            super.setText(formatter.format(value));
            pseudoClassStateChanged(negatifCSS, value < 0);
        }
    }
	
}
