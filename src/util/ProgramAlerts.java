package util;

import javafx.scene.control.Alert;

public class ProgramAlerts {
	
	public static void newException(String message) {
		Alert alert= new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Something went wrong");
		alert.setContentText(message);
		alert.show();
	}
	
	public static void newInformation(String message) {
		Alert alert= new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("New Event");
		alert.setContentText(message);
		alert.show();
	}
}
