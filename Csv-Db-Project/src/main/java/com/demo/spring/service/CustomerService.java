package com.demo.spring.service;

import java.io.BufferedReader;
import java.io.FileReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.spring.entity.Customer;
import com.demo.spring.repository.CustomerDTO;

@Service
public class CustomerService {

	@Autowired
	private CustomerDTO cdto;

	String line = "";

	public void saveCustomerData() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("src/main/resources/customer.csv"));
			while ((line = br.readLine()) != null)

			{
				String[] data = line.split(",");
				Customer c = new Customer();
				c.setName(data[0]);
				c.setIssue(data[1]);
				c.setIssueId(data[2]);
				c.setAge(data[3]);
				cdto.save(c);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
