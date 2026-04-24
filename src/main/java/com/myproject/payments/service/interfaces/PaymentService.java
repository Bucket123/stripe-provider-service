package com.myproject.payments.service.interfaces;

import com.myproject.payments.pojo.CreatePaymentReq;

public interface PaymentService {
	
	public String createPayment(CreatePaymentReq createPaymentReq);
}
