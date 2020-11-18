package application;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

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
import model.HikingHistory;
import model.UserProfile;
import util.ProgramAlerts;
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
	private String profilePicLocation;
	
	public RegistrationController() {
		
	}
	
	@FXML
	private void createUserButtonClick(ActionEvent event) {
		String userName=userRegistrationField.getText();
		String password=passwordRegistrationField.getText();
		UserProfile check=App.userStore.searchBag(userName);
		if(check!=null) {
			ProgramAlerts.newException("This is already a user");
		}
		else {
		if(profilePicLocation==null) {
			
			profilePicLocation="images/defaultUserIcon.jpg";
			
		}
		UserProfile up= new UserProfile(userName,password,profilePicLocation,new LinkedList<HikingHistory>(),false);
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
	private void createUserButtonClickAdmin(ActionEvent event) {
		String userName=userRegistrationField.getText();
		String password=passwordRegistrationField.getText();
		UserProfile check=App.userStore.searchBag(userName);
		profilePicLocation="images/defaultUserIcon.jpg";
		if(check!=null) {
			ProgramAlerts.newException("This is already a user");
		}
		else {
		UserProfile up= new UserProfile(userName,password,profilePicLocation,new LinkedList<HikingHistory>(),false);
		App.userStore.addUser(up);
		ProgramAlerts.newInformation("User successfully created");
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
			profilePicLocation="images/"+userRegistrationField.getText()+".png";
			ImageIO.write(profilePic, "png", new File("images/"+userRegistrationField.getText()+".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		profilePicNameLabel.setText(file.getName());
		
	}
	
	
}
