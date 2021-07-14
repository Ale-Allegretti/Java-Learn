package vendoerivendo;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import vendoerivendo.controller.Controller;
import vendoerivendo.controller.MyController;
import vendoerivendo.persistence.BadFileFormatException;
import vendoerivendo.persistence.MyProdottiReader;
import vendoerivendo.ui.AnnunciFrame;


public class AnnunciApp extends Application {
	public static void alert(String title, String headerMessage, String contentMessage) {
		javafx.scene.control.Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(headerMessage);
		alert.setContentText(contentMessage);
		alert.showAndWait();
	}
	
	@Override
	public void start(Stage stage) {
		stage.setTitle("Annunci di vendita");
		Controller controller = null;
		try (Reader reader = new FileReader("Prodotti.txt")){					
			controller = new MyController(new MyProdottiReader(reader));
					
			AnnunciFrame mainPanel = new AnnunciFrame(controller);
			Scene scene = new Scene(mainPanel, 550, 280, Color.AQUAMARINE);
			stage.setScene(scene);
			stage.show();
			
		} catch (IOException | BadFileFormatException e) {
			alert(
					"ERRORE DI I/O",
					"Errore di lettura: impossibile leggere i dati",
					"Dettagli: " + e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}