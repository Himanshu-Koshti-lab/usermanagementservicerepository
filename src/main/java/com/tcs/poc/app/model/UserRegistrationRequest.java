package com.tcs.poc.app.model;

import java.io.Serializable;
import java.util.Date;

import com.tcs.poc.app.entity.Role;

import lombok.Data;


@Data
public class UserRegistrationRequest implements Serializable {

    private static final long serialVersionUID = 2211979783517088623L;
    private Integer id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String emailID;
    private String password;
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
    private Date createdDate;
    private Date lastModifiedDate;
    private String createdBy;
    private String modifiedBy;
    private Role role;
    private int registrationStatus;

}