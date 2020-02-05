package se.nackademin.admin.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionListener;

import static se.nackademin.customer.view.SwingSetup.*;

public class CreateCustomer {

    JButton createButton;

    PanelHandler panelHandler;
    private JTextField firstName;
    private JTextField lastName;
    private JTextField personalNumber;
    private JTextField pinCode;
    private JLabel errorText;

    public CreateCustomer(PanelHandler panelHandler) {

        this.panelHandler = panelHandler;
        errorText = createLabel("",0);
        firstName = createTextField();
        lastName=createTextField();
        personalNumber=createTextField();
        pinCode = createTextField();

        JPanel createCustomerCenterPanel = SwingSetup.createPanel();
        createButton = createButton("Skapa kund");

        createCustomerCenterPanel.setLayout(new GridLayout(5, 2, 20, 20));
        createCustomerCenterPanel.add(createLabel("Förnamn", 2));
        createCustomerCenterPanel.add(createLabel("Efternamn", 2));
        createCustomerCenterPanel.add(firstName);
        createCustomerCenterPanel.add(lastName);
        createCustomerCenterPanel.add(createLabel("Personnummer", 2));
        createCustomerCenterPanel.add(createLabel("Pinkod", 2));
        createCustomerCenterPanel.add(personalNumber);
        createCustomerCenterPanel.add(pinCode);
        createCustomerCenterPanel.add(createLabel("", 0));
        createCustomerCenterPanel.add(createButton);
        createCustomerCenterPanel.setBorder(new EmptyBorder(10, 180, 210, 180));

        JPanel createCustomerPanel = createPanel();
        createCustomerPanel.setLayout(new BorderLayout());
        errorText.setVerticalAlignment(JLabel.CENTER);
        createCustomerPanel.setLayout(new BorderLayout());
        createCustomerPanel.add(createLogo(), BorderLayout.NORTH);
        createCustomerPanel.add(errorText, BorderLayout.CENTER);
        createCustomerPanel.add(createCustomerCenterPanel, BorderLayout.SOUTH);

        panelHandler.setCreateCustomerPanel(createCustomerPanel);
    }

    public void addListener(ActionListener listener) {
        createButton.addActionListener(listener);
    }

    public JLabel getErrorText() {
        return errorText;
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
}




