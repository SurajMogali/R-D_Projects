package com.demo.spring.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spring.service.PDFService;

@RestController
public class QAController {

    @Autowired
    private PDFService pdfService;

    @PostMapping("/answer")
    public ResponseEntity<String> getAnswer(@RequestHeader String question) {
        String answer = pdfService.getAnswerFromPDF("Docker.pdf", question);
        return ResponseEntity.ok(answer);
    }
}
