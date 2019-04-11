package application;

import java.io.File;
import java.util.LinkedList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Main extends Application {
	static LinkedList<Picture> pictures = new LinkedList<>();

	@Override
	public void start(Stage primaryStage) {
		try {
			Pane root = (Pane) FXMLLoader.load(Main.class.getResource("app.fxml"));
			Scene scene = new Scene(root, 900, 600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	static File getFile() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("PNG/JPG Files", "*.png", "*.jpg"));
		File selectedFile = fileChooser.showOpenDialog(null);
		return selectedFile;
	}

	static double luminosity(Image image) {
		PixelReader reader = image.getPixelReader();
		double luminosity = 0;
		double pixelCount = image.getWidth() * image.getHeight();
		for (int i = 0; i < image.getWidth(); i++) {
			for (int j = 0; j < image.getHeight(); j++) {
				luminosity = luminosity + pixelLuminosity(reader.getColor(i, j));
			}
		}
		return luminosity / pixelCount;
	}

	static Image grayscale(Image image) {
		WritableImage newImage = new WritableImage((int) image.getWidth(), (int) image.getHeight());
		double lum = luminosity(image) - .3;

		for (int i = 0; i < image.getWidth(); i++) {
			for (int j = 0; j < image.getHeight(); j++) {
				if (pixelLuminosity(image.getPixelReader().getColor(i, j)) < lum) {
					newImage.getPixelWriter().setColor(i, j, Color.BLACK);
				} else
					newImage.getPixelWriter().setColor(i, j, Color.WHITE);

			}
		}
		return newImage;
	}

	static double pixelLuminosity(Color color) {
		double luminosity = (0.2126 * color.getRed() + 0.7152 * color.getGreen() + 0.0722 * color.getBlue());
		return luminosity;
	}

	static int count(UnionSet set, int[][] arr) {
		int w = arr.length;
		int h = arr[0].length;

		int[] group = new int[w * h];
		int numberOfIslands = 0;
		for (int j = 0; j < w; j++) {
			for (int k = 0; k < h; k++) {
				if (arr[j][k] == 1) {

					int x = set.find(j * h + k);

					if (group[x] == 10) {
						numberOfIslands++;
						group[x]++; // counting pixels in group
					}

					else
						group[x]++; // counting pixels in group
				}
			}
		}
		return numberOfIslands;
	}

	static UnionSet buildSet(int arr[][]) {
		int w = arr.length;
		int h = arr[0].length;

		UnionSet set = new UnionSet(w * h);

		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				if (arr[i][j] == 0)
					continue;

				if (i + 1 < w && arr[i + 1][j] == 1)
					set.union(i * (h) + j, (i + 1) * (h) + j);

				if (i - 1 >= 0 && arr[i - 1][j] == 1)
					set.union(i * (h) + j, (i - 1) * (h) + j);

				if (j + 1 < h && arr[i][j + 1] == 1)
					set.union(i * (h) + j, (i) * (h) + j + 1);

				if (j - 1 >= 0 && arr[i][j - 1] == 1)
					set.union(i * (h) + j, (i) * (h) + j - 1);

				if (i + 1 < w && j + 1 < h && arr[i + 1][j + 1] == 1)
					set.union(i * (h) + j, (i + 1) * (h) + j + 1);

				if (i + 1 < w && j - 1 >= 0 && arr[i + 1][j - 1] == 1)
					set.union(i * h + j, (i + 1) * (h) + j - 1);

				if (i - 1 >= 0 && j + 1 < h && arr[i - 1][j + 1] == 1)
					set.union(i * h + j, (i - 1) * h + j + 1);

				if (i - 1 >= 0 && j - 1 >= 0 && arr[i - 1][j - 1] == 1)
					set.union(i * h + j, (i - 1) * h + j - 1);
			}
		}
		return set;
	}

	static Image boxBirds(Image image, UnionSet set, int[][] arr) {
		WritableImage newImage = new WritableImage((int) image.getWidth(), (int) image.getHeight());
		PixelWriter writer = newImage.getPixelWriter();

		int w = arr.length;
		int h = arr[0].length;
		int[] group = new int[w * h];
		for (int j = 0; j < w; j++) {
			for (int k = 0; k < h; k++) {
				if (arr[j][k] == 1) {
					// writer.setColor(j, k, Color.ALICEBLUE);

					int x = set.find(j * h + k);

					group[x]++; // counting pixels in group

				}
			}
		}
		for (int j = 0; j < w; j++) {
			for (int k = 0; k < h; k++) {
				if (arr[j][k] == 1) {
					int x = set.find(j * h + k);

					if (group[x] > 10) {
						writer.setColor(j, k, randomColor(group[x]));
					}
				}
			}
		}
		return newImage;
	}

	static int[][] numberPlace(UnionSet set, int[][] arr) {
		int w = arr.length;
		int h = arr[0].length;
		int[][] newArr = new int[w][h];
		int[] group = new int[w * h];
		for (int j = 0; j < w; j++) {
			for (int k = 0; k < h; k++) {
				if (arr[j][k] == 1) {

					int x = set.find(j * h + k);

					if (group[x] == 10) {
						newArr[j][k] = 1;
						group[x]++; // counting pixels in group
					}

					else {
						group[x]++; // counting pixels in group
					}
				}
			}
		}
		return newArr;
	}

	static Color randomColor(double v) {
		double r = 1 / (v % 3);
		double g = 1 / (v % 2);
		double b = 1 / (v % 4);
		while (r > 1) {
			r = 1 / r;
		}
		while (g > 1) {
			g = 1 / g;
		}
		while (b > 1) {
			b = 1 / b;
		}

		Color color = new Color(r, g, b, 1);
		return color;
	}

	public static Image identify(Image image) { // labels black pixels as 1
		double w = image.getWidth();
		double h = image.getHeight();
		int[][] set = new int[(int) w][(int) h];
		PixelReader reader = image.getPixelReader();

		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				if (reader.getColor(i, j).getBrightness() < .5) {
					set[i][j] = 1;
				}
			}
		}

		UnionSet unionSet = buildSet(set);
		int birds = count(unionSet, set);
		Image newImage = boxBirds(image, unionSet, set);
		System.out.println(birds);
		return newImage;
	}

	public static int[][] getNumberPlace(Image image) { // labels black pixels as 1
		double w = image.getWidth();
		double h = image.getHeight();
		int[][] set = new int[(int) w][(int) h];
		PixelReader reader = image.getPixelReader();

		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				if (reader.getColor(i, j).getBrightness() < .5) {
					set[i][j] = 1;
				}
			}
		}

		UnionSet unionSet = buildSet(set);
		int birds = count(unionSet, set);
		int[][] numbers = numberPlace(unionSet, set);
		System.out.println(birds);
		return numbers;
	}
}
