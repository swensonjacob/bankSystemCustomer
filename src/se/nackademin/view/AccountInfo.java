package se.nackademin.view;

import se.nackademin.model.Account;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

import static se.nackademin.view.SwingSetup.*;

public class AccountInfo {

    private JPanel accountPanel;
    private Account currentAccount;
    private PanelHandler panelHandler;
    private DefaultListModel accountTransactions;
    private JButton backButton;

    public AccountInfo(PanelHandler panelHandler, ActionListener listener) {

        this.panelHandler = panelHandler;
        accountTransactions = new DefaultListModel();
        backButton = createBackButton();

        showTransactionHistory();
        JList list = createJList(accountTransactions);

        backButton.addActionListener(listener);
        JPanel btnPanel = createPanel();
        btnPanel.setLayout(new GridLayout(2,1,10,10));
        btnPanel.add(backButton);
        btnPanel.setBorder(new EmptyBorder(50, 20, 50, 0));
        btnPanel.setPreferredSize(new Dimension(80,60));

        JPanel withdrawPanel = createPanel();
        withdrawPanel.setLayout(new GridLayout(2,2,10,10));
        JTextField withdrawSum = createTextField();
        JButton withdrawButton = createButton("Ta ut pengar");
        withdrawPanel.add(createLabel("Ange belopp",2));
        withdrawPanel.add(new JLabel());
        withdrawPanel.add(withdrawSum);
        withdrawPanel.add(withdrawButton);
        withdrawPanel.setBorder(new EmptyBorder(10,0,10,0));

        JPanel accountInfoPanel = createPanel();
        accountInfoPanel.setLayout(new GridLayout(4,1));
        accountInfoPanel.add(createBigLabel("Konto 213123123123123",2));
        accountInfoPanel.add(createLabel("Saldo: 100 000" ,2));
        accountInfoPanel.add(createLabel("Uttag",2));
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

    public void showTransactionHistory () {
        accountTransactions.addElement("Kontohistorik:");
        accountTransactions.addElement("2019-02-01, Uttag -1 000, Saldo: 15 000");
        }



    }

