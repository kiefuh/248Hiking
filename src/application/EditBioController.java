package application;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import util.ProgramAlerts;
import util.UserHolder;

public class EditBioController {
	@FXML
	private TextArea bio;
	@FXML
	private Button save;
	
	public EditBioController() {
		Platform.runLater(()->{
			bio.setText(UserHolder.getUser().getUserBio());
		});
	}
	
	@FXML
	private void saveButtonClick(ActionEvent event) {
		UserHolder.getUser().setUserBio(bio.getText());
		ProgramAlerts.newInformation("Bio has been saved");
	}
}
