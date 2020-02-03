package se.nackademin.customer.controller;

import se.nackademin.customer.model.Account;
import se.nackademin.customer.model.Customer;
import se.nackademin.customer.model.Loan;
import se.nackademin.customer.repository.Repository;
import se.nackademin.customer.view.PanelHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Controller {

    private PanelHandler panelHandler;
    private Repository repository;
    private Customer currentCustomer;

    public Controller() {

        panelHandler = new PanelHandler();
        repository = new Repository();
        panelHandler.getLoginView().addLoginListener(new LoginListener());

    }
    class LoanButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            Object ae = e.getSource();
            if (ae instanceof JButton) {
                JButton b = (JButton) ae;
                List<Loan> loans = repository.getLoansFromCustomer(currentCustomer);
                loans.forEach(l -> {
                    if (Integer.toString(l.getId()).equalsIgnoreCase(b.getName())) {
                        panelHandler.changeToLoanPanel(new infoListener(),l);
                    }
                });

            }
        }
    }

    class AccountButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            Object ae = e.getSource();
            if (ae instanceof JButton) {
                JButton b = (JButton) ae;
                List<Account> accounts = repository.getAccountFromCustomer(currentCustomer);
                accounts.forEach(a -> {
                    if (Integer.toString(a.getId()).equalsIgnoreCase(b.getName())) {
                        panelHandler.changeToAccountPanel(new infoListener(), new withdrawListener(),a);
                    }
                });

            }

        }
    }

    class infoListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            changeToMainMenu();
        }
    }

    class withdrawListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            Object ae = e.getSource();

            if (ae instanceof JButton) {
                JButton button =(JButton) ae;
                repository.withdrawFromAccount(Integer.parseInt(button.getName()),Integer.parseInt(panelHandler.getAccountInfo().getWithdrawSum().getText()));
                List<Account> accounts = repository.getAccountFromCustomer(currentCustomer);
                accounts.forEach(a -> {
                    if(a.getId() == panelHandler.getAccountInfo().getCurrentAccount().getId()) {
                        panelHandler.changeToAccountPanel(new infoListener(), new withdrawListener(),a);
                    }
                });
            }
        }
    }

    class LoginListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String passwordString = new String (panelHandler.getLoginView().getPassword().getPassword());
            String usernameInput = panelHandler.getLoginView().getUsername().getText();
            if (personalNumberExists(usernameInput)) {
                if (passwordCorrect(usernameInput, passwordString)) {
                    changeToMainMenu();
                    panelHandler.getMainMenuView().getUserLabel().setText("Inloggad som: " +
                            currentCustomer.getFirstName() + " " + currentCustomer.getLastName());
                } else {
                    panelHandler.getLoginView().getErrorText().setText("Felaktig pinkod");
                    panelHandler.getLoginView().getPassword().setText("");
                }
            } else {
                panelHandler.getLoginView().getErrorText().setText("Felaktigt personnummer");
                panelHandler.getLoginView().getUsername().setText("");
                panelHandler.getLoginView().getPassword().setText("");
            }
        }
    }

    private boolean personalNumberExists(String personalNr) {
        return repository.verifyPersonalNumber(personalNr);
    }

    private boolean passwordCorrect(String username, String password) {
        Customer customer = repository.verifyPin(username,password);
        if (customer!= null) {
            currentCustomer = customer;
            return true;
        } else {
            return false;
        }
    }

    private void changeToMainMenu() {
        panelHandler.changeToMainMenu(new AccountButtonListener(),new LoanButtonListener(),
                repository.getLoansFromCustomer(currentCustomer), repository.getAccountFromCustomer(currentCustomer));
    }


}
