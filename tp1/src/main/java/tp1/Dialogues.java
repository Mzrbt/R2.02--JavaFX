package tp1;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class Dialogues {
	public static boolean confirmation() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("Voulez vous vraiment quitter ?");
	    alert.getButtonTypes().setAll(ButtonType.NO, ButtonType.YES);
	    
	    Optional<ButtonType> res = alert.showAndWait();
	      
	    if (res.orElse(ButtonType.NO) != ButtonType.YES) {
	    	  return false;
	    }
		return true;
	}
}
