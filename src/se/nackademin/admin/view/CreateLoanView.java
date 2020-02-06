package se.nackademin.admin.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionListener;

import static se.nackademin.customer.view.SwingSetup.*;

public class CreateLoanView {

    JButton createButton;
    JButton backButton;

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
        backButton = createButton("Tillbaka");

        createAccountCenterPanel.setLayout(new GridLayout(12, 1, 10, 10));

        createAccountCenterPanel.add(SwingSetup.createBigLabel("Bevilja Lån", 2));

        createAccountCenterPanel.add(createLabel("Summa", 2));
        createAccountCenterPanel.add(amount);

        createAccountCenterPanel.add(createLabel("Lånnummer", 2));
        createAccountCenterPanel.add(loanNumb);

        createAccountCenterPanel.add(createLabel("Lånetid", 2));
        createAccountCenterPanel.add(loanTime);

        createAccountCenterPanel.add(createLabel("", 0));
        createAccountCenterPanel.add(createButton);
        createAccountCenterPanel.add(backButton);
        createAccountCenterPanel.setBorder(new EmptyBorder(0, 220, 60, 220));

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

    public JTextField getAmount() {
        return this.amount;
    }

    public void setAmount(JTextField amount) {
        this.amount = amount;
    }

    public JTextField getLoanNumbName() {
        return this.loanNumb;
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