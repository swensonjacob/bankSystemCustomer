package se.nackademin.admin.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionListener;

import static se.nackademin.customer.view.SwingSetup.*;

public class CreateAccountView {

    JButton createButton;

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
        createButton = createButton("Bevilja");

        createAccountCenterPanel.setLayout(new GridLayout(8, 1, 10, 10));
        createAccountCenterPanel.add(SwingSetup.createBigLabel("Öppna upp ett nytt konto", 2));
        createAccountCenterPanel.add(createLabel("Kontonamn", 2));
        createAccountCenterPanel.add(accountName);
        createAccountCenterPanel.add(createLabel("Ränta", 2));
        createAccountCenterPanel.add(rent);
        createAccountCenterPanel.add(createLabel("", 0));
        createAccountCenterPanel.add(createButton);
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
}