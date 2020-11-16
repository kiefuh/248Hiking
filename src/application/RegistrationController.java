package application;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import model.UserProfile;
import javafx.stage.Stage;

public class RegistrationController {

	@FXML
	private TextField userRegistrationField;
	@FXML
	private TextField passwordRegistrationField;
	@FXML
	private Button userCreateButton;
	@FXML
	private Button profilePicButton;
	@FXML
	private Label profilePicNameLabel;
	private BufferedImage profilePic;
	
	public RegistrationController() {
		
	}
	
	@FXML
	private void createUserButtonClick(ActionEvent event) {
		String userName=userRegistrationField.getText();
		String password=passwordRegistrationField.getText();
		UserProfile check=App.userStore.searchBag(userName);
		if(check!=null) {
			userRegistrationField.setText("This is already a user");
		}
		else {
		UserProfile up= new UserProfile(userName,password,profilePic,null);
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
	
	@FXML
	private void onProfilePicButtonClick(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choose Profile Picture");
		fileChooser.getExtensionFilters().addAll(
		         new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
		);
		Stage mainStage=(Stage)userCreateButton.getScene().getWindow();         
		File file= fileChooser.showOpenDialog(mainStage);
		try {
			profilePic=ImageIO.read(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		profilePicNameLabel.setText(file.getName());
		
	}
}
