package com.tcs.poc.app.model;

import lombok.Data;

@Data
public class UserForgotPasswordQuestionRequest {
	private String emailID;
	private String securityQuestion;
	private String answer;
	private String password;
}
