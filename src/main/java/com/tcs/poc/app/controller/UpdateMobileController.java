package com.tcs.poc.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.poc.app.entity.UserUpdateRequest;
import com.tcs.poc.app.model.ChangePasswordResponse;
import com.tcs.poc.app.model.UpdateMobileRequest;
import com.tcs.poc.app.model.UpdateMobileResponse;
import com.tcs.poc.app.service.UpdateMobileNoService;

@RestController
/* @CrossOrigin(origins = "http://localhost:4200") */

public class UpdateMobileController {

	@Autowired
	public UpdateMobileNoService service;

	@PreAuthorize("hasRole('ROLE_CUSTOMER','ROLE_EMPLOYEE','ROLE_ADMIN')")
	@PostMapping(value = "/Request")
	public UpdateMobileResponse addUserUpdateReq(@RequestBody UserUpdateRequest request,
			@AuthenticationPrincipal String emailID) throws Exception {
		UpdateMobileResponse response = new UpdateMobileResponse();
		if (request.getEmailID().equals(emailID))
			return service.saveUserRequest(request);
		response.setStatus(0);
		response.setMessage("Logged User not Match");
		return response;

	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(value = "/requestlist")
	public List<UserUpdateRequest> findAllRequests() {
		return service.getUserRequests();
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping(value = "/UpdateMobileNoRequestApproved")
	public UpdateMobileResponse UpdateMobileNoApproved(@RequestBody UpdateMobileRequest request) {
		System.out.println(request.getEmailID());
		return service.UpdateMobileNoApproved(request);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping(value = "/UpdateMobileNoRequestReject")
	public UpdateMobileResponse UpdateMobileNoRejected(@RequestBody UpdateMobileRequest request) {
		System.out.println(request.getEmailID());
		return service.UpdateMobileNoRejected(request);
	}
}