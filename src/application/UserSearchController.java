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
import model.Difficulty;
import model.Trail;
import model.TrailType;
import model.UserProfile;
import util.ProgramAlerts;
import util.SelectionHolder;
import util.TrailStoreHolder;
import util.UserStoreHolder;

public class UserSearchController {
	@FXML
	private TextField searchUserField;
	@FXML
	public TableView<UserProfile> userTV;
	@FXML
	private TableColumn<UserProfile,String> searchUsernameColumn;
	@FXML
	private TableColumn<UserProfile,String> searchPasswordColumn;
	@FXML
	private TableColumn<UserProfile,Boolean> searchIsAdminColumn;
	@FXML
	private Button searchUserButton;
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
	private MenuItem adminUserEditItem;
	@FXML
	private MenuItem adminUserAddItem;
	@FXML
	private MenuItem adminUserRemoveItem;
	
	
	public UserSearchController() {
		Platform.runLater(()->{
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
			adminUserEditItem.setOnAction(new EventHandler<ActionEvent>() {
		         public void handle(ActionEvent event) {
		        	SelectionHolder.setSelectedUserProfile(userTV.getSelectionModel().getSelectedItem());
		        	if(SelectionHolder.getSelectedUserProfile()==null) {
		        		ProgramAlerts.newException("Please select a user from the table below, or return to user search.");
		        	}else {
		        		
		        	
		        	 Parent root=null;
			        	try {
							root= FXMLLoader.load(getClass().getResource("editUser.fxml"));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			        	adminContainer.setCenter(root);
		         }
		         }
		      });
			adminUserRemoveItem.setOnAction(new EventHandler<ActionEvent>() {
		         public void handle(ActionEvent event) {
		        	 try {
						UserProfile deletion=userTV.getSelectionModel().getSelectedItem();
						 userTV.getItems().remove(userTV.getSelectionModel().getSelectedItem());
						 UserStoreHolder.getUserStore().removeUser(deletion.getUserName());
					} catch (Exception e) {
						ProgramAlerts.newException("Please select from a user below, or return to user search.");
					}
		        	 
		         }
		      });
			adminUserAddItem.setOnAction(new EventHandler<ActionEvent>() {
		         public void handle(ActionEvent event) {
		        	 Parent root=null;
			        	try {
							root= FXMLLoader.load(getClass().getResource("AddUser.fxml"));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			        	adminContainer.setCenter(root);
		        	 
		         }
		      });
		});
	}
	
	@FXML
	private void onSearchUserClick(ActionEvent event) {
		String searchPhrase=searchUserField.getText();
		List<UserProfile> userReturn=null;
		searchUsernameColumn.setCellValueFactory(new PropertyValueFactory<UserProfile,String>("userName"));
		searchPasswordColumn.setCellValueFactory(new PropertyValueFactory<UserProfile,String>("password"));
		searchIsAdminColumn.setCellValueFactory(new PropertyValueFactory<UserProfile,Boolean>("isAdmin"));
		userReturn=UserStoreHolder.getUserStore().searchUsersGui(searchPhrase);
		ObservableList<UserProfile> userReturnObservable=FXCollections.observableList(userReturn);
		userTV.setItems(userReturnObservable);
		}
		
	}


