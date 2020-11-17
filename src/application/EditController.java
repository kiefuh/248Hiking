package application;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import model.HikingHistory;
import model.UserProfile;
import util.ProgramAlerts;
import util.SelectionHolder;
import util.UserHolder;

public class EditController {
	@FXML
	private TextField trailNameEdit;
	@FXML
	private TextField distanceEdit;
	@FXML
	private TextField durationEdit;
	@FXML
	private Button editPhotosButton;
	@FXML
	private Button finishedEditButton;
	
	private LinkedList<BufferedImage> picturesList;
	
	public EditController() {
		Platform.runLater(()->{
			HikingHistory selected=SelectionHolder.getSelected();
			trailNameEdit.setText(selected.getTrailName());
			distanceEdit.setText(String.valueOf(selected.getDistance()));
			durationEdit.setText(String.valueOf(selected.getDuration()));
		});
		
	}
	
	@FXML
	private void onEditPhotosButtonClick(ActionEvent event) {
		BufferedImage hikePhoto = null;
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choose Profile Picture");
		fileChooser.getExtensionFilters().addAll(
		         new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
		);
		Stage mainStage=(Stage)editPhotosButton.getScene().getWindow();         
		File file= fileChooser.showOpenDialog(mainStage);
		try {
			hikePhoto=ImageIO.read(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SelectionHolder.getSelected().addPicture(hikePhoto);
	}
	
	@FXML
	private void onFinishedEditButtonClick(ActionEvent event) {
		SelectionHolder.getSelected().setTrailName(trailNameEdit.getText());
		SelectionHolder.getSelected().setDistance(Integer.parseInt(distanceEdit.getText()));
		SelectionHolder.getSelected().setDuration(Integer.parseInt(durationEdit.getText()));
		SelectionHolder.getSelected().setAveragePace();
		ProgramAlerts.newInformation("Your Edit Has Been Saved");
	}
	
	
}
