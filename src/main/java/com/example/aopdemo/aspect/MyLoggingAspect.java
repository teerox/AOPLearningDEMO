package com.example.aopdemo.aspect;

import com.example.aopdemo.model.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Aspect
@Component
@Order(2)
public class MyLoggingAspect {

    @Before("com.example.aopdemo.aspect.MyDemoAspect.forDaoPackageNoGetterSetter()")
    public void logging() {
        System.out.println("\n=====>>> Logs to the console");
    }

    /*
        The methods below show how to use pointcut expressions
        using the @Before advice
        also shows how to use pointcut expressions to match on any method in the dao package
        also using JoinPoints to get method signature
     */

    @Before("com.example.aopdemo.aspect.MyDemoAspect.forDaoPackageNoGetterSetter()")
    public void beforeAddAccount(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println("Method: " + methodSignature);

        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            System.out.println(arg);
            if (arg instanceof Account account) {
                System.out.println("Account name: " + account.getName());
                System.out.println("Account level: " + account.getLevel());
            }
        }
    }

    /*
    Using @AfterReturning advice
     */

    @AfterReturning(
            pointcut = "execution(* com.example.aopdemo.dao.AccountDAO.findAccounts(..))",
            returning = "result"
    )
    public void afterReturningFindAccounts(JoinPoint joinPoint, List<Account> result) {
        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @AfterReturning on method: " + method);
        System.out.println("\n=====>>> result is: " + result);

        convertAccountNamesToUpperCase(result);
        System.out.println("\n=====>>> result is: " + result);
//        if (Objects.nonNull(result)) {
//            System.out.println("\n=====>>> result is: " + result);
//        }
    }

    private void convertAccountNamesToUpperCase(List<Account> account) {
        for (Account acc : account) {
            acc.setName(acc.getName().toUpperCase());
        }
    }

    /*
    Using @AfterThrowing advice
     */
    @AfterThrowing(
            pointcut = "execution(* com.example.aopdemo.dao.AccountDAO.findAccounts(..))",
            throwing = "exception"
    )
    private void afterThrowingFindAccounts(JoinPoint joinPoint, Throwable exception) {
        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @AfterThrowing on method: " + method);
        System.out.println("\n=====>>> The exception is: " + exception);
    }

    /*
    Using @After advice
     */

    @After("execution(* com.example.aopdemo.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccounts(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @After (finally) on method: " + method);
    }

    /*
    Around advice
     */
    // @Around("execution(* com.example.aopdemo.dao.AccountDAO.findAccounts(..))")
    @Around("execution(* com.example.aopdemo.service.TrafficFortuneService.getFortune(..))")
    public Object aroundGetFortune(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String method = proceedingJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @Around on method: " + method);

        // get begin timestamp
        long begin = System.currentTimeMillis();

        Object result = null;
        try {
            // execute the method
            result = proceedingJoinPoint.proceed();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            //result = "Major accident! But no worries, your private helicopter is on the way!";

            // rethrow exception
            throw e;
        }

        // get end timestamp
        long end = System.currentTimeMillis();

        // compute duration and display it
        long duration = end - begin;
        System.out.println("\n=====>>> Duration: " + duration / 1000.0 + " seconds");

        // return the result
        return result;
    }
}
