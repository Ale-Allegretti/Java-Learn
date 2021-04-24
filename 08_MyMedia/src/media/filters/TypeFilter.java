package media.filters;

import media.Media;
import media.Type;

public class TypeFilter implements Filter {
	
	private Type typeToFind;
	
	public TypeFilter (Type typeToFind) {
		this.typeToFind = typeToFind;
	}

	public void setTypeToFind(Type typeToFind) {
		this.typeToFind = typeToFind;
	}

	
	@SuppressWarnings("preview")
	@Override
	public boolean filter(Media media) {
		if (media instanceof HasType t)
			return  t.getType().equals(this.typeToFind);
		return false;
	}
	
	

}
