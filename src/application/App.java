package application;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.UserProfile;
import store.UserStore;

public class App extends Application {

	public static void main(String[] args) {
		launch(args);

	}
	public static UserStore userStore= new UserStore();
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		File proPicFile= new File("catProfile.png");
		BufferedImage proPicImage=ImageIO.read(proPicFile);
		UserProfile test = new UserProfile("Kiefuh","11787",proPicImage,null);
		userStore.addUser(test);
		Parent root= FXMLLoader.load(getClass().getResource("Login.fxml"));
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}

}
