package com.reece.AddressBook.contact;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Contact {

	@Id
	private String id;
	private String firstName;
	private String lastName;
//	private Long phone;
//	private String type;
	
	
	public Contact() {
		
	}
	
	public Contact(String id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
//		this.phone = phone;
//		this.type = type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
/*	public Long getPhone() {
		return phone;
	}
	public void setPhone(Long phone) {
		this.phone = phone;
	}
*/	
	/*public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}*/
	
	
	
	
	
	
	
}
