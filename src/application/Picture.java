package application;

import java.net.URL;
import javafx.scene.image.*;

public class Picture{
	
	URL location;
	int height, width;
	Image image;
	
	Picture(URL location, Image image, int height, int width)
	{
		this.location = location;
		this.height = height;
		this. width = width;
		this.image = image;
	}
	
}
