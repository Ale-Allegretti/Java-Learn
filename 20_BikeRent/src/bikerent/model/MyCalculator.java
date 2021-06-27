package bikerent.model;

import java.time.Duration;

public class MyCalculator implements Calculator {
	
	@Override
	public double calc(Rate rate, Rent rent) {
		double costo = 0;
		long durata = Duration.between(rent.getStart(), rent.getEnd()).toMinutes();

		durata -= rate.getDurataPrimoPeriodo().toMinutes();
		costo = rate.getCostoPrimoPeriodo();
		while (durata > 0) { 
			costo += rate.getCostoPeriodiSuccessivi();
			durata -= rate.getDurataPeriodiSuccessivi().toMinutes();
		}
		return rent.isRegular() ? costo/100 : costo/100 + rate.getSanzione();
	}

}
