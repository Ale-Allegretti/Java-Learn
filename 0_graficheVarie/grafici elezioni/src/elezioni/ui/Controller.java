package elezioni.ui;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import elezioni.model.Coppia;
import elezioni.model.Risultato;
import elezioni.persistenza.BadFileFormatException;
import elezioni.persistenza.MyEleReader;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Controller {
	
	public static void alert(String title, String headerMessage, String contentMessage) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(headerMessage);
		alert.setContentText(contentMessage);
		alert.showAndWait();
	}
	
	public List<Series<String, Number>> getSeries(Risultato risultato){
		List<Series<String, Number>> result = new ArrayList<>();
		for (String comune : risultato.getComuni()) {
			List<Coppia> listaVotiPerComune = risultato.getRisultato(comune);
			Series<String, Number> serieVotiPerComune = new XYChart.Series<>();
			serieVotiPerComune.setName(comune);
			listaVotiPerComune.forEach(
					coppia -> serieVotiPerComune.getData().add(new XYChart.Data<>(coppia.getPartito(), coppia.getVoti())));
			result.add(serieVotiPerComune);
		}
		return result;
	}
	
	public Risultato getRisultato() {
		Risultato risultato = null;
		try {
			risultato = new MyEleReader().readAll(new FileReader("risultati.txt"));
		} catch (IOException | BadFileFormatException e) {
			Controller.alert("Errore", "Notizia funesta", "File risultati non trovato");
			risultato = new Risultato();
		}
		return risultato;
	}
}
