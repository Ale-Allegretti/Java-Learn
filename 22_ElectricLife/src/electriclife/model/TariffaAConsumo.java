package electriclife.model;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Locale;

public class TariffaAConsumo extends Tariffa {
	private double prezzoKWh;

	public TariffaAConsumo(String nome, double prezzoKWh) {
		super(nome);
		this.prezzoKWh = prezzoKWh;
	}

	public double getPrezzoKWh() {
		return prezzoKWh;
	}

	@Override
	public String getDettagli() {
		NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.ITALY);
		return "Costo KWh " + formatter.format(getPrezzoKWh());
	}

	@Override
	public Bolletta creaBolletta(LocalDate date, int consumo) {
		Bolletta bolletta = new Bolletta(date, this, consumo);

		double costo = prezzoKWh * consumo;
		bolletta.addLineaBolletta("Costo energia", costo);

		double accise = calcAccise(consumo);
		double iva = calcIVA(costo + accise);
		bolletta.addLineaBolletta("Corrispettivo per accise", accise);
		bolletta.addLineaBolletta("Corrispettivo per IVA", iva);
		bolletta.addLineaBolletta("Totale Bolletta", costo + accise + iva);

		return bolletta;
	}

}
