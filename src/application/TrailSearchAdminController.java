package application;

import java.io.IOException;
import java.util.List;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Difficulty;
import model.Trail;
import model.TrailType;
import model.UserProfile;
import util.ProgramAlerts;
import util.SelectionHolder;
import util.TrailStoreHolder;
import util.UserStoreHolder;

public class TrailSearchAdminController {
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
	@FXML
	private ComboBox<String> typeAndDifficulty;
	@FXML
	private MenuBar adminMenu;
	@FXML
	private MenuItem adminSearchUsersItem;
	@FXML
	private MenuItem adminSearchTrailItem;
	@FXML
	private BorderPane adminContainer;
	@FXML
	private MenuItem adminLogoutItem;
	@FXML
	private MenuItem adminHomeItem;
	@FXML
	private MenuItem adminTrailEditItem;
	@FXML
	private MenuItem adminTrailRemoveItem;
	@FXML
	private MenuItem adminTrailAddItem;
	
	
	
	public TrailSearchAdminController() {
		Platform.runLater(()->{
			searchTypeComboBox.getItems().addAll("Name","Length","Elevation Gain","Difficulty","Type");
			searchField.setVisible(false);
			typeAndDifficulty.setVisible(false);
			adminSearchUsersItem.setOnAction(new EventHandler<ActionEvent>() {
		         public void handle(ActionEvent event) {
		        	 Parent root=null;
		        	try {
						root= FXMLLoader.load(getClass().getResource("UserSearch.fxml"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        	Scene scene=adminContainer.getScene();
		        	scene.setRoot(root);
		         }
		      });
			adminSearchTrailItem.setOnAction(new EventHandler<ActionEvent>() {
		         public void handle(ActionEvent event) {
		        	 Parent root=null;
			        	try {
							root= FXMLLoader.load(getClass().getResource("TrailSearchAdmin.fxml"));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			        	Scene scene=adminContainer.getScene();
			        	scene.setRoot(root);
			         }
		      });
			adminLogoutItem.setOnAction(new EventHandler<ActionEvent>() {
		         public void handle(ActionEvent event) {
		        	 Parent root=null;
		        	try {
						root= FXMLLoader.load(getClass().getResource("Login.fxml"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        	Scene scene=adminContainer.getScene();
		        	scene.setRoot(root);
		         }
		      });
			adminHomeItem.setOnAction(new EventHandler<ActionEvent>() {
		         public void handle(ActionEvent event) {
		        	 Parent root=null;
		        	try {
						root= FXMLLoader.load(getClass().getResource("AdminProfile.fxml"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        	Scene scene=adminContainer.getScene();
		        	scene.setRoot(root);
		         }
		      });
			adminTrailRemoveItem.setOnAction(new EventHandler<ActionEvent>() {
		         public void handle(ActionEvent event) {
						Trail deletion=trailTV.getSelectionModel().getSelectedItem();
						trailTV.getItems().remove(trailTV.getSelectionModel().getSelectedItem());
						TrailStoreHolder.getTrailStore().deleteByID(deletion);
		         }
		      });
			adminTrailAddItem.setOnAction(new EventHandler<ActionEvent>() {
		         public void handle(ActionEvent event) {
		        	 final Stage dialog = new Stage();
			        	Parent root=null;
				        	try {
								root= FXMLLoader.load(getClass().getResource("TrailAdd.fxml"));
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
		                dialog.initModality(Modality.WINDOW_MODAL);
		                dialog.setTitle("Add Trail");
		                dialog.initOwner((Stage)adminContainer.getScene().getWindow());
		                Scene dialogScene = new Scene(root, 800, 470);
		                dialog.setScene(dialogScene);
		                dialog.show();
		         }
		      });
		});
		
	}
	
	@FXML
	private void onSearchClick(ActionEvent event) {
		String searchPhrase=searchField.getText();
		List<Trail> searchReturn=null;
		searchNameColumn.setCellValueFactory(new PropertyValueFactory<Trail,String>("trailName"));
		searchAddressColumn.setCellValueFactory(new PropertyValueFactory<Trail,String>("trailHeadAdresses"));
		searchLengthColumn.setCellValueFactory(new PropertyValueFactory<Trail,Integer>("length"));
		searchElevationColumn.setCellValueFactory(new PropertyValueFactory<Trail,Integer>("elevationGain"));
		searchDifficultyColumn.setCellValueFactory(new PropertyValueFactory<Trail,Difficulty>("difficulty"));
		searchTypeColumn.setCellValueFactory(new PropertyValueFactory<Trail,TrailType>("trailType"));
		if(searchTypeComboBox.getValue().equals("Name")) {
			searchField.setVisible(true);
			searchReturn=TrailStoreHolder.getTrailStore().searchByName(searchPhrase);
			ObservableList<Trail> hikingHistory=FXCollections.observableList(searchReturn);
			trailTV.setItems(hikingHistory);
		}
		if(searchTypeComboBox.getValue().equals("Length")) {
			searchField.setVisible(true);
			searchReturn=TrailStoreHolder.getTrailStore().searchByLength(Integer.parseInt(searchPhrase));
			ObservableList<Trail> hikingHistory=FXCollections.observableList(searchReturn);
			trailTV.setItems(hikingHistory);
		}
		if(searchTypeComboBox.getValue().equals("Elevation Gain")) {
			searchField.setVisible(true);
			searchReturn=TrailStoreHolder.getTrailStore().searchByElevation(Integer.parseInt(searchPhrase));
			ObservableList<Trail> hikingHistory=FXCollections.observableList(searchReturn);
			trailTV.setItems(hikingHistory);
		}
		if(searchTypeComboBox.getValue().equals("Difficulty")) {
			if(typeAndDifficulty.getValue().equals("Easy")) {
				searchReturn=TrailStoreHolder.getTrailStore().searchByDifficulty(Difficulty.EASY);
			}
			if(typeAndDifficulty.getValue().equals("Moderate")) {
				searchReturn=TrailStoreHolder.getTrailStore().searchByDifficulty(Difficulty.MODERATE);
			}
			if(typeAndDifficulty.getValue().equals("Hard")) {
				searchReturn=TrailStoreHolder.getTrailStore().searchByDifficulty(Difficulty.HARD);
			}
			ObservableList<Trail> hikingHistory=FXCollections.observableList(searchReturn);
			trailTV.setItems(hikingHistory);
		}
		if(searchTypeComboBox.getValue().equals("Type")) {
			if(typeAndDifficulty.getValue().equals("Loop")) {
				searchReturn=TrailStoreHolder.getTrailStore().searchByTrailType(TrailType.LOOP);
			}
			if(typeAndDifficulty.getValue().equals("Out and back")) {
				searchReturn=TrailStoreHolder.getTrailStore().searchByTrailType(TrailType.OUTANDBACK);
			}
			if(typeAndDifficulty.getValue().equals("Point to point")) {
				searchReturn=TrailStoreHolder.getTrailStore().searchByTrailType(TrailType.POINTTOPOINT);
			}
			ObservableList<Trail> hikingHistory=FXCollections.observableList(searchReturn);
			trailTV.setItems(hikingHistory);
		}
		
	}
	
	@FXML
	private void selectingTypeOrDifficulty(ActionEvent event) {
		if(searchTypeComboBox.getValue().equals("Name")) {
			searchField.setVisible(true);
			typeAndDifficulty.setVisible(false);
		}
		if(searchTypeComboBox.getValue().equals("Length")) {
			searchField.setVisible(true);
			typeAndDifficulty.setVisible(false);
		}
		if(searchTypeComboBox.getValue().equals("Elevation Gain")) {
			searchField.setVisible(true);
			typeAndDifficulty.setVisible(false);
		}
		if(searchTypeComboBox.getValue().equals("Difficulty")) {
			typeAndDifficulty.getItems().removeAll(typeAndDifficulty.getItems());
			searchField.setVisible(false);
			typeAndDifficulty.setVisible(true);
			typeAndDifficulty.getItems().addAll("Easy","Moderate","Hard");
		}
		if(searchTypeComboBox.getValue().equals("Type")) {
			typeAndDifficulty.getItems().removeAll(typeAndDifficulty.getItems());
			searchField.setVisible(false);
			typeAndDifficulty.setVisible(true);
			typeAndDifficulty.getItems().addAll("Loop","Out and back","Point to point");
		}
	}
}
