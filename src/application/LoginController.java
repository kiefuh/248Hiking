package application;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.UserProfile;
public class LoginController {
	@FXML
	private Button loginButton;
	@FXML
	private Button registerButton;
	@FXML
	private TextField usernameField;
	@FXML
	private TextField passwordField;
	public LoginController() {
		
	}
	
	@FXML
	private void loginButtonClick(ActionEvent event) {
		Parent root=null;
		try {
			root= FXMLLoader.load(getClass().getResource("UserProfile.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String username= usernameField.getText();
		String password= passwordField.getText();
		UserProfile check=App.userStore.searchBag(username);
		if(check!=null&&check.getPassword().equals(password)) {
			Scene scene=loginButton.getScene();
			scene.setRoot(root);	
		}
		else {
			usernameField.setText("Unsuccesful login" );
		}
		
	}
	
	@FXML
	private void registerButtonClick(ActionEvent event) {
		Parent root=null;
		try {
			root=FXMLLoader.load(getClass().getResource("Registration.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scene scene= registerButton.getScene();
		scene.setRoot(root);
	}
}
