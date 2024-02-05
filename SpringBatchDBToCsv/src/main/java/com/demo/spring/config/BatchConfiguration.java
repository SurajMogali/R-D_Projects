package com.demo.spring.config;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.RowMapper;

import com.demo.spring.entity.Student;
import com.demo.spring.processor.StuProcessor;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	@Autowired
	private StepBuilderFactory stepBuilderFactory; //create the instances of step 

	@Autowired
	private JobBuilderFactory jobBuilderFactory; // create the instance of job

	@Autowired
	public DataSource dataSource;

	@Bean
	public JdbcCursorItemReader<Student> reader() {
		JdbcCursorItemReader<Student> reader = new JdbcCursorItemReader<Student>();
		reader.setDataSource(dataSource);
		reader.setSql("select id,firstName,lastName,email from csvdbdata");
		reader.setRowMapper(new RowMapper<Student>() {

			@Override
			public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
				Student s = new Student();
				s.setId(rs.getInt("id"));
				s.setFirstName(rs.getString("firstName"));
				s.setLastName(rs.getString("lastName"));
				s.setEmail(rs.getString("email"));
				return s;
			}
		});

		return reader;

	}

	@Bean
	public FlatFileItemWriter<Student> writer() {
		FlatFileItemWriter<Student> writer = new FlatFileItemWriter<Student>();
		writer.setResource(new FileSystemResource("D://studentdata.csv"));
		DelimitedLineAggregator<Student> aggregator = new DelimitedLineAggregator<>();
		BeanWrapperFieldExtractor<Student> fieldExtractor = new BeanWrapperFieldExtractor<>();
		fieldExtractor.setNames(new String[] { "id", "firstName", "lastName", "email" });
		aggregator.setFieldExtractor(fieldExtractor);
		writer.setHeaderCallback(writer1-> writer1.write("id,firstName,lastName,email"));
		writer.setLineAggregator(aggregator);
		return writer;

	}

	@Bean
	public Step executeStep() {
		return stepBuilderFactory.get("executeStep").<Student, Student>chunk(10).reader(reader()).processor(new StuProcessor()).writer(writer())
				.build();
	}

	@Bean
	public Job processJob()

	{
		return jobBuilderFactory.get("processJob")
				.incrementer(new RunIdIncrementer())  // Incrementing the run id to make each execution unique
				.flow(executeStep()) //Defining a flow that includes a step named "executeStep"
				.end() 
				.build();

	}
	
	
	
	

}
