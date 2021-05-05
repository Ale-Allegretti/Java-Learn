package myfitnessdiary.model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class MyFitnessDiary implements FitnessDiary {
	
	private List<Workout> workouts;

	public MyFitnessDiary() {
		this.workouts = new ArrayList<Workout>();
	}
	
	@SuppressWarnings("unused")
	private int calcCalories (List<Workout> woList) {
		int totCalories = 0;
		for (int i = 0; i < woList.size(); i++) 
			totCalories += woList.get(i).getBurnedCalories();
		
		return totCalories;
	}
	
	@SuppressWarnings("unused")
	private int calcMinutes (List<Workout> woList) {
		int totMin = 0;
		for (int i = 0; i < woList.size(); i++) 
			totMin += woList.get(i).getDuration();
		
		return totMin;
	}
	
	@Override
	public void addWorkout(Workout wo) {
		this.workouts.add(wo);
	}

	
	@Override
	public List<Workout> getDayWorkouts(LocalDate date) {
		List<Workout> dayList = new ArrayList<Workout>();
		for (int i = 0; i < this.workouts.size(); i++)
			if (this.workouts.get(i).getDate().compareTo(date) == 0)
				dayList.add(this.workouts.get(i));
		
		return dayList;
	}
	
	
	@Override
	public List<Workout> getWeekWorkouts(LocalDate date) {
		List<Workout> weekList = new ArrayList<Workout>();
		LocalDate weekStart = date.with(DayOfWeek.MONDAY);
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < this.workouts.size(); j++) {
				if (this.workouts.get(j).getDate().compareTo(weekStart.plusDays(i)) == 0)
					weekList.add(this.workouts.get(j));
			}
		}
		return weekList;
	}


	@Override
	public int getWeekWorkoutCalories(LocalDate date) {
		int totWeekCalories = 0;
		LocalDate weekStart = date.with(DayOfWeek.MONDAY);
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < this.workouts.size(); j++) {
				if (this.workouts.get(j).getDate().compareTo(weekStart.plusDays(i)) == 0)
					totWeekCalories += this.workouts.get(j).getBurnedCalories();
			}
		}
		return totWeekCalories;
	}

	
	@Override
	public int getDayWorkoutCalories(LocalDate date) {
		int totDayCalories = 0;
		for (int i = 0; i < this.workouts.size(); i++)
			if (this.workouts.get(i).getDate().compareTo(date) == 0)
				totDayCalories += this.workouts.get(i).getBurnedCalories();
		return totDayCalories;
	}

	
	@Override
	public int getWeekWorkoutMinutes(LocalDate date) {
		int totWeekMin = 0;
		LocalDate weekStart = date.with(DayOfWeek.MONDAY);
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < this.workouts.size(); j++) {
				if (this.workouts.get(j).getDate().compareTo(weekStart.plusDays(i)) == 0)
					totWeekMin += this.workouts.get(j).getDuration();
			}
		}
		return totWeekMin;
	}

	@Override
	public int getDayWorkoutMinutes(LocalDate date) {
		int totDayMin = 0;
		for (int j = 0; j < this.workouts.size(); j++)
			if (this.workouts.get(j).getDate().compareTo(date) == 0)
				totDayMin += this.workouts.get(j).getDuration();
	
		return totDayMin;
	}

}
