package application;

import java.io.File;
import javafx.scene.image.*;

public class Picture{
	
	File location;
	int height, width;
	Image image;
	
	Picture(File location)
	{
		this.location = location;
	}
	
	public String toString()
	{
		return location.toString();
	}
	
	public File getLocation()
	{
		return location;
	}
	
	
}
