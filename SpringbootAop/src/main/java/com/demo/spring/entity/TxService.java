package com.demo.spring.entity;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TxService {
	//pointcut
	@Pointcut("execution(public void com.demo.spring.dao.EmployeeDao.saveEmployee())")
	public void p1(){}
	
	@Pointcut("execution(* com.demo.spring.dao.EmployeeDao.*())")
	public void p2(){}
	
	//before 
	@Before("p1()")      //joinpoint
	public void beginTx()
	{
		System.out.println("Tx started...");
	}
	
	
	//after
	@After("p1()")
	public void sendReport()
	{
		System.out.println("Report sent!");
	}
	
	
	//afterreturning - only after success of business class
	@AfterReturning("p1()")
	public void commitTx()
	{
		System.out.println("Tx is committed");
	}
	
	
	@AfterThrowing(value= "p1()",throwing="th")
	public void rollbackTx(Throwable th)
	{
		System.out.println("Tx is rollback "+th.getMessage());
	} 
	
	@Around("p1()")
	public void aroundTest(ProceedingJoinPoint jp)
	{
		System.out.println("Before b.method");
	
	    //code -->call: b.method
		try
		{
		//	Object ob=jp.proceed();  If it is non void method 
			jp.proceed();
			
		}catch(Throwable e)
		{
			e.printStackTrace();
		}
		
		//at any case  
		System.out.println("After b.method");
		
	}
	
	//Mapping through the annotation
	
	@Pointcut("@annotation(com.demo.spring.anno.MyTx)")
	public void p3(){}
	
	@Before("p3()")
	public void beginTx2()
	{
		System.out.println("Tx begin from annotation");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
     
	
	
	
	
	
	
	
	

}
