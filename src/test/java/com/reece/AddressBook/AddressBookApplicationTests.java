package com.reece.AddressBook;

import static org.junit.Assert.assertTrue;

import org.apache.catalina.filters.CorsFilter;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import com.reece.AddressBook.contact.Contact;
import com.reece.AddressBook.contact.ContactController;
import com.reece.AddressBook.contact.ContactService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
// @WebMvcTest(value = Contact.class, secure = false)

public class AddressBookApplicationTests {

	@InjectMocks
	ContactController contactController = new ContactController();

	@LocalServerPort
	private String port;

	@Autowired
	TestRestTemplate restTemplate;

	HttpHeaders headers = new HttpHeaders();

	private MockMvc mockMvc;

	@Mock
	private ContactService contactService;

	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(contactController).addFilters(new CorsFilter()).build();
		contactService = Mockito.mock(ContactService.class);

	}

	// @Test
	public void testRetrieveStudentContact() {
		String sort = "{\"contacts\"}";
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/contacts"), HttpMethod.GET, entity,
				String.class);

		String expected = "[{firstName:Nalin,lastName:Walgampaya,id:1}]";
		try {
			JSONAssert.assertEquals(expected, response.getBody(), false);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

	@Test
	public void addContact() {

		Contact contact = new Contact("3", "Jason", "Bond");

		HttpEntity<Contact> entity = new HttpEntity<Contact>(contact, headers);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/contacts"), HttpMethod.POST, entity,
				String.class);

		String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);

		assertTrue(actual.contains("/contacts"));

	}

	// @Test
	public void contextLoads() {

		final String uri = "http://localhost:37053/contacts/1/";

		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uri, String.class);

		System.out.println(result);
	}
}
