package com.loginportal.userconfirmation.model;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Register {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userID;
    private String userRole="user";
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name="emailid" , unique = true)
    private String emailID;
    @Column(name = "phone_no")
    private String phoneNo;
    private boolean emailConfirmationFlag=false;
    @Transient
    Date date = new Date();    
    private Timestamp accountCreationTime = new Timestamp(date.getTime());
    
    public Register() {
    	//empty constructor
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
 
    public boolean isEmailConfirmationFlag() {
        return emailConfirmationFlag;
    }

    public void setEmailConfirmationFlag(boolean emailConfirmationFlag) {
        this.emailConfirmationFlag = emailConfirmationFlag;
    }

    public Timestamp getAccountCreationTime() {
        return accountCreationTime;
    }
    
    public void setAccountCreationTime(Timestamp accountCreationTime) {
        this.accountCreationTime = accountCreationTime;
    }

    @Override
    public String toString() {
        return "User [userID=" + userID + ", userRole=" + userRole + ", firstName=" + firstName + ", lastName="
                + lastName + ", emailID=" + emailID + ", phoneNo=" + phoneNo + ", emailConfirmationFlag="
                + emailConfirmationFlag + ", date=" + date + ", accountCreationTime=" + accountCreationTime
                 + "]";
    }
		
	
}
