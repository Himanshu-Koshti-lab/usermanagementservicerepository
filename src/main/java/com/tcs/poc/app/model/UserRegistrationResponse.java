package com.tcs.poc.app.model;



import java.util.Date;

import com.tcs.poc.app.entity.User;

import lombok.Data;

@Data
public class UserRegistrationResponse {

	private Integer id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String emailID;
    private long mobileNo;
    private Date dob;
    private String gender;
    private String currentAddress;
    private String currentCity;
    private String currentState;
    private long currentZipcode;
    private String permanentAddress;
    private String permanentCity;
    private String permanentState;
    private long permanentZipcode;
    private String securityQuestion;
    private String answer;
    private int role;
    
    //private User user;
    private String statusMessage;

}