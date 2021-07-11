package minirail.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import minirail.model.Line;
import minirail.model.Section;

public class MyLineReader implements LineReader{

	private Line line;
	private String lineKeyword = "Line";
	private String lineName = "Section";
	private List<Section> sectionsList;
	
	@Override
	public Line getLine() {
		return line;
	}
	
	public MyLineReader(Reader baseReader) throws BadFileFormatException, IOException {
		if (baseReader == null)
			throw new BadFileFormatException("Reader nullo");
		BufferedReader reader = new BufferedReader(baseReader);
		this.sectionsList = new ArrayList<>();
		String line = reader.readLine();
		String data1[] = line.split("\\s+");
		if(!data1[0].trim().equalsIgnoreCase(lineKeyword) || data1.length != 2)
			throw new BadFileFormatException("Formato riga errato");
		String lineNome = data1[1].trim();
		if (!lineNome.contains("-"))
			throw new BadFileFormatException("Formato linea errato");
		
		while ((line = reader.readLine()) != null) {
			String data2[] = line.split("\\s+");
			if(!data2[0].trim().equalsIgnoreCase(lineName) || data2.length != 3)
				throw new BadFileFormatException("Formato riga errato");
			String sectNome = data2[1].trim();
			if (!sectNome.contains("-"))
				throw new BadFileFormatException("Formato sezione errato");
			double lunghezza;
			try {
				lunghezza = Double.parseDouble(data2[2].trim());
			} catch (NumberFormatException e) {
				throw new BadFileFormatException("Formato lunghezza errato");
			}
			
			sectionsList.add(new Section(sectNome, lunghezza));
		}
		
		this.line = new Line(lineNome, sectionsList);
	}

}
