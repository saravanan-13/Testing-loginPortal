package com.pack.springreact.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.pack.springreact.model.User;
import com.pack.springreact.service.UserService;

@RestController
public class ReactController {

	@Autowired
	UserService userService;
	@Autowired
	ObjectMapper mapper;

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/")
	public ObjectNode getdata() {
		List<User> userList = userService.getAllUsers();
		ObjectNode objectNode = mapper.createObjectNode();

		for (User user : userList) {
			objectNode.put("id", user.getId());
			objectNode.put("email", user.getEmail());
			objectNode.put("name", user.getName());
		}

		return objectNode;
	}
}
