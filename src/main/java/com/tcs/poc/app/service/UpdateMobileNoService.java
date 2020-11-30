package com.tcs.poc.app.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tcs.poc.app.entity.User;
import com.tcs.poc.app.entity.UserUpdateRequest;
import com.tcs.poc.app.entity.UserUpdateRequestStatus;
import com.tcs.poc.app.model.ChangePasswordResponse;
import com.tcs.poc.app.model.UpdateMobileRequest;
import com.tcs.poc.app.model.UpdateMobileResponse;
import com.tcs.poc.app.repository.UserRepository;
import com.tcs.poc.app.repository.UserUpdateReqRepository;
import com.tcs.poc.app.repository.UserUpdateRequestStatusRepo;

@Service
public class UpdateMobileNoService {

	@Autowired
	public UserUpdateReqRepository repository;

	@Autowired
	public UserRepository userRepo;

	@Autowired
	public UserUpdateRequestStatusRepo StatusRepo;

	public UpdateMobileResponse saveUserRequest(UserUpdateRequest request) {
		User temp = userRepo.findByEmailID(request.getEmailID());
		UpdateMobileResponse mobileResponse = new UpdateMobileResponse();
		UserUpdateRequest reqtable = repository.findByEmailID(request.getEmailID());
		if (temp == null) {
			mobileResponse.setStatus(0);
			mobileResponse.setMessage("Request not Submited Due to User Not Found with this EmailID");
			return mobileResponse;
		} else if (reqtable != null && reqtable.getUserRequestStatus().getId() == 1) {
			mobileResponse.setStatus(0);
			mobileResponse.setMessage("One Request Already Submited for this EmailID Pending for Approval");
			return mobileResponse;
		} else {
			UserUpdateRequestStatus status = StatusRepo.findById(1);
			request.setUser_id(temp.getId());
			request.setUserRequestStatus(status);
			repository.save(request);
			mobileResponse.setStatus(1);
			mobileResponse.setMessage("Request submited for Approval");
			return mobileResponse;
		}

//		if (temp == null) {
//			mobileResponse.setStatus(0);
//			mobileResponse.setMessage("Request not Submited Due to User Not Found with this EmailID");
//			return mobileResponse;
//		} else if (reqtable.getEmailID().equals(request.getEmailID()) && reqtable != null){
//			mobileResponse.setStatus(0);
//			mobileResponse.setMessage("One Request Already Submited for this EmailID Pending for Approval");
//			return mobileResponse;
//		}
//

	}

	public List<UserUpdateRequest> getUserRequests() {
		return repository.findAll();
	}

	public UpdateMobileResponse UpdateMobileNoApproved(UpdateMobileRequest request) {
		List<UserUpdateRequest> reqtable1 = repository.findAll();
		UserUpdateRequest reqtable = new UserUpdateRequest();
		UpdateMobileResponse response = new UpdateMobileResponse();
		System.out.println(reqtable1.size());
		for(int i=0;i<reqtable1.size();i++) {
			if(reqtable1.get(i).getUserRequestStatus().getId() == 1 && reqtable1.get(i).getEmailID().equals(request.getEmailID())) {
				reqtable = reqtable1.get(i);
			}
		}
		User maintable = userRepo.findByEmailID(request.getEmailID());
		if(reqtable == null) {
			response.setStatus(0);
			response.setMessage("User not Found in Request Table");
			return response;
		}
		if (reqtable.getEmailID().equals(maintable.getEmailID())) {
			UserUpdateRequestStatus status = StatusRepo.findById(2);
			reqtable.setUserRequestStatus(status);
			repository.save(reqtable);
			maintable.setMobileNo(reqtable.getNewmobileNo());
			userRepo.save(maintable);
			response.setStatus(1);
			response.setMessage("Mobile Number Updated Request Approved");
			return response;
		}else {
			response.setStatus(0);
			response.setMessage("User not Found in Main Table");
			return response;
		}
		

	}

	public UpdateMobileResponse UpdateMobileNoRejected(UpdateMobileRequest request) {
		List<UserUpdateRequest> reqtable1 = repository.findAll();
		UserUpdateRequest reqtable = new UserUpdateRequest();
		UpdateMobileResponse response = new UpdateMobileResponse();
		for(int i=0;i<reqtable1.size();i++) {
			if(reqtable1.get(i).getUserRequestStatus().getId() == 1 && reqtable1.get(i).getEmailID().equals(request.getEmailID())) {
				reqtable =  reqtable1.get(i);
			}
		}
		User maintable = userRepo.findByEmailID(request.getEmailID());
		if(reqtable == null) {
			response.setStatus(0);
			response.setMessage("User not Found in Request Table");
			return response;
		}
		if (reqtable.getEmailID().equals(maintable.getEmailID()) ) {
			UserUpdateRequestStatus status = StatusRepo.findById(3);
			reqtable.setUserRequestStatus(status);
			repository.save(reqtable);
			response.setStatus(1);
			response.setMessage("Mobile Number Update Request Rejected");
			return response;
		}else {
			response.setStatus(0);
			response.setMessage("User not Found in Main Table");
			return response;
		}
		
	}
}
