package com.tcs.poc.app.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tcs.poc.app.entity.User;
import com.tcs.poc.app.repository.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService{
	
	@Autowired
	 private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User userEntity = userRepo.findByEmailID(email);
		if(userEntity == null)
			throw new UsernameNotFoundException("User not Found " + email );
		if(userEntity.getRegistrationStatus().getRegistrationStatusId()==1)
		{
			throw new UsernameNotFoundException("User account not approved, pending for review " + email );
		}
		GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" +userEntity.getRole().getRoleName());
		return new org.springframework.security.core.userdetails.User(userEntity.getEmailID(),userEntity.getPassword(),Arrays.asList(grantedAuthority));
	}

}