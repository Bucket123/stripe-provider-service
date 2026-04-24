package com.myproject.payments.http;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class HttpServiceEngine {
	
	private final RestClient restClient;
	
	public String makeHttpCall(HttpRequest httpRequest) {
		log.info("Making HTTP call...");
		
		ResponseEntity<String> httpResponse = restClient.method(httpRequest.getMethod())
			.uri(httpRequest.getUrl())
			.headers(
					httpHeaders -> httpHeaders.addAll(httpRequest.getHeaders())
				)
			.body(httpRequest.getRequestData())
			.retrieve()
			.toEntity(String.class);
		
		log.info("Http call response status code: {}", httpResponse.getStatusCode(), 
				"http call response body: {}", httpResponse.getBody());
		
		return "\n"+ httpResponse.getBody();
	}

}
