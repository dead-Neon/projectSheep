package application;

import java.util.LinkedList;

import javafx.scene.image.*;

public class Picture extends ImageView{
	
	LinkedList<String> tags = new LinkedList<>();
	
	int height, width;
	Image image;
	
	Picture(Image image)
	{
		setImage(image);
		height = (int)image.getHeight();
		width = (int)image.getWidth();
	}
	
	public Picture() {
		// TODO Auto-generated constructor stub
	}

	LinkedList<String> getTags()
	{
		return tags;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	
	
	
}
