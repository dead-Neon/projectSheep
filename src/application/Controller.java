package application;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
				        new ExtensionFilter("PNG/JPG Files", "*.png", "*.jpg"));
		File selectedFile = fileChooser.showOpenDialog(null);
		Image image = new Image(selectedFile.toURI().toString(),
			    activeImage.getFitWidth(), // requested width
			    activeImage.getFitHeight(), // requested height
			    true, // preserve ratio
			    true, // smooth rescaling
			    true // load in background
			);
		activeImage.setImage(image);
		ImageView selectedImage = new ImageView();
		selectedImage.setImage(image);
		Main.pictures.add(selectedImage);
		selectedImage.setOnMouseClicked(e -> setOnMouseClicked(selectedImage));
		updateImageList();
    }
	
	public final void setOnMouseClicked(ImageView imageView)
	{
		Image image = imageView.getImage();
		activeImage.setImage(image);
	}
	
	void updateImageList()
	{
		imageList.getChildren().clear();
		for (int i = 0; i < Main.pictures.size(); i++)
		{
			ImageView temp = Main.pictures.get(i);
			temp.setFitWidth(160);
			temp.setPreserveRatio(true);
			imageList.getChildren().add(temp);
		}
	}
}
