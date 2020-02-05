package se.nackademin.admin.view;
import se.nackademin.admin.model.Account;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
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
        withdrawPanel.setLayout(new GridLayout(3, 1, 10, 5)); // 3 från 2 50 från 10
        JPanel buttons = createPanel();
        buttons.setLayout(new GridLayout(1, 3, 10, 10));
        withdrawSum = createTextField();
        JButton withdrawButton = createButton("Ta ut");
        JButton depositButton = createButton("Sätt in");
        buttons.add(withdrawSum);
        buttons.add(withdrawButton);
        buttons.add(depositButton);
        JPanel dateInput = createPanel();
        dateInput.setLayout(new GridLayout(1, 3, 10, 10));
        startDate = createTextField();
        endDate = createTextField();
        JButton showHistory = createButton("Visa");
        dateInput.add(startDate);
        dateInput.add(endDate);
        dateInput.add(showHistory);
        withdrawPanel.add(createLabel("Ange belopp",2));
        withdrawPanel.add(buttons);
        withdrawPanel.add(createLabel("ange datum för historia", 2));
        withdrawPanel.add(dateInput);
        withdrawPanel.setBorder(new EmptyBorder(0,10,0,10));
        JPanel accountInfoPanel = createPanel();
        accountInfoPanel.setLayout(new GridLayout(4,1));
        accountInfoPanel.add(createBigLabel("Konto 213123123123123",2));
        accountInfoPanel.add(createLabel("Saldo: 100 000" ,2));
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
        accountTransactions.addElement("Kontohistorik:");
        accountTransactions.addElement("2019-02-01, Uttag -1 000, Saldo: 15 000");
    }


}
