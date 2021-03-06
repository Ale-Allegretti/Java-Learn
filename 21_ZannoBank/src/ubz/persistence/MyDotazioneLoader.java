package ubz.persistence;



import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import ubz.model.DisponibilitÓ;
import ubz.model.Politiche;


public class MyDotazioneLoader implements DotazioneLoader {
	
	private DisponibilitÓ disponibilitÓ;
	private Politiche politiche;

	@Override
	public void load(InputStream r) throws IOException, BadFileFormatException {
		if (r == null)
			throw new IllegalArgumentException("File corrotto");
		try (ObjectInputStream stream = new ObjectInputStream(r)) {
			Object obj = stream.readObject();	
			if (obj instanceof DisponibilitÓ) 
				disponibilitÓ = (DisponibilitÓ) obj;
			else 
				throw new BadFileFormatException("DisponibilitÓ non presente nel file binario");
			obj = stream.readObject();
			if (obj instanceof Politiche) 
				politiche = (Politiche) obj;
			else 
				throw new BadFileFormatException("Politiche non presenti nel file binario");
			
		} catch (ClassNotFoundException e1) {
			throw new BadFileFormatException("Dati non riconosciuti", e1);
		} catch (EOFException e2) {
			throw new BadFileFormatException("Dati mancanti", e2);
		} catch (IOException e3) {
			throw e3;
		} catch (Exception e4) {
			throw new BadFileFormatException("Errore nella lettura del file", e4);
		}
		
	}

	@Override
	public DisponibilitÓ getDisponibilitÓ() {
		return disponibilitÓ;
	}

	@Override
	public Politiche getPolitiche() {
		return politiche;
	}

}
