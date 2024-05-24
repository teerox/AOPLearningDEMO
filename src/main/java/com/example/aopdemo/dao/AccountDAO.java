package com.example.aopdemo.dao;

import com.example.aopdemo.model.Account;

import java.util.List;

public interface AccountDAO {

    public void addAccount(Account account, boolean vipFlag);

    public boolean doWork();

    public String getName();

    public void setName(String name);

    public String getServiceCode();

    public void setServiceCode(String serviceCode);


    public List<Account> findAccounts();

    public List<Account> findAccounts(boolean tripWire);
}
