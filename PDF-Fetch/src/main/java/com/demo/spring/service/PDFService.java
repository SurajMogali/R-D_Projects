package com.demo.spring.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.InputStream;

@Service
public class PDFService {

	public String getAnswerFromPDF(String pdfFilePath, String question) {
		try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(pdfFilePath)) {
			PDDocument document = PDDocument.load(inputStream);
			PDFTextStripper stripper = new PDFTextStripper();
			String text = stripper.getText(document);
			document.close();

			// Logic to extract answer based on the question
			String answer = extractAnswer(text, question);

			return answer;
		} catch (IOException e) {
			e.printStackTrace();
			return "Error: Unable to read PDF file.";
		}
	}

	private String extractAnswer(String text, String question) {
		// Example logic: find the question in the text and return the next few
		// sentences as the answer
		int questionIndex = text.indexOf(question);
		if (questionIndex != -1) {
			// Find the end of the sentence
			int endIndex = text.indexOf(".", questionIndex);
			if (endIndex != -1) {
				// Extract the sentence as the answer
				return text.substring(questionIndex, endIndex + 1);
			}
		}

		return "Answer not found.";
	}
}
