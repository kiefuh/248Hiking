package application;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

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
import util.TrailStoreHolder;
import util.UserHolder;
import util.UserStoreHolder;

public class AdminController {
	@FXML
	private Pane adminProfilePicture;
	@FXML
	private TextArea adminInfo;
	@FXML
	private TableView<HikingHistory> aTV;
	@FXML
	private TableColumn<HikingHistory,String> adminTrailNameColumn;
	@FXML
	private TableColumn<HikingHistory,LocalDateTime> adminDateColumn;
	@FXML
	private TableColumn<HikingHistory,Integer> adminDistanceColumn;
	@FXML
	private TableColumn<HikingHistory,Integer> adminDurationColumn;
	@FXML
	private TableColumn<HikingHistory,Integer> adminPicturesColumn;
	@FXML
	private TableColumn<HikingHistory,Double> adminPaceColumn;
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
	private MenuItem adminAddHikeItem;
	@FXML
	private MenuItem adminEditHikeItem;
	@FXML
	private MenuItem adminRemoveHikeItem;
	@FXML
	private MenuItem adminChangePicItem;
	@FXML
	private MenuItem adminEditBioItem;
	@FXML
	private MenuItem adminViewPhotosItem;

	
	
	public AdminController() {
		Platform.runLater(()->{
			UserProfile currentUser=UserHolder.getUser();
			adminInfo.setText(currentUser.getUserBio());
			adminInfo.setEditable(false);
			try {
				BufferedImage pic= ImageIO.read(new File(currentUser.getProfilePicture()));
				Image profilePic=SwingFXUtils.toFXImage(pic,null);
				ImageView viewProfilePic=new ImageView(profilePic);
				viewProfilePic.setFitHeight(200);
				viewProfilePic.setFitWidth(200);
				adminProfilePicture.getChildren().add(viewProfilePic);
				ObservableList<HikingHistory> hikingHistory=FXCollections.observableList(currentUser.getHikingHistory());
				adminTrailNameColumn.setCellValueFactory(new PropertyValueFactory<HikingHistory,String>("trailName"));
				adminDateColumn.setCellValueFactory(new PropertyValueFactory<HikingHistory,LocalDateTime>("dateTime"));
				adminDistanceColumn.setCellValueFactory(new PropertyValueFactory<HikingHistory,Integer>("distance"));
				adminDurationColumn.setCellValueFactory(new PropertyValueFactory<HikingHistory,Integer>("duration"));
				adminPicturesColumn.setCellValueFactory(new PropertyValueFactory<HikingHistory,Integer>("numberOfPictures"));
				adminPaceColumn.setCellValueFactory(new PropertyValueFactory<HikingHistory,Double>("averagePace"));
				aTV.setItems(hikingHistory);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				System.out.println("There is a null value");
			}
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
		        	 try {
							FileWriterReader.saveUsers(UserStoreHolder.getUserStore(), "storeSaves/userStore.dat");
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
		        	 try {
						FileWriterReader.saveTrails(TrailStoreHolder.getTrailStore(), "storeSaves/trailStore.dat");
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
			adminAddHikeItem.setOnAction(new EventHandler<ActionEvent>() {
		         public void handle(ActionEvent event) {
		        	 Parent root=null;
		        	try {
						root= FXMLLoader.load(getClass().getResource("HikeAdd.fxml"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        	adminContainer.setCenter(root);
		        	
		         }
		      });
			adminRemoveHikeItem.setOnAction(new EventHandler<ActionEvent>() {
		         public void handle(ActionEvent event) {
		        	aTV.getItems().remove(aTV.getSelectionModel().getSelectedItem());
		         }
		      });
			adminEditHikeItem.setOnAction(new EventHandler<ActionEvent>() {
		         public void handle(ActionEvent event) {
		        	SelectionHolder.setSelected(aTV.getSelectionModel().getSelectedItem());
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
			        	adminContainer.setCenter(root);
		         }
		         }
		      });
			adminViewPhotosItem.setOnAction(new EventHandler<ActionEvent>() {
		         public void handle(ActionEvent event) {
		        	SelectionHolder.setSelected(aTV.getSelectionModel().getSelectedItem());
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
	                dialog.initOwner((Stage)adminContainer.getScene().getWindow());
	                Scene dialogScene = new Scene(root, 800, 470);
	                dialog.setScene(dialogScene);
	                dialog.show();
		         }
		         }
		      });
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
			adminEditBioItem.setOnAction(new EventHandler<ActionEvent>() {
		         public void handle(ActionEvent event) {
		        	 Parent root=null;
		        	try {
						root= FXMLLoader.load(getClass().getResource("EditBio.fxml"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        	adminContainer.setCenter(root);
		        	
		         }
		      });
		});
	}
}
