package electriclife.model;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Locale;

public class TariffaFlat extends Tariffa {
	private int sogliaMensile;
	private double quotaFissaMensile, prezzoKWhExtra;

	public TariffaFlat(String nome, double quotaFissaMensile, int sogliaMensile, double prezzoKWhExtra) {
		super(nome);
		this.quotaFissaMensile = quotaFissaMensile;
		this.sogliaMensile = sogliaMensile;
		this.prezzoKWhExtra = prezzoKWhExtra;
	}

	public int getSogliaMensile() {
		return sogliaMensile;
	}

	public double getQuotaFissaMensile() {
		return quotaFissaMensile;
	}

	public double getPrezzoKWhExtra() {
		return prezzoKWhExtra;
	}

	@Override
	public String getDettagli() {
		NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.ITALY);
		return formatter.format(getQuotaFissaMensile()) + "/mese per " + getSogliaMensile() + " KWh," + " poi "
				+ formatter.format(getPrezzoKWhExtra()) + "/KWh";

	}

	@Override
	public Bolletta creaBolletta(LocalDate date, int consumo) {
		double costoInSoglia = getQuotaFissaMensile();
		int consumoExtraSoglia = consumo - sogliaMensile;
		if (consumoExtraSoglia < 0) {
			consumoExtraSoglia = 0;
		}
		double costoExtraSoglia = getPrezzoKWhExtra() * consumoExtraSoglia;
		double costo = costoInSoglia + costoExtraSoglia;

		Bolletta bolletta = new Bolletta(date, this, consumo);

		bolletta.addLineaBolletta("Quota fissa mensile", costoInSoglia);
		bolletta.addLineaBolletta("Costo energia extra soglia", costoExtraSoglia);
		double accise = calcAccise(consumo);
		bolletta.addLineaBolletta("Corrispettivo per accise", accise);
		double iva = calcIVA(costo + accise);
		bolletta.addLineaBolletta("Corrispettivo per IVA", iva);
		bolletta.addLineaBolletta("Totale Bolletta", costo + accise + iva);

		return bolletta;
	}

}
