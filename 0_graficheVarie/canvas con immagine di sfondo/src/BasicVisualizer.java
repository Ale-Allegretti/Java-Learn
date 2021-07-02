
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;

public class BasicVisualizer extends Application {

	public void start(Stage stage) {
		Image img = null;
		try {
			List<String> listing = Files.list(Paths.get("."))
										.map(Path::getFileName)
										.map(Path::toString)
										.filter(p->p.endsWith("jpg"))
										.collect(Collectors.toList());
			System.out.println(listing);
			img = new Image(new FileInputStream(listing.get(0)));
		} catch (IOException e) {
			System.out.println("argh");
			System.exit(1);
		}
		stage.setTitle("Visualizzatore immagine");
		int larghezza = (int) Math.round(img.getWidth());
		int altezza   = (int) Math.round(img.getHeight());
		System.out.println(img + ", " + altezza + "x" + larghezza);
		
		FlowPane panel = new FlowPane();
		Canvas canvas = new Canvas(larghezza, altezza);
		panel.getChildren().add(canvas);
		
		GraphicsContext g = canvas.getGraphicsContext2D();
		g.drawImage(img,0,0);
		Scene scene = new Scene(panel,Color.WHITE);
		stage.setScene(scene);
		stage.show(); 
	}

	public static void main(String[] args) {
		launch(args);
	}
}
