package com.demo.spring.service;

import java.util.Objects;


import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.demo.spring.entity.DBSequence;

@Service
public class SequenceGeneratorService {
	
	@Autowired 
	private MongoOperations mongoOperations;
	
	public int getSequenceNumber(String sequenceName)
	{
		//getSequenceNumber
		Query query =new Query(Criteria.where("id").is(sequenceName));
		
		//update sequence number 
		Update update =new Update().inc("seq",1);
		DBSequence counter=mongoOperations.findAndModify(query,update,options().returnNew(true).upsert(true),DBSequence.class);
		
		return !Objects.isNull(counter)?counter.getSeq(): 1;		
		
		
		
	}

}
