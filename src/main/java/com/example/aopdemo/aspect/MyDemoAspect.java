package com.example.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

// Always narraow down the pointcut expression to the specific method you want to target always use package name
// for the class you want to target
// this is the aspect class
// @Aspect is a marker for spring to tell that this is an aspect
// @Component is a spring component

// * com.example.aopdemo.dao.*.*(..))" is the pointcut expression
// * is the return type
// com.example.aopdemo.dao is the package name
// * is the class name
// * is the method name
// (..) is the parameter list
// so this pointcut expression will match on any method in the dao package

@Aspect
public class MyDemoAspect {

    // this is where we add all of our related advices for logging

    /*
        The methods below show how to use pointcut expressions
        using the @Before advice
     */

//// match on any addAccount() method
//     @Before("execution(public void  addAccount())")
//     public void beforeAddAccountAdvice() {
//         System.out.println("\n=====>>> Executing @Before advice on addAccount()");
//     }

    // matches on any add method with any return type and any number of arguments
//        @Before("execution(* add*(com.example.aopdemo.model.Account,..))")
//        public void beforeAddAccountAdvice() {
//            System.out.println("\n=====>>> Executing @Before advice on addAccount()");
//        }

   // matches on any add account method with return type void and any number of arguments
//    @Before("execution(public void  addAccount(*))")
//    public void beforeAddAccountAdvice() {
//        System.out.println("\n=====>>> Executing @Before advice on addAccount()");
//    }


    // matches on any add method with any return type and any number of arguments
    // this is important to note that the package name is com.example
//    @Before("execution(* com.example..add* (..))")
//    public void beforeAddAccountAdvice() {
//        System.out.println("\n=====>>> Executing @Before advice on addAccount()");
//    }

    // matches on any add method with any return type and any number of arguments
//    @Before("execution(* add*(..))")
//    public void beforeAddAccountAdvice() {
//        System.out.println("\n=====>>> Executing @Before advice on addAccount()");
//    }

    // lets target a new package
    // matches on any add method with any return type and any number of arguments
//    @Before("execution(* com.example.aopdemo.dao.*.*(..))")
//    public void beforeAddAccountAdvice() {
//        System.out.println("\n=====>>> Executing @Before advice on addAccount()");
//    }


    /*
        The methods below show how to use pointcut declarations
        using the @Before advice
        using the @Pointcut declaration
     */

    // creating a pointcut declaration
    @Pointcut("execution(* com.example.aopdemo.dao.*.*(..))")
    public void forDaoPackage() { }

    @Pointcut("execution(* com.example.aopdemo.dao.*.get*(..))")
    public void getter() { }

    @Pointcut("execution(* com.example.aopdemo.dao.*.set*(..))")
    public void setter() { }

    // exclude getter/setter methods
    @Pointcut("forDaoPackage() && !(getter() || setter())")
    public void forDaoPackageNoGetterSetter() { }

}
