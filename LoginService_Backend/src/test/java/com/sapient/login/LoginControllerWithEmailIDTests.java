package com.sapient.login;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.io.IOException;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.login.model.CustomPasswordEncoder;
import com.sapient.login.model.LoginStatus;
import com.sapient.login.model.PasswordHistory;
import com.sapient.login.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest // (it can disable authentication of S security)
//@WebMvcTest (it alone can't disable authentication of S security; it does autowiring of MockMvc; it can't be used along with @SpringBootTest; so now its not required to specify controller class explicitly)
public class LoginControllerWithEmailIDTests {

	@Autowired
	WebApplicationContext webApplicationContext;

	@MockBean
	private RestTemplate restTemplate;

	@MockBean
	private CustomPasswordEncoder customPasswordEncoder;

	private MockMvc mvc;

	public String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	public <T> T mapFromJson(String json, Class<T> clazz) throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}

	@Before
	public void init() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	// Case1:
	// User exists
	// Input: Correct password
	// confirmed
	// Returns: Authenticated

	@Test
	public void testAuthenticateCase1() throws Exception {
		User userToLoginService = new User();
		userToLoginService.setEmailID("abc@gmail.com");
		PasswordHistory password = new PasswordHistory();
		password.setPwd1("smallWorld");
		userToLoginService.setPasswordHistory(password);

		User userToDataService = new User();
		userToDataService.setEmailID("abc@gmail.com");

		User userFromDataService = new User(1L, "abc@gmail.com", new PasswordHistory(2L,
				"$2a$12$2jDJzTrQ9UOP43LVEyrdweLBe10SA0csWa5EzsHlQm0suxaWv7UqS", "$2a$12$2jDJzTrQ9UOP43LVEyrdwe"));
		Optional<User> optional = Optional.of(userFromDataService);

		HttpEntity<User> httpEntity = new HttpEntity<User>(userToDataService);
		when(restTemplate.exchange("http://localhost:8017/api/data/user", HttpMethod.POST, httpEntity,
				new ParameterizedTypeReference<Optional<User>>() {
				})).thenReturn(new ResponseEntity<>(optional, HttpStatus.OK));

		when(customPasswordEncoder.encodeWithSalt("smallWorld", "$2a$12$2jDJzTrQ9UOP43LVEyrdwe"))
				.thenReturn("$2a$12$2jDJzTrQ9UOP43LVEyrdweLBe10SA0csWa5EzsHlQm0suxaWv7UqS");

		httpEntity = new HttpEntity<User>(userFromDataService);
		when(restTemplate.exchange("http://localhost:8017/api/data/checkflag", HttpMethod.POST, httpEntity,
				new ParameterizedTypeReference<Boolean>() {
				})).thenReturn(new ResponseEntity<>(true, HttpStatus.OK));

		MvcResult mvcResult = mvc.perform(post("/api/authenticate").contentType(MediaType.APPLICATION_JSON)
				.content(mapToJson(userToLoginService)).accept(MediaType.ALL_VALUE)).andReturn();

		int apiCallStatus = mvcResult.getResponse().getStatus();
		String apiCallResponseFromLoginService = mvcResult.getResponse().getContentAsString();
		LoginStatus loginStatus = mapFromJson(apiCallResponseFromLoginService, LoginStatus.class);
		assertEquals(200, apiCallStatus);
		assertEquals("Authenticated", loginStatus.getLoginStatusMessage());
	}

	// Case2:
	// User exists
	// Input: Correct password
	// not confirmed
	// Returns: Not a confirmed user

	@Test
	public void testAuthenticateCase2() throws Exception {
		User userToLoginService = new User();
		userToLoginService.setEmailID("abc@gmail.com");
		PasswordHistory password = new PasswordHistory();
		password.setPwd1("smallWorld");
		userToLoginService.setPasswordHistory(password);

		User userToDataService = new User();
		userToDataService.setEmailID("abc@gmail.com");

		User userFromDataService = new User(1L, "abc@gmail.com", new PasswordHistory(2L,
				"$2a$12$2jDJzTrQ9UOP43LVEyrdweLBe10SA0csWa5EzsHlQm0suxaWv7UqS", "$2a$12$2jDJzTrQ9UOP43LVEyrdwe"));
		Optional<User> optional = Optional.of(userFromDataService);

		HttpEntity<User> httpEntity = new HttpEntity<User>(userToDataService);
		when(restTemplate.exchange("http://localhost:8017/api/data/user", HttpMethod.POST, httpEntity,
				new ParameterizedTypeReference<Optional<User>>() {
				})).thenReturn(new ResponseEntity<>(optional, HttpStatus.OK));

		when(customPasswordEncoder.encodeWithSalt("smallWorld", "$2a$12$2jDJzTrQ9UOP43LVEyrdwe"))
				.thenReturn("$2a$12$2jDJzTrQ9UOP43LVEyrdweLBe10SA0csWa5EzsHlQm0suxaWv7UqS");

		httpEntity = new HttpEntity<User>(userFromDataService);
		when(restTemplate.exchange("http://localhost:8017/api/data/checkflag", HttpMethod.POST, httpEntity,
				new ParameterizedTypeReference<Boolean>() {
				})).thenReturn(new ResponseEntity<>(false, HttpStatus.OK));

		MvcResult mvcResult = mvc.perform(post("/api/authenticate").contentType(MediaType.APPLICATION_JSON)
				.content(mapToJson(userToLoginService)).accept(MediaType.ALL_VALUE)).andReturn();

		int apiCallStatus = mvcResult.getResponse().getStatus();
		String apiCallResponseFromLoginService = mvcResult.getResponse().getContentAsString();
		LoginStatus loginStatus = mapFromJson(apiCallResponseFromLoginService, LoginStatus.class);
		assertEquals(200, apiCallStatus);
		assertEquals("Not a confirmed user", loginStatus.getLoginStatusMessage());
	}

	// Case3:
	// User exists
	// Incorrect password
	// Returns: Incorrect password

	@Test
	public void testAuthenticateCase3() throws Exception {
		User userToLoginService = new User();
		userToLoginService.setEmailID("abc@gmail.com");
		PasswordHistory password = new PasswordHistory();
		password.setPwd1("smallBall");
		userToLoginService.setPasswordHistory(password);

		User userToDataService = new User();
		userToDataService.setEmailID("abc@gmail.com");

		User userFromDataService = new User(1L, "abc@gmail.com", new PasswordHistory(2L,
				"$2a$12$2jDJzTrQ9UOP43LVEyrdweLBe10SA0csWa5EzsHlQm0suxaWv7UqS", "$2a$12$2jDJzTrQ9UOP43LVEyrdwe"));
		Optional<User> optional = Optional.of(userFromDataService);

		HttpEntity<User> httpEntity = new HttpEntity<User>(userToDataService);
		when(restTemplate.exchange("http://localhost:8017/api/data/user", HttpMethod.POST, httpEntity,
				new ParameterizedTypeReference<Optional<User>>() {
				})).thenReturn(new ResponseEntity<>(optional, HttpStatus.OK));

		when(customPasswordEncoder.encodeWithSalt("smallBall", "$2a$12$2jDJzTrQ9UOP43LVEyrdwe"))
				.thenReturn("$2a$12$2jDJzTrQ9UOP43LVEyrdweLBe10SA0csWa5EzsHlQm0suxaWv7UqT");

		MvcResult mvcResult = mvc.perform(post("/api/authenticate").contentType(MediaType.APPLICATION_JSON)
				.content(mapToJson(userToLoginService)).accept(MediaType.ALL_VALUE)).andReturn();

		int apiCallStatus = mvcResult.getResponse().getStatus();
		String apiCallResponseFromLoginService = mvcResult.getResponse().getContentAsString();
		LoginStatus loginStatus = mapFromJson(apiCallResponseFromLoginService, LoginStatus.class);
		assertEquals(200, apiCallStatus);
		assertEquals("Incorrect password", loginStatus.getLoginStatusMessage());
	}

	// Case4:
	// User doesn't exist
	// Password doesn't matter
	// Returns: User doesn't exist

	@Test
	public void testAuthenticateCase4() throws Exception {
		User userToLoginService = new User();
		userToLoginService.setEmailID("xyz@gmail.com");
		PasswordHistory password = new PasswordHistory();
		password.setPwd1("smallWorld");
		userToLoginService.setPasswordHistory(password);

		User userToDataService = new User();
		userToDataService.setEmailID("xyz@gmail.com");

		Optional<User> optional = Optional.empty();

		HttpEntity<User> httpEntity = new HttpEntity<User>(userToDataService);
		when(restTemplate.exchange("http://localhost:8017/api/data/user", HttpMethod.POST, httpEntity,
				new ParameterizedTypeReference<Optional<User>>() {
				})).thenReturn(new ResponseEntity<>(optional, HttpStatus.OK));

		MvcResult mvcResult = mvc.perform(post("/api/authenticate").contentType(MediaType.APPLICATION_JSON)
				.content(mapToJson(userToLoginService)).accept(MediaType.ALL_VALUE)).andReturn();

		int apiCallStatus = mvcResult.getResponse().getStatus();
		String apiCallResponseFromLoginService = mvcResult.getResponse().getContentAsString();
		LoginStatus loginStatus = mapFromJson(apiCallResponseFromLoginService, LoginStatus.class);
		assertEquals(200, apiCallStatus);
		assertEquals("User doesn't exist", loginStatus.getLoginStatusMessage());
	}
}
