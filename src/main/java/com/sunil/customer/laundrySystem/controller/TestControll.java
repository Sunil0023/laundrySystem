package com.sunil.customer.laundrySystem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestControll {

	@GetMapping("/getName")
	public String getName() {
		
		String source="F:\\Office Projects\\AMM New CR\\sourceDir\\csvdata.csv";
		
		return source;
	}
}
