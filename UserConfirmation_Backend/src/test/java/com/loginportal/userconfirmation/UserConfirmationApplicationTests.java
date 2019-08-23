package com.loginportal.userconfirmation;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.google.gson.JsonObject;
import com.loginportal.userconfirmation.model.Register;
import com.loginportal.userconfirmation.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserConfirmationApplicationTests extends AbstractTest{

	@Autowired
	private UserService employeeService;
	
	@Override
	@Before
	public void setUp() {
	    super.setUp();
	}
	
	//When user is not in database
    @Test
    public void getSendMailApi1() throws Exception {
       String uri = "/api/v1/sendmail/";
       Register t = new Register();
       t.setEmailID("aditik@gmail.com");
       String inputJson = super.mapToJson(t);
       MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
          .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
      
       JsonObject person = new JsonObject();
       person.addProperty("response", "Not a valid id");
      
       String status = mvcResult.getResponse().getContentAsString();
       assertEquals(person.toString(), status);
    }
   
    //When user is in database
    @Test
    public void getSendMailApi2() throws Exception {
       String uri = "/api/v1/sendmail/";
       Register t = new Register();
      
       t.setEmailID("sanketgiri48@gmail.com");
       String inputJson = super.mapToJson(t);
       MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
          .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
      
       JsonObject person = new JsonObject();
       person.addProperty("response", "Congrats! Your account has been successfully activated...");
      
       String status = mvcResult.getResponse().getContentAsString();
       assertEquals(person.toString(), status);
       //Register temp=employeeRepo.findById((long) 12312).get();
       Register temp = employeeService.findByEmailId("sanketgiri48@gmail.com");
       temp.setEmailConfirmationFlag(false);
       employeeService.save(temp);
    }
   
    //When user is already verified
    @Test
    public void getSendMailApi3() throws Exception {
       String uri = "/api/v1/sendmail/";
       Register t = new Register();
      
       t.setEmailID("san48@gmail.com");
       String inputJson = super.mapToJson(t);
       MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
          .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
          
       JsonObject person = new JsonObject();
       person.addProperty("response", "User already verified");
          
       String status = mvcResult.getResponse().getContentAsString();
       assertEquals(person.toString(), status);
    }

}
