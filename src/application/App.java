package application;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Difficulty;
import model.HikingHistory;
import model.Trail;
import model.TrailType;
import model.UserProfile;
import store.TreeMapStore;
import store.UserStore;
import util.Factory;
import util.FileWriterReader;
import util.TrailStoreHolder;
import util.UserStoreHolder;

public class App extends Application {

	public static void main(String[] args) {
		launch(args);

	}
	public static UserStore userStore= new UserStore();
	public TreeMapStore trails= new TreeMapStore();
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		//User Kiefuh as username and 11787 as password for an admin profile
		userStore=FileWriterReader.loadUsers("storeSaves/userStore.dat");
		UserStoreHolder.setUserStore(userStore);
		trails= FileWriterReader.loadTreeMapStore("storeSaves/trailStore.dat");
		TrailStoreHolder.setTrailStore(trails);
		BufferedImage icon= ImageIO.read(new File("images/treeIcon.png"));
		Image iconImage= SwingFXUtils.toFXImage(icon,null);
		primaryStage.getIcons().add(iconImage);
		primaryStage.setOnCloseRequest(e->{
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
		}
			
				);
		Parent root= FXMLLoader.load(getClass().getResource("Login.fxml"));
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}

}
