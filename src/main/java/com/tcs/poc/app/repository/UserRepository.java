package com.tcs.poc.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcs.poc.app.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	User findByEmailID(String email);

	User findByMobileNo(long tempMobileNo);	
}