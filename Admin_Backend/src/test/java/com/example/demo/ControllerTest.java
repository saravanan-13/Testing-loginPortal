package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.model.User;
import com.example.repository.ProfileRepository;

public class ControllerTest extends AbstractTest {
   @Override
   @Before
   public void setUp() {
      super.setUp();
      
        }
   
   
   private ProfileRepository repo;
   
  @Test //Testing giving a userid to check its existence
   public void getUser() throws Exception {
      String uri = "/getAll";
      MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
         .accept(MediaType.ALL)).andReturn();
      System.out.println(mvcResult.getResponse()+"%%%%%%%%%%%%%%%%%");
      int status = mvcResult.getResponse().getStatus();
      assertEquals(200, status);
      String content = mvcResult.getResponse().getContentAsString();
      System.out.println(content+"%%%%%%%%%%%%%%%%%");
     User[] productlist =  super.mapFromJson(content, User[].class);
     System.out.println("hi............");
     for(int i=0;i<productlist.length;i++)
      System.out.println("hello...."+productlist[i]);
      
   }
   
   
  @Test   //Testing the updateUser giving a valid testcase
  public void validTestcaseForUpdateUser1() throws Exception {
      
      

 

	  String uri = "/getAll";
      MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
         .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
      
      int status = mvcResult.getResponse().getStatus();
      assertEquals(200, status);
      String content = mvcResult.getResponse().getContentAsString();
     // User productlist =  super.mapFromJson(content, User.class);
      System.out.println(content);
      
  }
 
  
  
   }
   