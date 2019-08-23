package com.sapient.login.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.sapient.login.model.CustomPasswordEncoder;
import com.sapient.login.model.LoginStatus;
import com.sapient.login.model.User;

@RestController
@RequestMapping("/api/authenticate")
@CrossOrigin(origins = "*")
public class LoginController {

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	CustomPasswordEncoder customPasswordEncoder;

	@PostMapping
	public LoginStatus authenticate(@RequestBody User loginUser) {
		String url = "http://localhost:8017/api/data/user";

		User user = new User();

		if (loginUser != null) {
			if (loginUser.getUserID() != null)
				user.setUserID(loginUser.getUserID());
			else
				user.setEmailID(loginUser.getEmailID());
		}

		HttpEntity<User> httpEntity = new HttpEntity<>(user);
		ResponseEntity<Optional<User>> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity,
				new ParameterizedTypeReference<Optional<User>>() {
				});
		Optional<User> optional = response.getBody();
		return authenticateUser(optional, loginUser);
	}

	private LoginStatus authenticateUser(Optional<User> optional, User loginUser) {
		LoginStatus loginStatus = new LoginStatus();

		if (optional.isPresent()) {
			User user = optional.get();
			String storedHashedPassword = user.getPasswordHistory().getPwd1();
			String storedSalt = user.getPasswordHistory().getSalt1();
			String inputHashedPassword = customPasswordEncoder.encodeWithSalt(loginUser.getPasswordHistory().getPwd1(),
					storedSalt);

			if (inputHashedPassword.equals(storedHashedPassword)) {
				String url = "http://localhost:8017/api/data/checkflag";
				HttpEntity<User> httpEntity = new HttpEntity<>(user);
				ResponseEntity<Boolean> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity,
						new ParameterizedTypeReference<Boolean>() {
						});
				if (response.getBody()) {
					loginStatus.setLoginStatusMessage("Authenticated");
					loginStatus.setUserID(user.getUserID());
				} else {
					loginStatus.setLoginStatusMessage("Not a confirmed user");
					loginStatus.setUserID(user.getUserID());
				}
			} else {
				loginStatus.setLoginStatusMessage("Incorrect password");
				loginStatus.setUserID(user.getUserID());
			}
		} else {
			loginStatus.setLoginStatusMessage("User doesn't exist");
		}

		return loginStatus;
	}

}
