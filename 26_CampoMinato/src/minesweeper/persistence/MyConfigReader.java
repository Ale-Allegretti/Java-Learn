package minesweeper.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;


public class MyConfigReader implements ConfigReader {

	private int mines = 0;
	private int size = 0;
	
	public MyConfigReader(Reader r) throws BadFileFormatException {
		if (r == null)
			throw new IllegalArgumentException("reader nullo");
		String line = null;
		
		try (BufferedReader reader = new BufferedReader(r)){
			while((line = reader.readLine()) != null) {
				String[] items = line.split(":");
				String nome = items[0].trim();
				if (nome.equalsIgnoreCase("mines"))
					this.mines = Integer.parseInt(items[1].strip());
				else if (nome.equalsIgnoreCase("size"))
					this.size = Integer.parseInt(items[1].strip());
				else
					throw new BadFileFormatException("Dato errato nella linea: " + line);
			}
		}
		catch (IOException | NumberFormatException e) {
			throw new BadFileFormatException(e);
		}
	}
	
	@Override
	public int getSize() {
		return this.size;
	}

	@Override
	public int getMinesNumber() {
		return this.mines;
	}

}
