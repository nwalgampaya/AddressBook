package com.reece.AddressBook.dao;

import org.springframework.data.repository.CrudRepository;

import com.reece.AddressBook.model.Contact;

public interface ContactRepository extends CrudRepository<Contact, String>{

}
