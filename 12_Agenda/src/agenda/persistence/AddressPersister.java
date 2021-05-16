package agenda.persistence;

import java.util.StringTokenizer;
import agenda.model.Address;
import agenda.model.*;

public class AddressPersister implements DetailPersister {
	
	private final static String SEPARATOR = ";";

	@Override
	public Detail load(StringTokenizer source) throws BadFileFormatException {
		Address address = (Address) DetailFactory.of("Address");
        if(source.countTokens() != 6)
            throw new BadFileFormatException("Address: not enough tokens");
        address.setDescription(source.nextToken(SEPARATOR));
        address.setStreet(source.nextToken(SEPARATOR));
        address.setNumber(source.nextToken(SEPARATOR));
        address.setZipCode(source.nextToken(SEPARATOR));
        address.setCity(source.nextToken(SEPARATOR));
        address.setState(source.nextToken(SEPARATOR));
        return address;
	}

	@Override
	public void save(Detail d, StringBuilder sb) {
		Address a = (Address) d;
        sb.append(a.getDescription() + SEPARATOR);
        sb.append(a.getStreet() + SEPARATOR);
        sb.append(a.getNumber() + SEPARATOR);
        sb.append(a.getZipCode() + SEPARATOR);
        sb.append(a.getCity() + SEPARATOR);
        sb.append(a.getState());
        sb.append(FileUtils.NEWLINE);
	}

}
