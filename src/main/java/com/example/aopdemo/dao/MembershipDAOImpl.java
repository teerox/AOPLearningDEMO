package com.example.aopdemo.dao;

import com.example.aopdemo.model.Account;
import org.springframework.stereotype.Repository;

@Repository
public class MembershipDAOImpl implements MembershipDAO{

        @Override
        public void addMember() {
            System.out.println(getClass() + ": DOING MY DB WORK: ADDING A MEMBERSHIP ACCOUNT");
        }
}
