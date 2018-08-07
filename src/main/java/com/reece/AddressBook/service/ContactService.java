package com.reece.AddressBook.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.reece.AddressBook.model.Contact;

@Service
public interface ContactService {

//	public Contact findById(String id);

	public List<Contact> getAllContacts();

	public void addContact(Contact contact);

	public void updateContact(Contact contact);

	public void deleteContact(String id);
}
