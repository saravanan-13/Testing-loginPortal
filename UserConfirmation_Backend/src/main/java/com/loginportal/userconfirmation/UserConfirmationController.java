package com.loginportal.userconfirmation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.loginportal.userconfirmation.model.Register;
import com.loginportal.userconfirmation.service.UserService;



import java.sql.Timestamp;
import java.util.Date;
import java.util.NoSuchElementException;


@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/v1")
public class UserConfirmationController{
	
	@Autowired
	ObjectMapper mapper;
	
	@Autowired
	private UserService employeeService;
	
	private String response = "response";
	
	@PostMapping(value="/sendmail/")
	public Object sendingEmail(@RequestBody Register user) {
		ObjectNode o = mapper.createObjectNode();
		
		
		try {
			Register e = employeeService.findByEmailId(user.getEmailID());
			if(e.isEmailConfirmationFlag()) {
				o.put(response, "User already verified");
			}
			else {
				e.setEmailConfirmationFlag(true);
				Date date = new Date();    
			    Timestamp accountCreationTime = new Timestamp(date.getTime());
				e.setAccountCreationTime(accountCreationTime);
	        	Register a = employeeService.save(e);
	        	if(a != null) {
	        		o.put(response, "Congrats! Your account has been successfully activated...");
	        	}
	        	else {
	        		o.put(response, "Error...");
	        	}
			}
		}
    	catch(NullPointerException e) {
    		o.put(response, "Not a valid id");
    	}
		return o;
	}
    
    @GetMapping(value="/confirmmail/{uid}")
   	public Object confirmingEmail(@PathVariable("uid") String userId) {
    	long id = Long.parseLong(userId);
    	String msg= "User already verified";
    	ObjectNode o = mapper.createObjectNode();
    	
    	try {
    		Register e = employeeService.findById(id);
        	
    		if(e==null)
    		{
    			o.put(response,msg);
    			return o;
    		}
    		
        	if(e.isEmailConfirmationFlag()) {
        		o.put(response,msg);
        	}
        	else {
        		e.setEmailConfirmationFlag(true);
            	Register a = employeeService.save(e);
            	
            	if(a != null) {
            		o.put(response, "Congrats! Your account has been successfully activated...");
            	}
            	else {
            		o.put(response, "Error...");
            	}
        	}
    	}
    	catch(NoSuchElementException e) {
    		o.put(response, "Not a valid id");
    	}
    	return o;
    }
}
