package application;

import java.awt.image.BufferedImage;

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
	private ListView<BufferedImage> imageListView;
	
	public ViewPicsController() {
		Platform.runLater(()->{
			imageListView.setItems(FXCollections.observableList(SelectionHolder.getSelected().getPicturesTaken()));
			imageListView.setCellFactory(imageListView-> new ListCell<BufferedImage>() {
				ImageView imageView= new ImageView();
				@Override
	            public void updateItem(BufferedImage bufferedImage, boolean empty) {
					super.updateItem(bufferedImage, empty);
					if(empty) {
						setText(null);
						setGraphic(null);
					}
					else {
						Image image= SwingFXUtils.toFXImage(bufferedImage,null);
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
