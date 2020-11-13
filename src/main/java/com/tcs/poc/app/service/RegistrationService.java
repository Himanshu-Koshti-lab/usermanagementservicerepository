package com.tcs.poc.app.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.poc.app.entity.Role;
import com.tcs.poc.app.entity.User;
import com.tcs.poc.app.model.UserRegistrationRequest;
import com.tcs.poc.app.model.UserRegistrationResponse;
import com.tcs.poc.app.repository.RoleRepository;
import com.tcs.poc.app.repository.UserRepository;

@Service
public class RegistrationService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	public UserRegistrationResponse registerUser(UserRegistrationRequest userRegistrationRequest) {
		UserRegistrationResponse userRegistrationResponse = new UserRegistrationResponse();
		try {
			Optional<Role> role = roleRepository.findById(userRegistrationRequest.getRole());
			System.out.println(role.get());
			System.out.println(userRegistrationRequest.getRole());
			User user = new User();
			user.setAnswer(userRegistrationRequest.getAnswer());
			user.setCreatedBy(userRegistrationRequest.getEmailID());
			user.setCreatedDate(new Date());
			user.setCurrentAddress(userRegistrationRequest.getCurrentAddress());
			user.setCurrentCity(userRegistrationRequest.getCurrentCity());
			user.setCurrentState(userRegistrationRequest.getCurrentState());
			user.setCurrentZipcode(userRegistrationRequest.getCurrentZipcode());
			user.setDob(userRegistrationRequest.getDob());
			user.setEmailID(userRegistrationRequest.getEmailID());
			user.setFirstName(userRegistrationRequest.getFirstName());
			user.setGender(userRegistrationRequest.getGender());
			user.setId(userRegistrationRequest.getId());
			user.setLastModifiedDate(new Date());
			user.setLastName(userRegistrationRequest.getLastName());
			user.setMiddleName(userRegistrationRequest.getMiddleName());
			user.setMobileNo(userRegistrationRequest.getMobileNo());
			user.setModifiedBy(userRegistrationRequest.getEmailID());
			user.setPassword(userRegistrationRequest.getPassword());
			user.setPermanentAddress(userRegistrationRequest.getPermanentAddress());
			user.setPermanentCity(userRegistrationRequest.getPermanentCity());
			user.setPermanentState(userRegistrationRequest.getPermanentState());
			user.setPermanentZipcode(userRegistrationRequest.getPermanentZipcode());
			user.setRole(role.get());
			user.setSecurityQuestion(userRegistrationRequest.getSecurityQuestion());
			user.setRegistrationStatus(1);

			User userResponse = userRepository.save(user);
			userRegistrationResponse.setUser(userResponse);

			userRegistrationResponse.setStatusMessage("User registration successfull");
			return userRegistrationResponse;
		} catch (Exception e) {
			e.printStackTrace();
			userRegistrationResponse.setStatusMessage("User registeration failed");
			return userRegistrationResponse;
		}
	}

	public User fetchUserByEmailID(String tempEmailID) {

		return userRepository.findByEmailID(tempEmailID);
	}

	public User fetchUserByMobileNo(long tempMobileNo) {
		return userRepository.findByMobileNo(tempMobileNo);
	}

	public void userRegistrationVerification(String emailID) {

		User tempUser = userRepository.findByEmailID(emailID);
		if (tempUser != null && tempUser.getRegistrationStatus() == 1) {
			tempUser.setRegistrationStatus(2);
			userRepository.save(tempUser);
		}
	}

	public void userRegistrationVerificationRejection(String emailID) {
		User tempUser = userRepository.findByEmailID(emailID);
		if (tempUser != null && tempUser.getRegistrationStatus() == 1) {
			tempUser.setRegistrationStatus(3);
			userRepository.save(tempUser);
		}
	}

	public void userEmployeeRegistrationVerification(String emailID) {
		User tempUser = userRepository.findByEmailID(emailID);
		if (tempUser != null && tempUser.getRegistrationStatus() == 1) {
			tempUser.setRegistrationStatus(2);
			userRepository.save(tempUser);
		}

	}

	public void userEmployeeRegistrationVerificationRejection(String emailID) {

		User tempUser = userRepository.findByEmailID(emailID);
		if (tempUser != null && tempUser.getRegistrationStatus() == 1) {
			tempUser.setRegistrationStatus(3);
			userRepository.save(tempUser);
		}

	}
}