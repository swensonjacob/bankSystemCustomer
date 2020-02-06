package se.nackademin.admin.view;
import se.nackademin.admin.model.Account;
import se.nackademin.admin.model.AccountHistory;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;

import static se.nackademin.admin.view.SwingSetup.*;

public class AccountInfo {
    private JPanel accountPanel;
    private Account currentAccount;
    private PanelHandler panelHandler;
    private DefaultListModel accountTransactions;
    private JButton backButton;
    private JTextField startDate;
    private JTextField endDate;
    private JTextField withdrawSum;
    private JButton withdrawButton;
    private JButton depositButton;
    private JButton changeInterestButton;
    private JButton deleteButton;
    private JTextField interestField;
    private JButton showHistory;

    public AccountInfo(PanelHandler panelHandler, ActionListener listener, Account account) {
        this.panelHandler = panelHandler;
        accountTransactions = new DefaultListModel();
        currentAccount = account;
        JList list = createJList(accountTransactions);
        changeInterestButton = createButton("Ändra räntan");
        interestField = createTextField();
        interestField.setText(Double.toString(currentAccount.getInterest().getInterestRate()));
        backButton = createBackButton();
        deleteButton = createButton("Radera konto");
        deleteButton.addActionListener(listener);
        showTransactionHistory();


        backButton.addActionListener(listener);
        JPanel btnPanel = createPanel();
        btnPanel.setLayout(new GridLayout(2,1,10,10));
        btnPanel.add(backButton);
        btnPanel.setBorder(new EmptyBorder(50, 20, 50, 0));
        btnPanel.setPreferredSize(new Dimension(80,80));
        JPanel withdrawPanel = createPanel();
        withdrawPanel.setLayout(new GridLayout(3, 1, 10, 5)); // 3 från 2 50 från 10
        JPanel buttons = createPanel();

        buttons.setLayout(new GridLayout(1, 3, 10, 10));
        withdrawSum = createTextField();
        withdrawButton = createButton("Ta ut");
        depositButton = createButton("Sätt in");
        withdrawButton.addActionListener(listener);
        depositButton.addActionListener(listener);
        buttons.add(withdrawSum);
        buttons.add(withdrawButton);
        buttons.add(depositButton);
        JPanel dateInput = createPanel();
        dateInput.setLayout(new GridLayout(1, 3, 10, 10));
        startDate = createTextField();
        endDate = createTextField();
        showHistory = createButton("Visa");
        showHistory.addActionListener(listener);
        dateInput.add(startDate);
        dateInput.add(endDate);
        dateInput.add(showHistory);
        withdrawPanel.add(createLabel("Ange belopp",2));
        withdrawPanel.add(buttons);
        withdrawPanel.add(createLabel("ange datum för historia", 2));
        withdrawPanel.add(dateInput);
        withdrawPanel.setBorder(new EmptyBorder(0,10,0,10));

        JPanel interestPanel = createPanel();
        interestPanel.setLayout(new GridLayout(1,4,10,10));
        interestPanel.setBorder(new EmptyBorder(15,20,15,20));
        interestPanel.add(createLabel("Ränta: ",2));
        interestPanel.add(interestField);
        interestPanel.add(changeInterestButton);
        interestPanel.add(deleteButton);


        JPanel accountInfoPanel = createPanel();
        accountInfoPanel.setLayout(new GridLayout(5,1));
        accountInfoPanel.add(createBigLabel("Konto: " + currentAccount.getAccountName(),2));
        accountInfoPanel.add(interestPanel);
        accountInfoPanel.add(createLabel("Saldo: " + currentAccount.getBalance() ,2));
        accountInfoPanel.add(withdrawPanel);
        accountInfoPanel.setBorder(new EmptyBorder(0,50,0,50));
        JScrollPane paymentList = new JScrollPane(list);
        paymentList.setBackground(new Color(208, 228, 255));
        paymentList.setBorder(new EmptyBorder(10,10,10,10));
        accountPanel = createPanel();
        accountPanel.setLayout(new BorderLayout());
        accountPanel.add(createLogo(),BorderLayout.NORTH);
        accountPanel.add(accountInfoPanel,BorderLayout.CENTER);
        accountPanel.add(btnPanel,BorderLayout.WEST);
        accountPanel.add(paymentList,BorderLayout.SOUTH);
        panelHandler.setAccountPanel(accountPanel);
    }
    public JTextField getStartDate() {
        return startDate;
    }
    public JTextField getEndDate() {
        return endDate;
    }
    public JTextField getWithdrawSum() {
        return withdrawSum;
    }

    public Account getCurrentAccount() {
        return currentAccount;
    }

    public void showTransactionHistory () {
        List<AccountHistory> transactions = currentAccount.getAccountTransactions();
        Collections.reverse(transactions);
        accountTransactions.addElement("Kontohistorik:");
        transactions.forEach(t -> {
            accountTransactions.addElement(t.getDate() + "," + t.getTransactionType() +
                    " " + Math.round(t.getAmount()) + " kr , Saldo: " + Math.round(t.getBalanceAfterAction()) + " kr");
        });
    }

    public void setFilteredTransactionHistory (List<AccountHistory> transactions) {
        accountTransactions.clear();
        Collections.reverse(transactions);
        accountTransactions.addElement("Kontohistorik:");
        transactions.forEach(t -> {
            accountTransactions.addElement(t.getDate() + "," + t.getTransactionType() +
                    " " + Math.round(t.getAmount()) + " kr , Saldo: " + Math.round(t.getBalanceAfterAction()) + " kr");
        });
    }

    public void setCurrentAccount(Account currentAccount) {
        this.currentAccount = currentAccount;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public void setBackButton(JButton backButton) {
        this.backButton = backButton;
    }

    public void setStartDate(JTextField startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(JTextField endDate) {
        this.endDate = endDate;
    }

    public void setWithdrawSum(JTextField withdrawSum) {
        this.withdrawSum = withdrawSum;
    }

    public JButton getWithdrawButton() {
        return withdrawButton;
    }

    public void setWithdrawButton(JButton withdrawButton) {
        this.withdrawButton = withdrawButton;
    }

    public JButton getDepositButton() {
        return depositButton;
    }

    public void setDepositButton(JButton depositButton) {
        this.depositButton = depositButton;
    }

    public JButton getChangeInterestButton() {
        return changeInterestButton;
    }

    public void setChangeInterestButton(JButton changeInterestButton) {
        this.changeInterestButton = changeInterestButton;
    }

    public JTextField getInterestField() {
        return interestField;
    }

    public void setInterestField(JTextField interestField) {
        this.interestField = interestField;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public void setDeleteButton(JButton deleteButton) {
        this.deleteButton = deleteButton;
    }

    public JButton getShowHistory() {
        return showHistory;
    }

    public void setShowHistory(JButton showHistory) {
        this.showHistory = showHistory;
    }

    public DefaultListModel getAccountTransactions() {
        return accountTransactions;
    }

    public void setAccountTransactions(DefaultListModel accountTransactions) {
        this.accountTransactions = accountTransactions;
    }
}
