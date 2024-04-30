package com.demo.spring.service;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PDFServiceImpl implements PDFService2 {

    @Override
    public String getAnswerFromPDF(String pdfFilePath, String word) throws Exception {
        // Logic to read PDF content
        String content = readPDFContent(pdfFilePath);

        if (content == null || content.isEmpty()) {
            throw new Exception("Error: Unable to read PDF content.");
        }

        // Simple search (replace with more sophisticated logic)
        if (content.toLowerCase().contains(word.toLowerCase())) {
            return "Found keyword: " + word;
        } else {
            return "Keyword not found in PDF.";
        }
    }

    private String readPDFContent(String pdfFilePath) throws IOException {
        File file = new File(pdfFilePath);
        PDDocument document = PDDocument.load(file);

        // Extract all text (can be modified to extract specific pages)
        PDFTextStripper stripper = new PDFTextStripper();
        String content = stripper.getText(document);

        // Close the document
        document.close();

        return content;
    }
}

