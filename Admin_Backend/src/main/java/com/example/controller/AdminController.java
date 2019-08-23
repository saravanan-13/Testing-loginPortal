package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.User;

import com.example.repository.ProfileRepository;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AdminController {

	@Autowired
	private ProfileRepository profileRepository;

	@GetMapping(value = "/getAll")
	public ResponseEntity<Iterable<User>> findAll() {

		Iterable<User> u1 = profileRepository.findAll();

		return ResponseEntity.ok().body(u1);
	}

}