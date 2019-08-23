package com.loginportal.userconfirmation.service;


import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loginportal.userconfirmation.model.Register;
import com.loginportal.userconfirmation.repository.UserRepository;

@Service
@Transactional
public class UserService {
	
	@Autowired
	UserRepository employeeRepository;
	
	@Autowired
    private JavaMailSender javaMailSender;
	 
	public Register findById(long id) {
		Optional<Register> user=employeeRepository.findById(id);
		if(user.isPresent())
			return user.get();
		else return null;
	}
	
	public Register findByEmailId(String id) {
		return employeeRepository.findByEmailId(id);
	}
	
	public Register save(Register r) {
		return employeeRepository.save(r);
	}
	
	public void sendEmail(String email, long uid) throws MessagingException {
		MimeMessage msg = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(msg, true);
		helper.setTo(email);
        helper.setSubject("Account Activation Link");
        String url = "http://localhost:8882/confirmmail/" + uid;
        helper.setText("<h1>Click on the following link to activate your account :</h1>" + url, true);
        javaMailSender.send(msg);
	}
}
