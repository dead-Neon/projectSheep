package application;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class Controller {
	
	//PANES
    @FXML
    private ImageView activeImage;
    @FXML
    private VBox imageList;
	
	@FXML
    void addImage(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(
				        new ExtensionFilter("PNG Files", "*.png", "JPG Files", "*.jpg"));
		File selectedFile = fileChooser.showOpenDialog(null);
		Picture picture = new Picture(selectedFile);
		
		
		
		
		Image image = new Image(selectedFile.toURI().toString(),
			    activeImage.getFitWidth(), // requested width
			    activeImage.getFitHeight(), // requested height
			    true, // preserve ratio
			    true, // smooth rescaling
			    true // load in background
			);
		activeImage.setImage(image);
		image = new Image(selectedFile.toURI().toString(),
			    180, // requested width
			    180, // requested height
			    true, // preserve ratio
			    true, // smooth rescaling
			    true // load in background
			);
		ImageView selectedImage = new ImageView();
		selectedImage.setImage(image);
		imageList.getChildren().add(selectedImage);
    }
}
