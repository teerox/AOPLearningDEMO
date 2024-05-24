package com.example.aopdemo;

import com.example.aopdemo.dao.AccountDAO;
import com.example.aopdemo.dao.MembershipDAO;
import com.example.aopdemo.model.Account;
import com.example.aopdemo.service.TrafficFortuneService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class AopdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AopdemoApplication.class, args);
    }


    // command line runner
    @Bean
    public CommandLineRunner commandLineRunner(AccountDAO accountDAO,
                                               MembershipDAO membershipDAO,
                                               TrafficFortuneService trafficFortuneService) {
        return args -> {
            //demoTheBeforeAdvice(accountDAO, membershipDAO);
            //demoAfterReturningAdvice(accountDAO);
            // demoTheAfterThrowAdvice(accountDAO);
            // demoAfterAdvice(accountDAO);
            //demoAroundAdvice(trafficFortuneService);
            //demoAroundAdviceForException(trafficFortuneService);
            demoAroundAdviceForExceptionRethrow(trafficFortuneService);
        };
    }

    private void demoTheBeforeAdvice(AccountDAO accountDAO,
                                     MembershipDAO membershipDAO) {
        Account account = new Account();
        account.setName("Madhu");
        account.setLevel("Platinum");
        accountDAO.addAccount(account, true);

        // call the account dao getter/setter methods
        accountDAO.setName("foobar");
        accountDAO.setServiceCode("silver");
        accountDAO.getName();
        accountDAO.getServiceCode();

        membershipDAO.addMember();
        accountDAO.doWork();
    }

    private void demoAfterReturningAdvice(AccountDAO accountDAO) {
        List<Account> account = accountDAO.findAccounts();
        System.out.println("Main app: AfterReturningDemoApp");
        System.out.println(account);
    }


    private void demoTheAfterThrowAdvice(AccountDAO accountDAO) {
        List<Account> account = null;
        try {
            boolean tripWire = true;
            account = accountDAO.findAccounts(tripWire);
        } catch (Exception e) {
            System.out.println("Main app: Caught exception: " + e);
        }
        System.out.println("Main app: AfterReturningDemoApp");
        System.out.println(account);
    }

    private void demoAfterAdvice(AccountDAO accountDAO) {
        List<Account> account = null;
        try {
            boolean tripWire = false;
            account = accountDAO.findAccounts(tripWire);
        } catch (Exception e) {
            System.out.println("Main app: Caught exception: " + e);
        }
        System.out.println("Main app: AfterDemoApp");
        System.out.println(account);
    }

    private void demoAroundAdvice(TrafficFortuneService trafficFortuneService) {
        System.out.println("Main app: AroundDemoApp");
        System.out.println("Calling getFortune()");
        String data = trafficFortuneService.getFortune();
        System.out.println("My fortune is: " + data);
        System.out.println("Finished");
    }

    // demoAroundAdvice(trafficFortuneService); for exception handling
    private void demoAroundAdviceForException(TrafficFortuneService trafficFortuneService) {
        System.out.println("Main app: AroundDemoApp");
        System.out.println("Calling getFortune()");
        String data = trafficFortuneService.getFortune(true);
        System.out.println("My fortune is: " + data);
        System.out.println("Finished");
    }

    private void demoAroundAdviceForExceptionRethrow(TrafficFortuneService trafficFortuneService) {
        System.out.println("Main app: AroundDemoApp");
        System.out.println("Calling getFortune()");
        String data = trafficFortuneService.getFortune(true);

        try {
            System.out.println("My fortune is: " + data);
        } catch (Exception e) {
            System.out.println("Exception caught: " + e);
        }
        System.out.println("My fortune is: " + data);
        System.out.println("Finished");
    }
}
