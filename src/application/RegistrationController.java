package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.UserProfile;

public class RegistrationController {

	@FXML
	private TextField userRegistrationField;
	@FXML
	private TextField passwordRegistrationField;
	@FXML
	private Button userCreateButton;
	
	public RegistrationController() {
		
	}
	
	@FXML
	private void createUserButtonClick(ActionEvent event) {
		String userName=userRegistrationField.getText();
		String password=passwordRegistrationField.getText();
		UserProfile up= new UserProfile(userName,password,null,null);
		App.userStore.addUser(up);
		Parent root=null;
		try {
			root=FXMLLoader.load(getClass().getResource("Login.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scene scene= userCreateButton.getScene();
		scene.setRoot(root);
	}
}
