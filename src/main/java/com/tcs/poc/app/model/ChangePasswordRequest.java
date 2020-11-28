package com.tcs.poc.app.model;

import lombok.Data;

@Data
public class ChangePasswordRequest {
	private String emailID;
	private String currentPassword;
	private String newPassword;
}
