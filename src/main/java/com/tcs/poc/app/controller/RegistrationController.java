package com.tcs.poc.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.poc.app.entity.User;
import com.tcs.poc.app.model.UserForgotPasswordQuestionRequest;
import com.tcs.poc.app.model.UserRegistrationRequest;
import com.tcs.poc.app.model.UserRegistrationResponse;
import com.tcs.poc.app.model.UserRegistrationVerificationRequest;
import com.tcs.poc.app.service.RegistrationService;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RestController
@RequestMapping("/service")
public class RegistrationController {

	@Autowired
	private RegistrationService registerationService;

	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;

	@RequestMapping(method = RequestMethod.POST, value = "/register-user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<UserRegistrationResponse> registerUser(
			@RequestBody UserRegistrationRequest userRegistrationRequest) throws Exception {

		String tempEmailID = userRegistrationRequest.getEmailID();

		long tempMobileNo = userRegistrationRequest.getMobileNo();

		userRegistrationRequest.setPassword(bcryptPasswordEncoder.encode(userRegistrationRequest.getPassword()));

		if (tempEmailID != null && !"".equals(tempEmailID) && !"".equals(tempMobileNo)) {
			User userObj = registerationService.fetchUserByEmailID(tempEmailID);
			User userObj1 = registerationService.fetchUserByMobileNo(tempMobileNo);

			if (userObj != null && userObj1 != null) {
				throw new Exception("User is already Present with the existing EmailID: " + tempEmailID
						+ " and Mobile No: " + tempMobileNo);
			}
		}

		if (tempEmailID != null && !"".equals(tempEmailID)) {
			User userObj = registerationService.fetchUserByEmailID(tempEmailID);

			if (userObj != null) {
				throw new Exception("User is already Present with the existing EmailID: " + tempEmailID);
			}
		}

		if (!"".equals(tempMobileNo)) {
			User userObj = registerationService.fetchUserByMobileNo(tempMobileNo);

			if (userObj != null) {
				throw new Exception("User is already Present with the existing MobileNo: " + tempEmailID);
			}
		}
		return new ResponseEntity<>(registerationService.registerUser(userRegistrationRequest), HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.POST, value = "/register-userRegistrationVerify", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	// @PostMapping(value="/register-userRegistrationVerify")
	public void userRegistrationVerification(@RequestBody UserRegistrationVerificationRequest user) {
		registerationService.userRegistrationVerification(user.getEmailID());
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.POST, value = "/register-userRegistrationReject", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	// @PostMapping(value="/register-userRegistrationVerify")
	public void userRegistrationVerificationRejection(@RequestBody UserRegistrationVerificationRequest user) {
		registerationService.userRegistrationVerificationRejection(user.getEmailID());
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.POST, value = "/register-userEmployeeRegistrationVerify", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	// @PostMapping(value="/register-userRegistrationVerify")
	public void userEmployeeRegistrationVerification(@RequestBody UserRegistrationVerificationRequest user) {
		registerationService.userEmployeeRegistrationVerification(user.getEmailID());
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.POST, value = "/register-userEmployeeRegistrationReject", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	// @PostMapping(value="/register-userRegistrationVerify")
	public void userEmployeeRegistrationVerificationRejection(@RequestBody UserRegistrationVerificationRequest user) {
		registerationService.userEmployeeRegistrationVerificationRejection(user.getEmailID());
	}

}