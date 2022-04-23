package com.sunil.customer.laundrySystem;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class Test {

	@GetMapping("/lms")
	public String helloTest() {
		return "Welcome to LMS";
	}
	
	@GetMapping("/laundrySystem")
	public String helloLaundrySystemTest() {
		return "Welcome to LMS Server";
	}

}
