package application;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.UserProfile;
import util.ProgramAlerts;
import util.SelectionHolder;

public class EditUserController {
	@FXML
	private TextField editUserName;
	@FXML
	private TextField editUserPassword;
	@FXML
	private ComboBox<Boolean> isAdminBox;
	@FXML
	private Button finishedUserEditButton;
	private UserProfile selectedUser=SelectionHolder.getSelectedUserProfile();
	
	public EditUserController() {
		Platform.runLater(()->{
			isAdminBox.getItems().addAll(true,false);
			editUserName.setText(selectedUser.getUserName());
			editUserPassword.setText(selectedUser.getPassword());
			isAdminBox.setPromptText("Select admin status");
		});
		
	}
	
	@FXML
	private void onFinishedUserEditButtonClick(ActionEvent event) {
		selectedUser.setUserName(editUserName.getText());
		selectedUser.setPassword(editUserPassword.getText());
		try {
			selectedUser.setIsAdmin(isAdminBox.getValue());
			ProgramAlerts.newInformation("Changes to user saved");
			 Parent root=null;
	     	try {
					root= FXMLLoader.load(getClass().getResource("AdminProfile.fxml"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	     	Scene scene=finishedUserEditButton.getScene();
	     	scene.setRoot(root);
		} catch (Exception e1) {
			ProgramAlerts.newException("Please select whether the user is an admin or not");
		}
		
		
	}
	
	
}
