package com.tcs.poc.app.model;

import lombok.Data;

@Data
public class UserLoginRequest {
	private String emailID;
	private String password;
}
