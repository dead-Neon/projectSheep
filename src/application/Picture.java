package application;

import javafx.scene.image.*;

public class Picture extends ImageView{
	
	int height, width;
	Image image;
	
	Picture(Image image)
	{
		setImage(image);
		height = (int)image.getHeight();
		width = (int)image.getWidth();
	}
	
	
	
}
