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
        new AccountInfoController(this);
        new AdminMenuController(this);
        new CreateCustomerController(this);
        new LoanInfoController(this);
        new LoginViewController(this);
        new SearchController(this);
        new UpdateCustomerController(this);

    }
        private boolean personalNumberExists(String personalNr) {
            return repository.verifyPersonalNumber(personalNr);
        }

        /*
        private boolean passwordCorrect(String username, String password) {
        Customer customer = repository.verifyPin(username,password);
        if (customer!= null) {
            currentCustomer = customer;
            return true;
        } else {
            return false;
        }
        }

         */

        public boolean textFieldEmpty() {
        if (panelHandler.getCreateCustomerView().getFirstName().getText().equals("") ||
                panelHandler.getCreateCustomerView().getLastName().getText().equals("") ||
                panelHandler.getCreateCustomerView().getPersonalNumber().getText().equals("") ||
                panelHandler.getCreateCustomerView().getPinCode().getText().equals("")) {
            return true;
        } else {
            return false;
        }
        }

    public PanelHandler getPanelHandler() {
        return panelHandler;
    }

    public void setPanelHandler(PanelHandler panelHandler) {
        this.panelHandler = panelHandler;
    }

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public Customer getCurrentCustomer() {
        return currentCustomer;
    }

    public void setCurrentCustomer(Customer currentCustomer) {
        this.currentCustomer = currentCustomer;
    }
}