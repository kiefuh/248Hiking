package application;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Difficulty;
import model.HikingHistory;
import model.Trail;
import model.TrailType;
import util.TrailStoreHolder;

public class TrailSearchController {
	@FXML
	private TextField searchField;
	@FXML
	private ComboBox<String> searchTypeComboBox;
	@FXML
	private TableView<Trail> trailTV;
	@FXML
	private TableColumn<Trail,String> searchNameColumn;
	@FXML
	private TableColumn<Trail,String> searchAddressColumn;
	@FXML
	private TableColumn<Trail,Integer> searchLengthColumn;
	@FXML
	private TableColumn<Trail,Integer> searchElevationColumn;
	@FXML
	private TableColumn<Trail,Difficulty> searchDifficultyColumn;
	@FXML
	private TableColumn<Trail,TrailType> searchTypeColumn;
	@FXML
	private Button searchButton;
	
	
	public TrailSearchController() {
		Platform.runLater(()->{
			searchTypeComboBox.getItems().addAll("Name","Length","Elevation Gain","Difficulty","Type");
		});
		
	}
	
	@FXML
	private void onSearchClick(ActionEvent event) {
		String searchPhrase=searchField.getText();
		List<Trail> searchReturn;
		searchNameColumn.setCellValueFactory(new PropertyValueFactory<Trail,String>("trailName"));
		searchAddressColumn.setCellValueFactory(new PropertyValueFactory<Trail,String>("trailHeadAdresses"));
		searchLengthColumn.setCellValueFactory(new PropertyValueFactory<Trail,Integer>("length"));
		searchElevationColumn.setCellValueFactory(new PropertyValueFactory<Trail,Integer>("elevationGain"));
		searchDifficultyColumn.setCellValueFactory(new PropertyValueFactory<Trail,Difficulty>("difficulty"));
		searchTypeColumn.setCellValueFactory(new PropertyValueFactory<Trail,TrailType>("trailType"));
		if(searchTypeComboBox.getValue().equals("Name")) {
			searchReturn=TrailStoreHolder.getTrailStore().searchByName(searchPhrase);
			ObservableList<Trail> hikingHistory=FXCollections.observableList(searchReturn);
			trailTV.setItems(hikingHistory);
		}
		if(searchTypeComboBox.getValue().equals("Length")) {
			searchReturn=TrailStoreHolder.getTrailStore().searchByElevation(Integer.parseInt(searchPhrase));
			ObservableList<Trail> hikingHistory=FXCollections.observableList(searchReturn);
			trailTV.setItems(hikingHistory);
		}
		if(searchTypeComboBox.getValue().equals("Elevation Gain")) {
			searchReturn=TrailStoreHolder.getTrailStore().searchByElevation(Integer.parseInt(searchPhrase));
			ObservableList<Trail> hikingHistory=FXCollections.observableList(searchReturn);
			trailTV.setItems(hikingHistory);
		}
		if(searchTypeComboBox.getValue().equals("Difficulty")) {
			searchReturn=TrailStoreHolder.getTrailStore().searchByDifficulty(Difficulty.EASY);
			ObservableList<Trail> hikingHistory=FXCollections.observableList(searchReturn);
			trailTV.setItems(hikingHistory);
		}
		if(searchTypeComboBox.getValue().equals("Type")) {
			searchReturn=TrailStoreHolder.getTrailStore().searchByTrailType(TrailType.LOOP);
			ObservableList<Trail> hikingHistory=FXCollections.observableList(searchReturn);
			trailTV.setItems(hikingHistory);
		}
		
	}
	
	
}