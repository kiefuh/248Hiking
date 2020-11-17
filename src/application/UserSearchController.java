package application;

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
import model.Trail;
import model.TrailType;
import model.UserProfile;
import util.TrailStoreHolder;
import util.UserStoreHolder;

public class UserSearchController {
	@FXML
	private TextField searchField;
	@FXML
	private TableView<UserProfile> userTV;
	@FXML
	private TableColumn<UserProfile,String> searchUsernameColumn;
	@FXML
	private TableColumn<UserProfile,String> searchPasswordColumn;
	@FXML
	private TableColumn<UserProfile,Boolean> searchIsAdminColumn;
	@FXML
	private Button searchButton;
	
	
	public UserSearchController() {
		
	}
	
	@FXML
	private void onSearchClick(ActionEvent event) {
		String searchPhrase=searchField.getText();
		List<UserProfile> userReturn=null;
		searchUsernameColumn.setCellValueFactory(new PropertyValueFactory<UserProfile,String>("userName"));
		searchPasswordColumn.setCellValueFactory(new PropertyValueFactory<UserProfile,String>("password"));
		searchIsAdminColumn.setCellValueFactory(new PropertyValueFactory<UserProfile,Boolean>("isAdmin"));
		userReturn=UserStoreHolder.getUserStore().searchUsersGui(searchPhrase);
		ObservableList<UserProfile> userReturnObservable=FXCollections.observableList(userReturn);
		userTV.setItems(userReturnObservable);
		}
		
	}


