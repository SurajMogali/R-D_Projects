package com.demo.api.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.demo.api.dto.UserRequest;
import com.demo.api.entity.User;

public class UserPinCodeLengthValidator implements ConstraintValidator<ValidatePinCodeLength, String> {

	
	
	UserRequest userRequest;

	@Override
	public boolean isValid(String pincode, ConstraintValidatorContext context) {
		
		
	    if (pincode.length()==5)
		{
			return true;
			
		}
		return false;
		
			
		
		
		
	}

}
