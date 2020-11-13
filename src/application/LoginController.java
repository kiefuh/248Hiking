package application;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextFlow;
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
	@FXML
	private Pane profilePicture;
	@FXML
	private TextFlow userInfo;
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
			Image image=SwingFXUtils.toFXImage(check.getProfilePicture(),null);
			profilePicture.getChildren().add(new ImageView(image));
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
