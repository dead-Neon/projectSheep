package application;
	
import java.io.File;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;


public class Main extends Application {
	Stage primaryStage;
	static LinkedList<Picture> pictures = new LinkedList<>();
	static Map<String, LinkedList<Picture>> tagList = new Hashtable<>();
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Pane root = (Pane) FXMLLoader.load(Main.class.getResource("app.fxml"));
			Scene scene = new Scene(root,900,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
		
		
	}
	
	static Image filterImage(Image image)
    {
    	WritableImage newImage = new WritableImage((int)image.getWidth(), (int)image.getHeight());
    	{
    		for (int i = 0; i < (int)image.getHeight(); i++) { 
        		for (int j = 0; j < (int)image.getWidth(); j++) {
        			Color newColor = image.getPixelReader().getColor(j, i);
        			newImage.getPixelWriter().setColor(j, i, newColor.grayscale());
        		}
        	}
    	}
    	return newImage;	
    }
    static Image filterImage(Image image, int r, int g, int b)
    {
    	WritableImage newImage = new WritableImage((int)image.getWidth(), (int)image.getHeight());
    	{
    		for (int i = 0; i < (int)image.getHeight(); i++) { 
        		for (int j = 0; j < (int)image.getWidth(); j++) {
        			Color newColor = image.getPixelReader().getColor(j, i);
        			newColor = Color.color(newColor.getRed()*r, newColor.getGreen()*g, newColor.getBlue()*b);
        			newImage.getPixelWriter().setColor(j, i, newColor);		
        		}
        	}
    	}
    	return newImage;
    }

	static File getFile() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("PNG/JPG Files", "*.png", "*.jpg"));
		File selectedFile = fileChooser.showOpenDialog(null);
		return selectedFile;
	}
	
	static void error(String title, String error)
	{
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setContentText(error);
		alert.showAndWait();
	}
	
	
	
	
	
}
