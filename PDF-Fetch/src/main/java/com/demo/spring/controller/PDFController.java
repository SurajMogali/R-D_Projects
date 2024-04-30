package com.demo.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spring.service.PDFService;

@RestController
public class PDFController {

    @Autowired
    private PDFService pdfService;

    @GetMapping("/find-in-pdf/{filePath}")
    public String findInPDF(@PathVariable String filePath, @RequestParam String word) throws Exception {
        return pdfService.getAnswerFromPDF(filePath, word);
    }
}