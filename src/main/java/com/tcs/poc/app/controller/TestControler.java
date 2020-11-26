package com.tcs.poc.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestControler {

	@GetMapping("/login")
	public String test() {
		System.out.println("Success");
		return "Success";
	}
	
	@GetMapping("/Admin")
	public String testA() {
		System.out.println("Success");
		return "Admin Success";
	}
	
	@GetMapping("/Customer")
	public String testC() {
		System.out.println("Success");
		return "Customer Success";
	}
	
	@GetMapping("/Employee")
	public String testE() {
		System.out.println("Success");
		return "Employee Success";
	}
}