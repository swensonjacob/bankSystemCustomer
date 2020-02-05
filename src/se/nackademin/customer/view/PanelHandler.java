package se.nackademin.customer.view;

import se.nackademin.customer.model.Loan;
import se.nackademin.customer.model.Account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class PanelHandler extends JFrame{

    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JPanel mainMenu;
    private JPanel loanPanel;
    private JPanel accountPanel;
    private JPanel loginPanel;
    private MainMenuView mainMenuView;
    private LoanInfo loanInfo;
    private AccountInfo accountInfo;
    private LoginView loginView;

    public PanelHandler() {

        loginView = new LoginView(this);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.add(loginPanel,"login");



        add(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setVisible(true);
        setSize(new Dimension(800, 600));
    }

    public JPanel getMainMenu() {
        return mainMenu;
    }

    public void setMainMenu(JPanel mainMenu) {
        this.mainMenu = mainMenu;
    }

    public JPanel getLoanPanel() {
        return loanPanel;
    }

    public void setLoanPanel(JPanel loanPanel) {
        this.loanPanel = loanPanel;
    }
    private void changePanel(String panelName) {
        cardLayout.show(mainPanel, panelName);
    }

    public void changeToLoanPanel(ActionListener listener, Loan loan) {
        loanInfo = new LoanInfo(this, listener, loan);
        mainPanel.add(loanPanel, "loan");
        changePanel("loan");
    }

    public void changeToAccountPanel(ActionListener backListener, ActionListener withdrawListener, Account account) {
        this.accountInfo = new AccountInfo(this, backListener, withdrawListener, account);
        mainPanel.add(accountPanel, "account");
        changePanel("account");
    }

    public void changeToMainMenu(ActionListener accountlistener,
                                 ActionListener loanListener, List<Loan> loans, List<Account> accounts) {
        this.mainMenuView = new MainMenuView(this, accountlistener, loanListener,loans,accounts);
        mainPanel.add(mainMenu, "mainMenu");
        changePanel("mainMenu");
    }

    public MainMenuView getMainMenuView() {
        return mainMenuView;
    }

    public void setMainMenuView(MainMenuView mainMenuView) {
        this.mainMenuView = mainMenuView;
    }

    public LoanInfo getLoanInfo() {
        return loanInfo;
    }

    public void setLoanInfo(LoanInfo loanInfo) {
        this.loanInfo = loanInfo;
    }

    public JPanel getAccountPanel() {
        return accountPanel;
    }

    public void setAccountPanel(JPanel accountPanel) {
        this.accountPanel = accountPanel;
    }

    public AccountInfo getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(AccountInfo accountInfo) {
        this.accountInfo = accountInfo;
    }

    public JPanel getLoginPanel() {
        return loginPanel;
    }

    public void setLoginPanel(JPanel loginPanel) {
        this.loginPanel = loginPanel;
    }

    public LoginView getLoginView() {
        return loginView;
    }

    public void setLoginView(LoginView loginView) {
        this.loginView = loginView;
    }
}