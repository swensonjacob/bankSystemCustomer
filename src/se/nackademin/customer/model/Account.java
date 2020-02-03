package se.nackademin.customer.model;

import java.util.ArrayList;
import java.util.List;

public class Account {

    private int id;
    private String accountName;
    private int customerId;
    private double balance;
    private int interestId;

    private Interest interest;
    private List<AccountHistory> accountTransactions = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getInterestId() {
        return interestId;
    }

    public void setInterestId(int interestId) {
        this.interestId = interestId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<AccountHistory> getAccountTransactions() {
        return accountTransactions;
    }

    public void setAccountTransactions(List<AccountHistory> accountTransactions) {
        this.accountTransactions = accountTransactions;
    }

    public Interest getInterest() {
        return interest;
    }

    public void setInterest(Interest interest) {
        this.interest = interest;
    }
}
