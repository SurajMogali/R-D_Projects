package com.demo.api.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.demo.api.validation.ValidatePinCodeLength;
import com.demo.api.validation.ValidateUniqueEmail;
import com.demo.api.validation.ValidateUserType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "build")  //This parameter instructs Lombok to generate a static factory method named build() for creating instances of the class.
@NoArgsConstructor
public class UserRequest {
    @NotNull(message = "username shouldn't be null")
    private String name;
  
    @Pattern(regexp = "^\\d{10}$",message = "invalid mobile number entered ")
    private String mobile;
    @NotNull(message = "gender shouldn't be null")
    private String gender;
    @Min(18)
    @Max(60)
    private int age;
    @NotBlank
    private String nationality;
    
    
    @Email(message = "invalid email address")
    @ValidateUniqueEmail
    private String email;
    
    
    
    //custom annotation
    @ValidateUserType                
    private String userType;          //permanent or vendor
    
    @ValidatePinCodeLength
    private String pincode;              //

}
