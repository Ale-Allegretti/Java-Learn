package elezioni.ui;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import elezioni.model.Coppia;
import elezioni.model.Risultato;
import elezioni.persistenza.MockEleReader;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.chart.XYChart.Series;
import javafx.stage.Stage;
import javafx.scene.layout.FlowPane;

public class PollChartAppMock extends Application {

	public void start(Stage stage){
		stage.setTitle("Risultati elezioni comunali");
		FlowPane panel = new FlowPane();  // analogo a FLowLayout di Swing

		CategoryAxis asseOrizz = new CategoryAxis(); asseOrizz.setLabel("Comuni");
		NumberAxis asseVert = new NumberAxis(); asseVert.setLabel("Voti");

		BarChart<String,Number> chart = new BarChart<>(asseOrizz,asseVert);
		chart.setTitle("Elezioni in Emilia-Romagna");
		
		Risultato risultato = null;
		try {
			risultato = new MockEleReader().readAll(new FileReader("risultati.txt"));
		} catch (IOException e) {
			Controller.alert("Errore", "Notizia funesta", "File risultati non trovato");
			risultato = new Risultato();
		}

		for (String comune:risultato.getComuni()) {
			List<Coppia> tmpList = risultato.getRisultato(comune);
			Series<String, Number> tmpSeries  = new XYChart.Series<>();
			tmpSeries.setName(comune);
			tmpList.forEach(coppia -> tmpSeries.getData().add(new XYChart.Data<>(coppia.getPartito(), coppia.getVoti())));
			chart.getData().add(tmpSeries);
		}
		
		panel.getChildren().add(chart);
		Scene scene = new Scene(panel);
		stage.setScene(scene);
		stage.show(); 
	}
	
	/*
	--module-path="C:\Program Files\Java\javafx-sdk-11.0.1\lib"
	--add-modules=javafx.controls
	*/
	
	public static void main(String[] args) {
		launch(args);
	}

}