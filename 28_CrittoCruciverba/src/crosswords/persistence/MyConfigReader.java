package crosswords.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;



import crosswords.model.Scheme;

public class MyConfigReader implements ConfigReader {

	private int size;
	protected Scheme board;
	
	public MyConfigReader(Reader reader) throws IOException, BadFileFormatException {
		if (reader == null)
			throw new BadFileFormatException("File corrotto");
		BufferedReader rdr = new BufferedReader(reader);
		this.size = parseFirstLine(rdr.readLine());
		
		this.board = parseOtherLines(rdr);
	}
	
	private int parseFirstLine(String line) throws BadFileFormatException {
		int n;
		try {
			if (line == null || !line.startsWith("DIM ")) {
				throw new BadFileFormatException("Missing DIM keyword in line: " + line);
			}
			String[] items = line.split("\\s+")[1].split("x");
			if (items==null || items.length!=2 ) {
				throw new BadFileFormatException("Bad NxN format in line: " + line);
			}				
			n = Integer.parseInt(items[0].trim());
			if (n != Integer.parseInt(items[1].trim())) {
				throw new BadFileFormatException("Unsquared size NxN in line: " + line);
			}
		} catch (NumberFormatException e) {
			throw new BadFileFormatException("Int expected - " + e);
		}
		return n;
	}
	
	private Scheme parseOtherLines(BufferedReader reader) throws IOException, BadFileFormatException {
		Scheme reScheme = new Scheme(this.size);
		String lineString;
		int count = 0;
		while ((lineString = reader.readLine()) != null) {
			String[] items = lineString.split("\\s+");
			if (items.length != this.getSize())
				throw new BadFileFormatException("Elementi in riga minori di" + this.size);
			
			try {
				int[] numValues = Arrays.stream(items)
						.map(String::trim)
						.map( s -> s.equals("#") ? "0" : s)
						.mapToInt(Integer::parseInt).toArray();
				reScheme.setCellRow(count, numValues);
			} 
			catch (NumberFormatException e) {
					throw new BadFileFormatException("Dimensione non quadrata");
			}
		
			count++;
			if (count > this.size)
				throw new BadFileFormatException("Numero di righe superiore alla dimensione");
		}
		return reScheme;
	}
	
	@Override
	public int getSize() {
		return this.size;
	}

	@Override
	public Scheme getScheme() {
		return this.board;
	}

}
