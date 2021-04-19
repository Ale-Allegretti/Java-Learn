
package persona;

import java.util.Objects;

public class Persona {
	protected String nameSurname;
	private int yearOfBirth;
	
	public Persona(String nameSurname, int yearOfBirth) {
		this.nameSurname = nameSurname;
		this.yearOfBirth = yearOfBirth;
	}
	
	public Persona(String name, String yearOfBirth) {
		this(name, Integer.parseInt(yearOfBirth));
	}
	
	public String getNameSurname() {
		return nameSurname;
	}
	
	public int getYearOfBirth() {
		return yearOfBirth;
	}
	
	public boolean omonimo(Persona p) {
		String sTemp = p.getNameSurname();
		return sTemp.equalsIgnoreCase(this.nameSurname);
	}
	
	public int olderThan(Persona other) {
		if(this.yearOfBirth > other.yearOfBirth)
			return -1;
		else if (this.yearOfBirth == other.yearOfBirth)
			return 0;
		else
			return 1;
	}
	
	
	public void show() {
		System.out.print(nameSurname);
	}
	
	@Override
	public String toString() {
		return getNameSurname() + "nato/a nel" + getYearOfBirth();
	}

	@Override
	public int hashCode() {
		return Objects.hash(nameSurname, yearOfBirth);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		return Objects.equals(nameSurname, other.nameSurname) && yearOfBirth == other.yearOfBirth;
	}
	
	
}
