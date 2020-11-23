package com.tcs.poc.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.poc.app.entity.UserRegistrationStatus;


public interface UserRegistrationStatusRepository extends JpaRepository<UserRegistrationStatus, Integer> {

}
