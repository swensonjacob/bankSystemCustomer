package se.nackademin.admin.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PanelHandler extends JFrame{

    private CardLayout cardLayout;

    private JPanel mainPanel;
    private JPanel customerPanel;
    private JPanel loanPanel;
    private JPanel accountPanel;
    private JPanel loginPanel;
    private JPanel searchPanel;

    private SearchPanel searchView;
    private CustomerView customerView;
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

    public JPanel getCustomerPanel() {
        return this.customerPanel;
    }

    public void setCustomerPanel(JPanel customerPanel) {
        this.customerPanel = customerPanel;
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

    public void changeToLoanPanel(ActionListener listener) {
        loanInfo = new LoanInfo(this, listener);
        mainPanel.add(loanPanel, "loan");
        changePanel("loan");
    }

    public void changeToAccountPanel(ActionListener listener) {
        this.accountInfo = new AccountInfo(this, listener);
        mainPanel.add(accountPanel, "account");
        changePanel("account");
    }

   public void changeToCustomerView() {
       this.customerView = new CustomerView(this);
       mainPanel.add(customerPanel, "customerView");
       changePanel("customerView");
    }

    public void changeToSearchPanel(ActionListener listener) {
        searchView = new SearchPanel(this, listener);
        mainPanel.add(searchPanel, "search");
        changePanel("search");
    }

    public CustomerView getCustomerView() {
        return this.customerView;
    }

    public void setCustomerView(CustomerView customerView) {
        this.customerView = customerView;
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

    public void setSearchPanel(JPanel searchPanel) {
        this.searchPanel = searchPanel;
    }

    public JPanel getSearchPanel() {
        return this.searchPanel;
    }

    public void setSearchView(SearchPanel searchview) {
        this.searchView = searchview;
    }

    public SearchPanel getSearchView() {
        return this.searchView;
    }
}