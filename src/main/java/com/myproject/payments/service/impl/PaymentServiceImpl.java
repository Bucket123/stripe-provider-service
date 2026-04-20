package com.myproject.payments.service.impl;

import org.springframework.stereotype.Service;

import com.myproject.payments.http.HttpServiceEngine;
import com.myproject.payments.service.interfaces.PaymentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
	
	private final HttpServiceEngine httpServiceEngine;
	
	
	@Override
	public String createPayment() {
		log.info("Creating payment...");
		String httpResponse = httpServiceEngine.makeHttpCall();
		log.info("getting response from http call: {}", httpResponse);
		return httpResponse;
	}

}
