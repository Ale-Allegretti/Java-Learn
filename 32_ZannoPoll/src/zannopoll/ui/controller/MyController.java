package zannopoll.ui.controller;

import java.io.IOException;
import java.io.Reader;
import java.util.Optional;

import zannopoll.model.Sesso;
import zannopoll.model.SondaggioPercentuale;
import zannopoll.persistence.BadFileFormatException;
import zannopoll.persistence.SondaggioReader;

public class MyController extends Controller{

	public MyController(SondaggioReader myReader, Reader reader, DialogManager dialogManager)
			throws IOException, BadFileFormatException {
		super(myReader, reader, dialogManager);
		
	}

	@Override
	public Optional<SondaggioPercentuale> getSondaggioPercentuale(int minAge, int maxAge, Optional<Sesso> sex) {
		Optional<SondaggioPercentuale> sondaggio = this.getSondaggioPercentuale().getSondaggioFiltrato(minAge, maxAge, sex);
		if (sondaggio.isEmpty())
			alert("Sondaggio vuoto","Sondaggio vuoto", "Sondaggio privo di interviste");
		
		return sondaggio;
	}
	
	

}
