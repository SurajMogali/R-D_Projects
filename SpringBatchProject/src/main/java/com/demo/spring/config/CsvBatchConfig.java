package com.demo.spring.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import com.demo.spring.entity.Customer;
import com.demo.spring.repository.CustomerRepository;

import jakarta.transaction.Transaction;

@Configuration
//@EnableBatchProcessing
//@AllArgsConstructor
public class CsvBatchConfig {
	
	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private JobRepository jobRepository;   //manage the state of jobs
	
	@Autowired 
	private PlatformTransactionManager manager;
	
	
	
	
//	@Autowired
//	private StepBuilderFactory stepBuilderFactory;
//	
//	
//	private JobBuilderFactory jobBuilderFactory;
	
	
	@Bean
	public FlatFileItemReader<Customer> customerReader()
	{
		FlatFileItemReader<Customer> itemReader =new FlatFileItemReader<>();
		itemReader.setResource(new FileSystemResource("src/main/resources/customers.csv"));
		itemReader.setName("csv-reader");
		itemReader.setLinesToSkip(1);
		itemReader.setLineMapper(lineMapper());
		
		return itemReader;
		
		
	}

	private LineMapper<Customer> lineMapper() {
		DefaultLineMapper<Customer> lineMapper =new DefaultLineMapper<>();
		DelimitedLineTokenizer lineTokenizer=new DelimitedLineTokenizer();
		lineTokenizer.setDelimiter(",");
		lineTokenizer.setStrict(false);
		lineTokenizer.setNames("id","firstName","lastName","email","gender","contactNo","country","dob");
		
		
		BeanWrapperFieldSetMapper<Customer> fieldSetMapper=new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(Customer.class);
		
		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(fieldSetMapper);
		
		return lineMapper;
	}
	
	//create processor
	@Bean
	public CustomerProcessor customerProcessor()
	{
		return new CustomerProcessor();
		
	}
	
	//create writer
	@Bean
	public RepositoryItemWriter<Customer>  customerWriter()
	{
		RepositoryItemWriter<Customer> repositoryWriter =new RepositoryItemWriter<>();
		repositoryWriter.setRepository(customerRepo);
		repositoryWriter.setMethodName("save");
		return repositoryWriter;
		
		
	}
	
	//create step
	@Bean
	public Step step1()
	{
		return new StepBuilder("step-1",jobRepository).<Customer,Customer>chunk(10,manager)
				.reader(customerReader())
				.processor(customerProcessor())
				.writer(customerWriter())
				.taskExecutor(taskExecutor())
				.build();
				
	}
	
	//create job
	@Bean
	public Job job()
	{
		return new JobBuilder("customers-job",jobRepository)
				.flow(step1())
				.end()
				.build();
		
	}
	
	
	@Bean
	public TaskExecutor taskExecutor()
	{
		
		SimpleAsyncTaskExecutor asyncTaskExecutor=new SimpleAsyncTaskExecutor();
		asyncTaskExecutor.setConcurrencyLimit(10);
		return asyncTaskExecutor;
		
		
	}
	
     
	
	
	
	
	
	
	
	
	

}
