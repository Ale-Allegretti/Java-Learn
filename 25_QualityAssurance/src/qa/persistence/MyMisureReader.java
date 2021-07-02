package qa.persistence;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import qa.model.Misura;

public class MyMisureReader implements MisureReader {

	public List<Misura> leggiMisure(Reader r) throws IOException, BadFileFormatException {
		if (r == null)
			throw new IllegalArgumentException("reader nullo");
		BufferedReader reader = new BufferedReader(r);

		String line = null;
		List<Misura> list = new ArrayList<>();

		try {
			while ((line = reader.readLine()) != null) {
				StringTokenizer tokenizer = new StringTokenizer(line, ",");
				String nome = tokenizer.nextToken().trim();
				if (nome.isEmpty())
					throw new BadFileFormatException();
				int pesoAtteso = Integer.parseInt(tokenizer.nextToken().trim());
				int pesoReale =  Integer.parseInt(tokenizer.nextToken().trim());
				list.add(new Misura(nome, pesoAtteso, pesoReale));
			}

		} catch (NoSuchElementException | NumberFormatException e) {
			throw new BadFileFormatException(e);
		}

		return list;
	}

	// quick test
	public static void main(String args[]) throws IOException, BadFileFormatException {
		try (Reader r = new FileReader("Misure.txt")) {
			MisureReader vReader = new MyMisureReader();
			System.out.println("reader aperto");
			List<Misura> myList = vReader.leggiMisure(r);
			System.out.println("misure caricate: " + myList.size());
			System.out.println(myList.stream().limit(10).collect(Collectors.toList()));
		}
	}

}
