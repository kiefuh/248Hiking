package application;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.UserProfile;
import util.UserHolder;
public class LoginController {
	@FXML
	private Button loginButton;
	@FXML
	private Button registerButton;
	@FXML
	private TextField usernameField;
	@FXML
	private PasswordField passwordField;
	public LoginController() {
		
	}
	
	@FXML
	private void loginButtonClick(ActionEvent event) {
		
		String username= usernameField.getText();
		String password= passwordField.getText();
		UserProfile check=App.userStore.searchBag(username);
		if(check!=null&&check.getPassword().equals(password)) {
			if(check.getIsAdmin()!=true) {
				UserHolder.setUser(check);
				Parent root=null;
				try {
					root= FXMLLoader.load(getClass().getResource("UserProfile.fxml"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Scene scene=usernameField.getScene();
				scene.setRoot(root);
				
			}else {
				Parent root=null;
				try {
					root= FXMLLoader.load(getClass().getResource("AdminProfile.fxml"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Scene scene=usernameField.getScene();
				scene.setRoot(root);
				UserHolder.setUser(check);
			}
		}
		else {
			usernameField.setText("Unsuccesful login" );
			passwordField.clear();
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
