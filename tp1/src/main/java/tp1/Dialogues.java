package tp1;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class Dialogues {
	public static boolean confirmation() {
		Alert alert = new Alert(AlertType.CONFIRMATION, "Voulez vous vraiment quitter ?", ButtonType.YES, ButtonType.NO);
		alert.setTitle("Fermeture");
	    
	    Optional<ButtonType> res = alert.showAndWait();
	      
	    if (res.orElse(ButtonType.NO) != ButtonType.YES) {
	    	  return false;
	    }
		return true;
	}
}
