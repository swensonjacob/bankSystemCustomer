package se.nackademin.admin.controller;

import se.nackademin.admin.model.Customer;
import se.nackademin.admin.repository.Repository;
import se.nackademin.admin.view.PanelHandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                panelHandler.changeToLoanPanel(new LoanInfoListener());
            }
        }

    class AccountButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            panelHandler.changeToAccountPanel(new LoanInfoListener());
        }
    }

        class LoanInfoListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelHandler.changeToCustomerView();
            }
        }

        class LoginListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {

                //Utkommenterad av marcus för testsyften
                /*
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
                */

                panelHandler.changeToSearchPanel(new SearchListener());
            }
        }

        class SearchListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelHandler.changeToCustomerView();
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
    }