package impostezannonia.ui.controller;

import java.text.NumberFormat;
import java.util.Locale;

import impostezannonia.model.CalcolatoreImposte;
import impostezannonia.model.Imposte;
import impostezannonia.model.TipologiaContribuente;

public class MyController implements Controller {
	
	private TipologiaContribuente[] elencoTipologia;
	private CalcolatoreImposte calcolatoreRedditi;
	
	public MyController(TipologiaContribuente[] elencoTipologia, CalcolatoreImposte calcolatoreRedditi) {
		this.elencoTipologia = elencoTipologia;
		this.calcolatoreRedditi = calcolatoreRedditi;
		
	}
	
	@Override
	public TipologiaContribuente[] getTipologie() {
		return this.elencoTipologia;
	}

	@Override
	public Imposte calcolaImposte(double reddito, double speseMediche, TipologiaContribuente tipologiaContribuente) {
		return this.calcolatoreRedditi.calcolaImposte(reddito, speseMediche, tipologiaContribuente);
	}

	@Override
	public String formatta(double valore) {
		NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.ITALY);
		formatter.setMaximumFractionDigits(2);
		formatter.setMinimumFractionDigits(2);
		
		return formatter.format(valore).toString();
	}

}
