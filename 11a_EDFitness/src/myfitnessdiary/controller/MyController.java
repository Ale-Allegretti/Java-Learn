package myfitnessdiary.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

import myfitnessdiary.model.*;
import myfitnessdiary.persistence.*;

public class MyController extends Controller {
	
	private DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(Locale.ITALY);

	public MyController(FitnessDiary diary, ActivityRepository activityRepository, ReportWriter reportWriter) {
		super(diary, activityRepository, reportWriter);
	}

	@Override
	public String getDayWorkout(LocalDate data) {
		StringBuilder builder = new StringBuilder();
		builder.append("Allenamento di " + data.format(formatter) + System.lineSeparator());
		for (int i = 0; i < this.getDayWorkouts(data).size(); i++)
			builder.append(this.getDayWorkouts(data).get(i).getActivity().getName() + " minuti: " + this.getDayWorkouts(data).get(i).getDuration() +
					" calorie bruciate: "+ this.getDayWorkouts(data).get(i).getBurnedCalories() + System.lineSeparator());
		builder.append("Minuti totali allenamento: " + getFitnessDiary().getDayWorkoutMinutes(data) + System.lineSeparator());
		builder.append("Calorie bruciate: " + getFitnessDiary().getDayWorkoutCalories(data) + System.lineSeparator());
		
		return builder.toString();
	}
	
}
