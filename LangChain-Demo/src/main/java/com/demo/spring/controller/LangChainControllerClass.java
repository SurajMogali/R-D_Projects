package com.demo.spring.controller;

import com.demo.spring.entity.Person;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.model.output.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spring.service.LangChainServiceClass;

@RestController
public class LangChainControllerClass {

	@Autowired
	LangChainServiceClass langChainServiceClass;

	@GetMapping("/generateAnswer")
	public String generateAnswer() {
		return langChainServiceClass.generateAnswer();

	}

	@GetMapping("/chatMemory")
	public void chatMemory() {
		  langChainServiceClass.ChatMemoryExecution();

	}

	@GetMapping("/chatMemory2")
	public void chatMemory2() {
		langChainServiceClass.multipleChatMemory();

	}



	@GetMapping("/systemMessage")
	public String systemMessageGenerator() {
		return langChainServiceClass.SystemMessageGenerator();

	}

	@GetMapping("/userMessage")
	public String userMessageGenerator() {
		return langChainServiceClass.userMessageGenerator();

	}
	
	
	@GetMapping("/objectFetch")
	public Person objectFetcher() {
		return langChainServiceClass.objectFetcher();

	}




	//For Fetching from the PDF document with maximum 1000 tokens only ie Felix Story.pdf
	@GetMapping("/fetchFromDoc")
	public String fetchFromDocument() {
		 return langChainServiceClass.fetchFromPDFDocument();
	}

	//For Fetching from the .txt document  ie Neokred_Employees.txt
	@GetMapping("/fetchFromDoc2")
	public String fetchFromDocument2(@RequestParam String question) {
		 return langChainServiceClass.fetchfromDocument2(question);

	}

	//For Fetching from the .docx document ie Neokred_Employees.docx
	@GetMapping("/fetchFromDoc3")
	public String fetchFromDocument3(@RequestParam String question) {
		return langChainServiceClass.fetchfromDocument3(question);

	}


	//For Fetching from the PDF document ie Neokred_Details.pdf
	@GetMapping("/fetchFromDoc4")
	public String fetchFromDocument4() {
		return langChainServiceClass.fetchfromDocument4();

	}

	@GetMapping("/classifyAndFetch")
	public void classifyAndFetch() {
		langChainServiceClass.EmbeddingModelTextClassifier();

	}

	@GetMapping("/analyzeSentiment")
	public void analyzeSentiment() {
		langChainServiceClass.analyzeSentiment();

	}




	
	

}
