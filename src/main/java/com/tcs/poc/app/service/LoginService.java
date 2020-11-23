package com.tcs.poc.app.service;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tcs.poc.app.entity.User;
import com.tcs.poc.app.model.UserForgotPasswordOtpGenRequest;
import com.tcs.poc.app.model.UserForgotPasswordOtpValidationRequest;
import com.tcs.poc.app.model.UserForgotPasswordQuestionRequest;
import com.tcs.poc.app.repository.UserRepository;



@Service
public class LoginService {
	
	@Autowired
	public UserRepository repository;
	public String DNF = "Data Not Found ";
	public int otp;

	@Autowired
	private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
	
	


	public boolean forgotPasswordByOtp(UserForgotPasswordOtpGenRequest user) throws Exception {
		String emailString = user.getEmailID();
		if (emailString == null || "".equals(emailString)) {
			throw new Exception("Please fill Email");
		} else {
			User temp = repository.findByEmailID(emailString);
			if (temp == null) {
				throw new Exception(DNF);
			} else if (temp.getEmailID() != null && !temp.getEmailID().isEmpty()) {
				otp = (int) (Math.random() * 10000);
				System.out.println(otp);
				return true;
			}
			return false;
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
				if (user.getOtp() == otp) {
					ExistingUser.setPassword(bcryptPasswordEncoder.encode(user.getPassword()));
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
				}else {
					throw new Exception("Question And Answer Not Match");
				}
			}
		}
	}
}
