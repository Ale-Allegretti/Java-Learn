package ubz.persistence;

import java.io.IOException;
import java.io.InputStream;

import ubz.model.Disponibilita;
import ubz.model.Politiche;

public interface DotazioneLoader {
	public void load(InputStream r) throws IOException, BadFileFormatException;
	public Disponibilita getDisponibilita();
	public Politiche getPolitiche();
}
