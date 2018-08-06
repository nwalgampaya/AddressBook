package com.reece.AddressBook.contact;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

	@Autowired
	private ContactRepository contactRepository;

	
	public String hello() {
		
		return "hello";
	}
	public List<Contact> getAllContacts() {

		List<Contact> contacts = new ArrayList<>();
		contactRepository.findAll().forEach(contacts::add);
		return contacts;

	}

	public void addContact(Contact contact) {
		contactRepository.save(contact);
	}

	public void updateTopic(Contact contact) {
		contactRepository.save(contact);
	}

		
	public void deleteContact(String id) {
		contactRepository.deleteById(id);
	}
}
