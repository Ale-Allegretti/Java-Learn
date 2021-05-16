package agenda.persistence;

import java.io.*;
import java.util.*;

import agenda.model.Contact;
import agenda.model.Detail;


public class TextContactsPersister implements ContactsPersister {

	private final static String SEPARATOR = ";";
	
	@Override
	public List<Contact> load(Reader reader) throws IOException, BadFileFormatException {
		if (reader == null)
			throw new IOException("reader null");
			
		BufferedReader innerReader = new BufferedReader(reader);
		List<Contact> contacts = new ArrayList<Contact>();
		Optional<Contact> c = readContact(innerReader);
		
		while (c.isPresent()) {
			contacts.add(c.get());
			c = readContact(innerReader);
		}
		return contacts;
	}
	
	private Optional<Contact> readContact(BufferedReader innerReader) throws IOException, BadFileFormatException {
		String riga = readLineSkippingEmpty(innerReader);
		if (riga == null)
			return Optional.empty();
		if (!riga.equals("StartContact"))
			throw new BadFileFormatException("StartContact expected");
		
		riga = readLineSkippingEmpty(innerReader);
		StringTokenizer stk1 = new StringTokenizer(riga);
		
		try {
			Contact c = new Contact(stk1.nextToken(SEPARATOR), stk1.nextToken(SEPARATOR));
			readDetails(c, innerReader);
			return Optional.of(c);
		} 
		catch (NoSuchElementException e) {
			throw new BadFileFormatException("Tokens not found", e);
		}
	}
	
	
	private void readDetails(Contact c, BufferedReader innerReader) throws IOException, BadFileFormatException {
		boolean isDetail;
		do {
			String line = readLineSkippingEmpty(innerReader);
			if (line == null) 
				throw new BadFileFormatException("Detail or EndContact expected");
			
			isDetail = !line.equals("EndContact");
			if (isDetail) {
				StringTokenizer tokenizer = new StringTokenizer(line);
				String detailType = tokenizer.nextToken(SEPARATOR);

				DetailPersister detailPersister = DetailPersister.of(detailType);

				if (detailPersister == null)
					throw new BadFileFormatException("Unknown Detail Type");
				

				Detail d = detailPersister.load(tokenizer);
				c.getDetailList().add(d);
			}
		} while (isDetail);
	}
	
	
	private String readLineSkippingEmpty(BufferedReader innerReader) throws IOException {
		String riga;
		do {
			riga = innerReader.readLine();
		} while (riga != null && riga.trim().isEmpty());
		return riga;
	}
		
		
	@Override
	public void save(List<Contact> contacts, Writer writer) throws IOException {
		for (Contact c : contacts) {
			saveContact(c, writer);
		}
	}
	
	
	private void saveContact(Contact c, Writer innerWriter) throws IOException {
		StringBuilder sb = new StringBuilder();
		sb.append("StartContact" + FileUtils.NEWLINE);
		sb.append(c.getName() + SEPARATOR + c.getSurname()
				+ FileUtils.NEWLINE);
		saveDetails(c.getDetailList(), sb);
		sb.append("EndContact" + FileUtils.NEWLINE);
		innerWriter.write(sb.toString());
	}
	
	private void saveDetails(List<Detail> detailList, StringBuilder sb) {
		for (Detail d : detailList) {
			DetailPersister loaderSaver = DetailPersister.of(d.getName());
			loaderSaver.save(d, sb);
		}
	}

}
