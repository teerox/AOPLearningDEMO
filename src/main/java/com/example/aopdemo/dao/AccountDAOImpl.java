package com.example.aopdemo.dao;

import com.example.aopdemo.model.Account;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountDAOImpl implements AccountDAO{

    private String name;
    private String serviceCode;

    @Override
    public void addAccount(Account account, boolean vipFlag) {
        System.out.println(getClass() + ": DOING MY DB WORK: ADDING AN ACCOUNT");
    }

    @Override
    public boolean doWork() {
        System.out.println(getClass() + ": DOING MY DB WORK: DOING WORK");
        return false;
    }

    @Override
    public String getName() {
        System.out.println(getClass() + ": in getName");
        return name;
    }

    @Override
    public void setName(String name) {
        System.out.println(getClass() + ": in setName");
        this.name = name;
    }

    @Override
    public String getServiceCode() {
        System.out.println(getClass() + ": in getServiceCode");
        return serviceCode;
    }

    @Override
    public void setServiceCode(String serviceCode) {
        System.out.println(getClass() + ": in setServiceCode");
        this.serviceCode = serviceCode;
    }

    @Override
    public List<Account> findAccounts() {
       return findAccounts(false);
    }

    @Override
    public List<Account> findAccounts(boolean tripWire) {
        if (tripWire) {
            throw new RuntimeException("No soup for you!");
        }
        return List.of(
                new Account("John", "Silver"),
                new Account("Madhu", "Platinum"));
    }
}
