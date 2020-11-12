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
public class LoginController {
	@FXML
	private Button loginButton;
	@FXML
	private Button registerButton;
	
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
		Scene scene=loginButton.getScene();
		scene.setRoot(root);
	}
}
