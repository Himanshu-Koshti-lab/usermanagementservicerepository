package com.tcs.poc.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.poc.app.entity.UserUpdateRequest;
import com.tcs.poc.app.model.UpdateMobileRequest;
import com.tcs.poc.app.model.UpdateMobileResponse;
import com.tcs.poc.app.service.UpdateMobileNoService;

@RestController

public class UpdateMobileController {

	@Autowired
	public UpdateMobileNoService service;

	@PreAuthorize("hasAnyRole('ROLE_CUSTOMER','ROLE_EMPLOYEE','ROLE_ADMIN')")
	@PostMapping(value = "/MobileUpdateRequest")
	public UpdateMobileResponse addUserUpdateReq(@RequestBody UserUpdateRequest request,
			@AuthenticationPrincipal String emailID) throws Exception {
		UpdateMobileResponse response = new UpdateMobileResponse();
		if (request.getEmailID().equals(emailID)) {
			return service.saveUserRequest(request);
		}else {
			response.setStatus(0);
			response.setMessage("Logged User not Match");
			return response;
		}
	}

	@PreAuthorize("hasAnyRole('ROLE_EMPLOYEE','ROLE_ADMIN')")
	@GetMapping(value = "/requestlist")
	public List<UserUpdateRequest> findAllRequests() {
		return service.getUserRequests();
	}

	@PreAuthorize("hasAnyRole('ROLE_EMPLOYEE','ROLE_ADMIN')")
	@PostMapping(value = "/UpdateMobileNoRequestApproved")
	public UpdateMobileResponse UpdateMobileNoApproved(@RequestBody UpdateMobileRequest request) {
		return service.UpdateMobileNoApproved(request);
	}

	@PreAuthorize("hasAnyRole('ROLE_EMPLOYEE','ROLE_ADMIN')")
	@PostMapping(value = "/UpdateMobileNoRequestReject")
	public UpdateMobileResponse UpdateMobileNoRejected(@RequestBody UpdateMobileRequest request) {
		return service.UpdateMobileNoRejected(request);
	}
}