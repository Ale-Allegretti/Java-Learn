package agenda.persistence;

import java.util.StringTokenizer;

import agenda.model.Detail;
import agenda.model.DetailFactory;
import agenda.model.Phone;

public class PhonePersister implements DetailPersister {
	private final static String SEPARATOR = ";";

	@Override
	public Detail load(StringTokenizer source) throws BadFileFormatException {
		Phone phone = (Phone) DetailFactory.of("Phone");
		if(source.countTokens()!=2)
            throw new BadFileFormatException("Address: not enough tokens");
		if (!phone.getName().equals("Phone"))
			throw new BadFileFormatException("Unknown Detail Type");
		phone.setDescription(source.nextToken(SEPARATOR));
		phone.setValue(source.nextToken());
		
        return phone;
	}

	@Override
	public void save(Detail d, StringBuilder sb) {
		Phone a = (Phone) d;
		sb.append(a.getName() + SEPARATOR);
		sb.append(a.getDescription() + SEPARATOR);
		sb.append(a.getValue());
		sb.append(FileUtils.NEWLINE);
		
	}

}
