package com.reece.AddressBook;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.apache.catalina.filters.CorsFilter;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reece.AddressBook.controller.ContactController;
import com.reece.AddressBook.model.Contact;
import com.reece.AddressBook.service.ContactService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

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

    @Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(contactController).addFilters(new CorsFilter()).build();

	}
    @Test
    public void testgetAllContacts() throws Exception {
        List<Contact> contacts= Arrays.asList(
                new Contact("1", "Daenerys","Targaryen"),
                new Contact("2", "John", "Snow"));

        when(contactService.getAllContacts()).thenReturn(contacts);

        mockMvc.perform(get("/contacts"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is("1")))
                .andExpect(jsonPath("$[0].firstName", is("Daenerys" )))
                .andExpect(jsonPath("$[0].lastName", is("Targaryen" )))
                .andExpect(jsonPath("$[1].id", is("2")))
                .andExpect(jsonPath("$[1].firstName", is("John")))
        		.andExpect(jsonPath("$[1].lastName", is("Snow" )));

        verify(contactService, times(1)).getAllContacts();
        verifyNoMoreInteractions(contactService);
    }
    
//    @Test
    public void testdeleteContacts() throws Exception {
    	Contact contact = new Contact("1", "Jack", "Brown");

        doNothing().when(contactService).deleteContact(contact.getId());
        
        mockMvc.perform(
                delete("/contacts/{id}", contact.getId()))
                .andExpect(status().isOk());

        verify(contactService, times(2)).deleteContact(contact.getId());
        verifyNoMoreInteractions(contactService);
    }

    @Test
    public void testupdateContacts() throws Exception {
    	Contact contact = new Contact("1", "Jacks", "Brown");
//        when(contactService.findById(contact.getId())).thenReturn(contact);


        doNothing().when(contactService).updateContact(contact);
        
        mockMvc.perform(
                put("/contacts/{id}", contact.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(contact)))
                .andExpect(status().isOk());

//        verify(contactService, times(1)).findById(contact.getId());
        verify(contactService, times(1)).updateContact(contact);
        verifyNoMoreInteractions(contactService);
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

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
	
	
	public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    
	// @Test
	public void contextLoads() {

		final String uri = "http://localhost:37053/contacts/1/";

		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uri, String.class);

		System.out.println(result);
	}
}
