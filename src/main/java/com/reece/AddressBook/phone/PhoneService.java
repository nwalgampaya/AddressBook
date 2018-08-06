package com.reece.AddressBook.phone;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhoneService {

	@Autowired
	private PhoneRepository phoneRepository;

	public List<Phone> getAllPhones(String contactId) {

		List<Phone> phones = new ArrayList<>();
		phoneRepository.findByContactId(contactId).forEach(phones::add);
		return phones;

	}

	public Optional<Phone> getPhones(String id) {

		return phoneRepository.findById(id);

	}

	public void addPhone(Phone phone) {
		phoneRepository.save(phone);
	}

	public void updateTopic(Phone phone) {
		phoneRepository.save(phone);
	}

	public void deletePhone(String id) {
		phoneRepository.deleteById(id);
	}
}
