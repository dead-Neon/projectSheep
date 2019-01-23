package application;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class Controller {

	// PANES
	@FXML
	private ImageView activeImage;
	@FXML
	private VBox imageList, controlPane;
	@FXML
	private BorderPane infoPane;

	@FXML
	void addImage(ActionEvent event) {
		Image image = new Image(getFile().toURI().toString(), true);
		Picture picture = new Picture(image);
		Main.pictures.add(picture);
		picture.setOnMouseClicked(e -> setActiveImage(picture));
		updateImageList();
	}

	/*
	public final void setOnMouseClicked(ImageView imageView) {
		int width, height;

		Image image = imageView.getImage();
		activeImage.setImage(image);
		width = (int) image.getWidth();
		height = (int) image.getHeight();
		
		 * WritableImage greyImage = new WritableImage(width, height); WritableImage
		 * redImage = new WritableImage((int)image.getWidth(), (int)image.getHeight());
		 * WritableImage greenImage = new WritableImage((int)image.getWidth(),
		 * (int)image.getHeight()); WritableImage blueImage = new
		 * WritableImage((int)image.getWidth(), (int)image.getHeight());
		 * 
		 * 
		 * 
		 * for (int i = 0; i < image.getHeight(); i++) { for (int j = 0; j
		 * <image.getWidth(); j ++) { Color greyColor =
		 * image.getPixelReader().getColor(j, i); greyColor = greyColor.grayscale();
		 * greyImage.getPixelWriter().setColor(j, i, greyColor);
		 * 
		 * Color redColor = image.getPixelReader().getColor(j, i); redColor =
		 * Color.color(redColor.getRed(), 0, 0); redImage.getPixelWriter().setColor(j,
		 * i, redColor);
		 * 
		 * Color greenColor = image.getPixelReader().getColor(j, i); greenColor =
		 * Color.color(0, greenColor.getGreen(), 0);
		 * greenImage.getPixelWriter().setColor(j, i, greenColor);
		 * 
		 * Color blueColor = image.getPixelReader().getColor(j, i); blueColor =
		 * Color.color(0, 0, blueColor.getBlue());
		 * blueImage.getPixelWriter().setColor(j, i, blueColor); } }
		 * greyPane.setImage(greyImage); redPane.setImage(redImage);
		 * greenPane.setImage(greenImage); bluePane.setImage(blueImage);
		 

	}
*/
	void updateImageList() {
		imageList.getChildren().clear();
		for (int i = 0; i < Main.pictures.size(); i++) {
			ImageView temp = Main.pictures.get(i);
			// double width = temp.getFitWidth();
			temp.setFitWidth(160);
			temp.setPreserveRatio(true);
			imageList.getChildren().add(temp);
		}
	}

	void setActiveImage(Picture picture)
	{
		activeImage.setImage(picture.getImage());
	}
	@FXML
    void blueFilter(ActionEvent event) {
		
    }

    @FXML
    void greenFilter(ActionEvent event) {

    }

    @FXML
    void greyscale(ActionEvent event) {

    }

    @FXML
    void redFilter(ActionEvent event) {

    }
    
    void filterImage(String color)
    {
    	int height, width;
    	height = (int) activeImage.getImage().getHeight();
    	width = (int)activeImage.getImage().getWidth();
    	
    	WritableImage newImage = new WritableImage(height, width);
    	for (int i = 0; i < width; i++) { 
    		for (int j = 0; j < height; j++) {
    			Color newColor = newImage.getPixelReader().getColor(j, i);
    			
    			newColor = newColor.grayscale();
    			newImage.getPixelWriter().setColor(j, i, newColor);
    		}
    	}
    }

	File getFile() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("PNG/JPG Files", "*.png", "*.jpg"));
		File selectedFile = fileChooser.showOpenDialog(null);
		return selectedFile;
	}

}
