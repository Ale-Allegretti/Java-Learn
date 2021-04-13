package ui;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import control.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Appointment;

public class MyCalendarApplication extends Application {
	private MyCalendar controller;

	public void start(Stage stage) {
		controller = new MyCalendar();
		riempiCalendarioAppTest();
		stage.setTitle("My Calendar");
		CalendarPane root = new CalendarPane(controller);
		root.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;"
				+ "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: gray;");
		Scene scene = new Scene(root, 800, 200);
		stage.setScene(scene);
		stage.show();
	}

	private void riempiCalendarioAppTest() {
		Appointment app = new Appointment("Dentista", LocalDateTime.now(), Duration.of(10, ChronoUnit.MINUTES));
		controller.add(app);
		app = new Appointment("Riunione Scuola", LocalDateTime.now().plusMonths(1),
				Duration.of(20, ChronoUnit.MINUTES));
		controller.add(app);

	}

	public static void main(String[] args) {
		launch(args);
	}

}
