package se.nackademin.admin.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PanelHandler extends JFrame{

    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JPanel adminMenuPanel;
    private JPanel loanPanel;
    private JPanel accountPanel;
    private JPanel loginPanel;
    private JPanel searchPanel;
    private JPanel createAccountPanel;

    private SearchPanel searchView;
    private AdminMenu adminMenuView;
    private JPanel createCustomerPanel;
    private JPanel updateCustomerPanel;
    private LoanInfo loanInfo;
    private AccountInfo accountInfo;
    private LoginView loginView;
    private CreateCustomer createCustomerView;
    private UpdateCustomer updateCustomerView;
    private CreateAccountView createAccountView;

    public PanelHandler() {

        loginView = new LoginView(this);
        createCustomerView = new CreateCustomer(this);
        updateCustomerView = new UpdateCustomer(this);
        searchView = new SearchPanel(this);
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.add(loginPanel,"login");
        mainPanel.add(searchPanel, "search");

        AccountInfo info = new AccountInfo(this, null);
        LoanInfo loan = new LoanInfo(this, null);
        mainPanel.add(accountPanel, "account");

        mainPanel.add(updateCustomerPanel,"updateCustomer");
        mainPanel.add(createCustomerPanel,"createCustomer");


        add(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setVisible(true);
        setSize(new Dimension(800, 600));
    }

    public JPanel getAdminMenuPanel() {
        return this.adminMenuPanel;
    }

    public void setAdminMenuPanel(JPanel adminMenuPanel) {
        this.adminMenuPanel = adminMenuPanel;
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

    public void changeToCreateCustomerPanel() {
        changePanel("createCustomer");
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

   public void changeToAdminMenu(ActionListener listener) {
       this.adminMenuView = new AdminMenu(this,listener);
       mainPanel.add(adminMenuPanel, "adminMenu");
       changePanel("adminMenu");

    }

    public void changeToSearchPanel() {
        changePanel("search");
    }

    public void changeToUpdateCustomerPanel() {
        changePanel("updateCustomer");
    }

    public AdminMenu getAdminMenuView() {
        return this.adminMenuView;
    }

    public void setAdminMenuView(AdminMenu adminMenuView) {
        this.adminMenuView = adminMenuView;
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

    public JPanel getCreateCustomerPanel() {
        return createCustomerPanel;
    }

    public void setCreateCustomerPanel(JPanel createCustomerPanel) {
        this.createCustomerPanel = createCustomerPanel;
    }

    public CreateCustomer getCreateCustomerView() {
        return createCustomerView;
    }

    public void setCreateCustomerView(CreateCustomer createCustomerView) {
        this.createCustomerView = createCustomerView;
    }

    public JPanel getUpdateCustomerPanel() {
        return updateCustomerPanel;
    }

    public void setUpdateCustomerPanel(JPanel updateCustomerPanel) {
        this.updateCustomerPanel = updateCustomerPanel;
    }

    public UpdateCustomer getUpdateCustomerView() {
        return updateCustomerView;
    }

    public void setUpdateCustomerView(UpdateCustomer updateCustomerView) {
        this.updateCustomerView = updateCustomerView;
    }

    public void setSearchView(SearchPanel searchview) {
        this.searchView = searchview;
    }

    public SearchPanel getSearchView() {
        return this.searchView;
    }


    public JPanel getCreateAccountPanel() {
        return this.createAccountPanel;
    }

    public void setCreateAccountPanel(JPanel panel) {
        this.createAccountPanel = panel;
    }

    public CreateAccountView getCreateAccountView() {
        return this.createAccountView;
    }

    public void setCreateAccountView(CreateAccountView view) {
        this.createAccountView = view;
    }

}