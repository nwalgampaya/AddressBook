package com.reece.AddressBook.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reece.AddressBook.dao.ContactRepository;
import com.reece.AddressBook.model.Contact;

@Service
public class ContactServiceImpl implements ContactService{

	@Autowired
	private ContactRepository contactRepository;
    private static final AtomicInteger counter = new AtomicInteger();

    /*private static List<Contact> contacts = new ArrayList<Contact>(
            Arrays.asList(
                    new Contact("1", "Jack","Brown"),
                    new Contact(counter.incrementAndGet()+"", "Cersei","Baratheon")));

    public Contact findById(String id) {
        for (Contact contact : contacts){
            if (contact.getId() == id){
                return contact;
            }
        }
        return null;
    }*/

    /**
     * Will return a list of all the contacts
     * return List<Contact>
     */
	public List<Contact> getAllContacts() {

		List<Contact> contacts = new ArrayList<>();
		contactRepository.findAll().forEach(contacts::add);
		return contacts;

	}
	
	/**
	 * Adds a contact to the db
	 * @param contact
	 */

	public void addContact(Contact contact) {
		contactRepository.save(contact);
	}

	/**
	 * This is used to update perticular contact in db
	 * @param contact
	 */
	public void updateContact(Contact contact) {
		contactRepository.save(contact);
	}

	/**
	 * This is used to delete a perticular record in db
	 * @param contact	
	 */
	public void deleteContact(String id) {
		contactRepository.deleteById(id);
	}
}
