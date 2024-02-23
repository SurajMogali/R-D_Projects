package com.demo.api.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.demo.api.repository.UserRepository;

public class UniqueEmailValidator implements ConstraintValidator<ValidateUniqueEmail, String> {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		
		//If the given email id is already in the list, then  return zero
		if(userRepository.findByEmail(value).size()==0)
		{ 
			return true;
			
		}
			
		
		return false;
	}
	

}
