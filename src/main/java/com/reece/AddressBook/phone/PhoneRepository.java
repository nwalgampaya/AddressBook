package com.reece.AddressBook.phone;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PhoneRepository extends CrudRepository<Phone, String>{
	
	public List<Phone> findByContactId(String contactId );

}
