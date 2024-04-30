package com.demo.spring.assistant;

import com.demo.spring.entity.Person;

import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

public interface PersonExtractor {

	    @UserMessage("Extract information about a person from {{myData}}")
	    Person extractPersonFrom(@V("myData") String text);
	}