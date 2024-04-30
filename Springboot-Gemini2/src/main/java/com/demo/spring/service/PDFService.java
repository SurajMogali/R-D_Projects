package com.demo.spring.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PDFService {

	private String pdfContent; // Store the entire PDF content as a string

	public void storePDFContent(String filePath) throws IOException {
		File file = new File("D:\\workspace1\\Springboot-Gemini2\\src\\main\\resources\\Girl_Story.pdf");
		PDDocument document = PDDocument.load(file);

		// Extract all text
		PDFTextStripper stripper = new PDFTextStripper();
		pdfContent = stripper.getText(document);

		// Close the document
		document.close();
	}

	public String answerQuestionFromPDF(String question) throws Exception {
		if (pdfContent == null || pdfContent.isEmpty()) {
			throw new Exception("Error: No PDF content loaded.");
		}
		String pdfTest = extractContentForPath();
		// Call Gemini API with question and the stored PDF content (replace with actual
		// call)
		return callGemini2(question, pdfTest);
	}

//	private String callGemini(String question, String pdfContent) throws Exception {
//		// Error Handling
//		if (question == null || question.isEmpty()) {
//			throw new IllegalArgumentException("Question cannot be null or empty.");
//		}
//		if (pdfContent == null || pdfContent.isEmpty()) {
//			throw new Exception("Error: No PDF content loaded.");
//		}
//
//		// API Integration
//		String apiKey = "AIzaSyAoWY0i8qWcOxatt3JarAWyRIcylDQpdFA"; // Replace with your actual API key
//		String apiUrl = "https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent?key="
//				+ apiKey;
//
//		URL url = new URL(apiUrl);
//		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//		conn.setRequestMethod("POST");
//		conn.setRequestProperty("Content-Type", "application/json");
//		conn.setDoOutput(true);
//
//		String requestBody = "{\"contents\":[{\"parts\":[{\"text\":\"" + "Hello" + "\"}]}]}";
//		System.out.println("requestBody = " + requestBody);
//
//		try (OutputStream os = conn.getOutputStream()) {
//			byte[] input = requestBody.getBytes(StandardCharsets.UTF_8);
//			os.write(input, 0, input.length);
//		}
//
//		StringBuilder response = new StringBuilder();
//		try (InputStream inputStream = conn.getInputStream();
//				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
//			String line;
//			while ((line = reader.readLine()) != null) {
//				response.append(line);
//			}
//		} catch (IOException e) {
//			// Handle error reading the response
//			e.printStackTrace();
//		}
//
//		conn.disconnect();
//		return response.toString();
//	}

	public String extractContentForPath() {
		String text;
		try (final PDDocument document = PDDocument
				.load(new File("D:\\workspace1\\Springboot-Gemini2\\src\\main\\resources\\Girl_Story.pdf"))) {
			final PDFTextStripper pdfStripper = new PDFTextStripper();
			text = pdfStripper.getText(document);
		} catch (final Exception ex) {
			text = "Error parsing PDF";
		}
		System.out.println("TEXT LENGTH OF PDF : " + text.length());
		return text;
	}
	
	String apiKey = "AIzaSyAoWY0i8qWcOxatt3JarAWyRIcylDQpdFA"; // Replace with your actual API key
	String apiUrl = "https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent?key="
			+ apiKey;

	private String callGemini2(String question, String pdfContent) throws Exception {
		// Error Handling
		if (question == null || question.isEmpty()) {
			throw new IllegalArgumentException("Question cannot be null or empty.");
		}
		if (pdfContent == null || pdfContent.isEmpty()) {
			throw new Exception("Error: No PDF content loaded.");
		}

		// API Integration
		

//		URL url = new URL(apiUrl);
//		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//		conn.setRequestMethod("POST");
//		conn.setRequestProperty("Content-Type", "application/json");
//		conn.setDoOutput(true);

		String requestBody = "{\"contents\":[{\"parts\":[{\"text\":\"" + pdfContent
				+ " considering the above details answer the following question :  " + question + "\"}]}]}";
		System.out.println("requestBody = " + requestBody);

		RestTemplate restTemplate = new RestTemplate();
		Object response = restTemplate.postForEntity(apiUrl, requestBody, String.class);

//		if (response.getStatusCode().is2xxSuccessful()) {
//			return response.getBody();
//		} else {
//			return "Error: Failed to generate answer";
//		}

		return response.toString();
	}
	
	private static final String GEMINI_API_KEY = "AIzaSyBDGusgeAaNiCpCS-G9W4Rg2x31kIaX9N8";

    private static final String GEMINI_ENDPOINT = "https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent?key=";

    // Function to call Gemini API with a question and website URL
    private String callGemini(String question, String websiteUrl) throws IOException {
        String apiUrl = GEMINI_ENDPOINT + GEMINI_API_KEY;

        // Prepare request body with question and website details
        String requestBody = "{\"contents\":[{\"parts\":[{\"text\":\"Considering the information available on " + websiteUrl + ", answer the following question: " + question + "\"}]}]}";

        URL url = new URL(apiUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        try (java.io.OutputStream os = conn.getOutputStream()) {
            byte[] input = requestBody.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        StringBuilder response = new StringBuilder();
        try (InputStream inputStream = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            // Handle error reading the response
            e.printStackTrace();
        }

        conn.disconnect();
        return response.toString();
    }

    public String answerQuestionFromWebsite(String question) throws Exception {
        if (question == null || question.isEmpty()) {
            throw new IllegalArgumentException("Question cannot be null or empty.");
        }

        String websiteUrl = "https://www.neokred.tech"; // Replace with the actual Neokred URL

        // Call Gemini API with question and website URL
        String geminiResponse = callGemini(question, websiteUrl);

        // Parse Gemini response to extract the answer (replace with actual logic based on Gemini's response format)
        String answer = extractAnswerFromGeminiResponse(geminiResponse);
        return answer;
    }
    
    private String extractAnswerFromGeminiResponse(String geminiResponse) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode responseNode = mapper.readTree(geminiResponse);
            String answer = responseNode.get("answer").asText();
            return answer;
        } catch (JsonProcessingException e) {
            // Handle JSON parsing errors
            return "Error parsing Gemini API response";
        }
    }



	
}
