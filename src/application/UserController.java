package application;

import java.io.IOException;
import java.time.LocalDateTime;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextFlow;
import model.HikingHistory;
import model.UserProfile;
import util.UserHolder;

public class UserController {
	@FXML
	private Pane profilePicture;
	@FXML
	private TextArea userInfo;
	@FXML
	private TableView<HikingHistory> tv;
	@FXML
	private TableColumn<HikingHistory,String> trailNameColumn;
	@FXML
	private TableColumn<HikingHistory,LocalDateTime> dateColumn;
	@FXML
	private TableColumn<HikingHistory,Integer> distanceColumn;
	@FXML
	private TableColumn<HikingHistory,Integer> durationColumn;
	@FXML
	private TableColumn<HikingHistory,Button> picturesColumn;
	@FXML
	private TableColumn<HikingHistory,Double> paceColumn;
	@FXML
	private MenuBar userMenu;
	@FXML
	private MenuItem searchItem;
	@FXML
	private BorderPane userContainer;
	@FXML
	private MenuItem logoutItem;
	@FXML
	private MenuItem homeItem;
	
	public UserController() {
		Platform.runLater(()->{
			UserProfile currentUser=UserHolder.getUser();
			try {
				Image profilePic=SwingFXUtils.toFXImage(currentUser.getProfilePicture(),null);
				ImageView viewProfilePic=new ImageView(profilePic);
				viewProfilePic.setFitHeight(200);
				viewProfilePic.setFitWidth(200);
				profilePicture.getChildren().add(viewProfilePic);
				ObservableList<HikingHistory> hikingHistory=FXCollections.observableList(currentUser.getHikingHistory());
				trailNameColumn.setCellValueFactory(new PropertyValueFactory<HikingHistory,String>("trailName"));
				dateColumn.setCellValueFactory(new PropertyValueFactory<HikingHistory,LocalDateTime>("dateTime"));
				distanceColumn.setCellValueFactory(new PropertyValueFactory<HikingHistory,Integer>("distance"));
				durationColumn.setCellValueFactory(new PropertyValueFactory<HikingHistory,Integer>("duration"));
				picturesColumn.setCellValueFactory(new PropertyValueFactory<HikingHistory,Button>("picturesTaken"));
				paceColumn.setCellValueFactory(new PropertyValueFactory<HikingHistory,Double>("averagePace"));
				tv.setItems(hikingHistory);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				System.out.println("There is a null value");
			}
			searchItem.setOnAction(new EventHandler<ActionEvent>() {
		         public void handle(ActionEvent event) {
		        	 Parent root=null;
		        	try {
						root= FXMLLoader.load(getClass().getResource("TrailSearch.fxml"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        	userContainer.setCenter(root);
		         }
		      });
			logoutItem.setOnAction(new EventHandler<ActionEvent>() {
		         public void handle(ActionEvent event) {
		        	 Parent root=null;
		        	try {
						root= FXMLLoader.load(getClass().getResource("Login.fxml"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        	Scene scene=userContainer.getScene();
		        	scene.setRoot(root);
		         }
		      });
			homeItem.setOnAction(new EventHandler<ActionEvent>() {
		         public void handle(ActionEvent event) {
		        	 Parent root=null;
		        	try {
						root= FXMLLoader.load(getClass().getResource("UserProfile.fxml"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        	Scene scene=userContainer.getScene();
		        	scene.setRoot(root);
		         }
		      });
		});
	}
	
	
	
}
