package com.reece.AddressBook.phone;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.reece.AddressBook.contact.Contact;

@Entity
public class Phone {

	@Id
	private String id;
	private Long phoneNo;
	@ManyToOne
	private Contact contact;
	
	
	
	
	public Phone() {
		 
	}

	public Phone(String id, Long phoneNo, Contact contact, String contactId) {
		super();
		this.id = id;
		this.phoneNo = phoneNo;
//		this.contact = contact;
		this.contact = new Contact(contactId,"","");
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Long getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(Long phoneNo) {
		this.phoneNo = phoneNo;
	}
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	
	
	
}
