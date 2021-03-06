package application;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.HikingHistory;
import util.ProgramAlerts;
import util.UserHolder;
import javafx.stage.FileChooser.ExtensionFilter;

public class HikeAddController {
	@FXML
	private TextField trailNameAdd;
	@FXML
	private TextField distanceAdd;
	@FXML
	private TextField durationAdd;
	@FXML
	private Button addPhotosButton;
	@FXML
	private Button finishedButton;
	
	private LinkedList<String> picturesList= new LinkedList<>();
	
	public HikeAddController() {
		
	}
	@FXML
	private void onAddPhotosButtonClick(ActionEvent event) {
		try {
			BufferedImage hikePhoto = null;
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Choose Profile Picture");
			fileChooser.getExtensionFilters().addAll(
			         new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
			);
			Stage mainStage=(Stage)addPhotosButton.getScene().getWindow();         
			File file= fileChooser.showOpenDialog(mainStage);
			try {
				hikePhoto=ImageIO.read(file);
				ImageIO.write(hikePhoto, "png", new File("images/"+file.getName()+".png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			picturesList.add("images/"+file.getName()+".png");
		} catch (Exception e) {
			System.out.println("User did not add any photos");
		}
	}
	
	@FXML
	private void onFinishedButtonClick(ActionEvent event) {
		try {
			HikingHistory hikingHistory= new HikingHistory(trailNameAdd.getText(),Integer.parseInt(distanceAdd.getText()),Integer.parseInt(durationAdd.getText()),picturesList);
			UserHolder.getUser().addTrailHistory(hikingHistory);
			trailNameAdd.clear();
			distanceAdd.clear();
			durationAdd.clear();
			ProgramAlerts.newInformation("Hike Successfully Added!");
		} catch (NumberFormatException e) {
			ProgramAlerts.newException("Please only type numbers into duration and distance fields");
		}
	}
	
	
}
