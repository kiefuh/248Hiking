package application;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextFlow;
import model.UserProfile;
import util.UserHolder;

public class UserController {
	@FXML
	private Pane profilePicture;
	@FXML
	private TextFlow userInfo;
	@FXML
	private Button checkButton;
	@FXML
	private ListView hikingHistoryList;
	
	private UserController() {
		
	}
	
	@FXML
	private void onCheckButtonClick(ActionEvent event) {
		UserProfile currentUser=UserHolder.getUser();
		Image profilePic=SwingFXUtils.toFXImage(currentUser.getProfilePicture(),null);
		profilePicture.getChildren().add(new ImageView(profilePic));
	}
	
}
