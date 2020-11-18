package application;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
		userStore=FileWriterReader.loadUsers("storeSaves/userStore.dat");
		UserStoreHolder.setUserStore(userStore);
		Trail trail= new Trail("trailName", "trailHeadAdresses", 20, 20, Difficulty.EASY,TrailType.LOOP);
		trails= FileWriterReader.loadTreeMapStore("storeSaves/trailStore.dat");
		TrailStoreHolder.setTrailStore(trails);
		Parent root= FXMLLoader.load(getClass().getResource("Login.fxml"));
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}

}
