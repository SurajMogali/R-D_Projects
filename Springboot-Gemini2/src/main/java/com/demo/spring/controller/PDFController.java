package com.demo.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spring.service.PDFService;

@RestController
public class PDFController {

	@Autowired
	private PDFService pdfService;

	@GetMapping("/upload-and-ask/{filePath}")
	public String uploadAndAsk(@PathVariable String filePath, @RequestParam String question )
			throws Exception {
		pdfService.storePDFContent(filePath);
		return pdfService.answerQuestionFromPDF(question);
	}
	
	
	@GetMapping("/answer")
	public String uploadAndAsk(@RequestParam String question) throws Exception {
		return pdfService.answerQuestionFromWebsite(question);
	}
	
	
	
	
	
	

}


