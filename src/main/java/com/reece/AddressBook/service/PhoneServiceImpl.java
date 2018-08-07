package com.reece.AddressBook.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reece.AddressBook.dao.PhoneRepository;
import com.reece.AddressBook.model.Phone;

@Service
public class PhoneServiceImpl {

	@Autowired
	private PhoneRepository phoneRepository;

	/**
	 * Will return all the phones no of a given contact.
	 * @param contactId
	 * @return
	 */
	public List<Phone> getAllPhones(String contactId) {

		List<Phone> phones = new ArrayList<>();
		phoneRepository.findByContactId(contactId).forEach(phones::add);
		return phones;

	}

	/**
	 * Will return phone no of the relevent phone record
	 * @param id
	 * @return
	 */
	public Optional<Phone> getPhones(String id) {

		return phoneRepository.findById(id);

	}
	
	/**
	 * This will add a phone record to db
	 * @param phone
	 */

	public void addPhone(Phone phone) {
		phoneRepository.save(phone);
	}

	/**
	 * Will update detais of a phone record in db
	 * @param phone
	 */
	public void updateTopic(Phone phone) {
		phoneRepository.save(phone);
	}

	/**
	 * This will delete a perticular phone record
	 * @param id
	 */
	public void deletePhone(String id) {
		phoneRepository.deleteById(id);
	}
}
