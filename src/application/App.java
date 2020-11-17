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
import util.TrailStoreHolder;

public class App extends Application {

	public static void main(String[] args) {
		launch(args);

	}
	public static UserStore userStore= new UserStore();
	public TreeMapStore trails= new TreeMapStore();
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Trail trail= new Trail("trailName", "trailHeadAdresses", 20, 20, Difficulty.EASY,TrailType.LOOP);
		trails.addTrail(trail);
		Factory.trailFactory(trails, 1000000);
		TrailStoreHolder.setTrailStore(trails);
		HikingHistory history= new HikingHistory("Test",20,20,null);
		File proPicFile= new File("images/catProfile.png");
		BufferedImage proPicImage=ImageIO.read(proPicFile);
		File proPicFileQuinn= new File("images/quinnPic.jfif");
		BufferedImage proPicImageQuinn=ImageIO.read(proPicFileQuinn);
		LinkedList<HikingHistory> listHist= new LinkedList<>();
		listHist.add(history);
		UserProfile test = new UserProfile("Kiefuh","11787",proPicImage,listHist);
		UserProfile test2 = new UserProfile("Quinn","123456",proPicImageQuinn,listHist);
		userStore.addUser(test);
		userStore.addUser(test2);
		Parent root= FXMLLoader.load(getClass().getResource("Login.fxml"));
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}

}
