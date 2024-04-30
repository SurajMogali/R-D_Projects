package com.demo.spring.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.api.GenerateContentResponse;
import com.google.cloud.vertexai.generativeai.ContentMaker;
import com.google.cloud.vertexai.generativeai.GenerativeModel;


@Service
public class VertexAiService {
	
	public String showOutput() throws IOException
	{
		String projectId = "abstract-bongo-418310";
	    String location = "us-central1";
	    String modelName = "gemini-pro-vision";

	    String output = quickstart(projectId, location, modelName);
	    return (output);
		
	}
	   
	  

	  // Analyzes the provided Multimodal input.
	  public static String quickstart(String projectId, String location, String modelName)
	      throws IOException {
	    // Initialize client that will be used to send requests. This client only needs
	    // to be created once, and can be reused for multiple requests.
	    try (VertexAI vertexAI = new VertexAI(projectId, location)) {
	     

	      GenerativeModel model = new GenerativeModel(modelName, vertexAI);
	      GenerateContentResponse response = model.generateContent(ContentMaker.fromMultiModalData(
	          "What is Collectbot?"
	      ));

	      return response.toString();
	    }
	  }

}
