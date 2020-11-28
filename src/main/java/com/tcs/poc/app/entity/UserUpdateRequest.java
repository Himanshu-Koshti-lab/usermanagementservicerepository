package com.tcs.poc.app.entity;

import java.util.Date;
import java.util.Optional;

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
public class UserUpdateRequest {

	@Id
    @GeneratedValue
    @Column(name="req_id")
    private Integer req_id;
    
    @Column(name="user_id")
    private Integer user_id;
    
    @Column(name="emailID")
    private String emailID;
    
    @Column(name="newmobileNo")
    private long newmobileNo;    
     
    @Column(name="created_date")
    private Date createdDate;
    
    @Column(name="lastModified_date")
    private Date lastModifiedDate;
    
    @Column(name="created_by")
    private String createdBy;
    
    @Column(name="modified_by")
    private String modifiedBy;
    
    @OneToOne
	@JoinColumn(name = "UserUpdateRequestStatus", referencedColumnName = "id")
	private UserUpdateRequestStatus UserRequestStatus;
    
}
