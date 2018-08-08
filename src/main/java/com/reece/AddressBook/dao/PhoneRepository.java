package com.reece.AddressBook.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.reece.AddressBook.model.Phone;

public interface PhoneRepository extends CrudRepository<Phone, String>{
	
	public List<Phone> findByContactId(String contactId );

//	@Query("select p from Phone p join fetch p.contact c")
//    List<Phone> findAllContacts();
}
