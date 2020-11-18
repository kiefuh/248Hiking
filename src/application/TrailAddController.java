package application;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Difficulty;
import model.Trail;
import model.TrailType;
import util.ProgramAlerts;
import util.TrailStoreHolder;

public class TrailAddController {
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
	
	public TrailAddController() {
		Platform.runLater(()->{
			difficultyCombo.getItems().addAll(Difficulty.EASY,Difficulty.MODERATE,Difficulty.HARD);
			trailTypeCombo.getItems().addAll(TrailType.LOOP,TrailType.OUTANDBACK,TrailType.POINTTOPOINT);
		});
	}
	
	@FXML
	private void onfinishedButtonClick(ActionEvent event) {
		try {
			String trailName=trailNameAdd.getText();
			String address= addressAdd.getText();
			Integer distance= Integer.parseInt(distanceAdd.getText());
			Integer elevationGain= Integer.parseInt(elevationGainAdd.getText());
			Difficulty difficulty= difficultyCombo.getValue();
			TrailType trailType=trailTypeCombo.getValue();
			Trail trail= new Trail(trailName,address,distance,elevationGain,difficulty,trailType);
			TrailStoreHolder.getTrailStore().addTrail(trail);
			ProgramAlerts.newInformation("Trail has been added");
			trailNameAdd.clear();
			addressAdd.clear();
			distanceAdd.clear();
			elevationGainAdd.clear();
		} catch (NumberFormatException e) {
			ProgramAlerts.newException("Please make sure to select a value from combo boxes, and that there are numbers in the distance and elevation gain fields" );
		}
		
	}
}
