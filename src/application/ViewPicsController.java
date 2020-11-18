package application;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import util.SelectionHolder;

public class ViewPicsController {
	@FXML
	private ListView<String> imageListView;
	
	public ViewPicsController() {
		Platform.runLater(()->{
			imageListView.setItems(FXCollections.observableList(SelectionHolder.getSelected().getPicturesTaken()));
			imageListView.setCellFactory(imageListView-> new ListCell<String>() {
				ImageView imageView= new ImageView();
				@Override
	            public void updateItem(String bufferedImage, boolean empty) {
					super.updateItem(bufferedImage, empty);
					if(empty) {
						setText(null);
						setGraphic(null);
					}
					else {
						BufferedImage bi=null;
						try {
							bi = ImageIO.read(new File(bufferedImage));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Image image= SwingFXUtils.toFXImage(bi,null);
						imageView.setImage(image);
						imageView.setFitWidth(imageListView.getWidth());
						imageView.setFitHeight(250);
						setGraphic(imageView);
					}
				}
			});
		});
	}
}
