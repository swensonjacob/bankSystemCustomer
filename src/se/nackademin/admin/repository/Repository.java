package se.nackademin.admin.repository;

import se.nackademin.admin.model.*;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
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

    public Customer getCurrCustomer(String personalNr) {
        try (
                Connection conn = DriverManager.getConnection(info.getProperty("connectionString"),
                        info.getProperty("user"),
                        info.getProperty("password"));
                PreparedStatement pstatement = conn.prepareStatement("SELECT * FROM Customer where personalNr = ? ")) {
            pstatement.setString(1, personalNr);
            ResultSet result = pstatement.executeQuery();
            while (result.next()) {
                Customer customer = new Customer();
                customer.setId(result.getInt(1));
                customer.setFirstName(result.getString(2));
                customer.setLastName(result.getString(3));
                customer.setPersonalNumber(result.getString(4));
                customer.setPinCode(result.getString(5));
                return customer;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public boolean verifyAdminPin(String userName, String pinCode) {
        try (
                Connection conn = DriverManager.getConnection(info.getProperty("connectionString"),
                        info.getProperty("user"),
                        info.getProperty("password"));
                PreparedStatement pstatement = conn.prepareStatement("select count(*) from Admin where username = ? and password = ? ")) {
            pstatement.setString(1, userName);
            pstatement.setString(2, pinCode);
            ResultSet result = pstatement.executeQuery();
            if (result.next()) {
                if (result.getInt(1) == 1) {
                    return true;
                }
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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
                pstatementInterest.setInt(1, account.getId());
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

    public boolean deleteAccount(int accountID) {
        try (
                Connection conn = DriverManager.getConnection(info.getProperty("connectionString"),
                        info.getProperty("user"),
                        info.getProperty("password"));
                PreparedStatement stm = conn.prepareStatement("delete from Account where id = ?")) {

            stm.setInt(1, accountID);
            stm.execute();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Något gick fel, Kontot ej borttaget");
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean changeRateForAccount(int accountID, double newInterestRate) {
        try (
                Connection conn = DriverManager.getConnection(info.getProperty("connectionString"),
                        info.getProperty("user"),
                        info.getProperty("password"));
                CallableStatement stm = conn.prepareCall("CALL changeRateForAccount(?,?)")) {

            stm.setInt(1, accountID);
            stm.setDouble(2, newInterestRate);
            stm.execute();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Något gick fel, Räntan ej ändrad");
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }


    public boolean changeRateForLoan(int loanID, double newInterestRate) {
        try (
                Connection conn = DriverManager.getConnection(info.getProperty("connectionString"),
                        info.getProperty("user"),
                        info.getProperty("password"));
                CallableStatement stm = conn.prepareCall("CALL changeRateForLoan(?,?)")) {

            stm.setInt(1, loanID);
            stm.setDouble(2, newInterestRate);
            stm.execute();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Något gick fel, Räntan ej ändrad");
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean createLoan(int customerID, int amount, int accountID, String loanNumber, int loanTimeInMonth) {
        try (
                Connection conn = DriverManager.getConnection(info.getProperty("connectionString"),
                        info.getProperty("user"),
                        info.getProperty("password"));
                CallableStatement stm = conn.prepareCall("CALL createLoan(?,?,?,?,?)")) {

            stm.setInt(1, customerID);
            stm.setInt(2, amount);
            stm.setInt(3, accountID);
            stm.setString(4, loanNumber);
            stm.setInt(5, loanTimeInMonth);
            stm.execute();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Något gick fel, Lånet skapades ej");
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }


    public boolean addNewCustomer(String firstName, String lastName, String personalNR, String pinCode) {
        try (
                Connection conn = DriverManager.getConnection(info.getProperty("connectionString"),
                        info.getProperty("user"),
                        info.getProperty("password"));
                CallableStatement stm = conn.prepareCall("CALL addCustomer(?,?,?,?)")) {

            stm.setString(1, firstName);
            stm.setString(2, lastName);
            stm.setString(3, personalNR);
            stm.setString(4, pinCode);
            stm.execute();
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateCustomerInfo(int customerID, String firstName, String lastName, String personalNr, String
            pinCode) {
        try (
                Connection conn = DriverManager.getConnection(info.getProperty("connectionString"),
                        info.getProperty("user"),
                        info.getProperty("password"));
                CallableStatement stm = conn.prepareCall("CALL updateCustomer(?,?,?,?,?)")) {

            stm.setInt(1, customerID);
            stm.setString(2, firstName);
            stm.setString(3, lastName);
            stm.setString(4, personalNr);
            stm.setString(5, pinCode);
            stm.execute();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Något gick fel, Ändringarna gick ej igenom");
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }


    public boolean deleteCustomer(int customerID) {
        try (
                Connection conn = DriverManager.getConnection(info.getProperty("connectionString"),
                        info.getProperty("user"),
                        info.getProperty("password"));
                CallableStatement stm = conn.prepareCall("CALL deleteCustomer(?)")) {

            stm.setInt(1, customerID);
            stm.execute();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Något gick fel, Personen är kvar i systemet");
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }


    public boolean changeLoanTimebyMonth(int loanID, int newLoantime) {
        try (
                Connection conn = DriverManager.getConnection(info.getProperty("connectionString"),
                        info.getProperty("user"),
                        info.getProperty("password"));
                Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            ResultSet resultSet = stm.executeQuery("select * from Loan");

            while (resultSet.next()) {
                if (resultSet.getInt("id") == (loanID)) {
                    resultSet.updateInt("loanTime", newLoantime);
                    resultSet.updateRow();

                }
            }

            // funkar detta såhär??
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Något gick fel, Ändringen gick inte igenom");
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateBalanceForAccount(int accountID, int amount) {
        try (
                Connection conn = DriverManager.getConnection(info.getProperty("connectionString"),
                        info.getProperty("user"),
                        info.getProperty("password"));
                CallableStatement stm = conn.prepareCall("CALL updateBankAccount(?,?)")) {

            stm.setInt(1, accountID);
            stm.setInt(2, amount);
            stm.execute();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Något gick fel, insättningen gick inte igenom");
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean addNewAccount(String accountName, int customerID) {
        try (
                Connection conn = DriverManager.getConnection(info.getProperty("connectionString"),
                        info.getProperty("user"),
                        info.getProperty("password"));
                PreparedStatement stm = conn.prepareStatement("insert into Account (accountName, customerId) values (?,?)")) {

            stm.setString(1, accountName);
            stm.setInt(2, customerID);
            stm.execute();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Något gick fel, Kontot kunde ej läggas till");
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }


    public List<AccountHistory> getAccountHistory(String firstDate, String lastDate) {
        List<AccountHistory> getAccountHistory = new ArrayList<>();
        try (
                Connection conn = DriverManager.getConnection(info.getProperty("connectionString"),
                        info.getProperty("user"),
                        info.getProperty("password"));
                PreparedStatement stm = conn.prepareStatement("select ah.id, ah.accountId, ah.action, ah.amount, ah.balanceAfterAction, ah.date from Customer " +
                        "join Account on customerid = Customer.id " +
                        "join AccountHistory ah on accountid = Account.id " +
                        "where ah.date between ? and ?")) {
            stm.setString(1, firstDate);
            stm.setString(2, String.valueOf(LocalDate.parse(lastDate).plusDays(1)));
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                AccountHistory accountHistory = new AccountHistory();
                accountHistory.setId(rs.getInt(1));
                accountHistory.setAccountId(rs.getInt(2));
                accountHistory.setTransactionType(rs.getString(3));
                accountHistory.setAmount(rs.getDouble(4));
                accountHistory.setBalanceAfterAction(rs.getDouble(5));
                accountHistory.setDate(rs.getTimestamp(6));
                getAccountHistory.add(accountHistory);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getAccountHistory;
    }

    public static void main(String[] args) {
        Repository test = new Repository();
        //test.deleteAccount(4); Funkar
        //test.changeRateForAccount(5,1); Funkar
        //test.changeRateForLoan(3,0.5); Funkar
        //test.createLoan(2,10000000,5,"123123123",24);
        //test.addNewCustomer("Johannes","Svensson","123456","1234"); Funkar
        //test.updateCustomerInfo(2,"Johannes","Aka The God","880203","1234"); Funkar
        //test.deleteCustomer(3); Funkar
        //test.changeLoanTimebyMonth(3,24);
        //test.updateBalanceForAccount(5,10);
        //test.addNewAccount("12345678",1);
        //for (AccountHistory a : test.getAccountHistory("2016-01-01", "2020-02-05")) {
        //   System.out.println(a.getId());
        //   System.out.println(a.getDate());
        }

    }



