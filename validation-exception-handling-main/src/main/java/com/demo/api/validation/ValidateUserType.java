package com.demo.api.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.FIELD,ElementType.PARAMETER})  //where u want to use it exactly
//It specifies that the annotated annotation should be retained by the Java Virtual Machine (JVM) so that it can be accessed at runtime via reflection
@Retention(RetentionPolicy.RUNTIME)     //At what time u want to execute 
@Constraint(validatedBy=UserTypeValidator.class)   //where the validation logic is written
public @interface ValidateUserType {
	
	public String message() default "Invalid user Type:It should either Permanent or Vendor";
	
	
	//These methods allow users of the @NotNull annotation to specify custom validation groups 
	//and payloads when using this constraint. 
	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
	

}
