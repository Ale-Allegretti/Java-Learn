package agenda.model;

import java.util.*;

public class Agenda {
	
	private SortedSet<Contact> contactSet;

	public Agenda(Collection<Contact> contacts) {
		this.contactSet = new TreeSet<Contact>(contacts);
	}
	
	public Agenda() {
		this.contactSet = new TreeSet<Contact>();
	}
	
	public void addContact(Contact c) {
		if (c != null)
			this.contactSet.add(c);
	}
	
	public void removeContact(Contact c) {
		if (c != null)
			this.contactSet.remove(c);
	}
	
	public Set<Contact> getContacts() {
		Set<Contact> res = new HashSet<Contact>();
		for (Contact contacts : this.contactSet)
			res.add(contacts);
		return res;
	}
	
	
	public Optional<Contact> getContact(String firstName, String secondName) {
		for (Contact contacts : this.contactSet)
			if(contacts.getName().equals(firstName) && contacts.getSurname().equals(secondName))
				return Optional.of(contacts);
		return Optional.empty();
	}
	
	public Optional<Contact> getContact(int index) {
		Contact[] contacts = this.contactSet.toArray(new Contact[0]);
		if (index < contacts.length)
			return Optional.of(contacts[index]);
		return Optional.empty();
	}
	

	public SortedSet<Contact> searchContacts(String secondName) {
		SortedSet<Contact> res = new TreeSet<Contact>();
		for (Contact contacts : this.contactSet)
			if (contacts.getSurname().equals(secondName))
				res.add(contacts);
		return res;
	}
	
	
}
