package com.tcs.poc.app.model;


import com.tcs.poc.app.entity.User;

import lombok.Data;

@Data
public class UserLoginResponse {
	private User user;
}
