package com.demo.api.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.FIELD,ElementType.PARAMETER,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME) 
@Constraint(validatedBy=UniqueEmailValidator.class)
public @interface ValidateUniqueEmail {
	
public String message() default "Email should be unique";
	
	
	//These methods allow users of the @NotNull annotation to specify custom validation groups 
	//and payloads when using this constraint. 
	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
	
	

}
