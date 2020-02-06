package se.nackademin.admin.view;

import se.nackademin.admin.model.Customer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

import static se.nackademin.admin.view.SwingSetup.createPanel;
import static se.nackademin.customer.view.SwingSetup.*;

public class UpdateCustomer {

    private JButton updateButton;
    private JButton eraseButton;
    private PanelHandler panelHandler;
    private JTextField firstName;
    private JTextField lastName;
    private JTextField personalNumber;
    private JTextField pinCode;
    private JLabel errorText;
    private JButton backButton;
    private Customer currentCustomer;

    public UpdateCustomer(PanelHandler panelHandler) {

        this.panelHandler = panelHandler;
        errorText = createLabel("",0);
        firstName = createTextField();
        lastName=createTextField();
        personalNumber=createTextField();
        pinCode = createTextField();

        JPanel createCustomerCenterPanel = SwingSetup.createPanel();
        updateButton = createButton("Uppdatera kund");
        eraseButton = createButton("Radera kund");
        backButton = createButton("Tillbaka");

        createCustomerCenterPanel.setLayout(new GridLayout(6, 2, 20, 20));
        createCustomerCenterPanel.add(createLabel("Förnamn", 2));
        createCustomerCenterPanel.add(createLabel("Efternamn", 2));
        createCustomerCenterPanel.add(firstName);
        createCustomerCenterPanel.add(lastName);
        createCustomerCenterPanel.add(createLabel("Personnummer", 2));
        createCustomerCenterPanel.add(createLabel("Pinkod", 2));
        createCustomerCenterPanel.add(personalNumber);
        createCustomerCenterPanel.add(pinCode);
        createCustomerCenterPanel.add(eraseButton);
        createCustomerCenterPanel.add(updateButton);
        createCustomerCenterPanel.add(backButton);
        createCustomerCenterPanel.setBorder(new EmptyBorder(40, 180, 120, 180));

        JPanel createCustomerPanel = createPanel();
        createCustomerPanel.setLayout(new BorderLayout());
        errorText.setVerticalAlignment(JLabel.CENTER);
        createCustomerPanel.setLayout(new BorderLayout());
        createCustomerPanel.add(createLogo(), BorderLayout.NORTH);
        createCustomerPanel.add(errorText, BorderLayout.CENTER);
        createCustomerPanel.add(createCustomerCenterPanel, BorderLayout.SOUTH);

        panelHandler.setUpdateCustomerPanel(createCustomerPanel);
    }

    public void addListener(ActionListener listener) {
        this.updateButton.addActionListener(listener);
        this.eraseButton.addActionListener(listener);
    }

    public JLabel getErrorText() {
        return errorText;
    }

    public void addBackButtonListener(ActionListener listener) {
        backButton.addActionListener(listener);
    }


    public void setErrorText(JLabel errorText) {
        this.errorText = errorText;
    }

    public JTextField getFirstName() {
        return firstName;
    }

    public void setFirstName(JTextField firstName) {
        this.firstName = firstName;
    }

    public JTextField getLastName() {
        return lastName;
    }

    public void setLastName(JTextField lastName) {
        this.lastName = lastName;
    }

    public JTextField getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(JTextField personalNumber) {
        this.personalNumber = personalNumber;
    }

    public JTextField getPinCode() {
        return pinCode;
    }

    public void setPinCode(JTextField pinCode) {
        this.pinCode = pinCode;
    }

    public JButton getUpdateButton() {
        return updateButton;
    }

    public void setUpdateButton(JButton updateButton) {
        this.updateButton = updateButton;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public void setBackButton(JButton backButton) {
        this.backButton = backButton;
    }

    public JButton getEraseButton() {
        return eraseButton;
    }

    public void setEraseButton(JButton eraseButton) {
        this.eraseButton = eraseButton;
    }

    public Customer getCurrentCustomer() {
        return currentCustomer;
    }

    public void setCurrentCustomer(Customer currentCustomer) {
        this.currentCustomer = currentCustomer;
        firstName.setText(currentCustomer.getFirstName());
        lastName.setText(currentCustomer.getLastName());
        personalNumber.setText(currentCustomer.getPersonalNumber());
        pinCode.setText(currentCustomer.getPinCode());
    }
}




