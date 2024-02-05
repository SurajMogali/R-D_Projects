package com.demo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;



@Aspect
public class MyAspect {

    @Before("execution(* com.demo.service.PaymentServiceImpl.makePayment(..))")
    public  void printBefore()     {
    System.out.println("Payment started..");
    }

    @After("execution(* com.demo.service.PaymentServiceImpl.makePayment(..))")
    public  void printAfter()     {
        System.out.println("Payment done...");
    }










}
