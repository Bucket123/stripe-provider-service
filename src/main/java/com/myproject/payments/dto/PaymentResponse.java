package com.myproject.payments.dto;

import lombok.Data;

@Data
public class PaymentResponse {
	String stripeSessionId;
	String hostedPageUrl;

}
