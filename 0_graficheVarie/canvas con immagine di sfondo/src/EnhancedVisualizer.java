
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;

public class EnhancedVisualizer extends Application {
	List<String> listing;
	int numberOfImages, currentImage;
	Image img = null;
	Canvas canvas;
	Label label;

	public void start(Stage stage) {
		try {
			listing =  Files.list(Paths.get("."))
							.map(Path::getFileName)
							.map(Path::toString)
							.filter(p -> p.endsWith("jpg"))
							.collect(Collectors.toList());
			//System.out.println(listing);
			
			numberOfImages = listing.size();
			currentImage = 0;
			if (numberOfImages < 1) {
				System.exit(1);
			}
			
			img = new Image(new FileInputStream(listing.get(currentImage)));
			int larghezza = (int) Math.round(img.getWidth());
			int altezza = (int) Math.round(img.getHeight());
			//System.out.println(img + ", " + altezza + "x" + larghezza);

			stage.setTitle("Visualizzatore immagini");

			FlowPane panel = new FlowPane();
			canvas = new Canvas(larghezza, altezza);
			panel.getChildren().add(canvas);

			// adding button
			Button button = new Button("Next");
			panel.getChildren().add(button);
			button.setOnAction(this::nextImage);
			label = new Label();
			updateLabel(img);
			panel.getChildren().add(label);

			GraphicsContext g = canvas.getGraphicsContext2D();
			g.drawImage(img, 0, 0);
			Scene scene = new Scene(panel, Color.WHITE);
			stage.setScene(scene);
			stage.show();

		} catch (IOException e) {
			System.out.println("argh");
			System.exit(1);
		}
	}

	private void nextImage(ActionEvent e) {
		currentImage++;
		if (currentImage == numberOfImages)
			currentImage = 0;
		try {
			img = new Image(new FileInputStream(listing.get(currentImage)));
			updateLabel(img);
		} catch (FileNotFoundException e1) {
			System.out.println("no such image");
		}
		GraphicsContext g = canvas.getGraphicsContext2D();
		g.drawImage(img, 0, 0);
	}

	private void updateLabel(Image img) {
		label.setText(" Immagine " + (currentImage + 1) + "/" + numberOfImages + " - " + (int) img.getWidth() + "x"
					+ (int) img.getHeight());
	}

	public static void main(String[] args) {
		launch(args);
	}
}
