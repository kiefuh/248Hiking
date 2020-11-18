package application;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import javax.imageio.ImageIO;

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
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.HikingHistory;
import model.UserProfile;
import util.FileWriterReader;
import util.ProgramAlerts;
import util.SelectionHolder;
import util.UserHolder;
import util.UserStoreHolder;

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
	private TableColumn<HikingHistory,Integer> picturesColumn;
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
	@FXML
	private MenuItem addHikeItem;
	@FXML
	private MenuItem editHikeItem;
	@FXML
	private MenuItem removeHikeItem;
	@FXML
	private MenuItem changePicItem;
	@FXML
	private MenuItem editBioItem;
	@FXML
	private MenuItem viewPhotosItem;
	
	
	public UserController() {
		Platform.runLater(()->{
			UserProfile currentUser=UserHolder.getUser();
			userInfo.setText(currentUser.getUserBio());
			userInfo.setEditable(false);
			try {
				BufferedImage pic = ImageIO.read(new File(currentUser.getProfilePicture()));
				Image profilePic=SwingFXUtils.toFXImage(pic,null);
				ImageView viewProfilePic=new ImageView(profilePic);
				viewProfilePic.setFitHeight(200);
				viewProfilePic.setFitWidth(200);
				profilePicture.getChildren().add(viewProfilePic);
				ObservableList<HikingHistory> hikingHistory=FXCollections.observableList(currentUser.getHikingHistory());
				trailNameColumn.setCellValueFactory(new PropertyValueFactory<HikingHistory,String>("trailName"));
				dateColumn.setCellValueFactory(new PropertyValueFactory<HikingHistory,LocalDateTime>("dateTime"));
				distanceColumn.setCellValueFactory(new PropertyValueFactory<HikingHistory,Integer>("distance"));
				durationColumn.setCellValueFactory(new PropertyValueFactory<HikingHistory,Integer>("duration"));
				picturesColumn.setCellValueFactory(new PropertyValueFactory<HikingHistory,Integer>("numberOfPictures"));
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
		        	 try {
						FileWriterReader.saveUsers(UserStoreHolder.getUserStore(), "storeSaves/userStore.dat");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
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
			addHikeItem.setOnAction(new EventHandler<ActionEvent>() {
		         public void handle(ActionEvent event) {
		        	 Parent root=null;
		        	try {
						root= FXMLLoader.load(getClass().getResource("HikeAdd.fxml"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        	userContainer.setCenter(root);
		        	
		         }
		      });
			removeHikeItem.setOnAction(new EventHandler<ActionEvent>() {
		         public void handle(ActionEvent event) {
		        	tv.getItems().remove(tv.getSelectionModel().getSelectedItem());
		         }
		      });
			editHikeItem.setOnAction(new EventHandler<ActionEvent>() {
		         public void handle(ActionEvent event) {
		        	SelectionHolder.setSelected(tv.getSelectionModel().getSelectedItem());
		        	SelectionHolder.setSelected(tv.getSelectionModel().getSelectedItem());
		        	if(SelectionHolder.getSelected()==null) {
		        		ProgramAlerts.newException("Please select a hike from the table on your homepage, or create one");
		        	}else {
		        		
		        	
		        	 Parent root=null;
			        	try {
							root= FXMLLoader.load(getClass().getResource("editHike.fxml"));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			        	userContainer.setCenter(root);
		         }
		         }
		      });
			viewPhotosItem.setOnAction(new EventHandler<ActionEvent>() {
		         public void handle(ActionEvent event) {
		        	SelectionHolder.setSelected(tv.getSelectionModel().getSelectedItem());
		        	if(SelectionHolder.getSelected()==null) {
		        		ProgramAlerts.newException("Please select a hike from the table on your homepage, or create one");
		        		
		        	}else {
		        		
		        	
		        	final Stage dialog = new Stage();
		        	Parent root=null;
			        	try {
							root= FXMLLoader.load(getClass().getResource("PhotoView.fxml"));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	                dialog.initModality(Modality.WINDOW_MODAL);
	                dialog.setTitle(SelectionHolder.getSelected().getTrailName()+" "+SelectionHolder.getSelected().getDateTime()+" pictures");
	                dialog.initOwner((Stage)userContainer.getScene().getWindow());
	                Scene dialogScene = new Scene(root, 800, 470);
	                dialog.setScene(dialogScene);
	                dialog.show();
		         }
		         }
		      });
			editBioItem.setOnAction(new EventHandler<ActionEvent>() {
		         public void handle(ActionEvent event) {
		        	 Parent root=null;
		        	try {
						root= FXMLLoader.load(getClass().getResource("EditBio.fxml"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        	userContainer.setCenter(root);
		         }
		      });
		});
	}
	
	
	
}
