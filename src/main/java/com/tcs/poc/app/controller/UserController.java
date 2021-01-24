package com.tcs.poc.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.poc.app.model.GetAdmin;
import com.tcs.poc.app.model.GetAllCustomerResponse;
import com.tcs.poc.app.model.GetAllEmployeeResponse;
import com.tcs.poc.app.model.GetCustomer;
import com.tcs.poc.app.model.GetEmployee;
import com.tcs.poc.app.model.UserResponse;
import com.tcs.poc.app.service.UserService;

@RestController
public class UserController {
	
	
	@Autowired
	public UserService userService;
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE','ROLE_CUSTOMER')")
	@GetMapping(value = "/getCustomer")
	@ResponseBody
	public GetCustomer getCustomer(@AuthenticationPrincipal String emailID) {
		return userService.getCustomer(emailID);
	}

	@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
	@GetMapping(value = "/getEmployee")
	@ResponseBody
	public GetEmployee getEmployee(@AuthenticationPrincipal String emailID) {
		return userService.getEmployee(emailID);
	}

	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(value = "/getAdmin")
	@ResponseBody
	public GetAdmin getAdmin(@AuthenticationPrincipal String emailID) {
		 return userService.getAdmin(emailID);	
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE')")
	@GetMapping(value = "/getCustomerList")
	@ResponseBody
	public List<GetAllCustomerResponse> getCustomerDetails() {		
		return userService.getCustomerList();
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(value = "/getEmployeeList")
	@ResponseBody
	public List<GetAllEmployeeResponse> getEmployeeDetails() {
		return userService.getEmployeeDetails();
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(value = "/getAllUsers")
	@ResponseBody
	public List<UserResponse> getAllUsers() {
		 return userService.getAllUsers();	
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE')")
	@GetMapping(value = "/AllUsers")
	@ResponseBody
	public List<UserResponse> getAllUser() {
		 return userService.allUsers();	
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE','ROLE_CUSTOMER')")
	@GetMapping(value = "/getUser")
	@ResponseBody
	public UserResponse getUser(@AuthenticationPrincipal String emailID) {
		 return userService.getUser(emailID);	
	}
}
