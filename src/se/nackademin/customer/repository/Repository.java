package se.nackademin.customer.repository;

import se.nackademin.customer.model.*;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class Repository {


    private Properties info = new Properties();

    public Repository() {
        try {
            info.load(new FileInputStream("src/se/nackademin/settings/settings.properties"));
        } catch (
                IOException e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public boolean verifyPersonalNumber(String personalNr) {
        try (
                Connection conn = DriverManager.getConnection(info.getProperty("connectionString"),
                        info.getProperty("user"),
                        info.getProperty("password"));
                PreparedStatement pstatement = conn.prepareStatement("SELECT Count(*) FROM Customer where personalNr = ?")) {

            pstatement.setString(1, personalNr);
            ResultSet result = pstatement.executeQuery();
            result.next();
            if (result.getInt(1) == 0) {
                return false;
            } else {
                return true;
            }

        } catch (
                SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public Customer verifyPin(String personalNr, String pinCode) {
        try (
                Connection conn = DriverManager.getConnection(info.getProperty("connectionString"),
                        info.getProperty("user"),
                        info.getProperty("password"));
                PreparedStatement pstatement = conn.prepareStatement("SELECT Count(*) FROM Customer where personalNr = ? AND pinCode = ?");
                PreparedStatement pstatement2 = conn.prepareStatement("SELECT * FROM Customer where personalNr = ? AND pinCode = ?")) {

            pstatement.setString(1, personalNr);
            pstatement.setString(2, pinCode);
            ResultSet result = pstatement.executeQuery();
            result.next();

            if (result.getInt(1) == 0) {
                return null;
            } else {
                pstatement2.setString(1, personalNr);
                pstatement2.setString(2, pinCode);
                result = pstatement2.executeQuery();
                while (result.next()) {
                    Customer customer = new Customer();
                    customer.setId(result.getInt(1));
                    customer.setFirstName(result.getString(2));
                    customer.setLastName(result.getString(3));
                    customer.setPersonalNumber(result.getString(4));
                    customer.setPinCode(result.getString(5));
                    return customer;
                }
            }

        } catch (
                SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public List<Loan> getLoansFromCustomer(Customer customer) {
        try (
                Connection conn = DriverManager.getConnection(info.getProperty("connectionString"),
                        info.getProperty("user"),
                        info.getProperty("password"));
                PreparedStatement pstatement = conn.prepareStatement("SELECT * FROM Loan" +
                        " INNER JOIN Customer on Loan.customerId = Customer.id WHERE Customer.id = ?");
                PreparedStatement pstatementInterest = conn.prepareStatement("SELECT * FROM Interest WHERE Interest.id = ?")) {

            pstatement.setInt(1, customer.getId());
            ResultSet result = pstatement.executeQuery();
            List<Loan> loans = new ArrayList<>();
            while (result.next()) {
                Loan loan = new Loan();
                loan.setId(result.getInt(1));
                loan.setCustomerId(result.getInt(2));
                loan.setAmount(result.getDouble(3));
                loan.setInterestId(result.getInt(4));
                loan.setLoanNumber(result.getString(5));
                loan.setLoanTime(result.getInt(6));
                loans.add(loan);
            }

            for (Loan loan : loans) {
                pstatementInterest.setInt(1, loan.getInterestId());
                result = pstatementInterest.executeQuery();
                while (result.next()) {
                    Interest interest = new Interest();
                    interest.setId(result.getInt(1));
                    interest.setInterestType(result.getString(2));
                    interest.setInterestRate(result.getDouble(3));
                    loan.setInterest(interest);
                }
            }
            return loans;

        } catch (
                SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }

    }

    public List<Account> getAccountFromCustomer(Customer customer) {
        try (
                Connection conn = DriverManager.getConnection(info.getProperty("connectionString"),
                        info.getProperty("user"),
                        info.getProperty("password"));
                PreparedStatement pstatement = conn.prepareStatement("SELECT * FROM Account" +
                        " INNER JOIN Customer on Account.customerId = Customer.id WHERE Customer.id = ?");
                PreparedStatement pstatementInterest = conn.prepareStatement("SELECT * FROM Interest WHERE Interest.id = ?");
                PreparedStatement pstatementTransactions = conn.prepareStatement("SELECT * FROM AccountHistory" +
                        " INNER JOIN Account on Account.id = AccountHistory.accountId WHERE Account.id = ?")) {

            pstatement.setInt(1, customer.getId());
            ResultSet result = pstatement.executeQuery();
            List<Account> accounts = new ArrayList<>();
            while (result.next()) {
                Account account = new Account();
                account.setId(result.getInt(1));
                account.setAccountName(result.getString(2));
                account.setCustomerId(result.getInt(3));
                account.setBalance(result.getDouble(4));
                account.setInterestId(result.getInt(5));
                accounts.add(account);
            }

            for (Account account : accounts) {
                pstatementInterest.setInt(1, account.getInterestId());
                result = pstatementInterest.executeQuery();
                while (result.next()) {
                    Interest interest = new Interest();
                    interest.setId(result.getInt(1));
                    interest.setInterestType(result.getString(2));
                    interest.setInterestRate(result.getDouble(3));
                    account.setInterest(interest);
                }
                pstatementTransactions.setInt(1, account.getId());
                result = pstatementTransactions.executeQuery();
                while (result.next()) {
                    AccountHistory accountHistory = new AccountHistory();
                    accountHistory.setId(result.getInt(1));
                    accountHistory.setAccountId(result.getInt(2));
                    accountHistory.setTransactionType(result.getString(3));
                    accountHistory.setAmount(result.getDouble(4));
                    accountHistory.setBalanceAfterAction(result.getDouble(5));
                    accountHistory.setDate(result.getTimestamp(6));
                    account.getAccountTransactions().add(accountHistory);
                }
            }

            return accounts;

        } catch (
                SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }

    }

    public boolean withdrawFromAccount(int accountId, int amount) {

        System.out.println(accountId);
        System.out.println(amount);

        try (
                Connection conn = DriverManager.getConnection(info.getProperty("connectionString"),
                        info.getProperty("user"),
                        info.getProperty("password"));
                CallableStatement stm = conn.prepareCall("CALL withdrawFromAccount(?,?)")) {

            stm.setInt(1, accountId);
            stm.setInt(2, amount);
            stm.executeQuery();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Något gick fel, Uttaget kunde ej genomföras");
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

}
