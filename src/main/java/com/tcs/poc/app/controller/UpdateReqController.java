package com.tcs.poc.app.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.poc.app.entity.UserUpdateRequest;
import com.tcs.poc.app.service.UserUpdateReqService;

@RestController
/* @CrossOrigin(origins = "http://localhost:4200") */

public class UpdateReqController {

	@Autowired
	public UserUpdateReqService updateService;

	@PostMapping("/Request")
	public UserUpdateRequest addUserUpdateReq(@RequestBody UserUpdateRequest request) throws Exception {
		return updateService.saveUserRequest(request);
	}

	@GetMapping("/requestlist")
	public List<UserUpdateRequest> findAllRequests() {
		return updateService.getUserRequest();
	}

	@PostMapping("/RequestVerifyUpdate")
	public void UpdateRequestApproval(@RequestBody UserUpdateRequest request) {
		System.out.println(request.getEmailID());
		updateService.UpdateRequestApproval(request);
	}

	@PostMapping("/RequestReject")
	public void UpdateRequestReject(@RequestBody UserUpdateRequest request) {
		System.out.println(request.getEmailID());
		updateService.UpdateRequestReject(request);
	}
}

