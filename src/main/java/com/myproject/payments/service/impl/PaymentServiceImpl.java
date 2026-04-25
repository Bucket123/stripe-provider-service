package com.myproject.payments.service.impl;

import org.springframework.stereotype.Service;

import com.myproject.payments.http.HttpRequest;
import com.myproject.payments.http.HttpServiceEngine;
import com.myproject.payments.pojo.CreatePaymentReq;
import com.myproject.payments.service.helper.CreatePaymentHelper;
import com.myproject.payments.service.interfaces.PaymentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

	private final HttpServiceEngine httpServiceEngine;
	private final CreatePaymentHelper createPaymentHelper;

	@Override
	public String createPayment(CreatePaymentReq createPaymentReq) {
		log.info("Creating payment request createPaymentReq: {}", createPaymentReq);
		
		HttpRequest httpRequest = createPaymentHelper.prepareStripeCreateSessionRequest(createPaymentReq);
		
		String httpResponse = httpServiceEngine.makeHttpCall(httpRequest);
		
		log.info("getting response from http call httpResponse: {}", httpResponse);
		return httpResponse;
	}

}
