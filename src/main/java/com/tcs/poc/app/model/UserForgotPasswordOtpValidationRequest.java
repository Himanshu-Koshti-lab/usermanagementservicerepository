package com.tcs.poc.app.model;

import lombok.Data;

@Data
public class UserForgotPasswordOtpValidationRequest {
	private String emailID;
	private int otp;
	private String password;
}
