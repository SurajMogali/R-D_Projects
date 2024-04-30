package com.demo.spring.controller;

import java.io.BufferedReader;
import java.io.FileReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spring.entity.Transaction;
import com.demo.spring.repository.TransactionRepository;

@RestController
public class TransactionController {

	@Autowired
	TransactionRepository transactionRepository;

	String line = "";

	@PostMapping("/import")
	public void saveCustomerData() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("src/main/resources/Transaction.csv"));
			while ((line = br.readLine()) != null)

			{
				String[] data = line.split(",");
				Transaction t = new Transaction();
				t.setTransactionId(data[0]);
				t.setTransactionType(data[1]);
				t.setAccountBalance(data[2]);
				transactionRepository.save(t);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
