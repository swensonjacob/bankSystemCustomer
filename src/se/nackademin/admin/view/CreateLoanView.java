package se.nackademin.admin.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionListener;

import static se.nackademin.customer.view.SwingSetup.*;

public class CreateLoanView {

    JButton createButton;

    PanelHandler panelHandler;
    private JTextField amount;
    private JTextField accountName;
    private JTextField loanNumb;
    private JTextField loanTime;
    private JLabel errorText;

    public CreateLoanView(PanelHandler panelHandler) {

        this.panelHandler = panelHandler;
        errorText = createLabel("",0);
        accountName = createTextField();
        amount = createTextField();
        loanNumb = SwingSetup.createTextField();
        loanTime = SwingSetup.createTextField();

        JPanel createAccountCenterPanel = SwingSetup.createPanel();
        createButton = createButton("Bevilja");

        createAccountCenterPanel.setLayout(new GridLayout(11, 1, 10, 10));

        createAccountCenterPanel.add(SwingSetup.createBigLabel("Bevilja Lån", 2));

        createAccountCenterPanel.add(createLabel("Summa", 2));
        createAccountCenterPanel.add(amount);

        createAccountCenterPanel.add(createLabel("Kontonamn", 2));
        createAccountCenterPanel.add(accountName);

        createAccountCenterPanel.add(createLabel("Lånnummer", 2));
        createAccountCenterPanel.add(loanNumb);

        createAccountCenterPanel.add(createLabel("Lånetid", 2));
        createAccountCenterPanel.add(loanTime);

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

        panelHandler.setCreateLoanPanel(createAccountPanel);
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

    public JTextField getAmount() {
        return this.amount;
    }

    public void setAmount(JTextField amount) {
        this.amount = amount;
    }

    public JTextField getLoanNumbName() {
        return this.accountName;
    }

    public void setLoanNumb(JTextField numb) {
        this.loanNumb = numb;
    }

    public JTextField getLoanTime() {
        return this.loanTime;
    }

    public void setLoanTime(JTextField time) {
        this.loanTime = time;
    }
}