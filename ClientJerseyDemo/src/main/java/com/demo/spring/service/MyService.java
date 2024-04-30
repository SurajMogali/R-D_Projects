package com.demo.spring.service;

import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

import com.demo.spring.entity.PincodeResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

@Service
public class MyService {

	public String sendMessage() {
		Client client = Client.create();
		WebResource resource = client.resource("https://api.postalpincode.in/pincode/560041");

		ClientResponse response = resource.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON)
				.get(ClientResponse.class);
		String result = response.getEntity(String.class);
		return result;
	}
	
	
	public void objectFetch() throws JsonMappingException, JsonProcessingException
	{
		String json = "[{\"Message\":\"Number of pincode(s) found:2\",\"Status\":\"Success\",\"PostOffice\":[{\"Name\":\"Jayanagar\",\"Description\":null,\"BranchType\":\"Head Post Office\",\"DeliveryStatus\":\"Delivery\",\"Circle\":\"Karnataka\",\"District\":\"Bangalore\",\"Division\":\"Bangalore South\",\"Region\":\"Bangalore HQ\",\"Block\":\"Bangalore South\",\"State\":\"Karnataka\",\"Country\":\"India\",\"Pincode\":\"560041\"},{\"Name\":\"Tilaknagar (Bangalore)\",\"Description\":null,\"BranchType\":\"Sub Post Office\",\"DeliveryStatus\":\"Non-Delivery\",\"Circle\":\"Karnataka\",\"District\":\"Bangalore\",\"Division\":\"Bangalore South\",\"Region\":\"Bangalore HQ\",\"Block\":\"Bangalore South\",\"State\":\"Karnataka\",\"Country\":\"India\",\"Pincode\":\"560041\"}]}]";

	    ObjectMapper objectMapper = new ObjectMapper();
	    PincodeResponse[] responses = objectMapper.readValue(json, PincodeResponse[].class);

	    for (PincodeResponse response : responses) {
	        System.out.println(response);
	    }
		
	}
	
}
