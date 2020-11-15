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
import model.HikingHistory;
import model.UserProfile;
import store.UserStore;

public class App extends Application {

	public static void main(String[] args) {
		launch(args);

	}
	public static UserStore userStore= new UserStore();
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		HikingHistory history= new HikingHistory("Test",20,20,null,1.0);
		File proPicFile= new File("images/catProfile.png");
		BufferedImage proPicImage=ImageIO.read(proPicFile);
		LinkedList<HikingHistory> listHist= new LinkedList<>();
		listHist.add(history);
		UserProfile test = new UserProfile("Kiefuh","11787",proPicImage,listHist);
		userStore.addUser(test);
		Parent root= FXMLLoader.load(getClass().getResource("Login.fxml"));
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}

}
