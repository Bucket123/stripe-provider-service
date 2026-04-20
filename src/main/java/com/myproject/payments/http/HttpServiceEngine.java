package com.myproject.payments.http;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class HttpServiceEngine {
	
	private final RestClient restClient;
	
	public String makeHttpCall() {
		log.info("Making HTTP call...");
		
		HttpHeaders httpheaders = new HttpHeaders();
		
		httpheaders.setBasicAuth("sk_test_51SwtfvHn9bdLVpFChwSdlDNpmHWv4KRlhcnCtMG74DgtHxAe1FbPfXhm3HDxiCnnLK7L99C1SED65V7HSzR8AWKx00Yb8eSvrY","");
		httpheaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		MultiValueMap<String, String> formdata = new LinkedMultiValueMap<>();

		formdata.add("line_items[0][price_data][currency]", "EUR");
		formdata.add("line_items[0][quantity]", "2");
		formdata.add("mode", "payment");
		formdata.add("success_url", "https://example.com/success");
		formdata.add("line_items[0][price_data][product_data][name]", "Phone xxx");
		formdata.add("line_items[0][price_data][unit_amount]", "100");
        
		ResponseEntity<String> httpResponse = restClient.method(HttpMethod.POST)
			.uri("https://api.stripe.com/v1/checkout/sessions")
			.headers((HttpHeaders t)->{
				t.addAll(httpheaders);
			})
			.body(formdata)
			.retrieve()
			.toEntity(String.class);
		
		log.info("Http call response status code: {}", httpResponse.getStatusCode(), 
				"http call response body: {}", httpResponse.getBody());
		
		return "\n"+ httpResponse.getBody();
	}

}
