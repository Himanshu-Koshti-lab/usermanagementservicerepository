package com.tcs.poc.app.service;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tcs.poc.app.entity.User;
import com.tcs.poc.app.model.ChangePasswordRequest;
import com.tcs.poc.app.model.ChangePasswordResponse;
import com.tcs.poc.app.model.UserForgotPasswordOtpGenRequest;
import com.tcs.poc.app.model.UserForgotPasswordOtpValidationRequest;
import com.tcs.poc.app.model.UserForgotPasswordQuestionRequest;
import com.tcs.poc.app.model.UserStatusReq;
import com.tcs.poc.app.repository.UserRepository;

@Service
public class LoginService {

	@Autowired
	public UserRepository repository;
	public String DNF = "Data Not Found ";
	public int otp = 0;

	@Autowired
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	public int forgotPasswordByOtp(UserForgotPasswordOtpGenRequest user) throws Exception {
		String emailString = user.getEmailID();
		if (emailString == null || "".equals(emailString)) {
			throw new Exception("Please fill Email");
		} else {
			User temp = repository.findByEmailID(emailString);
			if (temp == null) {
				// throw new Exception(DNF);
				// User Not Found
				return 202;
			} else {
				boolean x = true;
				while (x) {
					otp = (int) (Math.random() * 10000);
					x = !(otp > 1000 && otp < 9999);
				}
				temp.setOTP(otp);
				repository.save(temp);
				return otp;
			}

		}
	}

	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;

	public boolean VerifyOtp(UserForgotPasswordOtpValidationRequest user) throws Exception {
		String emailString = user.getEmailID();
		System.out.println(user.getOtp());
		System.out.println(user.getEmailID());
		if (emailString == null || "".equals(emailString)) {
			throw new Exception("Please fill Email");
		} else {
			User ExistingUser = repository.findByEmailID(emailString);
			if (ExistingUser == null) {
				throw new Exception(DNF);
			} else {
				if (user.getOtp() == ExistingUser.getOTP()) {
					ExistingUser.setPassword(bcryptPasswordEncoder.encode(user.getPassword()));
					ExistingUser.setOTP(0);
					repository.save(ExistingUser);
					return true;
				}
			}
		}
		return false;
	}

	public boolean forgotPasswordByQuestion(UserForgotPasswordQuestionRequest user) throws Exception {
		String emailString = user.getEmailID();
		String Question = user.getSecurityQuestion();
		String Answer = user.getAnswer();
		if (emailString == null || "".equals(emailString) && Question == null || "".equals(Question) && Answer == null
				|| "".equals(Answer)) {
			throw new Exception("Please Enter all mandatory fields");
		} else {
			User DBUser = repository.findByEmailID(emailString);
			if (DBUser == null) {
				throw new Exception(DNF);
			} else {
				if (Question.equals(DBUser.getSecurityQuestion()) && Answer.equals(DBUser.getAnswer())) {
					System.out.println("Inside If and Updating Password");
					DBUser.setPassword(bcryptPasswordEncoder.encode(user.getPassword()));
					repository.save(DBUser);
					return true;
				} else {
					throw new Exception("Question And Answer Not Match");
				}
			}
		}
	}
	
	public ChangePasswordResponse changePassword(ChangePasswordRequest request , String emailID){
		User user = repository.findByEmailID(emailID);
		ChangePasswordResponse response = new ChangePasswordResponse();
		if(user == null) {
			response.setStatus(false);
			response.setMessage("User not found");
			return response;
		}else if(encoder.matches(request.getCurrentPassword(),user.getPassword())){
			if(encoder.matches(request.getNewPassword(),user.getPassword())) {
				//new password cant be same as previous one
				response.setStatus(false);
				response.setMessage("New Password Can Be Same As Previous One");
				return response;
			}else {
				user.setPassword(bcryptPasswordEncoder.encode(request.getNewPassword()));
				repository.save(user);			
				response.setStatus(true);
				response.setMessage("Update Password Success");
				return response;
			}
		}else {
			response.setStatus(false);
			response.setMessage("Our Input Current Password Doesnt Match In DataBase");
			return response;
		}		
	}

	public int UserInfo(UserForgotPasswordOtpGenRequest user) {
		User user1 = repository.findByEmailID(user.getEmailID());
		System.out.println(user1.getRegistrationStatus().getRegistrationStatusId());
		return user1.getRegistrationStatus().getRegistrationStatusId();
	}
}
