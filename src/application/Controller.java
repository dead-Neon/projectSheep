package application;

import java.util.LinkedList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Controller {

	@FXML
	private HBox activePane;
	@FXML
	private VBox imageList, controlPane, tagPane;
	@FXML
	private BorderPane infoPane;
	@FXML
    private Label height, width;
	@FXML
	private TextField tagToAdd;
	
	private Picture activeImage = new Picture();

	@FXML
	void addImage(ActionEvent event) {
		Image image = new Image(Main.getFile().toURI().toString(), true);
		Picture picture = new Picture(image);
		picture.setOnMouseClicked(e -> setActivePane(picture));
		Main.pictures.add(picture);
		updateImageList();
	}

	
	void updateImageList() {
		imageList.getChildren().clear();
		for (int i = 0; i < Main.pictures.size(); i++) {
			Picture temp = Main.pictures.get(i);
			temp.setFitWidth(160);
			temp.setPreserveRatio(true);
			imageList.getChildren().add(temp);
			Pane pane = new Pane();
			pane.setMinHeight(20);
			imageList.getChildren().add(pane);
		}
	}
	
	@FXML
	void addTag(ActionEvent event)
	{	
		String tag = tagToAdd.getText().toLowerCase(); //The entered tag to add
		System.out.println("Adding tag");
		if (Main.tagList.containsKey(tag)){
			System.out.println("Tag Exists");
			Main.tagList.get(tag).add(activeImage);	//If the tag exists, add the current imageView
			activeImage.getTags().add(tag);
		}
		else {
			System.out.println("Creating new tag");
			LinkedList<Picture> tagList = new LinkedList<>();//If not, create new list for tag
			Main.tagList.put(tag, tagList);
			tagList.add(activeImage);
			activeImage.getTags().add(tag);
			
		}
		System.out.println("Added tag to image");
		updateImageList();
	}

	void setActivePane(Picture picture)
	{
		activeImage = picture;
		picture.setFitWidth(picture.getWidth());
		activePane.getChildren().clear();
		activePane.getChildren().add(activeImage);
		tagPane.getChildren().clear();
		for (int i = 0; i < picture.getTags().size(); i++)
		{
			Label label = new Label(picture.getTags().get(i));
			tagPane.getChildren().add(label);
		}
		updateImageList();
	}
	
	@FXML
    void blueFilter(ActionEvent event) {
		activeImage.setImage(Main.filterImage(activeImage.getImage(), 0,0,1));
    }
    @FXML
    void greenFilter(ActionEvent event) {
    	activeImage.setImage(Main.filterImage(activeImage.getImage(),0,1,0));
    }
    @FXML
    void greyscale(ActionEvent event) {
    	activeImage.setImage(Main.filterImage(activeImage.getImage()));
    }
    @FXML
    void redFilter(ActionEvent event) {
    	activeImage.setImage(Main.filterImage(activeImage.getImage(),1,0,0));
    }

}
