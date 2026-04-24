package com.myproject.payments.pojo;

import lombok.Data;

@Data
public class PriceData {
	private String currency;
    private ProductData productData;
    private long unitAmount;
}
