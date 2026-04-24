package com.myproject.payments.service.helper;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.myproject.payments.constant.Constant;
import com.myproject.payments.http.HttpRequest;
import com.myproject.payments.pojo.CreatePaymentReq;
import com.myproject.payments.pojo.LineItem;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreatePaymentHelper {
	
	@Value("${stripe.api.key}")
	private String stripeApiKey;
	
	@Value("${stripe.create.session.url}")
	private String stripeCreateSessionUrl;
	
	public HttpRequest prepareStripeCreateSessionRequest(CreatePaymentReq createPaymentReq) {
		HttpHeaders httpheaders = new HttpHeaders();
		
		httpheaders.setBasicAuth(stripeApiKey, "");
		httpheaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> formdata = new LinkedMultiValueMap<>();

		formdata.add(Constant.CREATE_SESSION_MODE, "payment");
		formdata.add(Constant.CREATE_SESSION_SUCCESS_URL, "https://example.com/success");
		formdata.add(Constant.CREATE_SESSION_CANCEL_URL, "https://example.com/cancel");
		
//		formdata.add("line_items[0][price_data][currency]", "EUR");
//		formdata.add("line_items[0][quantity]", "2");
//		formdata.add("line_items[0][price_data][product_data][name]", "Phone xxx");
//		formdata.add("line_items[0][price_data][unit_amount]", "100");
		
		List<LineItem> lineItems = createPaymentReq.getLineItems();
		for(int i=0; i<lineItems.size(); i++)
		{
			LineItem item = lineItems.get(i);
			String prefix = "line_items[" + i + "]";
			formdata.add(prefix + "[price_data][currency]", item.getPriceData().getCurrency());
			formdata.add(prefix + "[quantity]", String.valueOf(item.getQuantity()));
			formdata.add(prefix + "[price_data][product_data][name]", item.getPriceData().getProductData().getName());
			formdata.add(prefix + "[price_data][unit_amount]", String.valueOf(item.getPriceData().getUnitAmount()));
			
		}

		HttpRequest httpRequest = new HttpRequest();
		httpRequest.setMethod(HttpMethod.POST);
		httpRequest.setUrl(stripeCreateSessionUrl);
		httpRequest.setHeaders(httpheaders);
		httpRequest.setRequestData(formdata);

		return httpRequest;
	}

}
