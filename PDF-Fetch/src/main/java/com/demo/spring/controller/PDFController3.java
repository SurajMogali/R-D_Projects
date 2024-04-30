package com.demo.spring.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spring.service.PDFService3;

@RestController
public class PDFController3 {

    
    private PDFService3 pdfService3;

    @PostMapping("/upload-and-ask")
    public String uploadAndAsk(@RequestParam String filePath, @RequestParam String question) throws NullPointerException {
        try {
            pdfService3.storePDFContent(filePath);
            return pdfService3.answerQuestionFromPDF(question);
        } catch (IOException e) {
            return "Error uploading PDF: " + e.getMessage();
        } catch (Exception e) {
            return "Error answering question: " + e.getMessage();
        }
    }

}
