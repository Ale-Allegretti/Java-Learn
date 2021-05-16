package agenda.persistence;

import java.util.StringTokenizer;
import agenda.model.Detail;
import agenda.model.DetailFactory;
import agenda.model.EMail;

public class EMailPersister implements DetailPersister {
	
	private final static String SEPARATOR = ";";

	@Override
	public Detail load(StringTokenizer source) throws BadFileFormatException {
		EMail email = (EMail) DetailFactory.of("Email");
       
        if(source.countTokens()!=2)
            throw new BadFileFormatException("Email: not enough tokens");
        email.setDescription(source.nextToken(SEPARATOR));
        email.setValue(source.nextToken(SEPARATOR));
        return email;
	}

	@Override
	public void save(Detail d, StringBuilder sb) {
		EMail a = (EMail) d;
		sb.append(a.getName() + SEPARATOR);
		sb.append(a.getDescription() + SEPARATOR);
		sb.append(a.getValue());
		sb.append(FileUtils.NEWLINE);
	}

}
