package com.demo.spring.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpCount {
    private int count;
    private int maxAge;
    private int minAge;
    private int sum;



}
