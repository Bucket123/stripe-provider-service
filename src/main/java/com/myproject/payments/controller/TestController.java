package com.myproject.payments.controller;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestController {
	
	public String myMethod() {
		log.info("This is a test log message from TestController.");
		
		return "Hello from TestController!"; 
	}

}
