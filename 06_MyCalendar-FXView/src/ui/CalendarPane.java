package ui;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import control.MyCalendar;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Appointment;
import model.AppointmentCollection;

public class CalendarPane extends BorderPane {

	private MyCalendar controller;
	private DatePicker data;
	private DateTimeFormatter formatter;
	private ListView<Appointment> appList;
	private Button mese, settimana, tutti, inserisci, rimuovi;

	public CalendarPane(MyCalendar controller) {
		this.controller = controller;
		initUI();
	}

	private void initUI() {
		this.setPadding(new Insets(10, 20, 10, 20));
		appList = new ListView<Appointment>();
		appList.setPrefWidth(100);
		appList.setPrefHeight(100);
		riempiLista(controller.getDayAppointments(LocalDate.now()));
		formatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).withLocale(Locale.ITALY);
		data = new DatePicker(LocalDate.now());
		data.setOnAction(this::myDateHandle);
		VBox left = new VBox(10);
		left.setSpacing(10);

		HBox b1 = new HBox(10);
		b1.setSpacing(10);
		settimana = new Button("settimana");
		settimana.setOnAction(this::myWeekHandle);
		mese = new Button("mese");
		mese.setOnAction(this::myMonthHandle);
		tutti = new Button("tutti");
		tutti.setOnAction(this::myAllAppHandle);
		b1.getChildren().addAll(settimana, mese, tutti);

		HBox b2 = new HBox(10);
		b2.setSpacing(10);
		inserisci = new Button("inserisci");
		inserisci.setOnAction(this::myAddHandle);
		rimuovi = new Button("rimuovi");
		rimuovi.setOnAction(this::myRemoveHandle);
		b2.getChildren().addAll(inserisci, rimuovi);
		left.getChildren().addAll(new Label("Scegli la data:"), data, b1, b2);
		this.setLeft(left);

		// listview al centro
		VBox right = new VBox(10);
		right.getChildren().addAll(new Label("Lista appuntamenti:"), appList);
		this.setCenter(right);
		BorderPane.setMargin(right, new Insets(0, 0, 0, 10));

	}

	private void riempiLista(AppointmentCollection app) {
		List<Appointment> l = new ArrayList<Appointment>();
		for (int i = 0; i < app.size(); i++)
			l.add(app.get(i));
		ObservableList<Appointment> appointment = FXCollections.observableArrayList(l);
		appList.setItems(appointment);	
	}

	private void myAddHandle(ActionEvent event) {
		Dialog<Appointment> dialog = new Dialog<>();
		dialog.setTitle("Aggiungi Appuntamento");
		dialog.setHeaderText("Per favore specifica");
		DialogPane dialogPane = dialog.getDialogPane();
		dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
		TextField textField = new TextField("Descrizione");
		DatePicker datePicker = new DatePicker(LocalDate.now());
		TextField inizio = new TextField("ora di inizio (hh:mm)");
		TextField durationField = new TextField("Durata in minuti (numero intero)");

		dialogPane.setContent(new VBox(8, textField, datePicker, inizio, durationField));
		Platform.runLater(textField::requestFocus);
		dialog.setResultConverter((ButtonType button) -> {
			if (button == ButtonType.OK) {
				LocalDateTime time = null;
				Duration appDuration = null;
				try {
					time = LocalDateTime.of(datePicker.getValue(), LocalTime.parse(inizio.getText(), formatter));
					appDuration = Duration.of(Integer.parseInt(durationField.getText()), ChronoUnit.MINUTES);
				} catch (DateTimeParseException dtExc) {
					alert("Errore", "Attenzione", "Formato orario non valido");
				} catch (NumberFormatException | ArithmeticException arExc) {
					alert("Errore", "Attenzione", "La durata non è un numero");
				}
				if (time != null && appDuration != null)
					return new Appointment(textField.getText(), time, appDuration);
			}
			return null;
		});
		Optional<Appointment> result = dialog.showAndWait();

		if (result.isPresent()) {

			controller.add(result.get());
			riempiLista(controller.getDayAppointments(LocalDate.now()));
		}

	}

	private void myRemoveHandle(ActionEvent event) {
		Appointment app = appList.getSelectionModel().getSelectedItem();
		if (app == null)
			alert("Errore", "Attenzione", "Devi selezionare un appuntamento prima di rimuoverlo");
		else {
			if (controller.remove(app) == false)
				alert("Errore", "Attenzione", "Impossibile rimuovere appuntamento");
			else {
				appList.getItems().remove(app);
				appList.refresh();

			}
		}

	}

	private void myAllAppHandle(ActionEvent event) {

		riempiLista(controller.getAllAppointments());

	}

	private void myWeekHandle(ActionEvent event) {
		LocalDate value = data.getValue();

		riempiLista(controller.getWeekAppointments(value));

	}

	private void myMonthHandle(ActionEvent event) {
		LocalDate value = data.getValue();

		riempiLista(controller.getMonthAppointments(value));

	}

	private void myDateHandle(ActionEvent event) {
		LocalDate value = data.getValue();

		riempiLista(controller.getDayAppointments(value));

	}

	private void alert(String title, String headerMessage, String contentMessage) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(headerMessage);
		alert.setContentText(contentMessage);
		alert.showAndWait();
	}

}
