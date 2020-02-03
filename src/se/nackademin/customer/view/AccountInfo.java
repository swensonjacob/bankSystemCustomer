package se.nackademin.customer.view;

import se.nackademin.customer.model.Account;
import se.nackademin.customer.model.AccountHistory;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;

import static se.nackademin.customer.view.SwingSetup.*;

public class AccountInfo {
    private JPanel accountPanel;
    private Account currentAccount;
    private PanelHandler panelHandler;
    private DefaultListModel accountTransactions;
    private JButton backButton;
    private JTextField withdrawSum;
    private JButton withdrawButton;

    public AccountInfo(PanelHandler panelHandler, ActionListener backListener, ActionListener withdrawListener, Account account) {
        this.accountPanel = createPanel();
        this.panelHandler = panelHandler;
        this.currentAccount = account;
        accountTransactions = new DefaultListModel();
        backButton = createBackButton();
        withdrawButton = createButton("Ta ut pengar");
        withdrawButton.setName(Integer.toString(currentAccount.getId()));
        withdrawSum = createTextField();


        showTransactionHistory();
        JList list = createJList(accountTransactions);

        backButton.addActionListener(backListener);
        withdrawButton.addActionListener(withdrawListener);

        JPanel btnPanel = createPanel();
        btnPanel.setLayout(new GridLayout(2,1,10,10));
        btnPanel.add(backButton);
        btnPanel.setBorder(new EmptyBorder(50, 20, 50, 0));
        btnPanel.setPreferredSize(new Dimension(80,60));

        JPanel withdrawPanel = createPanel();
        withdrawPanel.setLayout(new GridLayout(2,2,10,10));
        withdrawPanel.add(createLabel("Ange belopp",2));
        withdrawPanel.add(new JLabel());
        withdrawPanel.add(withdrawSum);
        withdrawPanel.add(withdrawButton);
        withdrawPanel.setBorder(new EmptyBorder(10,0,10,0));

        JPanel accountInfoPanel = createPanel();
        accountInfoPanel.setLayout(new GridLayout(4,1));
        accountInfoPanel.add(createBigLabel("Konto " + currentAccount.getAccountName(),2));
        accountInfoPanel.add(createLabel("Saldo: " + Math.round(currentAccount.getBalance()) + " kr" ,2));
        accountInfoPanel.add(createLabel("Uttag",2));
        accountInfoPanel.add(withdrawPanel);
        accountInfoPanel.setBorder(new EmptyBorder(0,50,0,50));

        JScrollPane paymentList = new JScrollPane(list);

        paymentList.setBackground(new Color(208, 228, 255));
        paymentList.setBorder(new EmptyBorder(10,10,10,10));

        accountPanel.setLayout(new BorderLayout());
        accountPanel.add(createLogo(),BorderLayout.NORTH);
        accountPanel.add(accountInfoPanel,BorderLayout.CENTER);
        accountPanel.add(btnPanel,BorderLayout.WEST);
        accountPanel.add(paymentList,BorderLayout.SOUTH);
        panelHandler.setAccountPanel(accountPanel);

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

    public JTextField getWithdrawSum() {
        return withdrawSum;
    }

    public void setWithdrawSum(JTextField withdrawSum) {
        this.withdrawSum = withdrawSum;
    }

    public Account getCurrentAccount() {
        return currentAccount;
    }

    public void setCurrentAccount(Account currentAccount) {
        this.currentAccount = currentAccount;
    }
}

