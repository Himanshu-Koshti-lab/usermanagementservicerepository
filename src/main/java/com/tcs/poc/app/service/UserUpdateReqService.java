package com.tcs.poc.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.poc.app.entity.User;
import com.tcs.poc.app.entity.UserUpdateRequest;
import com.tcs.poc.app.entity.UserUpdateRequestStatus;
import com.tcs.poc.app.repository.UserRepository;
import com.tcs.poc.app.repository.UserUpdateReqRepository;
import com.tcs.poc.app.repository.UserUpdateRequestStatusRepo;

@Service
public class UserUpdateReqService {

	UserUpdateReqService service;

	@Autowired
	public UserUpdateReqRepository repository;

	@Autowired
	public UserRepository userRepo;

	@Autowired
	public UserUpdateRequestStatusRepo StatusRepo;

	public UserUpdateRequest saveUserRequest(UserUpdateRequest request) throws Exception {
		User temp = userRepo.findByEmailID(request.getEmailID());
		if (temp == null) {
			throw new Exception("User not found");
		} else {
			UserUpdateRequestStatus status = StatusRepo.findById(1);
			request.setUserRequestStatus(status);
			return repository.save(request);
		}
	}

	public List<UserUpdateRequest> getUserRequest() {
		return repository.findAll();
	}

	public void UpdateRequestApproval(UserUpdateRequest request) {
		UserUpdateRequest temp2 = repository.findByEmailID(request.getEmailID());
		User temp1 = userRepo.findByEmailID(request.getEmailID());
		if (temp2.getEmailID().equals(temp1.getEmailID()) && temp2.getUserRequestStatus().getId() == 1) {
			UserUpdateRequestStatus status = StatusRepo.findById(2);
			request.setUserRequestStatus(status);
			repository.save(request);
			temp1.setMobileNo(temp2.getNewMobileNo());
			userRepo.save(temp1);
		}
	}

	public void UpdateRequestReject(UserUpdateRequest request) {
		UserUpdateRequest temp2 = repository.findByEmailID(request.getEmailID());
		User temp1 = userRepo.findByEmailID(request.getEmailID());
		if (temp2.getEmailID().equals(temp1.getEmailID()) && temp2.getUserRequestStatus().getId() == 1) {
			UserUpdateRequestStatus status = StatusRepo.findById(3);
			request.setUserRequestStatus(status);
			repository.save(request);
		}
	}
}