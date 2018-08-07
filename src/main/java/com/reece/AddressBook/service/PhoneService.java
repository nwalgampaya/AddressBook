package com.reece.AddressBook.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.reece.AddressBook.model.Phone;

@Service
public interface PhoneService {

	public List<Phone> getAllPhones(String contactId);

	public Optional<Phone> getPhones(String id);

	public void addPhone(Phone phone);

	public void updateTopic(Phone phone);

	public void deletePhone(String id);
}
