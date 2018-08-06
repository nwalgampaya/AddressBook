package com.reece.AddressBook.phone;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.reece.AddressBook.contact.Contact;

@RestController
public class PhoneController {

	@Autowired
	private PhoneService phoneService;

	@GetMapping("/contacts/{phoneId}/phones")
	public List<Phone> getAllPhone(@PathVariable("phoneId")String phoneId) {
		return phoneService.getAllPhones(phoneId);

	}
	@GetMapping("/contacts/{phoneId}/phones/{id}/")
	public Optional<Phone> getPhon(@PathVariable("id")String id) {
		return phoneService.getPhones(id);

	}

	@PostMapping("/contacts/{phoneId}/phones")
	public void addCphone(@RequestBody Phone phone,@PathVariable String phoneId) {
		phone.setContact(new Contact(phoneId, "", ""));
		phoneService.addPhone(phone);

	}
	
	@PutMapping("/contacts/{phoneId}/phones/{id}/")
	public void updatePhone(@RequestBody Phone phone) {
		phoneService.updateTopic(phone);
	}
	
	@DeleteMapping("/contacts/{phoneId}/phones/{id}/")
	public void deletePhone(@PathVariable("id")String id) {
		phoneService.deletePhone(id);
	}
}
