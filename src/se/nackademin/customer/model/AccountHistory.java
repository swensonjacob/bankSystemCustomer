package se.nackademin.customer.model;

import java.sql.Timestamp;

public class AccountHistory {

    private int id;
    private int accountId;
    private String transactionType;
    private double amount;
    private double balanceAfterAction;
    private Timestamp date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getBalanceAfterAction() {
        return balanceAfterAction;
    }

    public void setBalanceAfterAction(double balanceAfterAction) {
        this.balanceAfterAction = balanceAfterAction;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
