package com.reece.AddressBook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.reece.AddressBook.model.Contact;
import com.reece.AddressBook.service.ContactService;

@RestController
public class ContactController {

	@Autowired
	private ContactService contactService;


	
	@GetMapping("/contacts")
	public List<Contact> getAllContacts() {
		return contactService.getAllContacts();

	}

	@PostMapping("/contacts")
	public void addContact(@RequestBody Contact contact) {
		contactService.addContact(contact);

	}
	
	@PutMapping("/contacts/{id}")
	public void updateContact(@RequestBody Contact contact) {
		contactService.updateContact(contact);
	}
	
	@DeleteMapping("/contacts/{id}")
	public void deleteContact(@PathVariable("id")String id) {
		contactService.deleteContact(id);
	}
	
	
/*	@RequestMapping(value="/topics/{id}/" ,method=RequestMethod.DELETE)
	public void deleteTopic(@PathVariable("id")String id) {
	
		topicService.deleteTopic(id);
	}*/
}
