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
		//trails= FileWriterReader.loadTreeMapStore("storeSaves/trailStore.dat");
		Factory.trailFactory(trails, 50000);
		trails.addTrail(trail);
		TrailStoreHolder.setTrailStore(trails);
		HikingHistory history= new HikingHistory("Test",20,20,new LinkedList<String>());
		File proPicFile= new File("images/catProfile.png");
		BufferedImage proPicImage=ImageIO.read(proPicFile);
		File proPicFileQuinn= new File("images/quinnPic.jfif");
		BufferedImage proPicImageQuinn=ImageIO.read(proPicFileQuinn);
		LinkedList<HikingHistory> listHist= new LinkedList<>();
		listHist.add(history);
		UserProfile test = new UserProfile("Kiefuh","11787","images/catProfile.png",listHist,true);
		UserProfile test2 = new UserProfile("Quinn","123456","images/quinnPic.jfif",listHist,false);
		//userStore.addUser(test);
		//userStore.addUser(test2);
		//Factory.userFactory(userStore, 10000);
		//FileWriterReader.saveUsers(userStore, "storeSaves/userStore.dat");
		//FileWriterReader.saveTrails(trails, "storeSaves/trailStore.dat");
		Parent root= FXMLLoader.load(getClass().getResource("Login.fxml"));
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}

}
