package com.myproject.payments.pojo;

import java.util.List;

import lombok.Data;

@Data
public class CreatePaymentReq {
	
	List<LineItem> lineItems;
}
