package com.pack.springreact.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pack.springreact.model.User;
import com.pack.springreact.repository.UserRepository;

@Service
@Transactional
public class UserService {
	
	 @Autowired
	 UserRepository userRepository;
	
	public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }
	 
}
