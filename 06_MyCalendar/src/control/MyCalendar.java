package control;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import model.Appointment;
import model.AppointmentCollection;

public class MyCalendar {
	
	private AppointmentCollection allAppointments;
	
	public MyCalendar() { this.allAppointments = new AppointmentCollection(); }
	
	private boolean isOverlapped(LocalDateTime start, LocalDateTime end, LocalDateTime refStart, LocalDateTime refEnd) {
		return start.isBefore(refEnd) && end.isAfter(refStart);
	}
	
	private AppointmentCollection getAppointmentsIn(LocalDateTime start, LocalDateTime end) {
		AppointmentCollection res = new AppointmentCollection();
		for (int i = 0; i < this.allAppointments.size(); i++)
			if (isOverlapped(this.allAppointments.get(i).getFrom(), this.allAppointments.get(i).getTo(), start, end))
				res.add(this.allAppointments.get(i));
		return res;
	}
	
	
	public AppointmentCollection getDayAppointments(LocalDate date) {
		LocalDateTime inizio = LocalDateTime.of(date, LocalTime.of(0, 0));
		LocalDateTime fine = inizio.plusDays(1);
		AppointmentCollection appDay = this.getAppointmentsIn(inizio, fine);
		return appDay;
	}
	
	public AppointmentCollection getWeekAppointments(LocalDate date) {
		long distanceFromPastMonday = date.getDayOfWeek().getValue() - DayOfWeek.MONDAY.getValue();
		LocalDate weekStart = date.minusDays(distanceFromPastMonday);
		// alternativa al weekStart di cui le due righe sopra:
		// LocalDateTime start =  LocalDateTime.of(date.getYear(), Month.of(date.getMonthValue()), date.getDayOfMonth() , 0, 0,0 );
		// start = start.with(DayOfWeek.MONDAY);  
		LocalDate weekEnd = weekStart.plusDays(6);
		AppointmentCollection appWeek = this.getAppointmentsIn(LocalDateTime.of(weekStart, LocalTime.of(0, 0)), 
				LocalDateTime.of(weekEnd, LocalTime.of(23, 59)));
		return appWeek;
	}
	
	public AppointmentCollection getMonthAppointments(LocalDate date) {
		LocalDateTime inizio = LocalDateTime.of(date.getYear(), date.getMonth(), 1, 0, 0, 0);
		LocalDateTime fine = inizio.plusMonths(1);
		AppointmentCollection appMonth = this.getAppointmentsIn(inizio, fine);
		return appMonth;
	}
	
	public AppointmentCollection getAllAppointments() {
		AppointmentCollection ris = new AppointmentCollection(allAppointments.size());
		for (int i = 0; i < this.allAppointments.size(); i++) {
			ris.add(this.allAppointments.get(i));
		}
		return ris;
	}
	
	public void add(Appointment app) {
		this.allAppointments.add(app);
	}
	
	public boolean remove(Appointment app) {
		int newSize = this.allAppointments.size();
		this.allAppointments.remove(this.allAppointments.indexOf(app));
		if ((newSize-1) == this.allAppointments.size())
			return true;
		else return false;
	}
	

}
