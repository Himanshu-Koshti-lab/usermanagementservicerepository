package com.tcs.poc.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcs.poc.app.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {


}