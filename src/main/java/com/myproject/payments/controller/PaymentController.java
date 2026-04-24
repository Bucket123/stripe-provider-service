package com.myproject.payments.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.payments.pojo.CreatePaymentReq;
import com.myproject.payments.service.interfaces.PaymentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("v1/api/payments")
@Slf4j
@RequiredArgsConstructor
public class PaymentController {
	
	private final PaymentService paymentService;
	
	@PostMapping
	public String processPayment(@RequestBody CreatePaymentReq createPaymentReq) {
		log.info("Processing payment... at controller level");
		return paymentService.createPayment(createPaymentReq);
	}

}
