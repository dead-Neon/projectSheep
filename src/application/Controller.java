package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Controller {

	@FXML
	private AnchorPane activePane;
	@FXML
	private VBox imageList, controlPane;

	private Picture activeImage = new Picture();

	@FXML
	void addImage(ActionEvent event) {
		Image image = new Image(Main.getFile().toURI().toString(), true);
		Picture picture = new Picture(image);
		picture.setOnMouseClicked(e -> setActivePane(picture));
		Main.pictures.add(picture);
		setActivePane(picture);
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

	void setActivePane(Picture picture) {
		activeImage = picture;
		picture.setFitWidth(picture.getWidth());
		activePane.getChildren().clear();
		updateImageList();
		activePane.getChildren().add(activeImage);
	}

	@FXML
	void greyscale(ActionEvent event) {
		activeImage.setImage(Main.grayscale(activeImage.getImage()));
		setActivePane(new Picture(activeImage.getImage()));
	}

	@FXML
	void findBirds(ActionEvent event) {
		int[][] numberSet = Main.getNumberPlace(Main.grayscale(activeImage.getImage()));
		Image image = Main.identify(Main.grayscale(activeImage.getImage()));
		Picture pic = new Picture(image);
		setActivePane(pic);
		int count = 1;
		for (int i = 0; i < image.getWidth(); i++) {
			for (int j = 0; j < image.getHeight(); j++) {
				if (numberSet[i][j] > 0) {
					Label label = new Label("" + count);
					count++;
					activePane.getChildren().add(label);
					label.setLayoutX(i);
					label.setLayoutY(j);

					System.out.println("Match");
				}
			}
		}
	}

	Image getActiveImage() {
		return activeImage.getImage();
	}

}
