package com.tcs.poc.app.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    @Column(name="id")
    private Integer id;
    
    @Column(name="first_name")
    private String firstName;
    
    @Column(name="middle_name")
    private String middleName;
    
    @Column(name="last_name")
    private String lastName;
    
    @Column(name="emailID")
    private String emailID;
    
    @Column(name="password")
    private String password;
    
    @Column(name="mobileNo")
    private long mobileNo;
    
    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name="Date_of_birth")
    private Date dob;
    
    @Column(name="gender")
    private String gender;
    
    @Column(name="current_Address")
    private String currentAddress;
    
    @Column(name="current_city")
    private String currentCity;
    
    @Column(name="current_state")
    private String currentState;
    
    @Column(name="current_zipcode")
    private long currentZipcode;
    
    @Column(name="permanent_address")
    private String permanentAddress;
    
    @Column(name="permanent_city")
    private String permanentCity;
    
    @Column(name="permanent_state")
    private String permanentState;
    
    @Column(name="permanent_zipcode")
    private long permanentZipcode;
    
    @Column(name="security_question")
    private String securityQuestion;
    
    @Column(name="answer")
    private String answer;
    
    @Column(name="created_date")
    private Date createdDate;
    
    @Column(name="lastModified_date")
    private Date lastModifiedDate;
    
    @Column(name="created_by")
    private String createdBy;
    
    @Column(name="modified_by")
    private String modifiedBy;
    
    @Column(name="OTP")
    private int OTP;
    
    @OneToOne
    @JoinColumn(name = "role", referencedColumnName = "id")
    private Role role;
    
    @OneToOne
    @JoinColumn(name = "registrationStatus", referencedColumnName = "registrationStatusId")
    private UserRegistrationStatus registrationStatus;
    
}
