package com.tcs.poc.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.tcs.poc.app.model.UserStatusReq;
import com.tcs.poc.app.service.LoginService;

@RestController
public class LoginController {

	@Autowired
	private LoginService service;

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
	public int UserInfo(@RequestBody UserForgotPasswordOtpGenRequest user ){
		System.out.println(user.getEmailID());
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
}
