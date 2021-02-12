package com.tcs.poc.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.poc.app.entity.User;
import com.tcs.poc.app.model.ChangePasswordRequest;
import com.tcs.poc.app.model.ChangePasswordResponse;
import com.tcs.poc.app.model.UserForgotPasswordOtpGenRequest;
import com.tcs.poc.app.model.UserForgotPasswordOtpValidationRequest;
import com.tcs.poc.app.model.UserForgotPasswordQuestionRequest;
import com.tcs.poc.app.service.LoginService;
import com.tcs.poc.app.service.UserDetailServiceImpl;

@RestController
public class LoginController {

	@Autowired
	private LoginService service;
	
	@Autowired
	private UserDetailServiceImpl servicea;

	@PostMapping(value = "/forgotPasswordByOtp")
	public int forgotPasswordByOtp(@RequestBody UserForgotPasswordOtpGenRequest user) throws Exception {
		return service.forgotPasswordByOtp(user);
	}

	@PostMapping(value = "/VerifyOtp")
	public Boolean verfyOtp(@RequestBody UserForgotPasswordOtpValidationRequest user) throws Exception {
		return service.VerifyOtp(user);
	}

	@PutMapping(value = "/forgotPasswordByQuestion")
	public boolean forgotPasswordByQuestion(@RequestBody UserForgotPasswordQuestionRequest user) throws Exception {
		return service.forgotPasswordByQuestion(user);
	}
	
	
	@PostMapping(value = "/getuserStatus")
	public int userInfo(@RequestBody UserForgotPasswordOtpGenRequest user ){
		return service.UserInfo(user);
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE','ROLE_CUSTOMER')")
	@PutMapping(value = "/UpdatePassword")
	public ChangePasswordResponse updatePassword(@RequestBody ChangePasswordRequest request,
			@AuthenticationPrincipal String emailID) {
		ChangePasswordResponse response = new ChangePasswordResponse();
		//if (request.getEmailID().equals(emailID))
			return service.changePassword(request,emailID);
//		response.setStatus(false);
//		response.setMessage("Logged User Mismatch");
//		return response;
	}
	
	@GetMapping("/userinfo")
	public ResponseEntity<?> getSelfUser(@AuthenticationPrincipal String username) {
		ResponseEntity<?> response = null;
		response = new ResponseEntity<>(servicea.loadUserByUsername(username), HttpStatus.OK);
		return response;
	}
	
	@GetMapping(value = "/getAllUser")
	public List<User> getAllUsers(){
		return service.getAllUsers();
	}
	
}
