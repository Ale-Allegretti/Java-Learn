package agenda.model;

import java.util.ArrayList;
import java.util.List;

public class Contact implements Comparable <Contact>{
	
	private List<Detail> detailList;
	private String name;
	private String surname;
	
	
	public Contact(String name, String surname, List<Detail> deatailList) {
		this.detailList = deatailList;
		this.name = name;
		this.surname = surname;
	}
	
	public Contact(String name, String surname) {
		this(name, surname, new ArrayList<Detail>());
	}
	

	public String getName() {
		return name;
	}

	public void setName(String firstName) {
		this.name = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String secondName) {
		this.surname = secondName;
	}

	public List<Detail> getDetailList() {
		return this.detailList;
	}
	
	
	@Override
	public int compareTo(Contact arg0) {
		if (this.equals(arg0))
			return 0;
		if (!this.surname.equals(arg0.getSurname()))
			return this.surname.compareTo(arg0.getSurname());
		if (!this.name.equals(arg0.getName()))
			return this.name.compareTo(arg0.getName());
		return 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((detailList == null) ? 0 : detailList.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Contact))
			return false;
		Contact other = (Contact) obj;
		if (detailList == null) {
			if (other.detailList != null)
				return false;
		} else if (!detailList.equals(other.detailList))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(name + " ");
		builder.append(surname + "\n");
		for (Detail detail : detailList) 
			builder.append(detail.toString() + "\n");
		
		return builder.toString();
	}

}
