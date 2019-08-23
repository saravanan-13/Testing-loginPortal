package com.sapient.loginportal.service;






import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.loginportal.dao.UserDAO;
@Service
public class ForgotPwdServiceImpl implements ForgotPwdService{


	@Autowired
	private UserDAO userDAO;

	
	@Override
	public boolean findByEmail(String email) {
		
		
		return userDAO.findByEmail(email)==1?true:false;
			
	}

	@Override
	public String findQuestionsById(String email) {
		
		return userDAO.findQuestionsById(email);
	}

	@Override
	public String findAnswersById(String email) {
		
		return userDAO.findAnswersById(email);
	}

	@Override
	@Transactional
	public boolean setPassword(String pass,String salt, String email) {
		
		
	   return userDAO.setPassword(pass,salt,email)==1?true:false;
		 
				
	}

	@Override
	@Transactional
	public void changeColumns(String email) {
		
		 userDAO.changeColumns(email);
	}

}
