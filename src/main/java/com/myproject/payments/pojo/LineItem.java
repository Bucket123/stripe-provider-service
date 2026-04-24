package com.myproject.payments.pojo;

import lombok.Data;

@Data
public class LineItem {
	private PriceData priceData;
    private int quantity; 
}
