package se.nackademin.admin.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionListener;

import static se.nackademin.customer.view.SwingSetup.*;

public class CreateAccountView {

    JButton createButton;
    JButton backButton;

    PanelHandler panelHandler;
    private JTextField accountName;
    private JTextField rent;
    private JLabel errorText;

    public CreateAccountView(PanelHandler panelHandler) {

        this.panelHandler = panelHandler;
        errorText = createLabel("",0);
        accountName = createTextField();
        rent=createTextField();

        JPanel createAccountCenterPanel = SwingSetup.createPanel();
        createButton = createButton("Öppna konto");
        backButton = createButton("Tillbaka");

        createAccountCenterPanel.setLayout(new GridLayout(9, 1, 10, 10));
        createAccountCenterPanel.add(SwingSetup.createBigLabel("Öppna upp ett nytt konto", 2));
        createAccountCenterPanel.add(createLabel("Kontonummer: ", 2));
        createAccountCenterPanel.add(accountName);
        createAccountCenterPanel.add(createLabel("", 0));
        createAccountCenterPanel.add(createButton);
        createAccountCenterPanel.add(backButton);
        createAccountCenterPanel.setBorder(new EmptyBorder(0, 220, 80, 220));

        JPanel createAccountPanel = createPanel();
        createAccountPanel.setLayout(new BorderLayout());
        errorText.setVerticalAlignment(JLabel.CENTER);
        createAccountPanel.setLayout(new BorderLayout());
        createAccountPanel.add(createLogo(), BorderLayout.NORTH);
        createAccountPanel.add(errorText, BorderLayout.CENTER);
        createAccountPanel.add(createAccountCenterPanel, BorderLayout.SOUTH);

        panelHandler.setCreateAccountPanel(createAccountPanel);
    }

    public void addListener(ActionListener listener) {
        createButton.addActionListener(listener);
        backButton.addActionListener(listener);
    }

    public JLabel getErrorText() {
        return errorText;
    }

    public void setErrorText(JLabel errorText) {
        this.errorText = errorText;
    }

    public JTextField getAccountName() {
        return this.accountName;
    }

    public void setAccountName(JTextField name) {
        this.accountName = name;
    }

    public JTextField getRent() {
        return this.rent;
    }

    public void setRent(JTextField rent) {
        this.rent = rent;
    }

    public JButton getCreateButton() {
        return createButton;
    }

    public void setCreateButton(JButton createButton) {
        this.createButton = createButton;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public void setBackButton(JButton backButton) {
        this.backButton = backButton;
    }
}