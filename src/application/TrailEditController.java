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
import model.Difficulty;
import model.Trail;
import model.TrailType;
import util.ProgramAlerts;
import util.SelectionHolder;

public class TrailEditController {
	@FXML
	private TextField trailNameAdd;
	@FXML
	private TextField distanceAdd;
	@FXML
	private TextField addressAdd;
	@FXML
	private Button finishedButton;
	@FXML
	private TextField elevationGainAdd;
	@FXML
	private ComboBox<Difficulty> difficultyCombo;
	@FXML
	private ComboBox<TrailType> trailTypeCombo;
	private Trail selectedTrail=SelectionHolder.getSelectedTrail();
	
	public TrailEditController() {
		Platform.runLater(()->{
			difficultyCombo.getItems().addAll(Difficulty.EASY,Difficulty.MODERATE,Difficulty.HARD);
			trailTypeCombo.getItems().addAll(TrailType.LOOP,TrailType.OUTANDBACK,TrailType.POINTTOPOINT);
			difficultyCombo.setValue(selectedTrail.getDifficulty());
			trailTypeCombo.setValue(selectedTrail.getTrailType());
			trailNameAdd.setText(selectedTrail.getTrailName());
			distanceAdd.setText(String.valueOf(selectedTrail.getLength()));
			addressAdd.setText(selectedTrail.getTrailHeadAdresses());
			elevationGainAdd.setText(String.valueOf(selectedTrail.getElevationGain()));
		});
	}
	
	@FXML
	private void onFinishedButtonClick(ActionEvent event) {
		selectedTrail.setElevationGain(Integer.parseInt(elevationGainAdd.getText()));
		selectedTrail.setLength(Integer.parseInt(distanceAdd.getText()));
		selectedTrail.setTrailHeadAdresses(addressAdd.getText());
		selectedTrail.setTrailName(trailNameAdd.getText());
		try {
			selectedTrail.setDifficulty(difficultyCombo.getValue());
			selectedTrail.setTrailType(trailTypeCombo.getValue());
			ProgramAlerts.newInformation("Trail successfully edited");
			 Parent root=null;
		     	try {
						root= FXMLLoader.load(getClass().getResource("AdminProfile.fxml"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		     	Scene scene=finishedButton.getScene();
		     	scene.setRoot(root);
		} catch (Exception e) {
			ProgramAlerts.newException("Please select both difficulty and trail type");
		}
	}
}
