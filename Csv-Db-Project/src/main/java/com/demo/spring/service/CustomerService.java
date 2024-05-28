package com.demo.spring.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.spring.entity.Customer;
import com.demo.spring.repository.CustomerRepository;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

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
				customerRepository.save(c);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	public void generateExcel(HttpServletResponse response) throws Exception {

		List<Customer> customers = customerRepository.findAll();

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("customers");
		HSSFRow row = sheet.createRow(0);

		row.createCell(0).setCellValue("name");
		row.createCell(1).setCellValue("issue");
		row.createCell(2).setCellValue("issueId");
		row.createCell(3).setCellValue("age");

		int dataRowIndex = 1;

		for (Customer customer : customers) {
			HSSFRow dataRow = sheet.createRow(dataRowIndex);
			dataRow.createCell(0).setCellValue(customer.getName());
			dataRow.createCell(1).setCellValue(customer.getIssue());
			dataRow.createCell(2).setCellValue(customer.getIssueId());
			dataRow.createCell(3).setCellValue(customer.getAge());
			
			
			dataRowIndex++;
		}

		ServletOutputStream ops = response.getOutputStream();
		workbook.write(ops);
		workbook.close();
		ops.close();

	}
	
	
}


