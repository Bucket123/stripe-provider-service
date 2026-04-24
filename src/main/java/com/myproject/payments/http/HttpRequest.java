package com.myproject.payments.http;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import lombok.Data;

@Data
public class HttpRequest {
	
	private HttpMethod method;
	private String url;
	private HttpHeaders headers;
	private Object requestData;
	
}
