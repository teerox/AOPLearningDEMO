package com.example.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class MyCloudLogAspect {

    // this is where we add all of our related advices for logging

    /*
        The methods below show how to use pointcut expressions
        using the @Before advice
     */

    @Before("com.example.aopdemo.aspect.MyDemoAspect.forDaoPackageNoGetterSetter()")
    public void logToCloudAsync() {
        System.out.println("\n=====>>> Logging to Cloud in async fashion");
    }
}
