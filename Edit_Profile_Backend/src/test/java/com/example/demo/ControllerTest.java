package com.example.demo;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.controller.ProfileController;
import com.example.model.User;

public class ControllerTest extends AbstractTest {
	
	private static Logger logger = LoggerFactory.getLogger(ProfileController.class);
	@Override
	@Before
	public void setUp() {
		super.setUp();

	}
	
	private String updateUrl="/updateUser";

	@Test // Testing giving a userid to check its existence
	public void getUser() throws Exception {
		String uri = "/4";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		User productlist = super.mapFromJson(content, User.class);
		logger.info("get user content: ",productlist);
	}

	@Test // Testing the updateUser giving a blank email
	public void updateUser() throws Exception {
		
		User product = new User();
		product.setUserID(3);
		product.setFirstName("Bhargavi");
		product.setLastName("Bindu");
		String inputJson = super.mapToJson(product);
		MvcResult mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.post(updateUrl).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		logger.info("update user status code: ",status);

	}

	@Test // Testing the updateUser giving invalid email
	public void updateUser1() throws Exception {

		User product1 = new User();
		product1.setUserID(3);
		product1.setLastName("123bhadsdr");
		product1.setFirstName("....");
		product1.setEmailID("test.com");
		product1.setPhoneNo("9999999999");

		String inputJson1 = super.mapToJson(product1);
		MvcResult mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.post(updateUrl).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson1))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		String content = mvcResult.getResponse().getContentAsString();
		logger.info("update user test 1 status code: ",status);
		logger.info("update user test 1 content: ",content);

	}

	@Test // Testing the updateUser giving a non-existing userid
	public void updateUser5() throws Exception {

		
		User product5 = new User();
		product5.setUserID(34324);
		product5.setLastName("123bhsar");
		product5.setFirstName("....");
		product5.setEmailID("test.com");
		product5.setPhoneNo("999999999");

		String inputJson5 = super.mapToJson(product5);
		MvcResult mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.post(updateUrl).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson5))
				.andReturn();
		String content = mvcResult.getResponse().getContentAsString();
		int status = mvcResult.getResponse().getStatus();
		int status5 = mvcResult.getResponse().getStatus();
		assertEquals(404, status5);
		logger.info("update user test 5 status code: ",status);
		logger.info("update user test 5 content: ",content);


	}

	@Test // Testing the updateUser giving a valid testcase
	public void validTestcaseForUpdateUser1() throws Exception {

		User product3 = new User();
		product3.setUserID(3);
		product3.setLastName("123bharg");
		product3.setFirstName("....");
		product3.setEmailID("test.1.23.3@com");
		product3.setPhoneNo("9999999998");

		String inputJson3 = super.mapToJson(product3);
		MvcResult mvcResult3 = mockMvc.perform(
				MockMvcRequestBuilders.post(updateUrl).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson3))
				.andReturn();

		int status3 = mvcResult3.getResponse().getStatus();
		assertEquals(200, status3);
		String content3 = mvcResult3.getResponse().getContentAsString();
		assertEquals(content3, "User Updated Successfully!");

	}

	@Test // Testing the updateUser giving a blank lastname
	public void updateUser2() throws Exception {
		
		User product2 = new User();
		product2.setUserID(3);
		product2.setLastName("");
		product2.setFirstName("....");
		product2.setEmailID("test@com");
		product2.setPhoneNo("9999@#39");

		String inputJson2 = super.mapToJson(product2);
		MvcResult mvcResult2 = mockMvc.perform(
				MockMvcRequestBuilders.post(updateUrl).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson2))
				.andReturn();

		int status = mvcResult2.getResponse().getStatus();
		logger.info("update user test 2 status code: ",status);

	}

	@Test // Testing the updateUser giving a invalid mobile number non-numeric
	public void updateUser4() throws Exception {

		
		User product4 = new User();
		product4.setUserID(3);
		product4.setLastName("");
		product4.setFirstName("....");
		product4.setEmailID("test.234.56@com");
		product4.setPhoneNo("9999@#92");

		String inputJson4 = super.mapToJson(product4);
		MvcResult mvcResult4 = mockMvc.perform(
				MockMvcRequestBuilders.post(updateUrl).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson4))
				.andReturn();

		int status = mvcResult4.getResponse().getStatus();
		String content = mvcResult4.getResponse().getContentAsString();
		logger.info("update user test 4 status code: ",status);
		logger.info("update user test 4 content: ",content);
	}

	@Test // Testing the updateUser giving a valid testcase
	public void validTestcaseForCreateUser2() throws Exception {

		
		User product6 = new User();
		product6.setUserID(3);
		product6.setLastName("bhargavi");
		product6.setFirstName("....");
		product6.setEmailID("test@gamil.com");
		product6.setPhoneNo("9676386669");

		String inputJson6 = super.mapToJson(product6);
		MvcResult mvcResult6 = mockMvc.perform(
				MockMvcRequestBuilders.post(updateUrl).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson6))
				.andReturn();

		int status6 = mvcResult6.getResponse().getStatus();
		assertEquals(200, status6);
		String content6 = mvcResult6.getResponse().getContentAsString();
		assertEquals(content6, "User Updated Successfully!");

	}

}
