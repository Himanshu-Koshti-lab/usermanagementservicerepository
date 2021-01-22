package com.tcs.poc.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.poc.app.entity.User;
import com.tcs.poc.app.model.GetAdmin;
import com.tcs.poc.app.model.GetAllCustomerResponse;
import com.tcs.poc.app.model.GetAllEmployeeResponse;
import com.tcs.poc.app.model.GetCustomer;
import com.tcs.poc.app.model.GetEmployee;
import com.tcs.poc.app.model.UserResponse;
import com.tcs.poc.app.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	public UserRepository userRepository;
	
	
	public GetCustomer getCustomer(String emailID) {
		User user=userRepository.findByEmailID(emailID);
		GetCustomer response=new GetCustomer();
		response.setUser_id(user.getId());
		response.setFirstName(user.getFirstName());
		response.setLastName(user.getLastName());
		response.setEmailID(user.getEmailID());
		response.setMobileNo(user.getMobileNo());
		response.setPermanentAddress(user.getPermanentAddress());
		response.setPermanentCity(user.getPermanentCity());
		response.setPermanentState(user.getPermanentState());
		response.setPermanentZipcode(user.getPermanentZipcode());
		return response;
	}

	public GetEmployee getEmployee(String emailID) {
		User user=userRepository.findByEmailID(emailID);
		GetEmployee response=new GetEmployee();
		response.setFirstName(user.getFirstName());
		response.setLastName(user.getLastName());
		response.setEmailID(user.getEmailID());
		response.setMobileNo(user.getMobileNo());
		response.setPermanentAddress(user.getPermanentAddress());
		response.setPermanentCity(user.getPermanentCity());
		response.setPermanentState(user.getPermanentState());
		response.setPermanentZipcode(user.getPermanentZipcode());
		return response;
	}

	public GetAdmin getAdmin(String emailID) {
		User user=userRepository.findByEmailID(emailID);
		GetAdmin response=new GetAdmin();
		response.setFirstName(user.getFirstName());
		response.setLastName(user.getLastName());
		response.setEmailID(user.getEmailID());
		response.setMobileNo(user.getMobileNo());
		response.setPermanentAddress(user.getPermanentAddress());
		response.setPermanentCity(user.getPermanentCity());
		response.setPermanentState(user.getPermanentState());
		response.setPermanentZipcode(user.getPermanentZipcode());
		return response;
	}
	
	
	public List<GetAllCustomerResponse> getCustomerList() {
		List<User> tempUser = userRepository.findAll();
		List<GetAllCustomerResponse> tempCustomer = new ArrayList<GetAllCustomerResponse>();

		for (int i = 0; i < tempUser.size(); i++) {
			if (tempUser.get(i).getRole().getRoleName().equals("CUSTOMER")) {
				GetAllCustomerResponse tempCustomer1 = new GetAllCustomerResponse();
				tempCustomer1.setId(tempUser.get(i).getId());
				tempCustomer1.setFirstName(tempUser.get(i).getFirstName());
				tempCustomer1.setLastName(tempUser.get(i).getLastName());
				tempCustomer1.setEmailID(tempUser.get(i).getEmailID());
				tempCustomer1.setDob(tempUser.get(i).getDob());
				tempCustomer1.setRegistrationStatus(tempUser.get(i).getRegistrationStatus().getRegistrationStatusId());
				tempCustomer.add(tempCustomer1);
			}
		}
		return tempCustomer;
	}
	
	
	public List<GetAllCustomerResponse> getAll() {
		List<User> tempUser = userRepository.findAll();
		List<GetAllCustomerResponse> tempCustomer = new ArrayList<GetAllCustomerResponse>();

		for (int i = 0; i < tempUser.size(); i++) {
			if (tempUser.get(i).getRole().getRoleName().equals("CUSTOMER")) {
				GetAllCustomerResponse tempCustomer1 = new GetAllCustomerResponse();
				tempCustomer1.setId(tempUser.get(i).getId());
				tempCustomer1.setFirstName(tempUser.get(i).getFirstName());
				tempCustomer1.setLastName(tempUser.get(i).getLastName());
				tempCustomer1.setEmailID(tempUser.get(i).getEmailID());
				tempCustomer1.setDob(tempUser.get(i).getDob());
				tempCustomer1.setRegistrationStatus(tempUser.get(i).getRegistrationStatus().getRegistrationStatusId());
				tempCustomer.add(tempCustomer1);
			}
		}
		return tempCustomer;
	}

	public List<GetAllEmployeeResponse> getEmployeeDetails() {

		List<User> tempUser = userRepository.findAll();
		List<GetAllEmployeeResponse> tempCustomer = new ArrayList<GetAllEmployeeResponse>();

		for (int i = 0; i < tempUser.size(); i++) {
			if (tempUser.get(i).getRole().getRoleName().equals("EMPLOYEE")) {
				GetAllEmployeeResponse tempCustomer1 = new GetAllEmployeeResponse();
				tempCustomer1.setId(tempUser.get(i).getId());
				tempCustomer1.setFirstName(tempUser.get(i).getFirstName());
				tempCustomer1.setLastName(tempUser.get(i).getLastName());
				tempCustomer1.setEmailID(tempUser.get(i).getEmailID());
				tempCustomer1.setDob(tempUser.get(i).getDob());
				tempCustomer1.setRegistrationStatus(tempUser.get(i).getRegistrationStatus().getRegistrationStatusId());
				tempCustomer.add(tempCustomer1);
			}
		}
		return tempCustomer;
	}

	public List<UserResponse> getAllUsers() {
		List<User> tempUser = userRepository.findAll();
		List<UserResponse> t1 = new ArrayList<UserResponse>();
		for(int i = 0;i<tempUser.size();i++) {
			UserResponse t2 = new UserResponse();
			t2.setUser_id(tempUser.get(i).getId());
			t2.setFirstName(tempUser.get(i).getFirstName());
			t2.setLastName(tempUser.get(i).getLastName());
			t1.add(t2);			
		}
		return t1;
	}

	public List<UserResponse> AllUsers() {
		List<UserResponse> temp = new ArrayList<UserResponse>();
		List<User> temp1 = userRepository.findAll();
		for(int i=0;i<temp1.size();i++) {
			UserResponse user = new UserResponse();
			user.setUser_id(temp1.get(i).getId());
			user.setFirstName(temp1.get(i).getFirstName());
			user.setLastName(temp1.get(i).getLastName());
			user.setRole(temp1.get(i).getRole().getRoleName());
			temp.add(user);
		}
		
		return temp;
	}
	
	
}
