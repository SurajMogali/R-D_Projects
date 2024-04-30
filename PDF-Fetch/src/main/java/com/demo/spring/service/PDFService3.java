package com.demo.spring.service;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.json.JSONObject;

public class PDFService3 {

    private String pdfContent; // Store the entire PDF content as a string

    public void storePDFContent(String pdfFilePath) throws IOException {
        File file = new File(pdfFilePath);
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

        // Call LLM API with question and the stored PDF content (replace with actual call)
        return callLLM(question, pdfContent);
    }

    // Replace with actual LLM API call and logic (e.g., OpenAI)
 // Replace with actual LLM API call and logic (e.g., OpenAI)
    private String callLLM(String question, String pdfContent) throws IOException, InterruptedException {
        // Configure prompt to answer the question based on the PDF content
        String prompt = "Answer the question: '" + question + "' based on the provided PDF content.\n" + pdfContent;
        
        String apikey="AIzaSyAoWY0i8qWcOxatt3JarAWyRIcylDQpdFA";
        String apiUrl = "https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent?key=" + apikey;

        String apiKey = Base64.getEncoder().encodeToString((apikey + ":").getBytes(StandardCharsets.UTF_8));
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent?key="))
                .POST(HttpRequest.BodyPublishers.ofString(apiUrl))
                .setHeader("Authorization", "Basic " + apiKey)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IOException("Error: LLM API call failed with status code: " + response.statusCode());
        }

        JSONObject jsonResponse = new JSONObject(response.body());
        List<Object> choices = jsonResponse.getJSONArray("choices").toList();
        String answer = ((JSONObject) choices.get(0)).getString("text").trim();  // Assuming the first choice contains the answer

        return answer;
    }

}
